package com.example.qixin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 创  建   时  间： 2018/11/19 0:01
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@Document(collection = "JDK8")
public class JDK8 implements Serializable {

    @Id
    private String id;
    private Integer age;
    private String name;
    private Date createTime;
}
