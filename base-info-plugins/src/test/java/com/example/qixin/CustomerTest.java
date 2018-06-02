package com.example.qixin;

import com.example.qixin.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 创  建   时  间： 2018/5/27 12:13
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerTest {

    private WebClient webClient;
    @LocalServerPort
    private int port;
    @Before
    public void setup() {
        this.webClient = WebClient.create("http://localhost:" + this.port);
    }
    @Test
    public void getCustomerAccounts() {
//        Account customer = this.webClient.get().uri("/customer/accounts/234543647565")
//                .accept(MediaType.APPLICATION_JSON).exchange().then(response -> response.bodyToMono(Account.class))
//                .block();

    }
    @Test
    public void addCustomer() {
        Account customer = new Account(null, "Adam", "Kowalski", "123456787654");
//        customer = webClient.post().uri("/customer").accept(MediaType.APPLICATION_JSON)
//                .exchange(BodyInserters.fromObject(customer)).then(response -> response.bodyToMono(Account.class))
//                .block();

    }
}
