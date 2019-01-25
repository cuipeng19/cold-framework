package com.cold.framework.biz;

import com.cold.framework.dao.model.User;

/**
 * @author cuipeng
 * @date 2019/1/24 11:36
 */
public interface UserService extends BaseService<User, String> {

    /**
     * Getting {@code User} by phone number.
     *
     * @param phoneNumber phone number
     * @return
     */
    User getByPhone(String phoneNumber);

    /**
     * Create a new user by phone number.
     *
     * @param phoneNumber phone number
     * @return
     */
    User createUser(String phoneNumber);

}