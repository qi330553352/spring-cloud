package com.example.qixin.feign;

import com.example.qixin.entity.UserFile;
import com.example.qixin.hystrix.UserFileApiHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/findById/{id}")
    @ApiOperation(value="获得用户文件对象", notes="根据id获得用户文件对象",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "id", value = "用户文件对象ID", required = true, dataType = "String")
    Mono<UserFile> findById(@PathVariable("id") String id);

    @PostMapping("/save")
    @ApiOperation(value="创建用户文件", notes="根据bean创建用户文件对象")
    @ApiImplicitParam(name = "bean", value = "用户文件对象", required = true, dataType = "UserFile")
    Mono<UserFile> save(@RequestBody UserFile bean);
}
