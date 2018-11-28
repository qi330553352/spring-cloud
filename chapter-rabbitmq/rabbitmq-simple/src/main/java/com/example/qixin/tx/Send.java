package com.example.qixin.tx;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**事务
 * 创  建   时  间： 2018/11/25 19:48
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send {

    public static String queue_name="queue_tx";
    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(queue_name,false,false,false,null);
        String msg = "hello tx 1...";

        try {
            channel.txSelect();
            channel.basicPublish("", queue_name, null, msg.getBytes());
            int a = 1/0;
            channel.txCommit();
        }catch (Exception e){
            channel.txRollback();
            System.out.println("出错了："+e);
        }
        channel.close();
        conn.close();
    }





}
