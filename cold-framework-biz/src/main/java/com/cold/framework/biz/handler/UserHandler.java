package com.cold.framework.biz.handler;

import com.cold.framework.common.util.ObjectId;
import com.cold.framework.dao.model.User;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng
 * @date 2019/1/24 14:32
 */
@Service
public class UserHandler {

    /**
     * Construct a new {@code User} by phone number and token.
     *
     * @param phoneNumber phone number
     * @param token token
     * @return {@code User}
     */
    public User buildUser(String phoneNumber, String token) {
        User user = new User();
        user.setUserId(ObjectId.get().toString());
        user.setPhoneNumber(phoneNumber);

        return user;
    }

    /**
     * Construct a content of SMS.
     *
     * @param smsCode SMS code
     * @return content
     */
    public String buildSmsContent(String smsCode) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SMS code: ");
        buffer.append(smsCode);
        return buffer.toString();
    }
}
