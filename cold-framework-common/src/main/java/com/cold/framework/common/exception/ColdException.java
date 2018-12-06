package com.cold.framework.common.exception;

import com.cold.framework.common.dictionary.ColdState;

/**
 * @author cuipeng
 * @since 2018/12/4 17:26
 */
public class ColdException extends RuntimeException{

    protected Integer state;
    protected String message;
    protected ColdState coldState;
    protected Exception srcException;

    public ColdException(String message) {
        super(message);
        this.message = message;
    }

    public ColdException(ColdState coldState) {
        super(coldState.getMessage());
        this.state = coldState.getState();
        this.message = coldState.getMessage();
    }

    public ColdException(ColdState coldState, Exception srcException) {
        super(coldState.getMessage());
        this.state = coldState.getState();
        this.message = coldState.getMessage();
        this.coldState = coldState;
        this.srcException = srcException;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ColdState getColdState() {
        return coldState;
    }

    public void setColdState(ColdState coldState) {
        this.coldState = coldState;
    }

    public Exception getSrcException() {
        return srcException;
    }

    public void setSrcException(Exception srcException) {
        this.srcException = srcException;
    }
}
