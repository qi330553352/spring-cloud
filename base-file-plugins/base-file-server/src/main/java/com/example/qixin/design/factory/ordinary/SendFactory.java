package com.example.qixin.design.factory.ordinary;

/** 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建
 * 创 建  时 间： 2018/8/27 22:01
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class SendFactory {

    public static Sender produce(String type){
        if("mail".equals(type)){
            return new MailSender();
        }else if("sms".equals(type)){
            return new SmsSender();
        }else{
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}
