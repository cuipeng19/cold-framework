package com.cold.framework.notify.sms;

/**
 * @author cuipeng
 * @date 2019/1/28 18:14
 */
public interface SmsSender {

    /**
     * The method of send sms.
     */
    void sendCustomSms(String phoneNumber, String content);
}
