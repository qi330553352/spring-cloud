package com.example.qixin.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创  建   时  间： 2018/11/4 21:58
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RestController
@RequestMapping("/test")
public class TestController {

//    @Autowired
//    private DiscoveryClient client;

    @GetMapping("/index")
    public String index(){
        //EurekaClientConfig config = client.getEurekaClientConfig();
        //log.info("{}","{}",config.getProxyHost(),config.getProxyUserName());
        return "hello world";
    }
}
