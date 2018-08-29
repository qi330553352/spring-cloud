package com.example.qixin.design.factory.multiple;

import com.example.qixin.design.factory.ordinary.MailSender;
import com.example.qixin.design.factory.ordinary.Sender;
import com.example.qixin.design.factory.ordinary.SmsSender;

/** 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，而多个工厂方法模式是提供多个工厂方法，分别创建对象
 * 创 建  时 间： 2018/8/27 22:08
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class SendFactory {

    public Sender produceMail(){

        return new MailSender();
    }

    public Sender produceSms(){

        return new SmsSender();
    }
}
