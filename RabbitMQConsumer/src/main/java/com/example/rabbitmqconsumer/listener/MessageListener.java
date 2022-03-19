package com.example.rabbitmqconsumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @EnableBinding指定一个Class数组，将这些Class中带有@Input或者@Output指定的通道和Binder绑定。
 * 这里可以用SpringCloudStream中预定义的几个接口：
 * org.springframework.cloud.stream.messaging.Source    ==> 指定了输出通道名为output
 * org.springframework.cloud.stream.messaging.Sink      ==> 指定了输入通道名为input
 * org.springframework.cloud.stream.messaging.Processor ==> 继承这两个类，同时指定输入和输出通道
 * 如果需要指定不同的通道名，也可以模仿上述接口实现自定义接口，例如这里使用的Channel.class
 * 这里表示将创建Channels.class中@Input指定的MessageChannel(名字为myInput)，并将其与Binder绑定
 */
@EnableBinding(Channels.class)
@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Value("${server.port}")
    private String serverPort;

    /**
     * 注意@StreamListener中的名字，该注解表示监听这个名字的通道，并从通道上取数据，
     * 该名字需要和@EnableBinding指定的类(即Channel.class)的@Input指定的通道名字一样。
     *
     * 这里的方法返回值为void，如果@StreamListener标注的方法有返回值，那么必须使用@SendTo注解指明返回的值写入哪个Channel
     * 比如：
     * @StreamListener(Channels.myInput)
     * @SendTo(Channels.myOutput)
     * public Integer input(Message<String> messge) {
     *     xxxx;
     *     return 1;
     * }
     */
    @StreamListener(Channels.myInput)
    public void input(Message<String> message) {
        LOGGER.info("Consumer {} Received message: {}", serverPort, message.getPayload());
    }
}
