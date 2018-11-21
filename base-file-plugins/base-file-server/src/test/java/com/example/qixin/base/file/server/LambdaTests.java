package com.example.qixin.base.file.server;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * 创  建   时  间： 2018/11/19 22:23
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@SpringBootTest
public class LambdaTests {

    @Test
    public void test1(){
        Runnable noArguments = () -> System.out.println("Hello World");
        ActionListener oneArgument = event -> System.out.println("button clicked");
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };
        BinaryOperator<Long> add = (x, y) -> x + y;
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;


    }
}
