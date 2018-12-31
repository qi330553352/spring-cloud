package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * http://127.0.0.1:9030/encrypt/status
 * http://127.0.0.1:9030/encrypt-symmetric/default
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigServerEncryptSymmetricApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerEncryptSymmetricApplication.class, args);
	}

}

