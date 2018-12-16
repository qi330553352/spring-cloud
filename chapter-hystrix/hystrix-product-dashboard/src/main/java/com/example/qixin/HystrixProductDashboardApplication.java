package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixProductDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixProductDashboardApplication.class, args);
	}

}

