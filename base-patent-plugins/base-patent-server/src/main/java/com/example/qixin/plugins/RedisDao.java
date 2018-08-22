package com.example.qixin.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * 创  建   时  间： 2018/8/19 14:18
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(String key,String value){
        ValueOperations<String, String> ops= stringRedisTemplate.opsForValue();
        ops.set(key,value,1,TimeUnit.MINUTES) ; //l 分钟过期
    }

    public String getKey(String key){
        ValueOperations<String, String> ops= stringRedisTemplate.opsForValue();
        return ops.get(key);
    }
}
