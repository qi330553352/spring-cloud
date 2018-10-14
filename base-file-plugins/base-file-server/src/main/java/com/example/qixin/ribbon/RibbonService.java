package com.example.qixin.ribbon;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

/**
 * 创  建   时  间： 2018/10/7 22:52
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class RibbonService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;  //负载均衡客户端

   /* private List<T> list<T>(){
        ServiceInstance instance = loadBalancerClient.choose("");
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(instance.getHost());
        sb.append(":");
        sb.append(instance.getPort());
        sb.append("/list");
        System.out.println(sb.toString());
        RestTemplate rt = new RestTemplate();
        ParameterizedTypeReference<List<T>> typeRef = new ParameterizedTypeReference<List<BaseFile>>(){};
        return null;
    }*/


}
