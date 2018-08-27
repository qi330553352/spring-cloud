package com.example.qixin.design.factory.multiple;

import com.example.qixin.design.factory.ordinary.Sender;

/**
 * 创 建  时 间： 2018/8/27 22:12
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class SendFactoryTest {

    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender mail = factory.produceMail();
        mail.send();
    }
}
