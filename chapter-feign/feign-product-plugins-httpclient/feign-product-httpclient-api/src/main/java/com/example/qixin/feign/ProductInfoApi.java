package com.example.qixin.feign;

import com.example.qixin.entity.ProductInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创  建   时  间： 2018/12/4 22:10
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RequestMapping("/productInfoApi")
public interface ProductInfoApi {

    @GetMapping(value = "/findByBean",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductInfo> findByBean(@RequestBody ProductInfo bean);
}
