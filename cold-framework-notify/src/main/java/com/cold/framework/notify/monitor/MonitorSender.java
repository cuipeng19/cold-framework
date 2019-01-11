package com.cold.framework.notify.monitor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Send data to rabbitMQ.
 *
 * @author cuipeng
 * @date 2019/1/10 18:20
 */
@Service
public class MonitorSender {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private WarnMsgHandler warnMsgHandler;

    /**
     * method of send.
     *
     * @param eventType event type
     * @param e exception
     * @param path project path
     */
    @Async
    public void send(String eventType, Exception e, String path) {
        WarnMsg warnMsg = warnMsgHandler.buildWarnMsg(eventType, e, path);

        if(!rabbitTemplate.isConfirmListener()) {
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if(ack) {
                    logger.info("Send to MQ with warn message successful：" + warnMsg.getExceptionSystem() + "(" + warnMsg.getExceptionName() + "),event type：" + warnMsg.getEventType());
                } else {
                    logger.info("Send to MQ with warn message fail：" + cause);
                }
            });
        }
        rabbitTemplate.convertAndSend("warn-fanout","", JSON.toJSONString(warnMsg));
    }

}
