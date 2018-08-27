package com.example.qixin.design.builder;

import com.example.qixin.design.factory.ordinary.MailSender;
import com.example.qixin.design.factory.ordinary.Sender;
import com.example.qixin.design.factory.ordinary.SmsSender;

import java.util.ArrayList;
import java.util.List;

/**
 * 创 建  时 间： 2018/8/27 22:45
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class Builder {

    private List<Sender> list = new ArrayList<>();


    public void productMailSend(int num){
        for(int i=0;i<num;i++){
            list.add(new MailSender());
        }
    }

    public void productSmsSend(int num){
        for(int i=0;i<num;i++){
            list.add(new SmsSender());
        }
    }
}
