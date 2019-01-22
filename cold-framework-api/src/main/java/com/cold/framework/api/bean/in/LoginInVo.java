package com.cold.framework.api.bean.in;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Parameters required for login.
 *
 * @author cuipeng
 * @date 2019/1/18 14:57
 */
public class LoginInVo extends BaseInVo {

    @NotBlank
    @Length(min = 11, max = 11)
    private String phoneNumber;

    @NotBlank
    @Length(min = 4, max = 4)
    private String smsCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
