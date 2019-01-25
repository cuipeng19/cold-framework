package com.cold.framework.biz.impl;

import com.cold.framework.biz.AbstractDbBaseService;
import com.cold.framework.biz.UserService;
import com.cold.framework.biz.handler.UserDeviceHandler;
import com.cold.framework.biz.handler.UserHandler;
import com.cold.framework.common.dictionary.ColdDictionary;
import com.cold.framework.common.util.ObjectId;
import com.cold.framework.dao.mapper.UserDeviceMapper;
import com.cold.framework.dao.mapper.UserMapper;
import com.cold.framework.dao.model.User;
import com.cold.framework.dao.model.UserDevice;
import com.cold.framework.dao.util.ColdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public User getByPhone(String phoneNumber) {
        return userMapper.getByPhone(phoneNumber);
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
            stringRedisTemplate.opsForSet().add(ColdDictionary.USER_TOKEN, token);
        }

        return userDevice.getToken();
    }
}
