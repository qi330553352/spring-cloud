package com.example.qixin.feign;

import com.example.qixin.entity.UserFile;
import com.example.qixin.hystrix.UserFileApiHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.reactivestreams.Publisher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 创  建   时  间： 2018/10/23 20:45
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
@Api(value = "用户文件")
@Resource(name = "对外接口")
@RequestMapping("/userFileApi")
@FeignClient(name="base-file-plugins",fallbackFactory=UserFileApiHystrix.class)
public interface UserFileApi {

    //===反应式编程======================= https://blog.csdn.net/simple_chao/article/details/73648238
    @PostMapping("/save")
    @ApiOperation(value="创建用户文件", notes="根据bean创建用户文件对象")
    @ApiImplicitParam(name = "bean", value = "用户文件对象", required = true, dataType = "UserFile")
    Mono<UserFile> save(@RequestBody UserFile bean);

    @PostMapping("/create")
    Mono<UserFile> create(@RequestBody Publisher<UserFile> beans);

    @PostMapping("/batchSave")
    @ApiOperation(value="批量保存数据", notes="批量保存数据")
    Flux<UserFile> batchSave();


    @GetMapping("/findById/{id}")
    @ApiImplicitParam(name = "id", value = "用户文件对象ID", required = true, dataType = "String")
    @ApiOperation(value="获得用户文件对象", notes="根据id获得用户文件对象",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Mono<UserFile> findById(@PathVariable("id") String id);

    @GetMapping("/findAll")
    @ApiOperation(value="文件对象集合", notes="获得全部",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Flux<UserFile> findAll();

    @GetMapping("/findByFileName/{fileName}")
    @ApiOperation(value="根据文件名称", notes="获得全部",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Flux<UserFile> findByFileName(@PathVariable("fileName")String fileName);

    //===函数式编程=======================

}
