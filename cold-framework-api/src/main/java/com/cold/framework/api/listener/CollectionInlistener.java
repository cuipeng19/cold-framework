package com.cold.framework.api.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2019/1/2 11:55
 */
@Configuration
public class CollectionInlistener {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean("collectionInContainer")
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("collection-in");
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            logger.info("receive: " + new String(message.getBody(),"utf-8"));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        });
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}
