package com.example.qixin.service;

import com.example.qixin.entity.UserFile;
import com.example.qixin.feign.UserFileApi;
import com.example.qixin.repository.UserFileRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 创  建   时  间： 2018/10/23 21:25
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Service
public class UserFileServiceImpl implements UserFileApi {

    @Autowired
    private UserFileRepository repository;
    @Autowired
    private ReactiveMongoTemplate template;

    @Override
    public Mono<UserFile> findById(@PathVariable("id") String id) {

        return repository.findById(id);
    }

    @Override
    public Mono<UserFile> save(@RequestBody UserFile bean) {

        return repository.save(bean);
    }


}
