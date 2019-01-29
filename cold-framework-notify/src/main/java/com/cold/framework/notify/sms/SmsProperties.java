package com.cold.framework.notify.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cuipeng
 * @date 2019/1/28 17:49
 */
@ConfigurationProperties("sms")
public class SmsProperties {

    private String type;
    private FeigeSource feige;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FeigeSource getFeige() {
        return feige;
    }

    public void setFeige(FeigeSource feige) {
        this.feige = feige;
    }
}
