package com.example.qixin.spring.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创  建   时  间： 2018/11/25 22:23
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Configuration
public class SenderConfig {

    @Bean
    public Queue aganqueue(){
        return new Queue("hello-agan-queue");
    }

}
