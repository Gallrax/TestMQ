package com.rocketmq.consumer;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RocketMQConsumer {

    private static final Logger LOGGER = Logger.getLogger(RocketMQConsumer.class);
    private final String consumerGroup = "testMQ-producer";
    @Value("${spring.rocketmq.nameServer}")
    private String nameServer;
    private DefaultMQPushConsumer consumer;

    @PostConstruct
    private void initRocketMQConsumer() {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(nameServer);
        try {
            consumer.subscribe("testMQ", "push");
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费,如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) ((list, context) -> {
                for (MessageExt messageExt : list) {
                    String messageBody = new String(messageExt.getBody());
                    System.out.println(" time : " + System.currentTimeMillis() + " message : " + messageBody);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }));
            LOGGER.info(" RocketMQConsumer start");
        } catch (MQClientException e) {
            LOGGER.error(" RocketMQConsumer start error");
            e.printStackTrace();
        }
    }

}
