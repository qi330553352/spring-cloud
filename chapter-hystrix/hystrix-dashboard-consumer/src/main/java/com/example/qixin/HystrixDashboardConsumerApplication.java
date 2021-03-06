package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * http://192.168.244.1:9087/hystrix
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardConsumerApplication.class, args);
	}

}

