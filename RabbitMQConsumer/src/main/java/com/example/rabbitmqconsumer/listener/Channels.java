package com.example.rabbitmqconsumer.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    //定义通道名字，该名字会与SpringCloudStream中的Binder绑定
    String myInput = "myInput";

    // 注意这里指定了一个输入通道名(即需要通过Binder接收消息，因此是输入)，
    // 该名字需要与application.yml中的spring.cloud.stream.bindings.<channelName>一致
    @Input(Channels.myInput)
    MessageChannel input();
}
