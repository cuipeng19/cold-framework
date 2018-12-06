package com.cold.framework.api.bean.out;

import com.cold.framework.common.dictionary.ColdState;

/**
 * @author cuipeng
 * @since 2018/12/4 18:03
 */
public class BaseOutVo {

    private Integer state;
    private String message;
    private Object data;

    public BaseOutVo() {
    }

    public BaseOutVo(ColdState coldState) {
        this.state = coldState.getState();
        this.message = coldState.getMessage();
    }

    public BaseOutVo(ColdState coldState, Object data) {
        this.state = coldState.getState();
        this.message = coldState.getMessage();
        this.data = data;
    }

    public BaseOutVo(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public BaseOutVo(Integer state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
