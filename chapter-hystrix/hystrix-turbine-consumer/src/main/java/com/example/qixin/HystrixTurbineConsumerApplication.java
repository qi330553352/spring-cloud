package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * http://192.168.244.1:1002/turbine.stream
 */
@EnableTurbine
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixTurbineConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixTurbineConsumerApplication.class, args);
	}
}

