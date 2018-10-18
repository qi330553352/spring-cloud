package com.example.qixin;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * 创  建   时  间： 2018/8/19 15:03
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@EnableCircuitBreaker //开启服务降级熔断器
@SpringBootApplication
public class AggregationClient {

    @Bean
    public IRule ribbonRule() {

        return new RandomRule();//这里配置策略，和配置文件对应
    }

    public static void main(String[] args) {
        SpringApplication.run(AggregationClient.class, args);
    }
}
