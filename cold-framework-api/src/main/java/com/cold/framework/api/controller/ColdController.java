package com.cold.framework.api.controller;

import com.cold.framework.api.bean.in.LoginInVo;
import com.cold.framework.api.bean.out.BaseOutVo;
import com.cold.framework.biz.UserDeviceService;
import com.cold.framework.biz.UserService;
import com.cold.framework.biz.handler.UserHandler;
import com.cold.framework.common.annotation.Token;
import com.cold.framework.dao.model.UserDevice;
import com.cold.framework.notify.sms.SmsFactory;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author cuipeng
 * @since 2018/12/4 11:23
 */
@RestController
@RequestMapping("/cold")
@Token(exclude = {"getSmsCode", "checkToken", "login"})
public class ColdController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private UserHandler userHandler;
    @Autowired
    private UserDeviceService userDeviceService;
    @Autowired
    private SmsFactory smsFactory;

    /**
     * Getting SMS verification code.
     *
     * @return SMS code
     */
    @GetMapping("/sms/code/get")
    public Object getSmsCode(@Length(min = 11, max = 11) @NotBlank String phoneNumber) {
        // obtain SMS verification code
        String smsCode = userService.getSmsCode(phoneNumber);

        // send SMS
        smsFactory.getSmsSender().sendCustomSms(phoneNumber,userHandler.buildSmsContent(smsCode));

        return new BaseOutVo(ImmutableMap.of("smsCode",smsCode));
    }

    /**
     * Asynchronous validate token when obtain SMS code or enter application every time.
     *
     * @param phoneNumber phone number
     * @return token
     */
    @GetMapping("/token/check")
    public Object checkToken(@Length(min = 11, max = 11) @NotBlank String phoneNumber) {
        String token = "";

        UserDevice userDevice = userDeviceService.getByPhone(phoneNumber);
        if(userDevice!=null) {
            token = userDevice.getToken();
        }

        return new BaseOutVo(ImmutableMap.of("token", token));
    }

    /**
     * User login.
     *
     * @return token
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginInVo inVo) {
        String token = inVo.getToken();

        // user login
        String deviceId = userService.login(inVo.getPhoneNumber(), inVo.getSmsCode(), inVo.getToken());
        if(StringUtils.isBlank(deviceId) || !deviceId.equals(inVo.getDeviceId())) {
            token = userService.createUser(inVo.getPhoneNumber(), inVo.getDeviceId());
        }

        return new BaseOutVo(ImmutableMap.of("token",token));
    }
}
