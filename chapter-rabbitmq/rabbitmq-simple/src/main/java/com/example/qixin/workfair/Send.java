package com.example.qixin.workfair;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Collections;

/** 发送消息(消息应答)
 * 创  建   时  间： 2018/11/25 12:27
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send {

    public static String queue_name = "work_queue";

    public static void main(String[] args) throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(queue_name,false,false,false, Collections.emptyMap());//声明队列

        int num = 1;
        channel.basicQos(num); //每个消息者发送确认消息之前，不会发送下一个消息

        for(int i=1;i<=50;i++){
            String msg = "这是我发的内容 "+i;
            channel.basicPublish("",queue_name,null,msg.getBytes());//发送
            Thread.sleep(1*800);
        }
        System.out.println("---消息已发送---");
        channel.close();
        conn.close();
    }
}
