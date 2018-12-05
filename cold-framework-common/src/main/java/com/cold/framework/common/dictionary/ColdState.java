package com.cold.framework.common.dictionary;

/**
 * @author cuipeng 2018/12/4 17:28
 */
public enum  ColdState {

    SUCCESS(0, "OK"),

    PARAM_VALIDATE_FAIL(10000, "接口参数校验失败"),

    INTERNAL_ERROR(-1, "服务器异常");

    private final Integer state;
    private final String message;

    ColdState(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}
