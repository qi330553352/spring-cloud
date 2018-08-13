package com.example.qixin.utils;

import com.example.qixin.vo.PatentBaseInfo;
import com.example.qixin.vo.PatentInfo;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 创  建   时  间： 2018/5/9 22:44
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class PatentInfoUtil {

    public static PatentBaseInfo setInfo(PatentBaseInfo entity, PatentInfo bean, Date date){
        entity.setGkh(bean.getGkh());
        entity.setGkr(DateUtil.shortTime(bean.getGkr(),"yyyy.MM.dd"));
        entity.setSqh(bean.getSqh());
        entity.setSqr(DateUtil.shortTime(bean.getSqr(),"yyyy.MM.dd"));
        entity.setZlh(bean.getZlh());
        entity.setMc(bean.getMc());
        entity.setZflh(bean.getZflh());
        entity.setFlh(bean.getFlh());
        entity.setZlqr(bean.getZlqr());
        entity.setFmr(bean.getFmr());
        entity.setZy(bean.getZy());
        //entity.setTzzlx(bean.getTzzlx());
        entity.setZqx(bean.getZqx());
        entity.setYxq(bean.getYxq());
        entity.setGsdm(bean.getGsdm());
        //entity.setFaysqh(bean.getfa);
        entity.setDz(bean.getDz());
        entity.setZldljg(bean.getZldljg());
        entity.setDlr(bean.getDlr());
        //entity.setBzr();
        entity.setScy(bean.getScy());
        entity.setGjsq(bean.getGjsq());
        entity.setGjgb(bean.getGjgb());
        entity.setJrgjrq(DateUtil.shortTime(bean.getJrgjrq(),"yyyy.MM.dd"));
        entity.setFblj(bean.getFblj());
        entity.setYs(StringUtils.isEmpty(bean.getYs())?0:Integer.valueOf(bean.getYs()));
        entity.setSqgdm(bean.getSqgdm());
        if(!StringUtils.isEmpty(bean.getZllx())) entity.setZllx(Integer.valueOf(bean.getZllx()));
        //entity.setFlzt(); TODO 需要再处理
        entity.setSqly(bean.getSqly());
        //entity.setCkwx();
        if(!StringUtils.isEmpty(bean.getQlyqsys()))
            entity.setQlyqsys(Integer.valueOf(bean.getQlyqsys()));
        //entity.setFcfl(bean.getF);
        //entity.setZlyqs(bean.getZlyqs);
        //entity.setSms(bean.getsms);
        if(!StringUtils.isEmpty(bean.getSmsys())) entity.setSmsys(Integer.valueOf(bean.getSmsys()));
        //entity.setSmsft(bean.getSmsft);
        if(!StringUtils.isEmpty(bean.getSmsftys())) entity.setSmsftys(Integer.valueOf(bean.getSmsftys()));
        entity.setGjz(bean.getTrskeyword());
        entity.setAbss(bean.getAbs());
        //entity.setDqqlr(bean.getdq);
        //entity.setDqqlrdz(bean.getd);
        //entity.setScsxr();
        //entity.setSqrlx();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        return entity;
    }
}
