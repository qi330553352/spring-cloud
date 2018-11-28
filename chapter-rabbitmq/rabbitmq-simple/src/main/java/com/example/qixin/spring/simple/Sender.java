package com.example.qixin.spring.simple;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 创  建   时  间： 2018/11/25 22:24
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() throws InterruptedException{
        String msg="hello"+new Date();
        this.rabbitTemplate.convertAndSend("hello-agan-queue", msg);
    }
}
