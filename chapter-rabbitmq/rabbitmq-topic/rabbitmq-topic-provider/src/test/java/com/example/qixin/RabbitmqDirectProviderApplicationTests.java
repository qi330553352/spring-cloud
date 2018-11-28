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
	private UserSender userSender;
	@Autowired
	private ProductSender productSender;
	@Autowired
	private OrderSender orderSender;

	@Test
	public void contextLoads() throws InterruptedException {
		this.userSender.send();
		this.productSender.send();
		this.orderSender.send();
	}

}
