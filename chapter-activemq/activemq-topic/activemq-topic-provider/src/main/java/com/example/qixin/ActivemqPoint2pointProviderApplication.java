/* 消息的发布与定阅
1、非持久化订阅（默认）,若当下没有可用的消费者时消息会丢失
2、持久化订阅（持久化队列的客户端），消息会保存在文件或DB中
*/
package com.example.qixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivemqPoint2pointProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqPoint2pointProviderApplication.class, args);
	}
}
