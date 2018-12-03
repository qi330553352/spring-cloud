package com.example.qixin.service;

import org.springframework.http.HttpMethod;
import com.example.qixin.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 创  建   时  间： 2018/12/3 22:56
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class ProductService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;//ribbon 负载均衡客户端

    public List<Product> listProduct(){
        ServiceInstance si=loadBalancerClient.choose("ribbon-strategy-provider");
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
