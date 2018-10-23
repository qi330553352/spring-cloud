package com.example.qixin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/** 用户文件
 * 创  建   时  间： 2018/10/23 20:42
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@Document(collection = "UserFile")

public class UserFile {

    @Id
    private String id;
    private String fileName;
    private Date createTime;

}
