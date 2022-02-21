package com.example.rabbitmqconsumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class) //定义消息的接收管道
public class MqMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqMessageListener.class);

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        LOGGER.info("Consumer {} Received message: {}", serverPort, message.getPayload());
    }
}
