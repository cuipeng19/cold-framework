package com.cold.framework.biz;

import com.cold.framework.dao.model.UserDevice;

/**
 * @author cuipeng
 * @date 2019/1/23 18:24
 */
public interface UserDeviceService extends BaseService<UserDevice,Long> {

    UserDevice getByPhone(String phoneNumber);

    /**
     * Create a new user.
     *
     * @param phoneNumber
     */
    UserDevice createUser(String phoneNumber);


}
