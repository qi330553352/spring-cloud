package com.example.qixin;

import com.example.qixin.stream.IReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 创 建  时 间： 2018/8/25 10:08
 * 版           本:  V1.0
 * 作           者:  qixin
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({IReceiveService.class})
public class BaseFileServer {

    public static void main(String[] args) {

        SpringApplication.run(BaseFileServer.class, args);
    }
}
