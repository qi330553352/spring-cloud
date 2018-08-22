package com.example.qixin.design.builder;

import com.example.qixin.entity.Users;

/**
 * 创  建   时  间： 2018/8/23 0:44
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UsersBuilderTest {

    public static void main(String[] args){
        Users users = new UsersBuilder().setId(0L).setUsernarne("qixin").build();
        System.out.println(users);
    }
}
