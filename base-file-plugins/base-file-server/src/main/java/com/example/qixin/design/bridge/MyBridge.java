package com.example.qixin.design.bridge;

/**
 * 创 建  时 间： 2018/8/28 21:37
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class MyBridge extends Bridge {

    @Override
    public void method() {
        getSource().method();
    }
}