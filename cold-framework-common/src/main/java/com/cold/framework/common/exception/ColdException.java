package com.cold.framework.common.exception;

import com.cold.framework.common.dictionary.ColdState;

/**
 * {@code ColdException} is a custom exception
 * that can be thrown during the normal operation of the
 * Java Virtual Machine.
 *
 * <p>It provides two constructors with some parameters
 * and doesn't provide the constructor without parameter.
 *
 * @author cuipeng
 * @since 2018/12/4 17:26
 */
public class ColdException extends RuntimeException{

    /**
     * State code of exception.
     */
    protected Integer state;
    /**
     * Reason phrase of exception.
     */
    protected String message;
    /**
     * Enumeration of application state codes.
     */
    protected ColdState coldState;
    /**
     * Original exception when has occurred a error by thrown.
     * <p>It will be used in {@code GlobalExceptionHandler}
     */
    protected Exception srcException;


    /**
     * Constructs a new custom exception with the specified state code and reason phrase.
     *
     * <p>The srcException is not initialized, and may subsequently be initialized
     * by a call to {@link #setSrcException}
     *
     * @param coldState include the specified state code and reason phrase
     */
    public ColdException(ColdState coldState) {
        super(coldState.getMessage());
        this.state = coldState.getState();
        this.message = coldState.getMessage();
        this.coldState = coldState;
    }


    /**
     * Constructs a new custom exception with the specified state code, reason phrase and original exception.
     *
     * <p>The parameter of {@code srcException} will be used in {@code GlobalExceptionHandler} that
     * print stack information in logs when have occur a error by thrown and
     * it helps you find out which line is wrong in logs.
     *
     * <p>The parameter of {@code coldState} will be return When an error occurs,
     * the front end can receive the custom of state code and reason phrase.
     *
     * @param coldState include the specified state code and reason phrase
     * @param srcException original exception
     */
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
