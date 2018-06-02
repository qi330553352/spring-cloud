package com.example.qixin;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * 创  建   时  间： 2018/5/27 11:35
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class SSEClient {

    public static void main(final String[] args) {
        final WebClient client = WebClient.create();
        client.get()
                .uri("http://localhost:8661/demoApi/randomNumbers")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .flatMapMany(response -> response.body(BodyExtractors.toFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
                })))
                .filter(sse -> Objects.nonNull(sse.data()))
                .map(ServerSentEvent::data)
                .buffer(10)
                .doOnNext(System.out::println)
                .blockFirst();
    }

}
