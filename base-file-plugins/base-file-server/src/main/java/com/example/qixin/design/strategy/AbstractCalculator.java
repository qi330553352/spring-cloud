package com.example.qixin.design.strategy;

/**
 * 创  建   时  间： 2018/9/2 14:58
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public abstract class AbstractCalculator {

    public int[] split(String exp,String opt){
        String array[]  = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}
