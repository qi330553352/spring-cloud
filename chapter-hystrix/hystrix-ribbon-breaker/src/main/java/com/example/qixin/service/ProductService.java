package com.example.qixin.service;

import com.example.qixin.entity.ProductInfo;
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

    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {
                    //默认20个;10s内请求数大于20个时就启动熔断器，当请求符合熔断条件时将触发getFallback()。
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value="10"),
                    //请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件时将触发getFallback()。
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value="50"),
                    //默认5秒;熔断多少秒后去尝试请求
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value="5000"),
            })
    public List<ProductInfo> list(Integer n){
        System.out.println(n);
        if(n==1){
            throw new RuntimeException();
        }
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
