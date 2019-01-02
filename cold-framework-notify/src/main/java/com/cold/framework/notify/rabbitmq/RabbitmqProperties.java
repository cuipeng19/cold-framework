package com.cold.framework.notify.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @author cuipeng
 * @date 2019/1/2 9:53
 */
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqProperties {

    private Map<String,ExchangeProperties> exchanges;

    private Map<String,QueueProperties> queues;

    private Map<String,List<String>> bindings;

    public Map<String, ExchangeProperties> getExchanges() {
        return exchanges;
    }

    public void setExchanges(Map<String, ExchangeProperties> exchanges) {
        this.exchanges = exchanges;
    }

    public Map<String, QueueProperties> getQueues() {
        return queues;
    }

    public void setQueues(Map<String, QueueProperties> queues) {
        this.queues = queues;
    }

    public Map<String, List<String>> getBindings() {
        return bindings;
    }

    public void setBindings(Map<String, List<String>> bindings) {
        this.bindings = bindings;
    }
}
