package com.example.qixin.routing;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 路由模式
 * 创  建   时  间： 2018/11/25 19:06
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send {

    public static String queue_name_1="queue_routing_1";
    public static String queue_name_2="queue_routing_2";
    public static String exchange_name="exchange_routing";
    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.exchangeDeclare(exchange_name,"direct");
        String msg = "hello direct ...";
        String routingKey = "info";
        channel.basicPublish(exchange_name,routingKey,null,msg.getBytes());
        channel.close();
        conn.close();
    }
}
