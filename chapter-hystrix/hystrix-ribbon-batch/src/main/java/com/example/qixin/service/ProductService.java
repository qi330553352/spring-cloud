package com.example.qixin.service;

import com.example.qixin.entity.ProductInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 创  建   时  间： 2018/12/5 23:12
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class ProductService {

    //利用hystrix合并请求
    @HystrixCollapser(batchMethod = "batchProduct", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
                    //请求时间间隔在50ms之内的请求会被合并为一个请求
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "20"),
                    //设置触发批处理执行之前，在批处理中允许的最大请求数。
                    @HystrixProperty(name = "maxRequestsInBatch", value = "200"),
            })
    public Future<ProductInfo> getProduct(Integer id){
        System.out.println("------------"+id+"-------------");
        return null;
    }

    @HystrixCommand
    public List<ProductInfo> batchProduct(List<Integer> ids){
        for(Integer id:ids){
            System.out.println(id);
        }

        List<ProductInfo> list=new ArrayList<>();
        list.add(new ProductInfo(1,"漫谈spring cloud与spring boot基础架构（微服务基础篇）",11));
        list.add(new ProductInfo(2,"漫谈spring cloud分布式服务架构（微服务进阶篇）",22));
        list.add(new ProductInfo(3,"漫谈spring cloud 与docker架构部署（微服务高级篇）",33));
        list.add(new ProductInfo(4,"444444444444444444",44));
        return  list;
    }

}
