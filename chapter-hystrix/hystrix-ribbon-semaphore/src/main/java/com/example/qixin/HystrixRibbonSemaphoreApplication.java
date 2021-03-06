package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker //开启服务降级 断路器
@EnableEurekaClient
@SpringBootApplication
public class HystrixRibbonSemaphoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixRibbonSemaphoreApplication.class, args);
	}
}
