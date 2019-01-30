package com.cold.framework.api.listener;

import com.alibaba.fastjson.JSON;
import com.cold.framework.biz.SysWarnService;
import com.cold.framework.notify.monitor.WarnMsg;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A Listener that will receive message to send email with alert information.
 *
 * @author cuipeng
 * @date 2019/1/10 18:52
 */
@Configuration
public class WarnEmailListener {

    @Autowired
    private ConnectionFactory factory;
    @Autowired
    private SysWarnService sysWarnService;

    /**
     * A simple listener container is used for receive message from rabbitMQ to send email
     * with alert information.
     *
     * @return MessageListenerContainer
     */
    @Bean("warnEmailContainer")
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames("warn-email");
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            sysWarnService.sendEmail(JSON.parseObject(new String(message.getBody(),"utf-8"),WarnMsg.class));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        });
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}
