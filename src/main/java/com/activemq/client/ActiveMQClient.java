package com.activemq.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class ActiveMQClient {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    public void send(String message) {
        //默认不注入Queue和Topic时，使用Queue
//        jmsTemplate.convertAndSend("testMQ", message);
        //1.测试queue性能
//        jmsTemplate.convertAndSend(queue, message);
        //2.测试topic性能
        jmsTemplate.convertAndSend(topic, message);
    }

}
