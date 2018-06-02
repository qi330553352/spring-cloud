package com.example.qixin.utils.weChat;

import com.example.qixin.utils.sign.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 创  建   时  间： 2018/6/2 14:39
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class OrderUtil {

    static Date today = new Date();
    static int orderIndex = 0;

    @SuppressWarnings("deprecation")
    private static String getIndex() {

        Date n = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currTime = outFormat.format(n);

        if (orderIndex > 0) {
            if (n.getYear() == today.getYear() && n.getMonth() == today.getMonth() && n.getDay() == today.getDay()) {
                orderIndex += 1;
            } else {
                today = n;
                orderIndex = 1;
            }
        } else {
            today = n;
            orderIndex = 1;
        }
        if (orderIndex > 999999) {
            orderIndex = 1;
        }
        String indexString = String.format("%s%06d", currTime, orderIndex);
        return indexString;
    }

    /**
     * 生成订单号
     *
     * @param preFixString
     * @return
     */
    public static String GetOrderNumber(String preFixString) {
        String orderNumberString = preFixString + getIndex();
        return orderNumberString;
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String GetTimestamp() {
        return Long.toString(new Date().getTime() / 1000);
    }

    /**
     * 生成随机数
     *
     * @return
     */
    public static String CreateNoncestr() {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }

}
