package com.example.rabbitmqconsumer.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * 这里声明需要使用到的通道，需要多少就声明多少。
 * @Input标注输入通道，表示由Middleware输入到应用程序中；
 * @Output标注输出通道，表示由应用程序输出到Middleware中。
 * 这个接口由SpringCloudStream帮我们实现，
 * 因此我们可以在其它地方注入这些通道。
 */
public interface Channels {

    //定义通道名字，该名字会与SpringCloudStream中的Binder绑定
    String myInput = "myInput";

    // 注意这里指定了一个输入通道名(即需要通过Binder从Middleware中接收消息，因此是输入)，
    // 该名字需要与application.yml中的spring.cloud.stream.bindings.<channelName>一致
    // 默认情况下，通道名就是方法名，这里通过指定@Input的value属性，来自定义通道名
    @Input(Channels.myInput)
    MessageChannel input();
}
