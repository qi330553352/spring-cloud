package com.example.qixin.design.factory;

import java.util.Map;

/**
 * 创  建   时  间： 2018/8/22 22:55
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public interface JsonParser {

    Map<String, Object> parseMap(String var1);

    String formatMap(Map<String, ?> var1);
}
