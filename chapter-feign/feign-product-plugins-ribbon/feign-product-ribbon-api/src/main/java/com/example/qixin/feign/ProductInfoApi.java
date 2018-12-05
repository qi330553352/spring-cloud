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

    @GetMapping(value = "/findList")
    List<ProductInfo> findList();

    @GetMapping(value = "/findById/{id}")
    ProductInfo findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/findInfo")
    List<ProductInfo> findInfo(//
            @RequestParam(value = "name",required = false) String name, //
            @RequestParam(value = "age",required = false)Integer age);

    @PostMapping(value = "/findByEntity",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductInfo> findByEntity(@RequestBody ProductInfo bean);
}
