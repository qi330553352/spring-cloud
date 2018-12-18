package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * http://192.168.244.1:1002/turbine.stream
 */
@EnableTurbineStream
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixMqTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixMqTurbineApplication.class, args);
	}

}

