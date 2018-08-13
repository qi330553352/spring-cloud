package com.example.qixin.utils;

import org.apache.commons.lang.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创  建   时  间： 2018/5/9 22:50
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
@Log4j2
public class DateUtil extends DateUtils {

    public static Date shortTime(String str, String pattern){
        if(StringUtils.isEmpty(str)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            log.error("将{}转换成时间对象出错了！",str);
            e.printStackTrace();
        }
        return null;
    }
}
