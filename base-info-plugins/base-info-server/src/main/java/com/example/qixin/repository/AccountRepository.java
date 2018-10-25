package com.example.qixin.repository;

import com.example.qixin.entity.Account;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 创  建   时  间： 2018/10/22 23:27
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    @Query("{'teachers.userId':?0}")
    Flux<Account> findStudents(String id);

   // Flux<Object> findByCustomerId(String customerId);
}
