package com.example.qixin.design.bridge;

/**
 * 创 建  时 间： 2018/8/28 21:45
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class BridgeTest {
    public static void main(String[] args) {
        Bridge bridge = new MyBridge();
        /*调用第一个对象*/
        Sourceable source1 = new SourceSub1();
        bridge.setSource(source1);
        bridge.method();

        /*调用第二个对象*/
        Sourceable source2 = new SourceSub2();
        bridge.setSource(source2);
        bridge.method();
    }
}
