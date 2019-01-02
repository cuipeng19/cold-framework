package com.cold.framework.biz.impl;

import com.cold.framework.biz.CollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cuipeng
 * @date 2019/1/2 11:47
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void collectionIn() {
        if(!rabbitTemplate.isConfirmListener()) {
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if(ack) {
                    logger.info("send success");
                } else {
                    logger.error("cause: " + cause);
                }
            });
        }
        rabbitTemplate.convertAndSend("collection-topic","collection-in","test");
    }
}
