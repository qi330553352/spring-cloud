package com.example.qixin.utils.weChat;

import com.example.qixin.utils.WeChatConfig;
import com.example.qixin.utils.sign.Sha1Util;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.jdom2.JDOMException;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/** 微信下单工具
 * 创  建   时  间： 2018/6/2 14:41
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
public class WXUnifiedOrderUtil {

    /**
     * @Title: appUnifiedorder
     * @Description: APP下单
     * @author pj
     * @date 2016年8月18日 下午3:37:23
     * @param notifyUrl
     * @param productName
     * @param orderNum
     * @param price
     * @param ipLocal
     * @return
     */
    public static Map<String,Object> appUnifiedorder(String mch_id, String appId, String notifyUrl, String productName, String orderNum, Double price, String ipLocal){
        log.error("微信APP下单开始");
        Map<String,Object> map = new HashMap<String, Object>();
        //金额转为分单位
        Double expandPrice = price*100;
        //转int类型
        Integer totalFee = expandPrice.intValue();
        TreeMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid",appId);
        packageParams.put("mch_id",mch_id);//设置商户号
        packageParams.put("nonce_str",Sha1Util.getNonceStr());
        packageParams.put("body", productName); //商品描述
        packageParams.put("out_trade_no", orderNum); //商户订单号
        packageParams.put("fee_type","CNY"); 	  //银行币种
        packageParams.put("total_fee", totalFee); //商品总金额,以分为单位
        packageParams.put("spbill_create_ip",ipLocal); //订单生成的机器IP，指用户浏览器端IP
        packageParams.put("notify_url",notifyUrl); //通知地址
        packageParams.put("trade_type", "APP");  //交易类型
        try {
            //调用获取订单接口，并返回相关参数
            Map<String,Object> payMap = WXPrepay.submitXmlGetPrepayId(packageParams);
            String appid = payMap.get("appid").toString();
            String nonce_str = payMap.get("nonce_str").toString();
            String prepay_id = payMap.get("prepay_id").toString();
            String sign = "";
            String timeStamp = Sha1Util.getTimeStamp();
            //拼接自定义参数，并生成支付签名
            SortedMap<Object, Object> signParams = new TreeMap<Object, Object>();
            signParams.put("appid",appId);
            signParams.put("partnerid", mch_id);
            signParams.put("prepayid", prepay_id);
            signParams.put("package", "Sign=WXPay");
            signParams.put("noncestr", nonce_str);
            signParams.put("timestamp", timeStamp);
            sign = Sha1Util.createMD5Sign(signParams);
            //返回调用支付api所用的数据
            map.put("appId", appid);
            map.put("partnerId", mch_id);
            map.put("prepayId", prepay_id);
            map.put("packages", "Sign=WXPay");
            map.put("nonceStr", nonce_str);
            map.put("timeStamp", timeStamp);
            map.put("paySign", sign);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信APP下单错误："+e);
        }

        log.error("微信APP下单结束");
        return map;
    }



    /**
     * @Title: unifiedOrder
     * @Description: 微信公众号下单
     * @author pj
     * @date 2016年8月18日 下午3:36:43
     * @param notifyUrl
     * @param productName
     * @param orderNum
     * @param price
     * @param ipLocal
     * @param openId 微信粉丝的公众号ID
     * @return
     */
    public static Map<String,Object> OfficialAccountsUnifiedOrder(String notifyUrl,String productName,String orderNum,Double price,String ipLocal,String openId){
        log.error("微信公众账号下单开始");
        Map<String,Object> map = new HashMap<String, Object>();
        //金额转为分单位
        Double expandPrice = price*100;
        //转int类型
        Integer totalFee = expandPrice.intValue();
        TreeMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", WeChatConfig.APP_ID);
        packageParams.put("mch_id",WeChatConfig.PARTNER);//设置商户号
        packageParams.put("nonce_str", Sha1Util.getNonceStr());
        packageParams.put("out_trade_no", orderNum); //商户订单号
        packageParams.put("body", productName); //商品描述
        packageParams.put("fee_type","CNY"); 	  //银行币种
        packageParams.put("total_fee", totalFee); //商品总金额,以分为单位
        packageParams.put("spbill_create_ip",ipLocal); //订单生成的机器IP，指用户浏览器端IP
        packageParams.put("notify_url",notifyUrl); //通知地址
        packageParams.put("trade_type", "JSAPI");  //交易类型
        packageParams.put("openid", openId);
        try {
            //调用获取订单接口，并返回相关参数
            Map<String,Object> payMap = WXPrepay.submitXmlGetPrepayId(packageParams);
            String appid = payMap.get("appid").toString();
            String nonce_str = payMap.get("nonce_str").toString();
            String prepay_id = payMap.get("prepay_id").toString();
            String sign = "";
            String timeStamp = Sha1Util.getTimeStamp();
            //拼接自定义参数，并生成支付签名
            SortedMap<Object, Object> signParams = new TreeMap<Object, Object>();
            signParams.put("appId", appid);
            signParams.put("timeStamp", timeStamp);
            signParams.put("nonceStr", nonce_str);
            signParams.put("package", "prepay_id="+prepay_id);
            signParams.put("signType", "MD5");
            sign = Sha1Util.createMD5Sign(signParams);
            //返回调用支付api所用的数据
            map.put("appId", appid);
            map.put("timeStamp", timeStamp);
            map.put("nonceStr", nonce_str);
            map.put("packages", "prepay_id="+prepay_id);
            map.put("prepayId", prepay_id);
            map.put("paySign", sign);
            map.put("signType", "MD5");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信公众账号下单错误："+e);
        }
        log.error("微信公众账号下单结束");
        return map;
    }


    /**
     * @Title: getQrocdePay
     * @Description:二维码支付
     * @author pj
     * @date 2016年8月11日 下午2:24:13
     * @param orderNum
     * @param price
     * @param ipLocal
     * @return
     * @throws Exception
     */
    public static String getQrocdePay(String notifyUrl,String productName,String orderNum,Double price,String ipLocal) throws Exception{
        log.error("微信pc下单开始");
        String url = "";
        //金额转为分单位
        Double expandPrice = price*100;
        //转int类型
        Integer totalFee = expandPrice.intValue();
        //生成签名
        String timeStamp = Sha1Util.getTimeStamp();
        TreeMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid",WeChatConfig.APP_ID);
        packageParams.put("mch_id",WeChatConfig.PARTNER);//设置商户号
        packageParams.put("nonce_str",Sha1Util.getNonceStr());
        packageParams.put("time_stamp",timeStamp);//时间戳
        packageParams.put("out_trade_no", orderNum); //商户订单号
        packageParams.put("body", productName); //商品描述
        packageParams.put("fee_type","CNY"); 	  //银行币种
        packageParams.put("total_fee", totalFee); //商品总金额,以分为单位
        packageParams.put("spbill_create_ip",ipLocal); //订单生成的机器IP，指用户浏览器端IP
        packageParams.put("notify_url", notifyUrl); //通知地址
        packageParams.put("trade_type", "NATIVE");  //交易类型 JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
        //调用获取订单接口，并返回相关参数
        try {
            String str = WXPrepay.QRcodePay(packageParams);
            if(str!=null){
                url = TenpayUtil.QRfromOSchina(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信PC下单错误："+e);
        }
        log.error("微信pc下单结束");
        return url;
    }

    /**
     * @Title: refund
     * @Description: pc退款
     * @author pj
     * @date 2016年9月19日 下午6:23:33
     * @param outTradeNo 商户订单号
     * @param transactionId 微信订单号
     * @param price 产品金额
     * @param refundPrice 退款金额
     * @param ipLocal
     * @return
     */
    public static Boolean refund(String outTradeNo,String transactionId,Double price,Double refundPrice,String ipLocal){
        log.error("微信退款开始");
        Boolean flg = false;
        Map<Object, Object> map = new HashMap<>();
        //金额转为分单位
        Double t_expandPrice = price*100;
        Double r_expandPrice = refundPrice*100;
        //转int类型
        Integer refund_fee = r_expandPrice.intValue();
        Integer total_fee = t_expandPrice.intValue();
        //当前时间 yyyyMMddHHmmss
        String currTime = TenpayUtil.getCurrTime();
        //8位日期
        String strTime = currTime.substring(8, currTime.length());
        //四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        //10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        //退款单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
        String out_refund_no = strReq;
        JSONObject obj = new JSONObject();
        TreeMap<Object, Object> packageParams = new TreeMap<>();
        packageParams.put("appid",WeChatConfig.APP_ID);
        packageParams.put("mch_id",WeChatConfig.PARTNER);//设置商户号
        packageParams.put("nonce_str",Sha1Util.getNonceStr());
        packageParams.put("transaction_id", transactionId); //微信订单号
        //packageParams.put("out_trade_no", outTradeNo); //商户订单号
        packageParams.put("out_refund_no", out_refund_no); //商户退款单号
        packageParams.put("total_fee", total_fee); //商品总金额,以分为单位
        packageParams.put("refund_fee",refund_fee); //退款金额
        packageParams.put("refund_fee_type","CNY"); 	  //银行币种
        packageParams.put("op_user_id", WeChatConfig.PARTNER);//操作员帐号, 默认为商户号
        try {
            //本地或者服务器的证书位置（证书在微信支付申请成功发来的通知邮件中）
            String path = new WXUnifiedOrderUtil().getClass().getResource("weChat/security/pc/apiclient_cert.p12").getPath();
            map = WXPrepay.reqOrderquery(packageParams,path,WeChatConfig.PARTNER);
            String return_code = map.get("return_code").toString();
            String return_msg = map.get("return_msg").toString();
            if(return_code.equals("SUCCESS")){
                String result_code = map.get("result_code").toString();
                if(result_code.equals("SUCCESS")){
                    log.error("微信退款成功");
                    flg = true;
                }
            }else{
                obj.put("return_msg", return_msg);
                log.error("微信退款失败，错误信息："+return_msg);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
            log.error("退款错误："+e);
        }
        log.error("微信退款结束");
        return flg;
    }

    /**
     * @Title: closeOrder
     * @Description: 取消订单
     * @author pj
     * @date 2016年8月23日 下午5:29:46
     * @param outTradeNo
     * @return
     */
    public static Map<String, Object> closeOrder(String outTradeNo){
        String timeStamp = Sha1Util.getTimeStamp();
        TreeMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid",WeChatConfig.APP_ID);
        packageParams.put("mch_id",WeChatConfig.PARTNER);//设置商户号
        packageParams.put("nonce_str",Sha1Util.getNonceStr());
        packageParams.put("time_stamp",timeStamp);//时间戳
        packageParams.put("out_trade_no", outTradeNo); //商户订单号
        //调用获取订单接口，并返回相关参数
        try {
            Map<String, Object> map = WXPrepay.closeorder(packageParams);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
