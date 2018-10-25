package com.example.qixin;

import com.example.qixin.stream.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableDiscoveryClient
@EnableBinding({SendService.class})
@SpringBootApplication
public class BaseInfoServer {

	public static void main(String[] args) {

		SpringApplication.run(BaseInfoServer.class, args);
	}
}
