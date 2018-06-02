package com.example.qixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 创  建   时  间： 2018/6/1 21:44
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PatentDescribe")
public class PatentDescribe {

    @Id
    private String id;
    /* 摘要 */
    private String zy;
    /* 主权项 */
    private String zqx;
    /* ABS */
    private String abs;

}
