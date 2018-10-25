package com.example.qixin.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository;

import java.io.Serializable;

/**
 * 创  建   时  间： 2018/10/22 23:32
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleReactiveMongoRepository<T, ID> implements BaseDao<T, ID> {

    @Autowired
    private ReactiveMongoTemplate template;
    @Autowired
    protected MongoEntityInformation<T, ID> entityInformation;

    private Class<T> clazz;

    public BaseDaoImpl(@NonNull MongoEntityInformation<T, ID> entityInformation, @NonNull ReactiveMongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);
        clazz = entityInformation.getJavaType();
    }

}
