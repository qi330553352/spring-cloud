/**
 * Copyright (c) 2011 Baidu.com, Inc. All Rights Reserved
 */
package com.example.qixin.service;


import com.example.qixin.BaiduApiClient;
import com.example.qixin.BaiduApiException;

import java.util.Map;

/**
 * 接口服务的基类信息
 * 
 * @author chenhetong(chenhetong@baidu.com)
 * 
 */
public class BaseService {

    private BaiduApiClient invoker = null;

    public BaseService(BaiduApiClient invoker) {
        this.invoker = invoker;
    }

    /**
     * 调用api请求
     * 
     * @param apiPath api请求的url
     * @param params 调用api时传递的参数
     * @param method 调用api的方法GET/POST
     * @return 响应信息，以json格式返回
     * @throws BaiduApiException
     */
    public String makeApiCall(String apiPath, Map<String, String> params,
            String method) throws BaiduApiException {
        String retStr = this.invoker.request(apiPath, params, method);
        return retStr;
    }

}
