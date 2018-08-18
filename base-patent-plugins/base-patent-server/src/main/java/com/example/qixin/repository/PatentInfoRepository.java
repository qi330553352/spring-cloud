package com.example.qixin.repository;

import com.example.qixin.entity.PatentInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 创  建   时  间： 2018/8/18 15:54
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Repository
public interface PatentInfoRepository extends ReactiveMongoRepository<PatentInfo,String> {
}
