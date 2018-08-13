package com.example.qixin.service;

import com.example.qixin.api.BaseDao;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 创  建   时  间： 2018/8/13 22:01
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Repository
public class BaseDaoImpl<T,ID> implements BaseDao<T,ID> {

    @Autowired
    private ReactiveMongoRepository repository;

    @Override
    @PostMapping("/save")
    public <S extends T> Mono<S> save(@RequestBody S var1) {

        return repository.save(var1);
    }

    @Override
    @PostMapping("/saveAllByIterable")
    public <S extends T> Flux<S> saveAll(@RequestBody Iterable<S> var1) {
        return repository.saveAll(var1);
    }

    @Override
    @PostMapping("/saveAllByPublisher")
    public <S extends T> Flux<S> saveAll(@RequestBody Publisher<S> var1) {

        return repository.saveAll(var1);
    }

    @Override
    @GetMapping("/findById/{id}")
    public Mono<T> findById(@PathVariable("id")ID id) {
        return repository.findById(id);
    }

    @Override
    @GetMapping("/findById")
    public Mono<T> findById(@RequestBody Publisher<ID> var1) {

        return repository.findById(var1);
    }

    @Override
    @GetMapping("/existsById/{id}")
    public Mono<Boolean> existsById(@PathVariable("id")ID id) {
        return repository.existsById(id);
    }

    @Override
    @GetMapping("/existsById")
    public Mono<Boolean> existsById(@RequestBody Publisher<ID> var1) {
        return repository.existsById(var1);
    }

    @Override
    @GetMapping("/findAll")
    public Flux<T> findAll() {
        return repository.findAll();
    }

    @Override
    @GetMapping("/findAllByIdIterable")
    public Flux<T> findAllById(@RequestBody Iterable<ID> var1) {
        return repository.findAllById(var1);
    }

    @Override
    @GetMapping("/findAllByIdPublisher")
    public Flux<T> findAllById(@RequestBody Publisher<ID> var1) {
        return repository.findAllById(var1);
    }

    @Override
    @GetMapping("/count")
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    @DeleteMapping("/deleteById/{id}")
    public Mono<Void> deleteById(@PathVariable("id")ID id) {
        return repository.deleteById(id);
    }

    @Override
    @DeleteMapping("/deleteByIdPublisher")
    public Mono<Void> deleteById(@RequestBody Publisher<ID> var1) {
        return repository.deleteById(var1);
    }

    @Override
    @DeleteMapping("/delete")
    public Mono<Void> delete(@RequestBody T var1) {
        return repository.delete(var1);
    }

    @Override
    @DeleteMapping("/deleteAllIterable")
    public Mono<Void> deleteAll(@RequestBody Iterable<? extends T> var1) {
        return repository.deleteAll(var1);
    }

    @Override
    @DeleteMapping("/deleteAllPublisher")
    public Mono<Void> deleteAll(@RequestBody Publisher<? extends T> var1) {
        return repository.deleteAll(var1);
    }

    @Override
    @DeleteMapping("/deleteAll")
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }
}
