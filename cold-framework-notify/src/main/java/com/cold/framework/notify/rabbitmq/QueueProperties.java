package com.cold.framework.notify.rabbitmq;

import org.springframework.amqp.core.Queue;

import java.util.Map;

/**
 * Configuration properties for Queue.
 *
 * @author cuipeng
 * @date 2019/1/2 9:56
 */
public class QueueProperties {

    /**
     * The name of the Queue - must be not null.
     */
    private String name;

    /**
     * True if we are declaring a durable queue (the queue will survive a server restart).
     */
    private boolean durable = true;

    /**
     * True if we are declaring a exclusive queue (the queue will only be userd by declarer's connection).
     */
    private boolean exclusive = false;

    /**
     * True if the server should delete the queue when it is no longer in use.
     */
    private boolean autoDelete = false;

    /**
     * The arguments used to declare the queue.
     */
    private Map<String,Object> arguments;

    /**
     * Construct a new queue, given a name, durability flag, and auto-delete flag, and arguments.
     *
     * @return a queue
     */
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
