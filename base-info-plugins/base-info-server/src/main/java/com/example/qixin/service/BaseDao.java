package com.example.qixin.service;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 创  建   时  间： 2018/10/22 23:26
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends ReactiveMongoRepository<T,ID> {

}
