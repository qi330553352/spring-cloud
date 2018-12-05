package com.example.qixin.web;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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


    @GetMapping("/findList")
    public void findList() throws InterruptedException, ExecutionException {
        Future<ProductInfo> p1 = this.productService.getProduct(1);
        Future<ProductInfo> p2 = this.productService.getProduct(2);
        Future<ProductInfo> p3 = this.productService.getProduct(3);
        System.out.println(p1.get().toString());
        System.out.println(p2.get().toString());
        System.out.println(p3.get().toString());
    }

}
