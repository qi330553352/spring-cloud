package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * http://192.168.244.1:9086/actuator/hystrix.stream
 */
@EnableHystrix
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixDashboardProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardProductApplication.class, args);
	}

}

