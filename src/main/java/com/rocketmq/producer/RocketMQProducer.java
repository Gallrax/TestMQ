package com.rocketmq.producer;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RocketMQProducer {

    private static final Logger LOGGER = Logger.getLogger(RocketMQProducer.class);
    private final String producerGroup = "testMQ-producer";
    @Value("${spring.rocketmq.nameServer}")
    private String nameServer;
    private DefaultMQProducer producer;

    @PostConstruct
    private void initRocketMQProducer() {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(nameServer);
        //解决RemotingConnectException: connect to <172.17.0.1:10909> failed
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            LOGGER.info(" RocketMQProducer start");
        } catch (MQClientException e) {
            LOGGER.error(" RocketMQProducer start error");
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        Message tempMessage = new Message("testMQ", message.getBytes());
        try {
            producer.send(tempMessage);
        } catch (Exception e) {
            LOGGER.error(" sendMessage error");
            e.printStackTrace();
        }
    }
}
