package com.example.qixin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 创  建   时  间： 2018/11/24 21:16
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
public class Product implements Serializable{

    private String id;
    private String name;
}
