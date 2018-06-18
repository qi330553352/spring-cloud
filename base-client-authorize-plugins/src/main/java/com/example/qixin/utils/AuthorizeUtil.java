package com.example.qixin.utils;

import com.example.qixin.domain.Session;
import com.example.qixin.store.BaiduCookieStore;
import com.example.qixin.store.BaiduStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创  建   时  间： 2018/6/18 18:34
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class AuthorizeUtil {

    public static String getAccessToken(String clientId,HttpServletResponse response, HttpServletRequest request) {
        BaiduStore store = new BaiduCookieStore(clientId, request, response);
        Session session = store.getSession();
        if (session == null) {
            try {
                response.sendRedirect("index");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return session.getToken().getAccessToken();
    }
}
