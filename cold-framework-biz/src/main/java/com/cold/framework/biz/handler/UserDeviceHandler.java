package com.cold.framework.biz.handler;

import com.cold.framework.dao.model.UserDevice;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng
 * @date 2019/1/23 18:15
 */
@Service
public class UserDeviceHandler {


    public UserDevice buildUserDevice(String phoneNumber, String token, String userId) {
        UserDevice u = new UserDevice();
        u.setUserId(userId);
        u.setPhoneNumber(phoneNumber);
        u.setToken(token);
        u.setStatus(1);
        return u;
    }
}
