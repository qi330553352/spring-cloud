package com.example.qixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/** 专利基础数据
 * 创  建   时  间： 2018/10/24 23:13
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BasePatent")
public class BasePatent extends BaseEntity {

    /* 文件类型 */
    private String fileType;
    /* 公开（公告）号 */
    private String gkh;
    /* 公开（公告）日 */
    private String gkr;
    /* 申请号 */
    private String sqh;
    /* 申请日*/
    private String sqr;
    /* 专利号 */
    private String zlh;
    /* 名称 */
    private String mc;
    /* 主分类号 */
    private String zflh;
    /* 分类号 */
    private String flh;
    /* 申请（专利权）人 */
    private String zlqr;
    /* 发明（设计）人 */
    private String fmr;
    /* 摘要 */
    private String zy;
    /* 主权项 */
    private String zqx;
    /* 优先权 */
    private String yxq;
    /* 国省代码 */
    private String gsdm;
    /* 地址 */
    private String dz;
    /* 专利代理机构 */
    private String zldljg;
    /* 代理人 */
    private String dlr;
    /* 审查员 */
    private String scy;
    /* 国际申请 */
    private String gjsq;
    /* 国际公布 */
    private String gjgb;
    /* 进入国家日期 */
    private String jrgjrq;
    /* 发布路径 */
    private String fblj;
    /* 页数 */
    private String ys;
    /* 申请国代码 */
    private String sqgdm;
    /* 专利类型 */
    private String zllx;
    /* 申请来源 */
    private String sqly;
    /* 权利要求书页数 */
    private String qlyqsys;
    /* 说明书页数 */
    private String smsys;
    /* 说明书附图页数 */
    private String smsftys;
    /* TRSKeyword */
    private String trskeyword;
    /* ABS */
    private String abs;
}
