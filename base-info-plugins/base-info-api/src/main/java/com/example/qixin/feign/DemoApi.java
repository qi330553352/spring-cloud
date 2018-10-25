package com.example.qixin.feign;

import com.example.qixin.entity.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 创  建   时  间： 2018/10/22 23:26
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Api(value = "示例接口A")
@Resource(name = "示例接口B")
@RequestMapping("/demoApi")
@FeignClient( name = "base-info-server")
public interface DemoApi {

    @GetMapping("/helloWorld")
    @ApiOperation(value = "Mono", notes = "字符串类型")
    Mono<String> sayHelloWorld();

    @GetMapping("/getById/{id}")
    Mono<Demo> getById(@PathVariable("id") final String id);

    @PutMapping("/createOrUpdate")
    Mono<Demo> createOrUpdate(@RequestBody final Demo user);

    @DeleteMapping("/delete/{id}")
    Mono<Demo> delete(@PathVariable("id") final String id);

    @GetMapping("/findInfo")
    @ApiOperation(value = "获得信息A", notes = "获得信息B")
    Map<String,Object> findInfo();

    @GetMapping("/findFlux")
    @ApiOperation(value = "FluxA", notes = "FluxB")
    Flux<Demo> findFlux();

    @PostMapping("/getById")
    Flux<Demo> getById(@RequestBody final Flux<String> ids);

    @PostMapping("/createOrUpdate")
    Flux<Demo> createOrUpdate(@RequestBody final Flux<Demo> users);

    @GetMapping("/randomNumbers")
    Flux<ServerSentEvent<Integer>> randomNumbers();
}
