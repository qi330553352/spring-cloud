package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableCircuitBreaker //开启服务降级 断路器
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixTurbineProduct1Application {

	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineProduct1Application.class, args);
	}

}

