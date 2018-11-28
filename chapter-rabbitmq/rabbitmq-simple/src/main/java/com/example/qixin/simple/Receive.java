package com.example.qixin.simple;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Collections;

/** 接收消息
 * 创  建   时  间： 2018/11/25 12:40
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Receive {


    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(Send.queue_name,false,false,false, Collections.emptyMap()); //队列声明
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("---接收信息：----"+msg);
            }
        };
        channel.basicConsume(Send.queue_name,true,consumer);//监听队列
    }

    //旧版本API
//    private static void oldApi()throws Exception{
//        Connection conn = ConnectionUtil.getConnection();
//        Channel channel = conn.createChannel(); //获得一个通道
//        QueueingConsumer consumer = new QueueingConsumer(channel); //定义队列消费者
//        channel.basicConsume(Send.queue_name,true,consumer);//监听队列
//        while (true){
//            String msg = new String(consumer.nextDelivery().getBody());
//            System.out.println("---接收信息：----"+msg);
//        }
//    }

}
