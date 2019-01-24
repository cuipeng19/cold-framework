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
     * Set a timeout for key and increase it by delta.
     *
     * @param key redis key
     * @param delta delta
     * @param timeout key of timeout
     */
    void setExpireAndIncrement(String key, long delta, long timeout);
}
