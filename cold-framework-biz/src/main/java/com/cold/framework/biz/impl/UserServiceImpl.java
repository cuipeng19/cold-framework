package com.cold.framework.biz.impl;

import com.cold.framework.biz.AbstractDbBaseService;
import com.cold.framework.biz.UserService;
import com.cold.framework.biz.handler.UserHandler;
import com.cold.framework.common.util.ObjectId;
import com.cold.framework.dao.mapper.UserMapper;
import com.cold.framework.dao.model.User;
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

    @Override
    public User getByPhone(String phoneNumber) {
        return userMapper.getByPhone(phoneNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User createUser(String phoneNumber) {
        // create token
        String token = "cold" + new ObjectId().toString();

        // create user
        User user = userHandler.buildUser(phoneNumber, token);
        userMapper.insertSelective(user);

        // save redis
        stringRedisTemplate.opsForSet().add("user-token", token);

        return user;
    }
}
