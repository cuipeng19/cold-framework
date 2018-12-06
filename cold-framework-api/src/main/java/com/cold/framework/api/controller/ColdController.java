package com.cold.framework.api.controller;

import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author cuipeng
 * @since 2018/12/4 11:23
 */
@RestController
@RequestMapping("/cold")
public class ColdController {

    @GetMapping("/test")
    public Object test(@NotBlank String param) {
        try {
            String a = null;
            a.split(";");
        } catch (Exception e) {
            throw new ColdException(ColdState.INTERNAL_ERROR, e);
        }
        System.out.println(param);
        return "success";
    }

}
