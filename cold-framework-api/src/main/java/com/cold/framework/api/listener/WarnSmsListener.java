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
 * A Listener that will receive message to send SMS with alert information.
 *
 * @author cuipeng
 * @date 2019/1/10 18:53
 */
@Configuration
public class WarnSmsListener {

    @Autowired
    private ConnectionFactory factory;
    @Autowired
    private SysWarnService sysWarnService;

    /**
     * A simple listener container is used for receive message from rabbitMQ to send SMS
     * with alert information.
     *
     * @return
     */
    @Bean("warnSmsContainer")
    public MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames("warn-sms");
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            sysWarnService.sendSms(JSON.parseObject(new String(message.getBody(),"utf-8"), WarnMsg.class));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        });
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}
