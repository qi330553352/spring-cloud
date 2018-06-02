package com.example.qixin;

import com.example.qixin.entity.Demo;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * 创  建   时  间： 2018/5/27 11:43
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class UserControllerTest {

    private final WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8661").build();

    @Test
    public void testCreateUser() throws Exception {
        final Demo user = new Demo();
        user.setName("Test");
        user.setEmail("test@example.org");
        client.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), Demo.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("name").isEqualTo("Test");
    }
}
