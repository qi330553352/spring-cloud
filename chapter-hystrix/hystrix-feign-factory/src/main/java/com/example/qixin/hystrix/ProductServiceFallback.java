package com.example.qixin.hystrix;

import com.example.qixin.entity.ProductInfo;
import com.example.qixin.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

/**
 * 创  建   时  间： 2018/12/6 19:39
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class ProductServiceFallback implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(final Throwable cause) {

        return new ProductService(){
            @Override
            public List<ProductInfo> findList() {
                log.info("cause:"+cause);
                return Collections.emptyList();
            }

            @Override
            public ProductInfo findById(@PathVariable("id") Integer id) {
                log.info("cause:"+cause);
                return null;
            }
        };
    }
}
