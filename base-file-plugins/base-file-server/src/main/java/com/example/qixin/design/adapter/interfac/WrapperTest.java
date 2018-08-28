package com.example.qixin.design.adapter.interfac;

/**
 * 创 建  时 间： 2018/8/28 20:57
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class WrapperTest {

    public static void main(String[] args) {
        Sourceable sourceSub1 = new SourceSub1();
        sourceSub1.method1();
        sourceSub1.method2();

        Sourceable sourceSub2 = new SourceSub2();
        sourceSub2.method1();
        sourceSub2.method2();
    }
}
