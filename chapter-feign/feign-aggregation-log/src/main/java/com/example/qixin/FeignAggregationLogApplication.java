package com.example.qixin;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignAggregationLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignAggregationLogApplication.class, args);
	}


	@Bean
	public Logger.Level feignLoggerLevel(){
//		NONE, 不记录任何信息，默认值。
//		BASIC, 记录请求方法、请求URL、状态码和用时。
//		HEADERS, 在BASIC的基础上再记录一些常用信息。
//		FULL: 记录请求和响应报文的全部内容。
		return Logger.Level.FULL;
	}
}
