package com.example.qixin.work;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Collections;

/** 接收消息(轮询分发)
 * 创  建   时  间： 2018/11/25 12:40
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Receive2 {


    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(Send.queue_name,false,false,false, Collections.emptyMap()); //队列声明
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("---接收信息2：----"+msg);
                try {
                    Thread.sleep(1*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(Send.queue_name,true,consumer);//监听队列
    }

}
