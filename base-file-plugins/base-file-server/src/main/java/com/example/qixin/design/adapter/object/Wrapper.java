package com.example.qixin.design.adapter.object;

import com.example.qixin.design.adapter.clazz.Source;
import com.example.qixin.design.adapter.clazz.Targetable;

/**
 * 创 建  时 间： 2018/8/28 20:48
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
