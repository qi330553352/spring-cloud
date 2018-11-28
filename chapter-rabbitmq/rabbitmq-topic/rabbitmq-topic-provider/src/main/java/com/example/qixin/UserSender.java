package com.example.qixin;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 创  建   时  间： 2018/11/29 0:08
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Component
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send() throws InterruptedException{
        this.rabbitTemplate.convertAndSend(this.exchange,"user.log.debug", "user.log.debug.......");
        this.rabbitTemplate.convertAndSend(this.exchange,"user.log.info", "user.log.info.......");
        this.rabbitTemplate.convertAndSend(this.exchange,"user.log.warn", "user.log.warn.......");
        this.rabbitTemplate.convertAndSend(this.exchange,"user.log.error", "user.log.error.......");
    }

}
