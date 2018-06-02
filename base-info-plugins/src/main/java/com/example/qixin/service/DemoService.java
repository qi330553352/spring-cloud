package com.example.qixin.service;

import com.example.qixin.entity.Demo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/** https://www.ibm.com/developerworks/cn/java/spring5-webflux-reactive/
 * 创  建   时  间： 2018/5/25 22:47
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@RestController
public class DemoService implements com.example.qixin.feign.DemoApi {

    private final Map<String, Demo> data = new ConcurrentHashMap<>();

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
    @ExceptionHandler(ResourceNotFoundException.class)
    public void notFound() {

    }

    @Override
    public Mono<String> sayHelloWorld() {

        return Mono.just("Hello World");
    }

    @Override
    public Map<String, Object> findInfo() {
        Map<String,Object> map = new HashMap<>();
        map.put("time",System.currentTimeMillis());
        map.put("name","qixin");
        return map;
    }

    @Override
    public Flux<Demo> findFlux() {
        List<Demo> list = new ArrayList<>();
        Demo demo1 = new Demo();
        demo1.setTime(System.currentTimeMillis());
        demo1.setName("qixin");
        list.add(demo1);
        return Flux.fromIterable(list);
        //return Flux.fromIterable(this.data.values());
    }

    @Override
    public Flux<Demo> getById(final Flux<String> ids) {

        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    @Override
    public Mono<Demo> getById(final String id) {

        return Mono.justOrEmpty(this.data.get(id)).switchIfEmpty(Mono.error(new ResourceNotFoundException("出错啦")));
    }

    @Override
    public Flux<Demo> createOrUpdate(final Flux<Demo> users) {

        return users.doOnNext(user -> this.data.put(user.getId(), user));
    }

    @Override
    public Mono<Demo> createOrUpdate(final Demo user) {
        this.data.put(user.getId(), user);
        return Mono.just(user);
    }

    @Override
    public Mono<Demo> delete(final String id) {

        return Mono.justOrEmpty(this.data.remove(id));
    }

    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder().event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }
}
