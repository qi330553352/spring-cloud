package com.example.qixin.design.abstractFactory;

import com.example.qixin.design.factory.ordinary.Sender;

/**
 * 创 建  时 间： 2018/8/27 22:32
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        Provider mail = new MailSenderFactory();
        Sender sender = mail.produce();
        sender.send();
    }

}
