package com.example.qixin;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FeignProductLogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignProductLogServerApplication.class, args);
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
