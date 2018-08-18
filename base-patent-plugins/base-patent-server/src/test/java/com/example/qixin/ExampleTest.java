package com.example.qixin;

import com.example.qixin.api.PatentDescribeApi;
import com.example.qixin.entity.PatentDescribe;
import com.example.qixin.repository.PatentDescribeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创  建   时  间： 2018/8/18 14:03
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExampleTest {

    @Autowired
    private PatentDescribeApi patentDescribeApi;

    @Test
    public void findAllTest() throws Exception{
        //创建查询条件数据对象
        PatentDescribe bean = new PatentDescribe();
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withIncludeNullValues() //改变“Null值处理方式”：包括
                .withMatcher("remarks", ExampleMatcher.GenericPropertyMatchers.startsWith()) //备注“开始匹配”的方式查询
                .withIgnorePaths("zy","zqx","abs","remarks");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        //创建实例
        Example<PatentDescribe> ex = Example.of(bean,matcher);
        Flux<PatentDescribe> flux = patentDescribeApi.findBeans(ex);
        flux.toStream().forEach(System.out::println);
    }

}
