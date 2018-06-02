package com.example.qixin.utils.weChat;

import com.example.qixin.utils.sign.Sha1Util;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom2.JDOMException;
import org.jsoup.Jsoup;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/** 微信订单生成工具
 * 创  建   时  间： 2018/6/2 14:40
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 * 公           司: 广州专利保姆有限公司
 */
public class WXPrepay {

    /**
     * 统一下单地址
     */
    private static String unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 查询订单地址
     */
    private static String orderquery = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     *退款接口地址
     */
    private static String refund = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**取消订单*/
    private static String closeorder ="https://api.mch.weixin.qq.com/pay/closeorder";


    /**
     * @Title: submitXmlGetPrepayId
     * @Description: 生成预支付订单
     * @author pj
     * @date 2016年8月11日 下午5:40:15
     * @param packageParams
     * @return
     */
    public static Map<String,Object> submitXmlGetPrepayId(TreeMap<Object, Object> packageParams) {
        Map<String,Object> map = new HashMap<String, Object>();
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(WXPrepay.unifiedorder);
        String xml = getPackage(packageParams);
        StringEntity entity;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(result);
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                String returnCode = Jsoup.parse(result).select("return_code").html();
                map.put("returnCode", returnCode);
                if(returnCode.equals("SUCCESS")){
                    String appid = Jsoup.parse(result).select("appid").html();
                    String device_info = Jsoup.parse(result).select("device_info").html();
                    String mch_id = Jsoup.parse(result).select("mch_id").html();
                    String nonce_str = Jsoup.parse(result).select("nonce_str").html();
                    String sign = Jsoup.parse(result).select("sign").html();
                    String result_code = Jsoup.parse(result).select("result_code").html();
                    map.put("appid", appid);
                    map.put("device_info", device_info);
                    map.put("mch_id", mch_id);
                    map.put("nonce_str", nonce_str);
                    map.put("sign", sign);
                    map.put("result_code", result_code);
                    if(result_code.equals("SUCCESS")){
                        String prepay_id = Jsoup.parse(result).select("prepay_id").html();
                        String trade_type = Jsoup.parse(result).select("trade_type").html();
                        map.put("prepay_id", prepay_id);
                        map.put("trade_type", trade_type);
                    }
                }
                return map;
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @Title: closeorder
     * @Description: 订单关闭
     * @author pj
     * @date 2016年9月21日 下午7:52:10
     * @param packageParams
     * @return
     */
    public static Map<String, Object> closeorder(TreeMap<Object, Object> packageParams){
        Map<String,Object> map = new HashMap<String, Object>();
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(WXPrepay.closeorder);
        String xml = getPackage(packageParams);
        StringEntity entity;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(result);
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                String returnCode = Jsoup.parse(result).select("return_code").html();
                map.put("returnCode", returnCode);
                if(returnCode.equals("SUCCESS")){
                    String appid = Jsoup.parse(result).select("appid").html();
                    String mch_id = Jsoup.parse(result).select("mch_id").html();
                    String nonce_str = Jsoup.parse(result).select("nonce_str").html();
                    String sign = Jsoup.parse(result).select("sign").html();
                    String result_code = Jsoup.parse(result).select("result_code").html();
                    String result_msg = Jsoup.parse(result).select("result_msg").html();
                    String err_code = Jsoup.parse(result).select("err_code").html();
                    String err_code_des = Jsoup.parse(result).select("err_code_des").html();
                    map.put("appid", appid);
                    map.put("mch_id", mch_id);
                    map.put("nonce_str", nonce_str);
                    map.put("sign", sign);
                    map.put("result_code", result_code);
                    map.put("result_msg", result_msg);
                    map.put("err_code", err_code);
                    map.put("err_code_des",err_code_des);
                }
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @Title: refundService
     * @Description: 请求订单查询接口
     * @author pj
     * @date 2016年8月11日 下午5:40:43
     * @return
     * @throws JDOMException
     */
    @SuppressWarnings("unchecked")
    public static Map<Object, Object> refundService() throws JDOMException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(orderquery);
        TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();
        String xml = getPackage(treeMap);
        StringEntity entity;
        Map<Object, Object> map = null;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            // getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                map = XMLUtil.doXMLParse(result);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @Title: reqOrderquery
     * @Description: 退款接口
     * @author pj
     * @date 2016年8月11日 下午5:40:54
     * @param treeMap
     * @return
     * @throws JDOMException
     */
    @SuppressWarnings("unchecked")
    public static Map<Object, Object> reqOrderquery(TreeMap<Object, Object> treeMap,String certPath,String password) throws JDOMException {
        String xml = getPackage(treeMap);
        StringEntity entity;
        Map<Object, Object> map = null;
        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            FileInputStream is = new FileInputStream(new File(certPath));
            //私钥（在安装证书时设置）
            char[] partner = password.toCharArray();
            try {
                keyStore.load(is, partner);
            } finally {
                is.close();
            }
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, partner).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf) .build();
            HttpPost httpost = new HttpPost(refund);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            entity = new StringEntity(xml, "utf-8");
            httpost.setEntity(entity);
//			 EntityUtils.consume(entity);
            CloseableHttpResponse httpResponse;
            // post请求
            httpResponse = httpclient.execute(httpost);
            // getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                map = XMLUtil.doXMLParse(result);
            }
            // 释放资源
            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @Title: getPackage
     * @Description: 根据传入的参数生成xml格式的字符串
     * @author pj
     * @date 2016年8月11日 下午5:44:35
     * @param map
     * @return
     */
    public static String getPackage(TreeMap<Object, Object> map) {
        StringBuilder sb = new StringBuilder();
        String sign;
        try {
            //签名信息
            sign = Sha1Util.createMD5Sign(map);
            map.put("sign", sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object key : map.keySet()) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
                xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue()).append("]]></" + entry.getKey() + ">\n");
//				xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
            } else {
                xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
            }
        }
        xml.append("</xml>");
        System.out.println(xml.toString());
        return xml.toString();
    }

    /**
     * @Title: QRcodePay
     * @Description: 获取微信支付二维码
     * @author pj
     * @date 2016年8月11日 下午5:45:27
     * @param packageParams
     * @return
     * @throws Exception
     */
    public static String QRcodePay(TreeMap<Object,Object> packageParams) throws Exception {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(WXPrepay.unifiedorder);
        //map对象转XML，同时对信息进行签名
        String xml = getPackage(packageParams);
        StringEntity entity;
        try {
            entity = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                //System.out.println(result);
                // 过滤
                result = result.replaceAll("<![CDATA[|]]>", "");
                String code_url = Jsoup.parse(result).select("code_url").html();
                return code_url;
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
