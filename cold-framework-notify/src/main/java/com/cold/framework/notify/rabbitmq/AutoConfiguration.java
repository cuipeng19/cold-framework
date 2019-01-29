package com.cold.framework.notify.rabbitmq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
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
 * {@link EnableConfigurationProperties Auto-configuration} for RabbitMQ.
 * <p>
 * This configuration class is active only when the RabbitMQ and spring AMQP client
 * libraries are on the classpath.
 * <p>
 * Registers the following beans:
 * <ul>
 * <li>{@link org.springframework.amqp.core.Queue} instance when the class is initializing</li>
 * <li>{@link org.springframework.amqp.core.Exchange} instance when the class is initializing</li>
 * <li>{@link org.springframework.amqp.core.Binding} instance when the class is initializing</li>
 * </ul>
 *
 * @author cuipeng
 * @date 2019/1/2 9:58
 */
@Configuration("mqAutoConfiguration")
@EnableConfigurationProperties(RabbitmqProperties.class)
public class AutoConfiguration {

    @Autowired
    private RabbitmqProperties rabbitmqProperties;

    /**
     * Registers {@link org.springframework.amqp.core.Queue}
     *
     * @return List<{@link org.springframework.amqp.core.Queue}>
     */
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

    /**
     * Registers {@link org.springframework.amqp.core.Exchange}
     *
     * @return List<{@link org.springframework.amqp.core.Exchange}>
     */
    @Bean("exchanges")
    public List<Exchange> exchanges() {
        List<Exchange> exchanges = new ArrayList<>();
        Map<String,ExchangeProperties> map = rabbitmqProperties.getExchanges();

        if(map!=null && map.size()>0) {
            map.forEach((k,v) -> exchanges.add(v.buildExchange()));
        }
        return exchanges;
    }

    /**
     * Registers {@link org.springframework.amqp.core.Binding}
     *
     * @param queues queue list
     * @param exchanges exchange list
     * @return List<{@link org.springframework.amqp.core.Binding}>
     */
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

    /**
     * Set {@link org.springframework.amqp.rabbit.core.RabbitTemplate} confirmation mode.
     *
     * @param connectionFactory RabbitMQ connection factory
     * @return {@link org.springframework.amqp.rabbit.core.RabbitTemplate}
     */
    @Bean
    @Scope(scopeName = "prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
}
