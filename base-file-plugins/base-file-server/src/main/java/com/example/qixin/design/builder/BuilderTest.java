package com.example.qixin.design.builder;

/** 建造者模式（Builder）
 * 创 建  时 间： 2018/8/27 22:48
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class BuilderTest {

    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.productSmsSend(10);
    }
}
