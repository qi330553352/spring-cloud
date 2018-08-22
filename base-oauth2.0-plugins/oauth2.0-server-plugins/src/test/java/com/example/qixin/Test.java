package com.example.qixin;

import org.springframework.security.crypto.codec.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 创  建   时  间： 2018/8/2 1:07
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(generate("oauth_client_ren", "oauth_client_secret_ren"));
    }

    private static String generate(String clientId, String clientSecret) {
        String creds = String.format("%s:%s", new Object[] { clientId, clientSecret });
        try {
            return "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }
}
