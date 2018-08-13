package com.example.qixin.api;

import com.example.qixin.entity.PatentDescribe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 创  建   时  间： 2018/6/10 15:30
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RequestMapping("/patentDescribeApi")
@FeignClient( name = "base-patent-plugins")
public interface PatentDescribeApi extends BaseDao<PatentDescribe,String> {

    @PostMapping("/addBeans")
    Flux<PatentDescribe> addBeans(@RequestBody List<PatentDescribe> beans);

    @PostMapping("/addBean")
    Mono<PatentDescribe> addBean(@RequestBody PatentDescribe bean);

    @GetMapping("/findTotal")
    Mono<Long> findTotal();
}
