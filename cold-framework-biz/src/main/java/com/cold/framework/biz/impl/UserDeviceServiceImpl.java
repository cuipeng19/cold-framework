package com.cold.framework.biz.impl;

import com.cold.framework.biz.AbstractDbBaseService;
import com.cold.framework.biz.UserDeviceService;
import com.cold.framework.biz.handler.UserDeviceHandler;
import com.cold.framework.common.util.ObjectId;
import com.cold.framework.dao.mapper.UserDeviceMapper;
import com.cold.framework.dao.model.UserDevice;
import com.cold.framework.dao.util.ColdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng
 * @date 2019/1/23 18:25
 */
@Service
public class UserDeviceServiceImpl extends AbstractDbBaseService<UserDevice,Long> implements UserDeviceService {
    public UserDeviceServiceImpl(ColdMapper<UserDevice> mapper) {
        super(mapper);
    }

    @Autowired
    private UserDeviceMapper userDeviceMapper;
    @Autowired
    private UserDeviceHandler userDeviceHandler;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public UserDevice getByPhone(String phoneNumber) {
        UserDevice userDevice = userDeviceMapper.getByPhone(phoneNumber);
        return userDevice;
    }

    @Override
    public UserDevice createUser(String phoneNumber) {
        // create token
        String token = "cold" + new ObjectId().toString();

        // create user
        UserDevice userDevice = userDeviceHandler.buildUserDevice(phoneNumber,token,"userId");
        userDeviceMapper.insertSelective(userDevice);

        // save redis
        stringRedisTemplate.opsForHash().put("userToken", token, "userId");
        return userDevice;
    }
}
