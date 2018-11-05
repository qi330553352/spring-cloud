package com.example.qixin;

import com.example.qixin.stream.ISendService;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 创  建   时  间： 2018/8/19 15:03
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@EnableEurekaClient
@EnableCircuitBreaker //开启服务降级熔断器
@SpringBootApplication
@EnableBinding({ISendService.class})
public class AggregationClient {

    @Bean
    public IRule ribbonRule() {

        return new RandomRule();//这里配置策略，和配置文件对应
    }

    public static void main(String[] args) {

        SpringApplication.run(AggregationClient.class, args);
    }

    /* 使用消息中间件RabbitMQ接收并输出到console */
//    @StreamListener(Sink.INPUT)
//    public void receive(Object obj) {
//        if(obj instanceof byte[]){
//            log.info("Received:"+new String((byte[])obj));
//        }
//    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
