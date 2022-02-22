package com.example.rabbitmqproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @EnableBinding指定一个Class数组，将这些Class中带有@Input或者@Output指定的通道和Binder绑定。
 * 这里可以用SpringCloudStream中预定义的几个接口：
 * org.springframework.cloud.stream.messaging.Source    ==> 指定了输出通道名为output
 * org.springframework.cloud.stream.messaging.Sink      ==> 指定了输入通道名为input
 * org.springframework.cloud.stream.messaging.Processor ==> 继承这两个类，同时指定输入和输出通道
 * 如果需要指定不同的通道名，也可以模仿上述接口实现自定义接口，例如这里使用的Channel.class
 * 这里表示将Channels.class中@Output指定的MessageChannel与Binder绑定
 */
@EnableBinding(Channels.class)
public class MessageServiceImpl implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    /**
     * 注意这个MessageChannel的名字，需要和Channels.class中定义的通道名一致，否则Spring不能注入
     */
    @Autowired
    private MessageChannel myOutput;

    @Override
    public boolean send(String payload) {
        try {
            Message message = MessageBuilder.withPayload(payload).build();
            myOutput.send(message);
            LOGGER.info("发送成功。payload=" + payload);
            return true;
        } catch (Exception e) {
            LOGGER.error("发送失败。payload=" + payload, e);
            return false;
        }
    }
}
