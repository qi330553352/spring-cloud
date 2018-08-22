package com.example.qixin.design.abstractFactory;

/** 抽象工厂模式
 * 创  建   时  间： 2018/8/22 23:30
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class AbstractFactory {

    public AbstractFactory() {

    }

    public static Food create(Food clazz){
        if(clazz instanceof AFood)
            return new AFactory().create();
        else if(clazz instanceof BFood)
            return new BFactory().create();
        else
            throw new IllegalStateException("参数错误");
    }
}
