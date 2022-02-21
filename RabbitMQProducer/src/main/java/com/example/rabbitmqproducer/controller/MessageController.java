package com.example.rabbitmqproducer.controller;

import com.example.rabbitmqproducer.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @GetMapping("/send")
    public boolean sendMessage(@RequestParam("msg") String msg) {
        return messageService.send(msg);
    }
}
