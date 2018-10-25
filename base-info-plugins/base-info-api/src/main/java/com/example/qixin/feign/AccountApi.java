package com.example.qixin.feign;

import com.example.qixin.entity.Account;
import io.swagger.annotations.Api;
import org.reactivestreams.Publisher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 创  建   时  间： 2018/10/22 23:25
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Api(value = "示例接口C")
@Resource(name = "示例接口D")
@RequestMapping("/accountApi")
@FeignClient( name = "base-info-plugins")
public interface AccountApi {

    @GetMapping(value = "/account/customer/{customer}")
    Flux<Account> findByCustomer(@PathVariable("customer") String customerId);

    @GetMapping(value = "/account/{id}")
    Mono<Account> findById(@PathVariable("id") String id);

    @PostMapping("/person")
    Mono<Account> create(@RequestBody Publisher<Account> accountStream);
}
