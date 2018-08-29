package com.example.qixin.design.factory.statics;

import com.example.qixin.design.factory.ordinary.MailSender;
import com.example.qixin.design.factory.ordinary.Sender;

/** 静态工厂方法模式，将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
 * 创 建  时 间： 2018/8/27 22:17
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class SendFactoryTest {

    public static void main(String[] args) {
        Sender mail = SendFactory.produceMail();
        mail.send();
    }
}
