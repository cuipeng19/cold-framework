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
        String smsCode = stringRedisTemplate.opsForValue().get("sms:code:" + phoneNumber);
        if(smsCode==null) {
            // get sms code
            smsCode = StringUtil.randomStr(4);
            stringRedisTemplate.opsForValue().set("sms:code:" + phoneNumber, smsCode,600L, TimeUnit.SECONDS);

            // check error count
            String errorCount = stringRedisTemplate.opsForValue().get("sms:error:" + phoneNumber);
            if(Integer.valueOf(errorCount==null ? "0" : errorCount) >= 5) {
                throw new ColdException(ColdState.SMS_CODE_ERROR_FREQUENT);
            }

            // check success count
            String successCount = stringRedisTemplate.opsForValue().get("sms:success:" + phoneNumber);
            if(Integer.valueOf(successCount==null ? "0" : successCount) >=5) {
                throw new ColdException(ColdState.SMS_CODE_SEND_FREQUENT);
            }
        }

        // send SMS code

        // increment success count +1
        stringRedisTemplate.opsForValue().increment("sms:success:" + phoneNumber,1);

        return smsCode;
    }
}
