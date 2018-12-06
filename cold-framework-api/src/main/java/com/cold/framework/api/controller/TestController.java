package com.cold.framework.api.controller;

import com.cold.framework.api.bean.out.BaseOutVo;
import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.notify.email.EmailSender;
import com.cold.framework.common.exception.ColdException;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is a controller of test.
 *
 * @author cuipeng
 * @since 2018/12/6 11:14
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EmailSender emailSender;

    /**
     * Send a simple e-mail to specific mailbox.
     *
     * @param emailTo an e-mail address
     * @return BaseOutVo
     */
    @GetMapping("/email/simple")
    public Object sendSimpleEmail(String emailTo) {
        try {
            emailSender.sendSimpleEmail(emailTo, "email test", "receive email success");
        } catch (Exception e) {
            throw new ColdException(ColdState.EMAIL_SEND_FAIL, e);
        }

        return new BaseOutVo(ColdState.SUCCESS);
    }

    /**
     * Send an e-mail with extra to specific mailbox.
     *
     * @param emailTo an e-mail address
     * @return BaseOutVo
     */
    @PostMapping("/email")
    public Object emailTest(String emailTo, MultipartFile file) {
        try {
            emailSender.sendExtraEmail(emailTo, "Test to send an email with extra", "receive some of extra.", ImmutableMap.of(file.getOriginalFilename(), file));
        } catch (Exception e) {
            throw new ColdException(ColdState.EMAIL_SEND_FAIL, e);
        }

        return new BaseOutVo(ColdState.SUCCESS);
    }

}
