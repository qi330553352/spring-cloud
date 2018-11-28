package com.example.qixin.simple;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

/** 发送消息
 * 创  建   时  间： 2018/11/25 12:27
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send {

    public static String queue_name = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(queue_name,false,false,false, Collections.emptyMap());//声明队列
        String msg = "这是我发的内容11854388";
        channel.basicPublish("",queue_name,null,msg.getBytes());//发送
        System.out.println("---消息已发送---"+msg);
        channel.close();
        conn.close();
    }
}
