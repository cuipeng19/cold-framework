package com.cold.framework.biz.impl;

import com.cold.framework.biz.AbstractDbBaseService;
import com.cold.framework.biz.UserService;
import com.cold.framework.biz.handler.UserDeviceHandler;
import com.cold.framework.biz.handler.UserHandler;
import com.cold.framework.common.dictionary.ColdDictionary;
import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import com.cold.framework.common.util.ObjectId;
import com.cold.framework.common.util.StringUtil;
import com.cold.framework.dao.mapper.UserDeviceMapper;
import com.cold.framework.dao.mapper.UserMapper;
import com.cold.framework.dao.model.User;
import com.cold.framework.dao.model.UserDevice;
import com.cold.framework.dao.util.ColdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author cuipeng
 * @date 2019/1/24 11:37
 */
@Service
public class UserServiceImpl extends AbstractDbBaseService<User, String> implements UserService {
    public UserServiceImpl(ColdMapper<User> mapper) {
        super(mapper);
    }

    @Autowired
    private UserHandler userHandler;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserDeviceMapper userDeviceMapper;
    @Autowired
    private UserDeviceHandler userDeviceHandler;

    /**
     * Set a timeout for key and increase it by delta.
     *
     * <p>NOTE: This is a private method.
     *
     * @param key key
     * @param delta delta
     * @param timeout timeout of key
     */
    private void setExpireAndIncrement(String key, long delta, long timeout) {
        stringRedisTemplate.opsForValue().increment(key, delta);
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String getSmsCode(String phoneNumber) {
        // check error count
        String errorCount = stringRedisTemplate.opsForValue().get(ColdDictionary.SMS_ERROR + phoneNumber);
        if(Integer.valueOf(errorCount==null ? "0" : errorCount) >= 5) {
            throw new ColdException(ColdState.SMS_CODE_ERROR_FREQUENT);
        }

        // get SMS code
        String smsCode = StringUtil.randomStr(6);
        stringRedisTemplate.opsForValue().set(ColdDictionary.SMS_CODE + phoneNumber, smsCode,600, TimeUnit.SECONDS);

        return smsCode;
    }

    @Override
    public User getByPhone(String phoneNumber) {
        return userMapper.getByPhone(phoneNumber);
    }

    @Override
    public String signIn(String phoneNumber, String orgSmsCode, String token) {
        // check SMS code
        String errorCount = stringRedisTemplate.opsForValue().get(ColdDictionary.SMS_ERROR + phoneNumber);
        if(Integer.valueOf(errorCount==null ? "0" : errorCount) >= 5) {
            throw new ColdException(ColdState.SMS_CODE_ERROR_FREQUENT);
        }
        String smsCode = stringRedisTemplate.opsForValue().get(ColdDictionary.SMS_CODE + phoneNumber);
        if(smsCode==null || !smsCode.equals(orgSmsCode)) {
            this.setExpireAndIncrement(ColdDictionary.SMS_ERROR +phoneNumber,1,600);
            throw new ColdException(ColdState.SMS_CODE_INVALID);
        }

        // delete SMS code
        stringRedisTemplate.delete(ColdDictionary.SMS_CODE + phoneNumber);

        // obtain device id
        Object deviceId = stringRedisTemplate.opsForHash().get(ColdDictionary.USER_TOKEN, token);
        return (String) deviceId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createUser(String phoneNumber, String deviceId) {
        UserDevice userDevice = userDeviceMapper.getByPhone(phoneNumber);
        if(userDevice==null) {
            // create token
            String token = "cold" + new ObjectId().toString();

            // create user
            User user = userHandler.buildUser(phoneNumber, token);
            userMapper.insertSelective(user);
            userDevice = userDeviceHandler.buildUserDevice(user.getUserId(),token,deviceId,phoneNumber);
            userDeviceMapper.insertSelective(userDevice);

            // save redis
            stringRedisTemplate.opsForHash().put(ColdDictionary.USER_TOKEN, token, deviceId);
        } else if(!deviceId.equals(userDevice.getDeviceId())) {
            // invalid original device
            userDeviceMapper.updateByPrimaryKeySelective(userDeviceHandler.buildInvalidDevice(userDevice.getId()));

            // create new device
            userDeviceMapper.insertSelective(userDeviceHandler.buildUserDevice(userDevice.getUserId(),userDevice.getToken(),deviceId,phoneNumber));
        }

        return userDevice.getToken();
    }

    @Override
    public Long signOut(String token) {
        return stringRedisTemplate.opsForHash().delete(ColdDictionary.USER_TOKEN, token);
    }
}
