package com.example.qixin;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 创  建   时  间： 2018/11/10 14:22
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RestController
public class TestController {


    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String home(){
        log.info("hi is being called");
        return "hi i'm service-bbb!";
    }

    @RequestMapping(value = "/bbb", method = RequestMethod.GET)
    public String info(){
        log.info("info is being called");
        return restTemplate.getForObject("http://localhost:8888/aaa",String.class);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
