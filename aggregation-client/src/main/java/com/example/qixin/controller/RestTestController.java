package com.example.qixin.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * 创  建   时  间： 2018/8/19 15:04
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RefreshScope
@Controller
public class RestTestController {

    @Value("${configuration.name}")
    private String name;
    @Autowired
    private RestTemplate template;

    @RequestMapping("/config")
    public String config(){

        return this.name;
    }

    @GetMapping("/test")
    public String test() {
        String str = template.getForObject("https://www.baidu.com/",String.class);
        log.info("str:{}",str);
        return str;
    }

    @GetMapping("/test1")
    public String test1() {
        String str = template.getForObject("https://www.baidu.com/",String.class);
        log.info("str:{}",str);
        return str;
    }

    @GetMapping("/hello")
    public String hello(){
        String str = template.getForEntity("http://www.baidu.com/",String.class).getBody();
        return str;
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String callHome(){
        log.info("calling trace service-aaa  ");
        return template.getForObject("http://localhost:8663/hi", String.class);
    }
    @RequestMapping(value = "/aaa", method = RequestMethod.GET)
    public String info(){
        log.info("calling trace service-aaa ");
        return "i'm service-aaa";

    }


}
