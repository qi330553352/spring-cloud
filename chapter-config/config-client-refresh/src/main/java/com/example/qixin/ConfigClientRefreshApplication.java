package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 在git端修改配置后，如何让客户端生效
 * POST http://127.0.0.1:9031/actuator/refresh
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientRefreshApplication.class, args);
	}

}
