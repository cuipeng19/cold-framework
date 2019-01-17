package com.cold.framework.api.controller;

import com.cold.framework.api.bean.out.BaseOutVo;
import com.cold.framework.biz.SysService;
import com.cold.framework.common.annotation.Token;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author cuipeng
 * @since 2018/12/4 11:23
 */
@RestController
@RequestMapping("/cold")
@Token(exclude = {"getSmsCode"})
public class ColdController {

    @Autowired
    private SysService sysService;

    /**
     * Getting SMS verification code.
     *
     * @return
     */
    @GetMapping("/sms/code/get")
    public BaseOutVo getSmsCode(@Length(min = 11, max = 11) @NotBlank String phoneNumber) {
        String smsCode = sysService.getSmsCode(phoneNumber);
        return new BaseOutVo(smsCode);
    }
}
