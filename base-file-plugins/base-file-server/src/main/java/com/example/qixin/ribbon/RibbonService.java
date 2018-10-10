package com.example.qixin.ribbon;

import com.example.qixin.entity.BaseFile;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    private List<BaseFile> list(){
        ServiceInstance instance = loadBalancerClient.choose("");
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(instance.getHost());
        sb.append(":");
        sb.append(instance.getPort());
        sb.append("/list");
        System.out.println(sb.toString());
        RestTemplate rt = new RestTemplate();
        ParameterizedTypeReference<List<BaseFile>> typeRef = new ParameterizedTypeReference<List<BaseFile>>(){};
        return null;
    }


}
