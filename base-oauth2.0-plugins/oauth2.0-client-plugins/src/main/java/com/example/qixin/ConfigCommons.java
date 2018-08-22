package com.example.qixin;

/**
 * 创  建   时  间： 2018/7/30 23:43
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class ConfigCommons {

    // 为防止CSRF跨站攻击，每次请求STATE的值应该不同，可以放入Session！
    // 由于都是localhost测试，所以session无法保持，用一个固定值。
    public static final String STATE = "rensanning";


}
