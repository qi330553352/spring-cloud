package com.example.qixin;

import com.example.qixin.entity.Demo;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 创  建   时  间： 2018/5/27 11:28
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class RESTClient {

    public static void main(final String[] args) {
        final Demo user = new Demo();
        user.setName("Test");
        user.setEmail("test@example.org");
        final WebClient client = WebClient.create("http://localhost:8080/user");
        final Mono<Demo> createdUser = client.post()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), Demo.class)
                .exchange()
                .flatMap(response -> response.bodyToMono(Demo.class));
        System.out.println(createdUser.block());
    }

}
