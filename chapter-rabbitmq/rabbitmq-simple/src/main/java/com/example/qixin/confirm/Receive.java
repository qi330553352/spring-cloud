package com.example.qixin.confirm;

import com.example.qixin.confirm.Send1;
import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/** 事务
 * 创  建   时  间： 2018/11/25 21:11
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Receive {

    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(Send1.queue_name,false,false,false,null);
        channel.basicConsume(Send1.queue_name,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("receive confirm:"+new String(body,"UTF-8"));
            }
        });
    }




}
