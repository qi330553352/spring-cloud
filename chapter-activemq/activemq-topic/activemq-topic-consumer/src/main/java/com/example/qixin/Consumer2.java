package com.example.qixin;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * 创  建   时  间： 2018/11/30 0:54
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class Consumer2 {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "zh-topic")
    @SendTo("return-queue")
    public String receiveQueue(String text) {
        System.out.println("Consumer2 收到的报文为:"+text);
        return "Consumer2收到!"+text;
    }
}
