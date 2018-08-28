package com.example.qixin.design.proxy;

/**
 * 创 建  时 间： 2018/8/28 21:15
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class ProxyTest {

    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }
}
