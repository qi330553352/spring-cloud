package com.example.qixin.design.factory.ordinary;

/**
 * 创 建  时 间： 2018/8/27 22:00
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}
