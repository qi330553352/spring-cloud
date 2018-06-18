package com.example.qixin.controller;

import com.example.qixin.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.qixin.Baidu;
import com.example.qixin.BaiduApiException;
import com.example.qixin.BaiduOAuthException;
import com.example.qixin.store.BaiduCookieStore;
import com.example.qixin.store.BaiduStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 创  建   时  间： 2018/6/18 16:01
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@RestController
public class ClientAuthorizeController {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.resource.userInfoUri}")
    private String redirectUri;

    @PostMapping("/oauthCode")
    public void oauthCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Baidu baidu = null;
        BaiduStore store = new BaiduCookieStore(clientId, request, response);
        try {
            baidu = new Baidu(clientId, clientSecret, redirectUri, store, request);
            String state = baidu.getState();
            Map<String, String> params = new HashMap<>();
            params.put("state", state);
            String authorizeUrl = baidu.getBaiduOAuth2Service().getAuthorizeUrl(params);
            response.sendRedirect(authorizeUrl);
        } catch (BaiduOAuthException e) {
            log.info("BaiduOAuthException ", e);
        } catch (BaiduApiException e) {
            log.info("BaiduApiException ", e);
        }
    }

    @GetMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("回调成功！！！");
        StringBuilder sb = new StringBuilder();
        BaiduStore store = new BaiduCookieStore(clientId, request, response);
        Baidu baidu = null;
        String accessToken = "";
        String refreshToken = "";
        String sessionKey = "";
        String sessionSecret = "";
        User loggedInUser = null;
        try {
            baidu = new Baidu(clientId, clientSecret, redirectUri, store, request);
            accessToken = baidu.getAccessToken();
            refreshToken = baidu.getRefreshToken();
            sessionKey = baidu.getSessionKey();
            sessionSecret = baidu.getSessionSecret();
            loggedInUser = baidu.getLoggedInUser();
        } catch (BaiduApiException e) {
            log.info("BaiduApiException", e);
        } catch (BaiduOAuthException e) {
            log.info("BaiduOAuthException ", e);
        }
        request.setAttribute("accessToken", accessToken);
        request.setAttribute("refreshToken", refreshToken);
        request.setAttribute("sessionKey", sessionKey);
        request.setAttribute("sessionSecret", sessionSecret);
        sb.append("accessToken:").append(accessToken).append("\n");
        sb.append("refreshToken:").append(refreshToken).append("\n");
        sb.append("sessionKey:").append(sessionKey).append("\n");
        sb.append("sessionSecret:").append(sessionSecret).append("\n");
        if (loggedInUser != null) {
            request.setAttribute("user", loggedInUser);
            sb.append("user:").append(loggedInUser).append("\n");
        }
        System.out.println(sb.toString());
        System.out.println("------------这是漂亮的分割线-------------");
        request.getRequestDispatcher("accesstoken.html").forward(request, response);
    }

    @GetMapping("/userService")
    public void userService(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getRequestDispatcher("userService.html").forward(request, response);
    }
}
