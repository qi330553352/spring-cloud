package com.example.qixin;

import com.example.qixin.entity.Account;
import com.example.qixin.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseInfoPluginsApplicationTests {

	@Test
	public void contextLoads() {


	}

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("13537651800@139.com");
		message.setTo("13537651800@139.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");

		mailSender.send(message);
	}

	@Autowired
	private WebClient webClient;
	@Autowired
	private AccountRepository repository;

//	@GetMapping(value = "/customer/accounts/{pesel}")
//	public Mono<Account> findByPeselWithAccounts(@PathVariable("pesel") String pesel) {
//		return repository.findByPesel(pesel).flatMap(customer -> webClient.get().uri("/account/customer/{customer}", customer.getId()).accept(MediaType.APPLICATION_JSON)
//				.exchange().flatMap(response -> response.bodyToFlux(Account.class))).collectList().map(l -> {return new Account();});
//	}
}
