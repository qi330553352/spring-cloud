package com.example.qixin.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 创  建   时  间： 2018/10/13 13:21
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class BigNumberUtil {

    public static String format(BigDecimal b1, int scale) {
        String format = validate(scale);
        b1 = b1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        DecimalFormat mformat = new DecimalFormat(format);
        return mformat.format(b1);
    }

    /**
     * 公用部分
     * @param scale 位数
     * @return 结果
     */
    static String validate(int scale) {
        String m_strFormat = "##0";
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (scale > 0) {
            m_strFormat += ".";
            for (int i = 0; i < scale; i++) {
                m_strFormat += "0";
            }
        }
        return m_strFormat;
    }
}
