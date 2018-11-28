package com.example.qixin;

import com.example.qixin.spring.simple.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqSimpleApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private Sender sender;

	@Test
	public void send() throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			this.sender.send();
		}

	}

}
