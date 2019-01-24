package com.cold.framework.biz;

/**
 * @author cuipeng
 * @date 2019/1/24 17:49
 */
public interface RedisService {

    /**
     * Getting SMS verification code.
     *
     * @return sms code
     */
    String getSmsCode(String phoneNumber);

    /**
     * Set a timeout for key and increase it by delta.
     *
     * @param key redis key
     * @param delta delta
     * @param timeout key of timeout
     */
    void setExpireAndIncrement(String key, long delta, long timeout);

    /**
     * Check SMS code in login.
     *
      * @param phoneNumber phone number
     * @param smsCode SMS code
     */
    void checkCodeInLogin(String phoneNumber, String smsCode);

    /**
     * Check token in login.
     *
     * @param token
     * @return
     */
    Boolean checkTokenInLogin(String token);
}
