package com.example.rabbitmqproducer.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    String myOutput = "myOutput";

    // 注意这里@Output指定了一个输出通道名(即有消息需要通过Binder发出去，因此是输出)，
    // 该名字需要与application.yml中的spring.cloud.stream.bindings.<channelName>一致
    @Output(Channels.myOutput)
    MessageChannel output();
}
