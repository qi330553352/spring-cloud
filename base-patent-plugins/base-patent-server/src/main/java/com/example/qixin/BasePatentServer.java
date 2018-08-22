package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 创  建   时  间： 2018/6/1 21:42
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class BasePatentServer {

    public static void main(String[] args) {
        SpringApplication.run(BasePatentServer.class, args);
    }
}
