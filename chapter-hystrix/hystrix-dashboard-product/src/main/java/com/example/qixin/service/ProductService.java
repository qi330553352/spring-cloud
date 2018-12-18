package com.example.qixin.service;

import com.example.qixin.entity.ProductInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 创  建   时  间： 2018/12/5 23:12
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class ProductService {

    @Autowired
    private LoadBalancerClient client;//ribbon 负载均衡客户端

    @HystrixCommand(groupKey="hystrix-product-server", commandKey = "list",
            threadPoolKey="hystrix-product-server",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),//线程池大小
                    @HystrixProperty(name = "maxQueueSize", value = "100"),//最大队列长度
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),//线程存活时间
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15")//拒绝请求
            },
            fallbackMethod = "fallback")
    public List<ProductInfo> list(Integer n){
        System.out.println(n);
        ServiceInstance si=client.choose("hystrix-product-server");
        StringBuffer sb=new StringBuffer("");
        sb.append("http://");
        sb.append(si.getHost());
        sb.append(":");
        sb.append(si.getPort());
        sb.append("/productInfoApi/findList");
        System.out.println(sb.toString());

        RestTemplate rt=new RestTemplate();
        ParameterizedTypeReference<List<ProductInfo>> typeRef = new ParameterizedTypeReference<List<ProductInfo>>(){};
        ResponseEntity<List<ProductInfo>> resp=rt.exchange(sb.toString(), HttpMethod.GET, null, typeRef)	;
        return resp.getBody();
    }

    public List<ProductInfo> fallback(Integer n){
        List<ProductInfo> list=new ArrayList<>();
        list.add(new ProductInfo(-1,"fallback",100));
        return list;
    }

}
