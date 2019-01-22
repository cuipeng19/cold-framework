package com.cold.framework.common.dictionary;

/**
 * Enumeration of application state codes.
 *
 * @author cuipeng
 * @since 2018/12/4 17:28
 */
public enum  ColdState {

    /**
     * {@code 200 OK}
     */
    SUCCESS(200, "OK"),
    /**
     * {@code 10000 Parameter check failed}
     */
    PARAM_VALIDATE_FAIL(10000, "Parameter check failed"),
    /**
     * {@code 10001 Token is invalid}
     */
    TOKEN_INVALID(10001, "Token is invalid"),
    /**
     * {@code 10002 No token exists}
     */
    TOKEN_NOT_EXIST(10002, "No token exists"),
    /**
     * {@code 11001 Send e-mail failed}
     */
    EMAIL_SEND_FAIL(11001, "Send e-mail failed"),
    /**
     * {@code 11010 Get SMS code error frequently}
     */
    SMS_CODE_ERROR_FREQUENT(11010, "Get SMS code error frequently"),
    /**
     * {@code 11011 SMS code send frequently}
     */
    SMS_CODE_SEND_FREQUENT(11011, "SMS code send frequently"),
    /**
     * {@code 11012 SMS code is invalid}
     */
    SMS_CODE_INVALID(11012, "SMS code is invalid"),
    /**
     * {@code 11013 SMS code error}
     */
    SMS_CODE_ERROR(11013, "SMS code error"),
    /**
     * {@code -1 Internal Server Error}
     */
    INTERNAL_SERVER_ERROR(-1, "Internal Server Error");


    private final Integer state;

    private final String message;


    ColdState(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    /**
     * Return the integer value of this state code.
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * Return the message of this state code.
     */
    public String getMessage() {
        return this.message;
    }
}
