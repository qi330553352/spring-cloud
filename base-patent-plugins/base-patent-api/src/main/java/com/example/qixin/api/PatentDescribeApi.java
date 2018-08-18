package com.example.qixin.api;

import com.example.qixin.entity.PatentDescribe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * 创  建   时  间： 2018/6/10 15:30
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RequestMapping("/patentDescribeApi")
@FeignClient( name = "base-patent-plugins")
public interface PatentDescribeApi{

    @GetMapping("/findTotal")
    Mono<Long> findTotal();
    @GetMapping("/findBeans")
    Flux<PatentDescribe> findBeans(@Valid @RequestBody Example<PatentDescribe> bean);
    @GetMapping("/findById/{id}")
    Mono<PatentDescribe> findById(@PathVariable String id);
    @GetMapping("/getById/{id}")
    Mono<ResponseEntity<PatentDescribe>> getById(@PathVariable String id);
    @GetMapping("/findPageInfo")
    Flux<PatentDescribe> findPageInfo(@Valid @RequestBody Example<PatentDescribe> bean);

    @PostMapping("/addBeans")
    Flux<PatentDescribe> addBeans(@Valid @RequestBody List<PatentDescribe> beans);
    @PostMapping("/addBean")
    Mono<PatentDescribe> addBean(@RequestBody @Valid PatentDescribe bean);

    @PutMapping("/updateById/{id}")
    Mono<ResponseEntity<PatentDescribe>> updateById(@PathVariable String id, @Valid @RequestBody PatentDescribe bean);

    @DeleteMapping("/deleteById/{id}")
    Mono<ResponseEntity<Void>> deleteById(@PathVariable String id);
}
