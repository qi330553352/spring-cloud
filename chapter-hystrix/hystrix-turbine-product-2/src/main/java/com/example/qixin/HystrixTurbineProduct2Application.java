package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableCircuitBreaker //开启服务降级 断路器
@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
public class HystrixTurbineProduct2Application {

	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineProduct2Application.class, args);
	}

}

