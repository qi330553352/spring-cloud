package com.example.qixin.service;

import com.example.qixin.feign.ProductInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 创  建   时  间： 2018/12/4 22:22
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@FeignClient(name = "feign-product-ribbon-server")
public interface ProductService extends ProductInfoApi{

}
