package com.example.qixin;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivemqPoint2pointProviderApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	public void contextLoads() {
		Destination destination = new ActiveMQQueue("mytest.queue");
		while (true){
			producer.sendMessage(destination, "myname is chhliu!!!");
		}
	}

}
