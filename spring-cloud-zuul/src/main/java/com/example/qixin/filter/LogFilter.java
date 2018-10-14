package com.example.qixin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

/** 23种设计模式中行为类型-模板方法模式
 * 创  建   时  间： 2018/10/14 20:44
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class LogFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        log.info("method={},url={}",request.getMethod(),request.getRequestURL().toString());
        //throw new RuntimeException("error");
        return null;
    }

    @Override
    public String filterType() {

        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
