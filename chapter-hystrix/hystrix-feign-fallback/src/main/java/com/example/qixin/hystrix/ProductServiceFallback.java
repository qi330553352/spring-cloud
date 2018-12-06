package com.example.qixin.hystrix;

import com.example.qixin.entity.ProductInfo;
import com.example.qixin.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 创  建   时  间： 2018/12/6 19:39
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Component
public class ProductServiceFallback implements ProductService {

    @Override
    public List<ProductInfo> findList() {

        return Collections.emptyList();
    }
}
