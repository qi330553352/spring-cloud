package com.example.qixin;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创  建   时  间： 2018/11/27 23:52
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Component
public class Send {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Value("${mq.config.exchange}")
    private String exchange;

    public void send() throws InterruptedException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String msg="hello"+sdf.format(new Date());
        this.rabbitTemplate.convertAndSend(this.exchange,"log.error.routing.key", msg);
    }


}
