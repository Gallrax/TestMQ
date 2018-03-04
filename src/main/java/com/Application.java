package com;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import javax.jms.Queue;
import javax.jms.Topic;


@SpringBootApplication
//@EnableAutoConfiguration
//@EnableKafka
public class Application {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("testMQ-queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("testMQ-topic");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
