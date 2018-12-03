package com.example.qixin;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RibbonStrategyConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonStrategyConsumerApplication.class, args);
	}

//	@Bean
//	public IRule ribbonRule() {
//
//		return new RandomRule();//这里配置策略，和配置文件对应
//	}
}
