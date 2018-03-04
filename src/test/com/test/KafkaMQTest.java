package com.test;

import com.Application;
import com.kafka.provider.KafkaSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class KafkaMQTest {

    @Autowired
    private KafkaSender kafkaSender;

    /*
        Kafka 生产消费同时启动   生产10000条数据：373ms
        Kafka 只生产不消费启动   生产10000条数据：351ms
        Kafka 只消费不生产启动   消费10000条数据：123ms
     */
    @Test
    public void test01() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            kafkaSender.send("" + i);
        }
        stopWatch.stop();
        System.out.println(" totalTime : " + stopWatch.getTotalTimeMillis());
    }

    @Test
    public void test02() throws IOException {
        System.out.println(System.currentTimeMillis());
        System.in.read();
    }
}
