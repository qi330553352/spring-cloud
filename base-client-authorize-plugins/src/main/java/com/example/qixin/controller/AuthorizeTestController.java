package com.example.qixin.controller;

import com.example.qixin.BaiduApiClient;
import com.example.qixin.BaiduApiException;
import com.example.qixin.domain.User;
import com.example.qixin.service.IUserService;
import com.example.qixin.service.impl.UserServiceImpl;
import com.example.qixin.utils.AuthorizeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 创  建   时  间： 2018/6/18 17:58
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
public class AuthorizeTestController {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @PostMapping("/loggedInUser")
    public Map<String,Object> loggedInUser(HttpServletRequest request, HttpServletResponse response){
        StringBuilder sb = new StringBuilder();
        Map<String,Object> map = new HashMap<>();
        String token = AuthorizeUtil.getAccessToken(clientId,response, request);
        sb.append("token:").append(token).append("\n");
        map.put("token",token);
        IUserService userService = new UserServiceImpl(new BaiduApiClient(token));
        User loggedInUser= null;
        try {
            loggedInUser = userService.getLoggedInUser();
        } catch (BaiduApiException e) {
            e.printStackTrace();
        }
        sb.append("uid:").append(loggedInUser.getUid()).append("\n");
        sb.append("uname:").append(loggedInUser.getUname()).append("\n");
        sb.append("portrait:").append(loggedInUser.getPortrait()).append("\n");
        map.put("uid",loggedInUser.getUid());
        map.put("uname",loggedInUser.getUname());
        map.put("portrait",loggedInUser.getPortrait());
        System.out.println(sb.toString());
        System.out.println("------------这是漂亮的分割线-------------");
        return map;
    }


    @PostMapping("/userInfo")
    public Map<String,Object> userInfo(HttpServletRequest request, HttpServletResponse response){
        StringBuilder sb = new StringBuilder();
        Map<String,Object> map = new HashMap<>();
        String token = AuthorizeUtil.getAccessToken(clientId,response, request);
        sb.append("token:").append(token).append("\n");
        map.put("token",token);
        IUserService userService = new UserServiceImpl(new BaiduApiClient(token));
        long uid = 0;
        String userParameter = request.getParameter("uid");
        if (userParameter != null && !userParameter.trim().equals("")) {
            uid = Long.valueOf(userParameter);
        }
        String fields = request.getParameter("fields");
        String[] split = fields.split("\\,");
        String info = "";
        try {
            info = userService.getInfo(uid, split);
        } catch (BaiduApiException e) {
            info = e.toString();
        }
        request.setAttribute("result", info);
        sb.append("result:").append(info).append("\n");
        map.put("result",info);
        System.out.println(sb.toString());
        System.out.println("------------这是漂亮的分割线-------------");
        return map;
    }
}
