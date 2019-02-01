package com.cold.framework.biz;

import com.cold.framework.dao.model.User;

/**
 * @author cuipeng
 * @date 2019/1/24 11:36
 */
public interface UserService extends BaseService<User, String> {

    /**
     * Getting SMS verification code.
     *
     * @return sms code
     */
    String getSmsCode(String phoneNumber);

    /**
     * Getting {@code User} by phone number.
     *
     * @param phoneNumber phone number
     * @return {@code User}
     */
    User getByPhone(String phoneNumber);

    /**
     * User sign in.
     *
     * @param phoneNumber phone number
     * @param smsCode SMS code
     * @param token token
     * @return device id
     */
    String signIn(String phoneNumber, String smsCode, String token);

    /**
     * Create a new user by phone number.
     *
     * @param phoneNumber phone number
     * @param deviceId device id
     * @return token
     */
    String createUser(String phoneNumber, String deviceId);

    /**
     * User sign out.
     *
     * @param token token
     * @return delete result
     */
    Long signOut(String token);
}
