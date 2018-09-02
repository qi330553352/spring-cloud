//package com.example.qixin.design.factory;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.Map;
//
///**
// * 创  建   时  间： 2018/8/22 23:07
// * 版           本: V1.0
// * 作           者: qixin
// * 版  权   所  有: 版权所有(C)2016-2026
// */
//public class Jackson2JsonParser implements JsonParser {
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    public Jackson2JsonParser() {
//    }
//
//    @Override
//    public Map<String, Object> parseMap(String json) {
//        try {
//            return (Map)this.mapper.readValue(json, Map.class);
//        } catch (Exception var3) {
//            throw new IllegalArgumentException("Cannot parse json", var3);
//        }
//    }
//
//    @Override
//    public String formatMap(Map<String, ?> map) {
//        try {
//            return this.mapper.writeValueAsString(map);
//        } catch (Exception var3) {
//            throw new IllegalArgumentException("Cannot format json", var3);
//        }
//    }
//}
