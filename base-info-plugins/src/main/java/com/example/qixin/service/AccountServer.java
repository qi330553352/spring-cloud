package com.example.qixin.service;

import com.example.qixin.entity.Account;
import com.example.qixin.repository.AccountRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * 创  建   时  间： 2018/5/27 11:50
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class AccountServer {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private ReactiveMongoTemplate template;

//    public Flux<Account> findByCustomer(String customerId) {
//        return repository.findByCustomerId(customerId)
//                .map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
//    }
    @GetMapping(value = "/account")
    public Flux<Account> findAll() {
        return repository.findAll().map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
    }
    @GetMapping(value = "/account/{id}")
    public Mono<Account> findById(@PathVariable("id") String id) {
        return repository.findById(id).map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
    }

//    public Mono<Account> create(Publisher<Account> accountStream) {
//        return repository.save(Mono.from(accountStream).map(a -> new Account(a.getNumber(), a.getCustomerId(),a.getAmount())))
//                .map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
//    }

    public Mono<Account> findById(Integer id) {
        return template.findById(id, Account.class);
    }
    public Flux<Account> findByCustomerId(String customerId) {
        return template.find(query(where("customerId").is(customerId)), Account.class);
    }
    public Mono<Account> save(Mono<Account> account) {
        return template.insert(account);
    }
}
