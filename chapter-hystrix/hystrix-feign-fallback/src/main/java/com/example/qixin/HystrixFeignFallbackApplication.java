package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication

@EnableCircuitBreaker //开启服务降级 断路器
@EnableHystrixDashboard
public class HystrixFeignFallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixFeignFallbackApplication.class, args);
	}
}
