package com.cold.framework.biz;

import com.cold.framework.dao.model.UserDevice;

/**
 * @author cuipeng
 * @date 2019/1/25 11:28
 */
public interface UserDeviceService extends BaseService<UserDevice,Long> {

    /**
     * Getting {@code UserDevice} by device id.
     *
     * @param deviceId device id
     * @return {@code UserDevice}
     */
    UserDevice getByDeviceId(String deviceId);

    /**
     * Getting {@code UserDevice} by phone number.
     *
     * @param phoneNumber phone number
     * @return {@code UserDevice}
     */
    UserDevice getByPhone(String phoneNumber);
}
