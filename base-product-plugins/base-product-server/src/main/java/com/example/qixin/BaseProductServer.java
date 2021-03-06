package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 创  建   时  间： 2018/10/17 20:53
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@EnableEurekaClient
@SpringBootApplication
public class BaseProductServer {

    public static void main(String[] args) {
        SpringApplication.run(BaseProductServer.class, args);
    }
}
