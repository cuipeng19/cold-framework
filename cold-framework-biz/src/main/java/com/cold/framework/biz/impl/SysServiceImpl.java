package com.cold.framework.biz.impl;

import com.cold.framework.biz.SysService;
import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import com.cold.framework.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author cuipeng
 * @date 2019/1/17 18:24
 */
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getSmsCode(String phoneNumber) {
        // check error count
        String errorCount = stringRedisTemplate.opsForValue().get("sms:error:" + phoneNumber);
        if(Integer.valueOf(errorCount==null ? "0" : errorCount) >= 5) {
            throw new ColdException(ColdState.SMS_CODE_ERROR_FREQUENT);
        }

        // get SMS code
        String smsCode = StringUtil.randomStr(6);
        stringRedisTemplate.opsForValue().set("sms:code:" + phoneNumber, smsCode,600, TimeUnit.SECONDS);

        return smsCode;
    }

    @Override
    public void setExpireAndIncrement(String key, long delta, long timeout) {
        stringRedisTemplate.opsForValue().increment(key, delta);
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

}
