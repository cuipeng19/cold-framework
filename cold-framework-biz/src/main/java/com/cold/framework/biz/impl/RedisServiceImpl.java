package com.cold.framework.biz.impl;

import com.cold.framework.biz.RedisService;
import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import com.cold.framework.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author cuipeng
 * @date 2019/1/24 17:49
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final String USER_TOKEN = "user-token";
    private final String SMS_ERROR = "sms:error:";
    private final String SMS_CODE = "sms:code:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getSmsCode(String phoneNumber) {
        // check error count
        String errorCount = stringRedisTemplate.opsForValue().get(SMS_ERROR + phoneNumber);
        if(Integer.valueOf(errorCount==null ? "0" : errorCount) >= 5) {
            throw new ColdException(ColdState.SMS_CODE_ERROR_FREQUENT);
        }

        // get SMS code
        String smsCode = StringUtil.randomStr(6);
        stringRedisTemplate.opsForValue().set(SMS_CODE + phoneNumber, smsCode,600, TimeUnit.SECONDS);

        return smsCode;
    }

    @Override
    public void setExpireAndIncrement(String key, long delta, long timeout) {
        stringRedisTemplate.opsForValue().increment(key, delta);
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void checkCodeInLogin(String phoneNumber, String orgSmsCode) {
        // check SMS code
        String errorCount = stringRedisTemplate.opsForValue().get(SMS_ERROR + phoneNumber);
        if(Integer.valueOf(errorCount==null ? "0" : errorCount) >= 5) {
            throw new ColdException(ColdState.SMS_CODE_ERROR_FREQUENT);
        }
        String smsCode = stringRedisTemplate.opsForValue().get(SMS_CODE + phoneNumber);
        if(smsCode==null) {
            this.setExpireAndIncrement(SMS_ERROR +phoneNumber,1,600);
            throw new ColdException(ColdState.SMS_CODE_INVALID);
        }
        if(!smsCode.equals(orgSmsCode)) {
            this.setExpireAndIncrement(SMS_ERROR +phoneNumber,1,600);
            throw new ColdException(ColdState.SMS_CODE_ERROR);
        }

        // delete SMS code
        stringRedisTemplate.delete(SMS_CODE + phoneNumber);
    }

    @Override
    public Boolean checkTokenInLogin(String token) {
        return stringRedisTemplate.opsForSet().isMember(USER_TOKEN, token);
    }

}
