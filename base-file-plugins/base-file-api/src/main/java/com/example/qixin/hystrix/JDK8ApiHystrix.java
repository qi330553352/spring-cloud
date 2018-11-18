package com.example.qixin.hystrix;

import com.example.qixin.entity.JDK8;
import com.example.qixin.feign.JDK8Api;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 创  建   时  间： 2018/11/19 0:02
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class JDK8ApiHystrix implements FallbackFactory<JDK8Api> {

    @Override
    public JDK8Api create(Throwable e) {
        log.info("错误信息为:"+e);
        return new JDK8Api() {

            @Override
            public Mono<JDK8> findByName(@PathVariable("name") String name) {

                return null;
            }

            @Override
            public Mono<Boolean> isexistence(@PathVariable("id") String id) {
                return null;
            }

            @Override
            public Flux<JDK8> findGtAge(@PathVariable("age") Integer age) {
                return null;
            }
        };
    };
}
