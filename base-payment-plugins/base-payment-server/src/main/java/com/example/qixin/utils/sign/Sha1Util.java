package com.example.qixin.utils.sign;

import com.example.qixin.utils.WeChatConfig;

import java.security.MessageDigest;
import java.util.*;

/** createSHA1Sign创建签名SHA1
 * 创  建   时  间： 2018/6/2 13:32
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class Sha1Util {

    /**
     * @Title: getNonceStr
     * @Description: 获取随机字符串
     * @date 2016年8月11日 下午5:38:22
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
    }
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * @Title: createMD5Sign
     * @Description: 创建签名MD5签名
     * @date 2016年8月11日 下午5:38:41
     * @param parameters
     * @return
     * @throws Exception
     */
    public static String createMD5Sign(SortedMap<Object, Object> parameters) throws Exception {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=").append(WeChatConfig.PARTNER_KEY);
        String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase();
        return sign;
    }

    /**
     * @Title: getSha1
     * @Description: 创建Sha1签名
     * @date 2016年8月11日 下午5:38:54
     * @param str
     * @return
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("GBK"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
