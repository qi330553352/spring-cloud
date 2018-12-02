/* 消息点对点
1、一条消息只能被一个消费者消费（并且是持久化消息），当没有被消费时会一直在队列中
2、当一条消息被一个消费者收到但没处理（响应时间多久，如何设置？）该消息会一直保留或者转到另一个消费者（起负载均衡作用）
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
