package com.example.qixin.controller;

import com.example.qixin.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创  建   时  间： 2018/11/24 17:06
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hi")
    public Map<String,Object> hi(){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","数据返回成功");
        log.info("{}",map);
        return map;
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;//ribbon 负载均衡客户端

    public List<Product> listProduct(){
        ServiceInstance si=loadBalancerClient.choose("eureka-provider");
        StringBuffer sb=new StringBuffer("");
        sb.append("http://");
        sb.append(si.getHost());
        sb.append(":");
        sb.append(si.getPort());
        sb.append("/list");
        System.out.println(sb.toString());

        RestTemplate rt=new RestTemplate();
        ParameterizedTypeReference<List<Product>> typeRef=new ParameterizedTypeReference<List<Product>>(){};
        ResponseEntity<List<Product>> resp=rt.exchange(sb.toString(), HttpMethod.GET, null, typeRef)	;
        List<Product> plist=resp.getBody();
        return plist;
    }
}
