package com.example.qixin.design.singleton;

/**
 * 创 建  时 间： 2018/8/27 22:34
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class Singleton {



    private Singleton() {

    }

    private static class SingletonFactory{

        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){

        return SingletonFactory.instance;
    }
}
