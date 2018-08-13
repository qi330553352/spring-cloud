package com.example.qixin.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 创  建   时  间： 2018/4/29 1:41
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Data
public class BaseEntity {

    @Id
    private String id;
    /* 创建日期 */
    private String createTime;
    /* 序号 */
    private Integer idx;
    private Integer year;
}
