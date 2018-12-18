package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 1、http://192.168.244.1:1003/test/findList
 * 2、http://192.168.244.1:1003/actuator/hystrix.stream
 */


@EnableCircuitBreaker //开启服务降级 断路器
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class HystrixMqProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixMqProductApplication.class, args);
	}

}

