package com.example.qixin.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 创  建   时  间： 2018/11/24 17:06
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hi")
    public Map<String,Object> hi(){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","数据返回成功");
        log.info("{}",map);
        return map;
    }
}
