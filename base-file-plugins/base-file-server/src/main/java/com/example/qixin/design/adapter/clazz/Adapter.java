package com.example.qixin.design.adapter.clazz;

/**
 * 创 建  时 间： 2018/8/28 20:41
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
