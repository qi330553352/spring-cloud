package com.example.qixin;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创  建   时  间： 2018/10/26 0:16
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RefreshScope
@RestController
public class RestTestController {

    @Value("${configuration.name}")
    private String name;

    @RequestMapping("/config")
    public String config(){

        return this.name;
    }

}
