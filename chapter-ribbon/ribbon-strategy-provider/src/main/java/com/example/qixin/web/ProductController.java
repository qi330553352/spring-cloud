package com.example.qixin.web;

import com.example.qixin.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 创  建   时  间： 2018/12/3 23:04
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
public class ProductController {


    @GetMapping("/list")
    public List<Product> list(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"创  建   时  间： 2018/12/3 23:04"));
        list.add(new Product(2,"版           本: V1.0"));
        list.add(new Product(3,"作           者: qixin"));
        list.add(new Product(4,"版  权   所  有: 版权所有(C)2016-2026"));
        return list;
    }
}
