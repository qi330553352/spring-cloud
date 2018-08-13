package com.example.qixin.vo;

import lombok.Data;

import java.util.Date;

/**
 * 创  建   时  间： 2018/5/9 21:54
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Data
public class PatentBaseInfo {

    private Long id;
    /** 公开（公告）号 */
    private String gkh;
    /** 公开（公告）日 */
    private Date gkr;
    /** 申请号 */
    private String sqh;
    /** 申请日  */
    private Date sqr;		//1

    /** 专利号 */
    private String zlh;
    /** 名称 */
    private String mc;
    /** 主分类号 */
    private String zflh;
    /** 分类号 */
    private String flh;
    /** 申请（专利权）人 */
    private String zlqr;		//2

    /** 发明（设计）人 */
    private String fmr;
    /** 摘要 */
    private String zy;
    /** 同族专利项 */
    private String tzzlx;                           //-------------------------------------------
    /** 主权项 */
    private String zqx;
    /** 优先权 */
    private String yxq;		//3

    /** 国省代码 */
    private String gsdm;
    /** 分案原申请号 */
    private String faysqh;
    /** 地址 */
    private String dz;
    /** 专利代理机构 */
    private String zldljg;
    /** 代理人 */
    private String dlr;			//4

    /** 颁证日 */
    private Date bzr;

    /** 审查员 */
    private String scy;
    /** 国际申请 */
    private String gjsq;
    /** 国际公布 */
    private String gjgb;
    /** 进入国家日期 */
    private Date jrgjrq;
    /** 发布路径 */
    private String fblj;			//5

    /** 页数 */
    private Integer ys;
    /** 申请国代码 */
    private String sqgdm;
    /** 专利类型 */
    private Integer zllx;		//PatentStateType 枚举		Patent2ApplyInfo
    /** 法律状态 */
    private Integer flzt;		//LegalStatus 枚举
    /** 申请来源 */
    private String sqly;
    /** 参考文献 */
    private String ckwx;			//6

    /** 权利要求书页数 */
    private Integer qlyqsys;
    /** 范畴分类 */
    private String fcfl;
    /** 权利要求书 */
    private String zlyqs;
    /** 说明书 */
    private String sms;
    /** 说明书页数 */
    private Integer smsys;			//7

    /** 说明书附图 */
    private String smsft;
    /** 说明书附图页数 */
    private Integer smsftys;
    /** TRSKeyword */
    private String gjz;
    /** ABS */
    private String abss;
    /** 当前权利人 */
    private String dqqlr;

    /** 当前权利人地址 */
    private String dqqlrdz;
    /** 审查生效日 */
    private Date scsxr;
    /** 申请人类型 */
    private Integer sqrlx;	// SqrlxEnum 枚举
    /** solr加载数据时 null加载、0或1:不加载 */
    private Integer sigle;
    /** 创建时间 */
    private Date createTime;			//8

    /** 修改时间 */
    private Date updateTime;
}
