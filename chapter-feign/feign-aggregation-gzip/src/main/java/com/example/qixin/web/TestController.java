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

    @GetMapping("/findList")
    public List<ProductInfo> findList(){

        return productService.findList();
    }

    @GetMapping("/findById/{id}")
    public ProductInfo findById(@PathVariable("id") Integer id){

        return productService.findById(id);
    }
    @GetMapping("/findInfo")
    public List<ProductInfo> findInfo(
            @RequestParam(value = "name",required = false) String name, //
            @RequestParam(value = "age",required = false)Integer age){

        return productService.findInfo(name,age);
    }
    @PostMapping("/findByEntity")
    public List<ProductInfo> findByEntity(@RequestBody ProductInfo bean){

        return productService.findByEntity(bean);
    }
}
