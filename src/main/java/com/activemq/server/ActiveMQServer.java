package com.activemq.server;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jms.annotation.JmsListener;
        import org.springframework.stereotype.Component;

        import javax.jms.Queue;

@Component
public class ActiveMQServer {

    //    @JmsListener(destination = "testMQ")
//    @JmsListener(destination = "testMQ-queue")
    @JmsListener(destination = "testMQ-topic")
    public void receive(String message) {
        System.out.println(" now : "+ System.currentTimeMillis());
        System.out.println(" activeMQ receive : " + message);
    }
}
