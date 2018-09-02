//package com.example.qixin.design.factory;
//
//import org.springframework.util.ClassUtils;
//
///** 工厂设计模式
// * 创  建   时  间： 2018/8/22 23:09
// * 版           本: V1.0
// * 作           者: qixin
// * 版  权   所  有: 版权所有(C)2016-2026
// */
//public class JsonParserFactory {
//
//    public JsonParserFactory() {
//
//    }
//
//    public static JsonParser create(){
//        if(ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper",(ClassLoader)null)){
//            return new Jackson2JsonParser();
//        }else if(ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper",(ClassLoader)null)){
//            return new JacksonJsonParser();
//        }else{
//            throw new IllegalStateException("No Jackson parser found. Please add Jackson to your classpath.");
//        }
//    }
//}
