package com.cold.framework.notify.sms;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author cuipeng
 * @date 2019/1/28 18:05
 */
public class SmsFactory {

    private String type;
    private List<String> typeSource;
    private Map<String, SmsSender> smsSource;

    public SmsFactory() {
    }

    public SmsFactory(String type, List<String> typeSource, Map<String, SmsSender> smsSource) {
        this.type = type;
        this.typeSource = typeSource;
        this.smsSource = smsSource;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTypeSource() {
        return typeSource;
    }

    public void setTypeSource(List<String> typeSource) {
        this.typeSource = typeSource;
    }

    public Map<String, SmsSender> getSmsSource() {
        return smsSource;
    }

    public void setSmsSource(Map<String, SmsSender> smsSource) {
        this.smsSource = smsSource;
    }

    public SmsSender getSmsSender() {
        SmsSender smsSender = null;

        if(StringUtils.isBlank(this.type)) {
            // no send type specified
            smsSender = smsSource.get(typeSource.get(new Random().nextInt(typeSource.size())));
        } else {
            // specify the sending type
            smsSender = smsSource.get(type);
        }

        return smsSender;
    }
}
