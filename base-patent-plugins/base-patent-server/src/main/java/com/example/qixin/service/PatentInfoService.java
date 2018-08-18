package com.example.qixin.service;

import com.example.qixin.api.PatentInfoApi;
import com.example.qixin.entity.PatentDescribe;
import com.example.qixin.entity.PatentInfo;
import com.example.qixin.repository.PatentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * 创  建   时  间： 2018/8/18 15:52
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
@RestController
public class PatentInfoService implements PatentInfoApi {

    @Autowired
    private PatentInfoRepository repository;

    @Override
    public Mono<PatentInfo> save(@RequestBody @Valid PatentInfo bean) {

        return repository.save(bean);
    }


    @Override
    public Flux<PatentInfo> findAll(@RequestBody @Valid Example<PatentDescribe> bean) {

        return null;
    }
}
