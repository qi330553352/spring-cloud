package com.example.qixin.service;

import com.example.qixin.entity.UserFile;
import com.example.qixin.feign.UserFileApi;
import com.example.qixin.repository.UserFileRepository;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        log.info(repository.findById(id).block());
        return repository.findById(id);
    }

    @Override
    public Flux<UserFile> findAll() {

        return repository.findAll();
    }

    @Override
    public Flux<UserFile> findByFileName(@PathVariable("fileName") String fileName) {
        Flux<UserFile> flux = template.find(Query.query(Criteria.where("fileName").is(fileName)),UserFile.class);
        return flux.map(a->new UserFile(a.getId()));
    }

    @Override
    public Mono<UserFile> save(@RequestBody UserFile bean) {

        return repository.save(bean);
    }

    @Override
    public Mono<UserFile> create(@RequestBody Publisher<UserFile> beans) {

        return template.insert(Mono.from(beans).map(a->new UserFile(a.getFileName(),a.getCreateTime())).map(a->new UserFile(a.getId())));
    }

    @Override
    public Flux<UserFile> batchSave() {
        List<UserFile> list = new ArrayList<>();
        list.add(new UserFile(String.valueOf(System.currentTimeMillis()),new Date()));
        list.add(new UserFile(String.valueOf(System.currentTimeMillis()),new Date()));
        list.add(new UserFile(String.valueOf(System.currentTimeMillis()),new Date()));
        return repository.saveAll(Flux.fromIterable(list));
    }


}
