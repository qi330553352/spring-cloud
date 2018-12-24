package com.example.qixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/** 专利大文本数据
 * 创  建   时  间： 2018/10/24 23:13
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PatentDescribe")
public class PatentLargeText extends BaseEntity {

    /* 专利号 */
    private String zlh;
    /* 摘要 */
    private String zy;
    /* 主权项 */
    private String zqx;
    /* ABS */
    private String abs;
}
