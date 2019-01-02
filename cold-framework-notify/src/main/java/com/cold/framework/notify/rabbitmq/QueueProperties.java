package com.cold.framework.notify.rabbitmq;

import org.springframework.amqp.core.Queue;

import java.util.Map;

/**
 * @author cuipeng
 * @date 2019/1/2 9:56
 */
public class QueueProperties {

    private String name;

    private boolean durable = true;

    private boolean exclusive = false;

    private boolean autoDelete = false;

    private Map<String,Object> arguments;

    public Queue buildQueue() {
        return new Queue(name,durable,exclusive,autoDelete,arguments);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }
}
