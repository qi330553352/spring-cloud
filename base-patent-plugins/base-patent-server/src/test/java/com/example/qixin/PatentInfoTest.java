package com.example.qixin;

import com.example.qixin.api.PatentInfoApi;
import com.example.qixin.entity.PatentInfo;
import com.example.qixin.repository.PatentInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 创  建   时  间： 2018/8/18 15:58
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PatentInfoTest {

    @Autowired
    private PatentInfoApi patentInfoApi;
    @Autowired
    private PatentInfoRepository repository;

    @Test
    public void test(){
        PatentInfo bean = new PatentInfo();
        bean.setRemarks("test");
        bean.setCreateTime(new Date());
        bean.setMc("abc");
        Mono<PatentInfo> mono = patentInfoApi.save(bean);
        System.out.println(mono.block());
    }
}
