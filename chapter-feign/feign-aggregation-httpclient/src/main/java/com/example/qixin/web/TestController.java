package com.example.qixin.web;

import java.util.List;

import com.example.qixin.entity.ProductInfo;
import com.example.qixin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //采用httpclient解决了feign自动转post的问题
    @GetMapping("/findByBean")
    public List<ProductInfo> findByBean(ProductInfo bean){

        return productService.findByBean(bean);
    }
}
