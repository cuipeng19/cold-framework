package com.cold.framework.api.controller;

import com.cold.framework.api.bean.in.LoginInVo;
import com.cold.framework.api.bean.out.BaseOutVo;
import com.cold.framework.biz.SysService;
import com.cold.framework.biz.UserService;
import com.cold.framework.common.annotation.Token;
import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import com.cold.framework.dao.model.User;
import com.google.common.collect.ImmutableMap;
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
    private SysService sysService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    /**
     * Getting SMS verification code.
     *
     * @return
     */
    @GetMapping("/sms/code/get")
    public Object getSmsCode(@Length(min = 11, max = 11) @NotBlank String phoneNumber) {
        String smsCode = sysService.getSmsCode(phoneNumber);
        // todo send SMS code

        return new BaseOutVo(ImmutableMap.of("smsCode",smsCode));
    }

    /**
     * Asynchronous validate token when obtain SMS code.
     *
     * @param phoneNumber phone number
     * @return
     */
    @GetMapping("/token/check")
    public Object checkToken(@Length(min = 11, max = 11) @NotBlank String phoneNumber) {
        User user = userService.getByPhone(phoneNumber);
        String token = "";
        if(user!=null) {
            token = user.getToken();
        }
        return new BaseOutVo(ImmutableMap.of("token", token));
    }

    /**
     * Login.
     *
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody LoginInVo inVo) {
        String token = inVo.getToken();

        // check SMS code
        String errorCount = stringRedisTemplate.opsForValue().get("sms:error:" + inVo.getPhoneNumber());
        if(Integer.valueOf(errorCount==null ? "0" : errorCount) >= 5) {
            throw new ColdException(ColdState.SMS_CODE_ERROR_FREQUENT);
        }
        String smsCode = stringRedisTemplate.opsForValue().get("sms:code:" + inVo.getPhoneNumber());
        if(smsCode==null) {
            sysService.setExpireAndIncrement("sms:error:"+inVo.getPhoneNumber(),1,600);
            throw new ColdException(ColdState.SMS_CODE_INVALID);
        }
        if(!smsCode.equals(inVo.getSmsCode())) {
            sysService.setExpireAndIncrement("sms:error:"+inVo.getPhoneNumber(),1,600);
            throw new ColdException(ColdState.SMS_CODE_ERROR);
        }

        // try to obtain in redis
        Boolean tokenExist = stringRedisTemplate.opsForSet().isMember("user-token", token);
        if(tokenExist!=null && !tokenExist) {
            User user = userService.createUser(inVo.getPhoneNumber());
            token = user.getToken();
        }

        return new BaseOutVo(ImmutableMap.of("token",token));
    }
}
