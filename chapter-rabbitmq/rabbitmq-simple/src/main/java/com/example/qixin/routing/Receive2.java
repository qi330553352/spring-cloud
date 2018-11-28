package com.example.qixin.routing;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**路由模式
 * 创  建   时  间： 2018/11/25 19:14
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Receive2 {

    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(Send.queue_name_2,false,false,false,null);
        channel.basicQos(1);
        channel.queueBind(Send.queue_name_2,Send.exchange_name,"error");
        channel.queueBind(Send.queue_name_2,Send.exchange_name,"info");
        channel.queueBind(Send.queue_name_2,Send.exchange_name,"warning");
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("---接收信息2：----"+msg);
                channel.basicAck(envelope.getDeliveryTag(),false);
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        boolean autoAck = false; //自动应答
        channel.basicConsume(Send.queue_name_2,autoAck,consumer);//监听队列
    }
}
