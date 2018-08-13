package com.example.qixin.utils;

import com.example.qixin.vo.PatentInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 创  建   时  间： 2018/5/10 1:12
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class DataUtils {

    public static void setTrsZL(String val,String str,PatentInfo bean){
        if(str.startsWith("<公开（公告）号>")){
            bean.setGkh(val);
        }else if(str.startsWith("<公开（公告）日>")){
            bean.setGkr(val);
        }else if(str.startsWith("<申请号>")){
            bean.setSqh(val);
        }else if(str.startsWith("<申请日>")){
            bean.setSqr(val);
        }else if(str.startsWith("<专利号>")){
            bean.setZlh(val);
        }else if(str.startsWith("<名称>")){
            bean.setMc(val);
        }else if(str.startsWith("<主分类号>")){
            bean.setZflh(val);
        }else if(str.startsWith("<分类号>")){
            bean.setFlh(val);
        }else if(str.startsWith("<申请（专利权）人>")){
            bean.setZlqr(val);
        }else if(str.startsWith("<发明（设计）人>")){
            bean.setFmr(val);
        }else if(str.startsWith("<摘要>")){
            bean.setZy(val);
        }else if(str.startsWith("<主权项>")){
            bean.setZqx(val);
        }else if(str.startsWith("<优先权>")){
            bean.setYxq(val);
        }else if(str.startsWith("<国省代码>")){
            bean.setGsdm(val);
        }else if(str.startsWith("<地址>")){
            bean.setDz(val);
        }else if(str.startsWith("<专利代理机构>")){
            bean.setZldljg(val);
        }else if(str.startsWith("<代理人>")){
            bean.setDlr(val);
        }else if(str.startsWith("<审查员>")){
            bean.setScy(val);
        }else if(str.startsWith("<国际申请>")){
            bean.setGjsq(val);
        }else if(str.startsWith("<国际公布>")){
            bean.setGjgb(val);
        }else if(str.startsWith("<进入国家日期>")){
            bean.setJrgjrq(val);
        }else if(str.startsWith("<发布路径>")){
            bean.setFblj(val);
        }else if(str.startsWith("<页数>")){
            bean.setYs(val);
        }else if(str.startsWith("<申请国代码>")){
            bean.setSqgdm(val);
        }else if(str.startsWith("<专利类型>")){
            bean.setZllx(val);
        }else if(str.startsWith("<申请来源>")){
            bean.setSqly(val);
        }else if(str.startsWith("<权利要求书页数>")){
            bean.setQlyqsys(val);
        }else if(str.startsWith("<说明书页数>")){
            bean.setSmsys(val);
        }else if(str.startsWith("<说明书附图页数>")){
            bean.setSmsftys(val);
        }else if(str.startsWith("<TRSKeyword>")){
            bean.setTrskeyword(val);
        }else if(str.startsWith("<ABS>")){
            bean.setAbs(val);
        }
    }

    //输入流公共类
    public static BufferedReader reader(File file, String charsetName) throws Exception {
        InputStreamReader inp = new InputStreamReader(new FileInputStream(file),charsetName);
        return new BufferedReader(inp);
    }
}
