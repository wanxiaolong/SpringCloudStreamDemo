package com.example.rabbitmqproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class MessageService implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageChannel output;

    @Override
    public boolean send(String payload) {
        try {
            Message message = MessageBuilder.withPayload(payload).build();
            output.send(message);
            LOGGER.info("发送成功。payload=" + payload);
            return true;
        } catch (Exception e) {
            LOGGER.error("发送失败。payload=" + payload, e);
            return false;
        }
    }
}
