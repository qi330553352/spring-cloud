package com.example.qixin.web;

import com.example.qixin.entity.ProductInfo;
import com.example.qixin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创  建   时  间： 2018/12/4 22:19
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findList")
    public List<ProductInfo> findList(Integer n){

        return productService.list(n);
    }

}
