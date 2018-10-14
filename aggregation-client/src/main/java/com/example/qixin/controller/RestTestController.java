package com.example.qixin.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 创  建   时  间： 2018/8/19 15:04
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RestController
public class RestTestController {

    @GetMapping("/test")
    public String test() {
        RestTemplate restTemplate=new RestTemplate() ;
        String str = restTemplate.getForObject("https://www.baidu.com/",String.class);
        log.info("str:{}",str);
        return str;
    }
}