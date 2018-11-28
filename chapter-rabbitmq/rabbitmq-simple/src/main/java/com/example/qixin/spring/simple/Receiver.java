package com.example.qixin.spring.simple;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 创  建   时  间： 2018/11/25 22:24
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Component
public class Receiver {


    @RabbitListener(queues="hello-agan-queue")
    public void process(String msg){
        System.out.println("receiver:"+msg);
    }
}
