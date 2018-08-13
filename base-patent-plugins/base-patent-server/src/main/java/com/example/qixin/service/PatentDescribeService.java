package com.example.qixin.service;

import com.example.qixin.api.PatentApi;
import com.example.qixin.api.PatentDescribeApi;
import com.example.qixin.entity.PatentDescribe;
import com.example.qixin.repository.PatentDescribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 创  建   时  间： 2018/6/1 22:12
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Service
@RestController
public class PatentDescribeService extends BaseDaoImpl<PatentDescribe,String> implements PatentDescribeApi {

    @Autowired
    private ReactiveMongoTemplate template;
    @Autowired
    private PatentDescribeRepository repository;

    @Override
    public Flux<PatentDescribe> addBeans(@RequestBody List<PatentDescribe> beans) {

        return template.insertAll(beans);
    }

    @Override
    public Mono<PatentDescribe> addBean(@RequestBody PatentDescribe bean) {

        return null;
    }

    @Override
    public Mono<Long> findTotal() {

        return repository.count();
    }
}
