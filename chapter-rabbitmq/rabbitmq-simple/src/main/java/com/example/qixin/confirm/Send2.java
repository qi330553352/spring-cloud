package com.example.qixin.confirm;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/** 批量模式
 * 创  建   时  间： 2018/11/25 21:28
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send2 {

    public static String queue_name="queue_confirm_1";
    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(queue_name,false,false,false,null);
       //生产者调用confirmSelect，将channel设置为confirm模式
        channel.confirmSelect();
        String msg = "hello confirm 1...";
        //批量
        for(int i=1;i<=20;i++){
            channel.basicPublish("", queue_name, null, msg.getBytes());
        }
        //确认
        if(!channel.waitForConfirms()){
            System.out.println("confirm 发送失败...");
        }else {
            System.out.println("confirm 发送成功...");
        }


        channel.close();
        conn.close();

    }
}
