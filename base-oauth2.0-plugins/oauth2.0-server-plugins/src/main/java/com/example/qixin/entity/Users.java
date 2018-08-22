package com.example.qixin.entity;

import com.example.qixin.design.builder.UsersBuilder;
import lombok.Data;

import javax.persistence.*;

/**
 * 创  建   时  间： 2018/8/19 13:59
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false,unique = true)
    private String usernarne;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled;

    public Users() {

    }
    public Users(UsersBuilder builder) {
        this.id=builder.id;
        this.usernarne=builder.usernarne;
        this.password=builder.password;
        this.enabled=builder.enabled;
    }
}
