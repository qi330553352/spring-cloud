package com.example.qixin;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 创  建   时  间： 2018/11/28 23:51
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Component
@RabbitListener(bindings=@QueueBinding(
        value= @Queue(value="${mq.config.queue.red}",autoDelete="true"),
        exchange=@Exchange(value="${mq.config.exchange}",type= ExchangeTypes.FANOUT)
)
)
public class RedReceiver {

    @RabbitHandler
    public void process(String msg){
        System.out.println("为用户发送10元红包 :"+msg);
    }
}
