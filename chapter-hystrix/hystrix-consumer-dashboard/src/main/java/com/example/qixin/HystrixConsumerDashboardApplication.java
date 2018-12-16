package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixConsumerDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixConsumerDashboardApplication.class, args);
	}

}

