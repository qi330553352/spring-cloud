package com.example.qixin.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
public class TestController {

	@Value("${configuration.name}")
	private String name;
	
	@GetMapping("/test")
	public String test(){
		
		return this.name;
	}
	
}
