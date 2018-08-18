package com.example.qixin.service;

import com.example.qixin.api.PatentDescribeApi;
import com.example.qixin.entity.PatentDescribe;
import com.example.qixin.repository.PatentDescribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * 创  建   时  间： 2018/6/1 22:12
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Service
@RestController
public class PatentDescribeService implements PatentDescribeApi {

    @Autowired
    private ReactiveMongoTemplate template;
    @Autowired
    private PatentDescribeRepository repository;

    @Override
    public Mono<PatentDescribe> findById(@PathVariable String id) {

        return repository.findById(id);
    }

    @Override
    public Mono<ResponseEntity<PatentDescribe>> getById(@PathVariable String id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Flux<PatentDescribe> findPageInfo(@RequestBody @Valid Example<PatentDescribe> bean) {
        //获取排序对象
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return repository.findAll(bean,sort);
    }

    @Override
    public Flux<PatentDescribe> addBeans(@RequestBody List<PatentDescribe> beans) {

        return template.insertAll(beans);
    }

    @Override
    public Mono<PatentDescribe> addBean(@Valid @RequestBody PatentDescribe bean) {

        return repository.save(bean);
    }

    @Override
    public Mono<Long> findTotal() {

        return repository.count();
    }

    @Override
    public Flux<PatentDescribe> findBeans(@RequestBody @Valid Example<PatentDescribe> bean) {

        return repository.findAll(bean);
    }

    @Override
    public Mono<ResponseEntity<PatentDescribe>> updateById(@PathVariable String id, @RequestBody @Valid PatentDescribe entity) {
        return repository.findById(id)
                .flatMap(bean -> {
                    bean.setZy(entity.getZy());
                    bean.setZqx(entity.getZqx());
                    bean.setAbs(entity.getAbs());
                    return repository.save(bean);
                })
                .map(bean -> new ResponseEntity<>(bean, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {

        return repository.findById(id)
                .flatMap(bean ->repository.delete(bean)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
