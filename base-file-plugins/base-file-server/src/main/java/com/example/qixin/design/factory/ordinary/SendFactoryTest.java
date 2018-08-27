package com.example.qixin.design.factory.ordinary;

import java.util.Objects;

/**
 * 创 建  时 间： 2018/8/27 22:09
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class SendFactoryTest {

    public static void main(String[] args) {
        Sender sms = SendFactory.produce("sms");
        Objects.requireNonNull(sms).send();;
    }
}
