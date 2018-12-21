package com.example.qixin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 创  建   时  间： 2018/12/20 0:22
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class PostFilter extends ZuulFilter {


    @Override
    public Object run() throws ZuulException {
        log.info("");
        log.info("------------post----------------");
        return null;
    }

    @Override
    public String filterType() {

        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }
}
