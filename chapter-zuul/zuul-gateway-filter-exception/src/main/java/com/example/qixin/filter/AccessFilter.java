package com.example.qixin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 创  建   时  间： 2018/12/20 0:15
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class AccessFilter extends ZuulFilter {


    @Override
    public Object run() throws ZuulException {
        log.info("");
        log.info("----------pre1----------------");
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest hsr = rc.getRequest();
        String token = hsr.getParameter("token");
        if(token==null){
            log.warn("token is null............");
            rc.setSendZuulResponse(false);//代表结束请求，不在继续下级传递。
            rc.setResponseStatusCode(401);
            rc.setResponseBody("{\"result\":\"token is null\"}");
            rc.getResponse().setContentType("text/html;charset=utf-8");
        }else{
//			TODO  redis 验证
            log.info("token is OK");
        }

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

    @Override
    public boolean shouldFilter() {

        return true;
    }
}
