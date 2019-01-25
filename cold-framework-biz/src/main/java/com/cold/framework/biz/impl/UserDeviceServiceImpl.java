package com.cold.framework.biz.impl;

import com.cold.framework.biz.AbstractDbBaseService;
import com.cold.framework.biz.UserDeviceService;
import com.cold.framework.dao.mapper.UserDeviceMapper;
import com.cold.framework.dao.model.UserDevice;
import com.cold.framework.dao.util.ColdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng
 * @date 2019/1/25 11:28
 */
@Service
public class UserDeviceServiceImpl extends AbstractDbBaseService<UserDevice,Long> implements UserDeviceService {
    public UserDeviceServiceImpl(ColdMapper<UserDevice> mapper) {
        super(mapper);
    }

    @Autowired
    private UserDeviceMapper userDeviceMapper;


    @Override
    public UserDevice getByDeviceId(String deviceId) {
        return userDeviceMapper.getByDeviceId(deviceId);
    }

    @Override
    public UserDevice getByPhone(String phoneNumber) {
        return userDeviceMapper.getByPhone(phoneNumber);
    }
}
