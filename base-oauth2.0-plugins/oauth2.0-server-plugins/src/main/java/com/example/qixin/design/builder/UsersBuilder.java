package com.example.qixin.design.builder;

import com.example.qixin.entity.Users;

/**
 * 创  建   时  间： 2018/8/23 0:26
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class UsersBuilder {

    public Long id ;
    public String usernarne;
    public String password;
    public boolean enabled;


    public Users build(){
        return new Users(this);
    }


    public UsersBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UsersBuilder setUsernarne(String usernarne) {
        this.usernarne = usernarne;
        return this;
    }

    public UsersBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UsersBuilder setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
