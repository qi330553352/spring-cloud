package com.example.qixin.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 创  建   时  间： 2018/12/19 21:50
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class LogFilter extends ZuulFilter {

    /*  pre：请求在路由之前调用，一般用户权限认证，记录日志等
        routing：在路由执行之后被调用
        post 在routing和error过滤之后被调用。可用于信息收集、统计信息。例如性能指标，对response的结构做些特殊处理
        error 处理请求发生错误时被调用。用于异常处理封装
     */
    @Override
    public String filterType() {

        return "pre";
    }

    /*  1、请求前拦截，对请求无效进行处理，对请求有效进行加工
        2、请求结果后处理，即对请求结果进行加工
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest hsr = rc.getRequest();
        String sessionId = hsr.getSession().getId();
        String contextPath = hsr.getContextPath();
        String method = hsr.getMethod();
        String url = hsr.getRequestURL().toString();
        log.info("请求消息1 sessionId:{}  contextPath:{}",sessionId,contextPath);
        log.info("请求消息2 method:{}  url:{}",method,url);
        return null;
    }

    /* 过滤器的执行顺序，值越小越先执行 */
    @Override
    public int filterOrder() {
        return 0;
    }

    /* 是否开启过滤器 true:开启 、false:不开启 */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
