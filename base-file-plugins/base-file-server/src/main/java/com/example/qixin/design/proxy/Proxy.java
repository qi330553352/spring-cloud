package com.example.qixin.design.proxy;

/**
 * 创 建  时 间： 2018/8/28 21:12
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class Proxy implements Sourceable {

    private Source source;

    public Proxy() {

        this.source = new Source();
    }

    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }

    private void atfer() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
