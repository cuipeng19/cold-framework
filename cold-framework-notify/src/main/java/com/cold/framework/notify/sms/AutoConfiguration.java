package com.cold.framework.notify.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cuipeng
 * @date 2019/1/28 18:03
 */
@Configuration("smsAutoConfiguration")
@EnableConfigurationProperties(SmsProperties.class)
public class AutoConfiguration {

    @Autowired
    private SmsProperties smsProperties;

    @Bean
    public SmsFactory smsFactory() {
        String type = smsProperties.getType();
        Map<String, SmsSender> smsSource = new HashMap<>();
        List<String> typeSource = new ArrayList<>();

        if(smsProperties.getFeige()!=null) {
            smsSource.put("feige",smsProperties.getFeige());
        }

        return new SmsFactory(type,typeSource,smsSource);
    }
}
