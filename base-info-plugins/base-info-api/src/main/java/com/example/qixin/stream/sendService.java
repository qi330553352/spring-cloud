package com.example.qixin.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * 创  建   时  间： 2018/10/22 23:17
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public interface SendService {

    @Output("agan-exchange")
    SubscribableChannel send();
}
