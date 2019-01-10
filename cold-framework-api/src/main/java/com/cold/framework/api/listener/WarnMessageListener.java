package com.cold.framework.api.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cuipeng
 * @date 2019/1/10 18:53
 */
@Configuration
public class WarnMessageListener {

    @Autowired
    private ConnectionFactory factory;

    @Bean("warnMessageContainer")
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames("warn-message");
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            System.out.println(new String(message.getBody(),"utf-8"));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        });
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}
