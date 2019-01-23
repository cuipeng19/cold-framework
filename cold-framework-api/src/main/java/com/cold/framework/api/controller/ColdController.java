package com.cold.framework.api.controller;

import com.cold.framework.api.bean.in.LoginInVo;
import com.cold.framework.api.bean.out.BaseOutVo;
import com.cold.framework.biz.SysService;
import com.cold.framework.biz.UserDeviceService;
import com.cold.framework.common.annotation.Token;
import com.cold.framework.dao.model.UserDevice;
import com.google.common.collect.ImmutableMap;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
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
@Token(exclude = {"getSmsCode","login"})
public class ColdController {

    @Autowired
    private SysService sysService;
    @Autowired
    private UserDeviceService userDeviceService;

    /**
     * Getting SMS verification code.
     *
     * @return
     */
    @GetMapping("/sms/code/get")
    public Object getSmsCode(@Length(min = 11, max = 11) @NotBlank String phoneNumber) {
        String smsCode = sysService.getSmsCode(phoneNumber);
        return new BaseOutVo(ImmutableMap.of("smsCode",smsCode));
    }

    /**
     * Login.
     *
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginInVo inVo) {
        // check user
        UserDevice userDevice = userDeviceService.getByPhone(inVo.getPhoneNumber());
        if(userDevice==null) {
            userDevice = userDeviceService.createUser(inVo.getPhoneNumber());
        }

        return new BaseOutVo(ImmutableMap.of("token",userDevice.getToken()));
    }
}
