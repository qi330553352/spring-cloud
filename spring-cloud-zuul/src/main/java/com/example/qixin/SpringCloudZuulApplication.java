package com.example.qixin;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableHystrix
@EnableZuulProxy
@SpringBootApplication
public class SpringCloudZuulApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringCloudZuulApplication.class, args);
	}

	/**
	 * 解决 spring boot 2.* 部份请求路径404问题
     */
	@Bean
	public ServletRegistrationBean getServlet(){
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);  //系统启动时加载顺序
		registrationBean.addUrlMappings("/hystrix.stream");//路径
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}
}
