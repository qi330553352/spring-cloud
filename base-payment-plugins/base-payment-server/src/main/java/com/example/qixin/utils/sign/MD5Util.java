package com.example.qixin.utils.sign;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/** createMD5Sign创建签名MD5Util
 * 创  建   时  间： 2018/6/2 13:28
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            str.append(byteToHexString(b[i]));
        return str.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String str = null;
        try {
            str = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (StringUtils.isEmpty(charsetname))
                str = byteArrayToHexString(md.digest(str.getBytes()));
            else
                str = byteArrayToHexString(md.digest(str.getBytes(charsetname)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
