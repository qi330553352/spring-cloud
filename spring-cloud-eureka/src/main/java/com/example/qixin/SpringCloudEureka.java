package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 创 建  时 间： 2018/7/12 20:17
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEureka {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudEureka.class, args);
    }
}
