package com.example.rabbitmqproducer.service;

public interface IMessageService {
    boolean send(String payload);
}
