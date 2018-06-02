package com.example.qixin.service;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 创  建   时  间： 2018/5/27 11:07
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Log4j2
@Component
public class EchoHandler implements WebSocketHandler {

    @Override
    public List<String> getSubProtocols() {
        System.out.println("EchoHandler.getSubProtocols");
        return null;
    }

    @Override
    public Mono<Void> handle(final WebSocketSession session) {
        return session.send(
                session.receive()
                        .map(msg -> session.textMessage("ECHO -> " + msg.getPayloadAsText())));
    }
}
