package com.test;

import com.Application;
import com.activemq.client.ActiveMQClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ActiveMQTest {

    @Autowired
    private ActiveMQClient activeMQClient;

    /*
        queue
        ActiveMQ 生产消费同时启动   生产10000条数据：89000ms
        ActiveMQ 只生产不消费启动   生产10000条数据：84000ms
        ActiveMQ 只消费不生产启动   消费10000条数据：14471ms
        topic
        ActiveMQ 生产消费同时启动   生产1w条数据：80605ms
        ActiveMQ 只生产不消费启动   生产1w条数据：78867ms
        ActiveMQ 只消费不生产启动   消费1w条数据：无法设置
     */
    @Test
    public void test01() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            activeMQClient.send("send : " + i);
        }
        stopWatch.stop();
        System.out.println(" send 10000 共耗时 : " + stopWatch.getTotalTimeMillis());
    }

    @Test
    public void test02() throws IOException {
        //项目启动之后，Queue消费者则自动消费队列数据，根据消费者提供时间进行计算
        System.in.read();
    }

}
