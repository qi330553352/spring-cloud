package com.example.qixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 创  建   时  间： 2018/11/30 0:49
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
@RequestMapping("/activeMq")
public class ActiveMqController {
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    @Autowired
    private Producer producer;

    @GetMapping("/queue/{msg}")
    public void sendQueue(@PathVariable("msg") String msg){
        producer.sendMessage(this.queue,msg);
    }

    @GetMapping("/topic/{msg}")
    public void sendTopic(@PathVariable("msg")String msg){
        producer.sendMessage(this.topic,msg);
    }
}
