package com.cold.framework.biz.handler;

import com.cold.framework.dao.model.UserDevice;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng
 * @date 2019/1/25 10:58
 */
@Service
public class UserDeviceHandler {

    /**
     * Construct a new {@code UserDevice} by some parameters.
     *
     * @param userId user id
     * @param token token
     * @param deviceId device id
     * @return {@code UserDevice}
     */
    public UserDevice buildUserDevice(String userId, String token, String deviceId, String phoneNumber) {
        UserDevice userDevice = new UserDevice();
        userDevice.setUserId(userId);
        userDevice.setToken(token);
        userDevice.setDeviceId(deviceId);
        userDevice.setPhoneNumber(phoneNumber);
        userDevice.setStatus(1);
        return userDevice;
    }

    /**
     * Construct a {@code UserDevice} of invalid state by id.
     *
     * @param id id
     * @return
     */
    public UserDevice buildInvalidDevice(Long id) {
        UserDevice userDevice = new UserDevice();
        userDevice.setId(id);
        userDevice.setStatus(0);
        return userDevice;
    }

    /**
     * Construct a content of SMS.
     *
     * @return content
     */
    public String buildNoticePhone() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Account login on other device.");
        return buffer.toString();
    }
}
