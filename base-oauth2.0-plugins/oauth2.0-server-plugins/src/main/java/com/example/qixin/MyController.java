package com.example.qixin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 创  建   时  间： 2018/8/4 0:34
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
public class MyController {

    @GetMapping("/api/get")
    public String get() {
        return "Hello World!";
    }

    @PostMapping("/api/post")
    public String post() {
        //RandomValueStringGenerator
        return "POST process has finished.";
    }

    @GetMapping("/api/user")
    public Object get(HttpServletRequest req) {
        SecurityContextImpl sci = (SecurityContextImpl) req.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (sci != null) {
            Authentication authentication = sci.getAuthentication();
            if (authentication != null) {
                return authentication.getPrincipal();
            }
        }
        return "none";
    }
}
