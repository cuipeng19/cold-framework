package com.cold.framework.notify.rabbitmq;

import org.springframework.amqp.core.*;

import java.util.Map;

/**
 * @author cuipeng
 * @date 2019/1/2 9:55
 */
public class ExchangeProperties {

    private String name;

    private boolean durable = true;

    private boolean autoDelete = false;

    private Map<String,Object> arguments;

    private boolean delayed;

    private boolean internal;

    private String type;

    public Exchange buildExchange() {
        Exchange exchange = null;
        if(ExchangeTypes.TOPIC.equals(type)) {
            exchange = new TopicExchange(name,durable,autoDelete,arguments);
        } else if(ExchangeTypes.DIRECT.equals(type)) {
            exchange = new DirectExchange(name,durable,autoDelete,arguments);
        } else if(ExchangeTypes.FANOUT.equals(type)) {
            exchange = new FanoutExchange(name,durable,autoDelete,arguments);
        } else if(ExchangeTypes.HEADERS.equals(type)) {
            exchange = new HeadersExchange(name,durable,autoDelete,arguments);
        } else {
            exchange = new CustomExchange(name,type,durable,autoDelete,arguments);
        }
        return exchange;
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

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
