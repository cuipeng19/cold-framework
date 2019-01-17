package com.cold.framework.api.bean.out;

import com.cold.framework.common.dictionary.ColdState;

/**
 * Class {@code BaseOutVo} is a basic return structure of all return objects.
 *
 * @author cuipeng
 * @since 2018/12/4 18:03
 */
public class BaseOutVo {

    /**
     * State code of return.
     */
    private Integer state;
    /**
     * Message phrase of return.
     */
    private String message;
    /**
     * Data of return.
     */
    private Object data;

    /**
     * Constructs a default {@code BaseOutVo}
     */
    public BaseOutVo() {
        this.state = ColdState.SUCCESS.getState();
        this.message = ColdState.SUCCESS.getMessage();
    }

    /**
     * Constructs a {@code BaseOutVo} using the given {@code coldState}
     *
     * @param coldState include specific state and message phrase
     */
    public BaseOutVo(ColdState coldState) {
        this.state = coldState.getState();
        this.message = coldState.getMessage();
    }

    /**
     * Constructs a {@code BaseOutVo} using the given {@code coldState} and object.
     *
     * @param coldState include specific state and message phrase
     * @param data data of return
     */
    public BaseOutVo(ColdState coldState, Object data) {
        this.state = coldState.getState();
        this.message = coldState.getMessage();
        this.data = data;
    }

    /**
     * Constructs a {@code BaseOutVo} using the given state and message phrase.
     *
     * @param state state of return
     * @param message message phrase of return
     */
    public BaseOutVo(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    /**
     * Constructs a {@code BaseOutVo} using the given state, message phrase and object.
     *
     * @param state state of return
     * @param message message phrase of return
     * @param data data of return
     */
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
