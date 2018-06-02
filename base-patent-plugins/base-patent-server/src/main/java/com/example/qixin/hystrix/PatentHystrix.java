package com.example.qixin.hystrix;

import com.example.qixin.api.PatentApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 创  建   时  间： 2018/6/1 22:09
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Log4j2
@Component
public class PatentHystrix implements FallbackFactory<PatentApi> {

    @Override
    public PatentApi create(Throwable throwable) {

        return new PatentApi() {

        };
    }
}
