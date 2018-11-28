package com.example.qixin.ps;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 订阅模式
 * 创  建   时  间： 2018/11/25 15:45
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send {

    public static String queue_email = "test_queue_email";
    public static String queue_sms = "test_queue_sms";
    public static String exchange_name = "test_exchange_name";
    public static void main(String[] args) throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.exchangeDeclare(exchange_name,"fanout");//分发  声明交换机
        String msg = "hello 消息订阅";
        channel.basicPublish(exchange_name,"",null,msg.getBytes());
        System.out.println("-已发送-----"+msg);
        channel.close();
        conn.close();
    }

}
