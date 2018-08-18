package com.example.qixin.api;

import com.example.qixin.entity.PatentDescribe;
import com.example.qixin.entity.PatentInfo;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 创  建   时  间： 2018/6/1 21:56
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Api(value = "专利信息API接口")
@Resource(name = "专利信息")
@RequestMapping("/patentInfoApi")
@FeignClient( name = "base-patent-plugins")
public interface PatentInfoApi {

    @GetMapping("/findAll")
    Flux<PatentInfo> findAll(@RequestBody @Valid Example<PatentDescribe> bean);

    @PostMapping("/save")
    Mono<PatentInfo> save(@RequestBody @Valid PatentInfo bean);
}
