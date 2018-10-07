package com.example.qixin.design.strategy;

/**
 * 创  建   时  间： 2018/9/2 15:06
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class StrategyTest {

    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator cal = new Minus();
        int result = cal.calculate(exp);
        System.out.println(result);
    }
}
