package com.cold.framework.biz;

/**
 * The base service in system.
 *
 * @author cuipeng
 * @date 2019/1/17 18:24
 */
public interface SysService {

    /**
     * Getting SMS verification code.
     *
     * @return sms code
     */
    String getSmsCode(String phoneNumber);

    /**
     * Login.
     *
     * @param phoneNumber
     * @param smsCode
     * @return
     */
    String login(String phoneNumber, String smsCode);
}
