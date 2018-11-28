package com.example.qixin.topic;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**主题订阅
 * 创  建   时  间： 2018/11/25 19:35
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send {

    public static String queue_name_1="queue_topic_1";
    public static String queue_name_2="queue_topic_2";
    public static String exchange_name="exchange_topic";
    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.exchangeDeclare(exchange_name,"topic");
        String msg = "hello topic ...";
        String routingKey = "goods.find";
        channel.basicPublish(exchange_name,routingKey,null,msg.getBytes());
        System.out.println("消息发送成功----"+msg);
        channel.close();
        conn.close();
    }
}
