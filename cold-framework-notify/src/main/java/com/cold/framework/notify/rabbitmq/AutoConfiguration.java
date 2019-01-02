package com.cold.framework.notify.rabbitmq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cuipeng
 * @date 2019/1/2 9:58
 */
@Configuration
@EnableConfigurationProperties(RabbitmqProperties.class)
public class AutoConfiguration {

    @Autowired
    private RabbitmqProperties rabbitmqProperties;

    @Bean("queues")
    public List<Queue> queues() {
        List<Queue> queues = new ArrayList<>();
        Map<String,QueueProperties> map = rabbitmqProperties.getQueues();

        if(map!=null && map.size()>0) {
            map.forEach((k,v) -> {
                if(StringUtils.isEmpty(v.getName())) {
                    v.setName(k);
                }
                queues.add(v.buildQueue());
            });
        }
        return queues;
    }

    @Bean("exchanges")
    public List<Exchange> exchanges() {
        List<Exchange> exchanges = new ArrayList<>();
        Map<String,ExchangeProperties> map = rabbitmqProperties.getExchanges();

        if(map!=null && map.size()>0) {
            map.forEach((k,v) -> exchanges.add(v.buildExchange()));
        }
        return exchanges;
    }

    @Bean
    public List<Binding> bindings(List<Queue> queues, List<Exchange> exchanges) {
        List<Binding> bindings = new ArrayList<>();
        Map<String,Queue> queueMap = new HashMap<>();
        Map<String,Exchange> exchangeMap = new HashMap<>();

        queues.forEach(e -> queueMap.put(e.getName(),e));
        exchanges.forEach(e -> exchangeMap.put(e.getName(),e));

        Map<String,List<String>> map = rabbitmqProperties.getBindings();
        if(map!=null && map.size()>0) {
            map.forEach((exchangeName,v) -> {
                v.forEach(queueName -> {
                    Exchange exchange = exchangeMap.get(exchangeName);
                    Queue queue = queueMap.get(queueName);
                    if(exchange instanceof TopicExchange) {
                        bindings.add(BindingBuilder.bind(queue).to((TopicExchange)exchange).with(queueName));
                    } else if(exchange instanceof FanoutExchange) {
                        bindings.add(BindingBuilder.bind(queue).to((FanoutExchange)exchange));
                    } else if(exchange instanceof DirectExchange) {
                        bindings.add(BindingBuilder.bind(queue).to((DirectExchange)exchange).with(queueName));
                    } else if(exchange instanceof HeadersExchange) {
                        bindings.add(BindingBuilder.bind(queue).to((HeadersExchange)exchange).where(queueName).exists());
                    }
                });
            });
        }
        return bindings;
    }

    @Bean
    @Scope(scopeName = "prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        CachingConnectionFactory c = (CachingConnectionFactory) connectionFactory;
        c.setPublisherConfirms(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(c);
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
}
