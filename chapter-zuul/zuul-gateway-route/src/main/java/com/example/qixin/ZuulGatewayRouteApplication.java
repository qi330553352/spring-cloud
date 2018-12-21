package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayRouteApplication.class, args);
	}

}

