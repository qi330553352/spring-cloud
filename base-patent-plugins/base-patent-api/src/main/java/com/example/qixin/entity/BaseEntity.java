package com.example.qixin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/** 数据公共属性
 * 创  建   时  间： 2018/10/24 23:58
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
public class BaseEntity implements Serializable {

    @Id
    private String id;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//    private @NotNull Date createTime;    //创建时间

}
