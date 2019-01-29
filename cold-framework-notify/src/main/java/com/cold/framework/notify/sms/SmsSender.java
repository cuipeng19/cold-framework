package com.cold.framework.notify.sms;

/**
 * @author cuipeng
 * @date 2019/1/28 18:14
 */
public interface SmsSender {

    /**
     * The method of send custom SMS.
     *
     * @param phoneNumber phone number
     * @param content SMS content
     */
    void sendCustomSms(String phoneNumber, String content);
}
