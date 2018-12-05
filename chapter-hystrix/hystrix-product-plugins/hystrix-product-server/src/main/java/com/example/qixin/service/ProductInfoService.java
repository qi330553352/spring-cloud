package com.example.qixin.service;

import com.example.qixin.entity.ProductInfo;
import com.example.qixin.feign.ProductInfoApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 创  建   时  间： 2018/12/4 22:14
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RestController
public class ProductInfoService implements ProductInfoApi {

    @Override
    public List<ProductInfo> findList() {
        log.info("参数:");
        List<ProductInfo> list = new ArrayList<>();
        list.add(new ProductInfo(1,"小白",20));
        list.add(new ProductInfo(2,"小红",15));
        list.add(new ProductInfo(3,"小黄",22));
        list.add(new ProductInfo(4,"小紫",26));
        return list;
    }

    @Override
    public ProductInfo findById(@PathVariable("id") Integer id) {
        log.info("参数 id:{}",id);
        return new ProductInfo(1,"小白",20);
    }

    @Override
    public List<ProductInfo> findInfo(//
            @RequestParam(value = "name", required = false) String name, //
            @RequestParam(value = "age", required = false) Integer age) {
        log.info("参数 name:{} age:{}",name,age);
        List<ProductInfo> list = new ArrayList<>();
        list.add(new ProductInfo(1,"小白",20));
        list.add(new ProductInfo(2,"小红",15));
        list.add(new ProductInfo(3,"小黄",22));
        list.add(new ProductInfo(4,"小紫",26));
        return list;
    }

    @Override
    public List<ProductInfo> findByEntity(@RequestBody ProductInfo bean) {
        log.info("参数 {}",bean);
        List<ProductInfo> list = new ArrayList<>();
        list.add(new ProductInfo(1,"小白",20));
        list.add(new ProductInfo(2,"小红",15));
        list.add(new ProductInfo(3,"小黄",22));
        list.add(new ProductInfo(4,"小紫",26));
        return list;
    }
}
