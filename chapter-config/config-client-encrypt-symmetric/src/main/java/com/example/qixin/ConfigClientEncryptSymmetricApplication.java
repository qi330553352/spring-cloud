package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientEncryptSymmetricApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientEncryptSymmetricApplication.class, args);
	}

}

