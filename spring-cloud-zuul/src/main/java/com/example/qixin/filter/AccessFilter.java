package com.example.qixin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 创  建   时  间： 2018/10/14 21:56
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class AccessFilter extends ZuulFilter {


    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            rc.setSendZuulResponse(false);  //代表请求结束，不再向下级传递
            rc.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            rc.setResponseBody("\"msg\":\"token is null\"");
            rc.getResponse().setContentType("text/html;charset=utf-8");
        }else{
            log.info("token is not null");
            // TODO redis 校验
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}
