package com.example.qixin.hystrix;

import com.example.qixin.entity.UserFile;
import com.example.qixin.feign.UserFileApi;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import feign.hystrix.FallbackFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 创  建   时  间： 2018/10/23 20:47
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class UserFileApiHystrix implements FallbackFactory<UserFileApi> {

    @Override
    public UserFileApi create(Throwable e) {
        log.info("错误信息为:"+e);
        return new UserFileApi() {
            @Override
            public Mono<UserFile> findById(@PathVariable("id") String id) {

                return null;
            }

            @Override
            public Flux<UserFile> findAll() {

                return null;
            }

            @Override
            public Flux<UserFile> findByFileName(String fileName) {

                return null;
            }

            @Override
            public Mono<UserFile> save(@RequestBody UserFile bean) {

                return null;
            }

            @Override
            public Mono<UserFile> create(@RequestBody Publisher<UserFile> beans) {

                return null;
            }

            @Override
            public Flux<UserFile> batchSave() {

                return null;
            }
        };
    }
}
