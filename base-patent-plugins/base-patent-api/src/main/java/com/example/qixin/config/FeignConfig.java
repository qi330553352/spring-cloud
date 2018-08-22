package com.example.qixin.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 创  建   时  间： 2018/8/19 16:04
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer () {

        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(1L), 5);
    }

}
