package com.example.qixin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDirectProviderApplicationTests {

	@Autowired
	private Send send;

	@Test
	public void contextLoads() throws InterruptedException {
		for (int i = 0; i < 200; i++) {
			Thread.sleep(2000);
			this.send.send(i);
		}
	}

}
