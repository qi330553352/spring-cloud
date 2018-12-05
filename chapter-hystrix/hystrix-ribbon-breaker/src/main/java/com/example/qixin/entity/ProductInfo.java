package com.example.qixin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 创  建   时  间： 2018/12/4 22:09
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
public class ProductInfo implements Serializable{

    private Integer id;
    private String name;
    private Integer age;

    public ProductInfo() {
    }

    public ProductInfo(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
