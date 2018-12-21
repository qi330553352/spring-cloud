package com.example.qixin.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创  建   时  间： 2018/12/20 0:26
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
public class ErrorGatewayController implements ErrorController {


    @Override
    public String getErrorPath() {

        return "/error";
    }

    @RequestMapping("/error")
    public String error(){

        return "{\"result\":\"500 error!!!\"}";
    }
}
