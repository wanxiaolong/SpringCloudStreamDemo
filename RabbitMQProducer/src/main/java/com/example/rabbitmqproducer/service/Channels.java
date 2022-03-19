package com.example.rabbitmqproducer.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 这里声明需要使用到的通道，需要多少就声明多少。
 * @Input标注输入通道，表示由Middleware输入到应用程序中；
 * @Output标注输出通道，表示由应用程序输出到Middleware中。
 * 这个接口由SpringCloudStream帮我们实现，
 * 因此我们可以在其它地方注入这些通道。
 */
public interface Channels {

    String myOutput = "myOutput";

    // 注意这里@Output指定了一个输出通道名(即有消息需要通过Binder发出到Middleware中，因此是输出)，
    // 该名字需要与application.yml中的spring.cloud.stream.bindings.<channelName>一致
    // 默认情况下，通道名就是方法名，这里通过指定@Output的value属性，来自定义通道名
    @Output(Channels.myOutput)
    MessageChannel output();
}
