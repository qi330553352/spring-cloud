package com.example.qixin.design.abstractFactory;

/**
 * 创  建   时  间： 2018/8/22 23:26
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class AFactory implements Produce {

    public AFactory() {
    }

    @Override
    public Food create() {

        return new AFood();
    }
}
