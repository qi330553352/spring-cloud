package com.example.qixin.service;

import com.example.qixin.entity.ProductInfo;
import com.example.qixin.hystrix.ProductServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 创  建   时  间： 2018/12/4 22:22
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@FeignClient(name = "hystrix-product-server",fallback = ProductServiceFallback.class)
public interface ProductService{

    @GetMapping(value = "/productInfoApi/findList")
    List<ProductInfo> findList();
}
