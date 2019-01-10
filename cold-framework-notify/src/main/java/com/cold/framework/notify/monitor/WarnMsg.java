package com.cold.framework.notify.monitor;

import java.util.Date;

/**
 * Warn data.
 *
 * @author cuipeng
 * @date 2019/1/10 18:22
 */
public class WarnMsg {

    /**
     * The type of the event.
     * <p>
     * Subscribe to this event and receive information.
     */
    private String eventType;

    /**
     * exception name.
     */
    private String exceptionName;

    /**
     * The time of occur exception.
     */
    private Date warnTime;

    /**
     * The name of project occur the exception.
     */
    private String exceptionSystem;

    /**
     * The stack of exception.
     */
    private String exceptionStack;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public String getExceptionSystem() {
        return exceptionSystem;
    }

    public void setExceptionSystem(String exceptionSystem) {
        this.exceptionSystem = exceptionSystem;
    }

    public String getExceptionStack() {
        return exceptionStack;
    }

    public void setExceptionStack(String exceptionStack) {
        this.exceptionStack = exceptionStack;
    }
}
