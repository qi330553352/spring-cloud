package com.example.qixin;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 创  建   时  间： 2018/11/29 0:07
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Component
public class ProductSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send() throws InterruptedException{
        this.rabbitTemplate.convertAndSend(this.exchange,"product.log.debug", "product.log.debug.......");
        this.rabbitTemplate.convertAndSend(this.exchange,"product.log.info", "product.log.info.......");
        this.rabbitTemplate.convertAndSend(this.exchange,"product.log.warn", "product.log.warn.......");
        this.rabbitTemplate.convertAndSend(this.exchange,"product.log.error", "product.log.error.......");
    }

}
