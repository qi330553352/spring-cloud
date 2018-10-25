package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigApplication.class, args);
	}

	/*	1、配置中心访问测试地址：GET http://192.168.244.1:8541/application/test
		2、刷新地址：GET http://127.0.0.1:8541/bus/refresh
	 */
}
