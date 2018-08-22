package com.example.qixin.design.singleton;

/** 单例模式  https://www.cnblogs.com/malihe/p/6891920.html
 * 创  建   时  间： 2018/8/22 23:39
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Singleton {

    private Singleton() {
    }

    private static class SingletonBuild{
        private static Singleton value = new Singleton();
    }

    public Singleton getInstance(){
        return SingletonBuild.value;
    }
}
