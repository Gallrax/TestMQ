package com.test;

import com.Application;
import com.rocketmq.consumer.RocketMQConsumer;
import com.rocketmq.producer.RocketMQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.SortedSet;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RocketMQTest {

    @Autowired
    private RocketMQProducer producer;
//    @Autowired
//    private RocketMQConsumer consumer;

    /*
        RocketMQ 生产消费同时启动   生产10w条数据：
        RocketMQ 只生产不消费启动   生产1w条数据：8525ms
        RocketMQ 只生产不消费启动   生产10w条数据：76513ms
        RocketMQ 只消费不生产启动   消费10w条数据：
     */

    @Test
    public void test01() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            producer.sendMessage(""+ i);
        }
        stopWatch.stop();
        System.out.println(" totalTime : " + stopWatch.getTotalTimeMillis());
        System.in.read();
    }

    @Test
    public void test02() throws IOException {
        System.out.println(" test rocketMQ consume");
        System.in.read();
    }


}
