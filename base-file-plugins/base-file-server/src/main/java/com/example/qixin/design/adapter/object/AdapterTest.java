package com.example.qixin.design.adapter.object;

import com.example.qixin.design.adapter.clazz.Source;
import com.example.qixin.design.adapter.clazz.Targetable;

/**
 * 创 建  时 间： 2018/8/28 20:50
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class AdapterTest {

    public static void main(String[] args) {
        Source source = new Source();
        Targetable wrapper = new Wrapper(source);
        wrapper.method1();
        wrapper.method2();
    }
}
