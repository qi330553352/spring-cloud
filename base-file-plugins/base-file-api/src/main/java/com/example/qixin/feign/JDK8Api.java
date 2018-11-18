package com.example.qixin.feign;

import com.example.qixin.entity.JDK8;
import com.example.qixin.hystrix.JDK8ApiHystrix;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 创  建   时  间： 2018/11/19 0:02
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
@Api(value = "JDK1.8流式编程")
@Resource(name = "流式编程")
@RequestMapping("/JDK8Api")
@FeignClient(name="base-file-plugins",fallbackFactory=JDK8ApiHystrix.class)
public interface JDK8Api {

    @GetMapping("/findByName/{name}")
    Mono<JDK8> findByName(@PathVariable("name") String name);

    //是否存在
    @GetMapping("/findByName/{id}")
    Mono<Boolean> isexistence(@PathVariable("id") String id);

    //大于
    @GetMapping("/findGtAge/{age}")
    Flux<JDK8> findGtAge(@PathVariable("age") Integer age);
}
