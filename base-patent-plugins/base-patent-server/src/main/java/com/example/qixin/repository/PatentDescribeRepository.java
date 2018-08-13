package com.example.qixin.repository;

import com.example.qixin.entity.PatentDescribe;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 创  建   时  间： 2018/6/1 22:00
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Repository
public interface PatentDescribeRepository extends ReactiveMongoRepository<PatentDescribe,String> {

//    @Query("{'teachers.userId':?0}")
//    Flux<PatentDescribe> findStudents(String id);
}
