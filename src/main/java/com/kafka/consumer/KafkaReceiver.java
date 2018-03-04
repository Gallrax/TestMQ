package com.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"testMQ"})
    public void receive(ConsumerRecord<?, ?> record) {
        System.out.println(" now : " + System.currentTimeMillis());
//        System.out.println(" record : " + record);
        Object value = record.value();
        System.out.println(" value : " + value);
    }
}
