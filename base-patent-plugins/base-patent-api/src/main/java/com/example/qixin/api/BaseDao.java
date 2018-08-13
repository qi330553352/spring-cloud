package com.example.qixin.api;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 创  建   时  间： 2018/8/13 22:01
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public interface BaseDao<T,ID> {

    <S extends T> Mono<S> save(S var1);

    <S extends T> Flux<S> saveAll(Iterable<S> var1);

    <S extends T> Flux<S> saveAll(Publisher<S> var1);

    Mono<T> findById(ID var1);

    Mono<T> findById(Publisher<ID> var1);

    Mono<Boolean> existsById(ID var1);

    Mono<Boolean> existsById(Publisher<ID> var1);

    Flux<T> findAll();

    Flux<T> findAllById(Iterable<ID> var1);

    Flux<T> findAllById(Publisher<ID> var1);

    Mono<Long> count();

    Mono<Void> deleteById(ID var1);

    Mono<Void> deleteById(Publisher<ID> var1);

    Mono<Void> delete(T var1);

    Mono<Void> deleteAll(Iterable<? extends T> var1);

    Mono<Void> deleteAll(Publisher<? extends T> var1);

    Mono<Void> deleteAll();
}
