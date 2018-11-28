package com.example.qixin.confirm;

import com.example.qixin.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/** 批量模式
 * 创  建   时  间： 2018/11/25 21:28
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class Send3 {

    public static String queue_name="queue_confirm_1";
    public static void main(String[] args)throws Exception{
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel(); //获得一个通道
        channel.queueDeclare(queue_name,false,false,false,null);
       //生产者调用confirmSelect，将channel设置为confirm模式
        channel.confirmSelect();
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<>());
        channel.addConfirmListener(new ConfirmListener() {
            //没有问题的
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if(multiple){
                    System.out.println("handleAck-----------multiple");
                    confirmSet.headSet(deliveryTag+1).clear();
                }else{
                    System.out.println("handleAck-----------multiple--------false");
                    confirmSet.clear();
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if(multiple){
                    System.out.println("handleAck-----------multiple");
                    confirmSet.headSet(deliveryTag+1).clear();
                }else{
                    System.out.println("handleAck-----------multiple--------false");
                    confirmSet.clear();
                }
            }
        });
        String msg = "hello world ...";
        while (true){
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",queue_name,null,msg.getBytes());
            confirmSet.add(seqNo);
        }

    }
}
