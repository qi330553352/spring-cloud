package com.example.qixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * 创  建   时  间： 2018/11/30 0:06
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class Producer {

    @Autowired
    private JmsMessagingTemplate template; // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message){
        template.convertAndSend(destination, message);
    }


}
