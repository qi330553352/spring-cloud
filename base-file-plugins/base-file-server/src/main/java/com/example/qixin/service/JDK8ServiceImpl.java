package com.example.qixin.service;

import com.example.qixin.entity.JDK8;
import com.example.qixin.feign.JDK8Api;
import com.example.qixin.repository.JDK8Repository;
import com.example.qixin.repository.UserFileRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 创  建   时  间： 2018/11/19 0:05
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Service
public class JDK8ServiceImpl implements JDK8Api {

    @Autowired
    private JDK8Repository repository;
    @Autowired
    private ReactiveMongoTemplate template;

    @Override
    public Mono<JDK8> findByName(@PathVariable("name") String name) {
        Flux<JDK8> flux = repository.findAll();
        return flux.filter(a->a.getName().equals(name)).singleOrEmpty();
    }

    @Override
    public Mono<Boolean> isexistence(@PathVariable("id") String id) {
        Flux<JDK8> flux = repository.findAll();
        return Mono.just(flux.toStream().anyMatch(a->a.getId().equals(id)));
    }

    @Override
    public Flux<JDK8> findGtAge(@PathVariable("age") Integer age) {
        Flux<JDK8> flux = repository.findAll();
        return flux.filter(a->a.getAge()>age);
    }
}
