package com.example.qixin.utils.excel;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 创  建   时  间： 2018/10/13 13:15
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class InstallTool {

    /**
     * 设置标题样式、内容等
     * @param book 文件对象
     * @param sheet sheet页
     * @param fileName 标题内容
     * @param witch 标题占几个单元格的宽度
     * @param currentUser 当前用户
     * @author qixin
     */
    static void fitTitle(HSSFWorkbook book, HSSFSheet sheet, String fileName, int witch, String currentUser) {
        HSSFFont titleFont = book.createFont();
        titleFont.setFontHeightInPoints((short)24);
        titleFont.setFontName("SimHei");

        HSSFCellStyle titleCellStyle = book.createCellStyle();
        titleCellStyle = (HSSFCellStyle) CellStyleTool.setDefaultCellStyle(titleCellStyle);
        titleCellStyle.setFont(titleFont);

        Row row = sheet.createRow(0);
        Cell title = row.createCell(0);
        title.setCellValue(fileName);
        title.setCellStyle(titleCellStyle);
        CellRangeAddress rangeAddress = new CellRangeAddress(0,3,0,witch);
        sheet.addMergedRegion(rangeAddress);
        rangeAddress = new CellRangeAddress(4,4,0,witch);
        sheet.addMergedRegion(rangeAddress);
        row = sheet.createRow(4);
        title = row.createCell(0);
        title.setCellValue("制表人："+currentUser);
    }

    /**
     * 设置标题样式、内容等
     * @param book 文件对象
     * @param sheet sheet页
     * @param fileName 标题内容
     * @param witch 标题占几个单元格的宽度
     * @author qixin
     */
    static void fitTitle(HSSFWorkbook book, HSSFSheet sheet, String fileName, int witch) {
        HSSFFont titleFont = book.createFont();
        titleFont.setFontHeightInPoints((short)24);
        titleFont.setFontName("SimHei");

        HSSFCellStyle titleCellStyle = book.createCellStyle();
        titleCellStyle = (HSSFCellStyle) CellStyleTool.setDefaultCellStyle(titleCellStyle);
        titleCellStyle.setFont(titleFont);

        Row row = sheet.createRow(0);
        Cell title = row.createCell(0);
        title.setCellValue(fileName);
        title.setCellStyle(titleCellStyle);
        CellRangeAddress rangeAddress = new CellRangeAddress(0,3,0,witch);
        sheet.addMergedRegion(rangeAddress);
    }

    static void fitInfos(HSSFWorkbook book, HSSFSheet sheet, List<LinkedHashMap<String, String>> treeMapList, Map<String, Object> treeMapData) {
        WriteExcelUtil.CREATE_ROW_NUMBER = 3;
        HSSFFont infoFont = book.createFont();
        infoFont.setFontHeightInPoints((short)11);
        CellStyle infoCellStyle = book.createCellStyle();
        infoCellStyle.setFont(infoFont);
        for(LinkedHashMap<String,String> treeMap : treeMapList){
            Row row = sheet.createRow(++WriteExcelUtil.CREATE_ROW_NUMBER);
            Set<String> treeSet = treeMap.keySet();
            String[] attrKey = treeSet.toArray(new String[treeSet.size()]);
            for(int i=0; i<treeSet.size(); i++){
                Cell title = row.createCell(i*3);
                String key = attrKey[i];
                title.setCellValue(key);
                title.setCellStyle(infoCellStyle);

                String attribute = treeMap.get(key);
                title = row.createCell((i*3)+1);
                title.setCellStyle(infoCellStyle);
                Object value = treeMapData.get(attribute);
                if(value==null) continue;
                String simpleClassName = value.getClass().getSimpleName();
                if(simpleClassName.equals("Timestamp")){
                    title.setCellValue(DateFormatUtils.format((Date)value,"yyyy-MM-dd HH:mm:ss"));
                }else{
                    title.setCellValue((String) value);
                }
            }
        }
    }



    /**
     * 设置表头样式、内容等
     * @param book 文件对象
     * @param sheet sheet页
     * @param headers 内容
     * @author qixin
     */
    static void fitHeader(HSSFWorkbook book, HSSFSheet sheet, String[] headers) {
        HSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short)11);
        headerFont.setFontName("SimHei");
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        CellStyle headerCellStyle = book.createCellStyle();
        headerCellStyle = CellStyleTool.setDefaultCellStyle((HSSFCellStyle) headerCellStyle);
        headerCellStyle.setFont(headerFont);

        Row header = sheet.createRow(++WriteExcelUtil.CREATE_ROW_NUMBER);
        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerCellStyle);
        }
    }

    /**
     * 设置数据内容样式、内容等
     * @param book 文件对象
     * @param sheet sheet页
     * @param withProperties 数据属性名称
     * @param dataLists 数据
     * @author qixin
     */
    static void fitContent(HSSFWorkbook book, HSSFSheet sheet, String[] withProperties, List<Map<String,Object>> dataLists) {
        CreationHelper ch = book.getCreationHelper();
        CellStyle dateStyle = book.createCellStyle();
        dateStyle = CellStyleTool.setDefaultCellStyle((HSSFCellStyle) dateStyle);
        dateStyle.setDataFormat(ch.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

        HSSFFont dataFont = book.createFont();
        dataFont.setFontHeightInPoints((short)12);
        dataFont.setFontName("SimSun");

        CellStyle dataCellStyle = book.createCellStyle();
        dataCellStyle = CellStyleTool.setDefaultCellStyle((HSSFCellStyle) dataCellStyle);
        dataCellStyle.setFont(dataFont);

        for (int i = 1; i < dataLists.size()+1; i++) {
            Row dataRow = sheet.createRow(++WriteExcelUtil.CREATE_ROW_NUMBER);
            for (int j = 0; j < withProperties.length; j++) {
                String attrName = withProperties[j];
                Map<String,Object> groovyRowResult = dataLists.get(i-1);
                Object value = groovyRowResult.get(attrName);
                Class<?> clazz = groovyRowResult.get(attrName).getClass();
                String simpleClassName = clazz.getSimpleName();

                Cell cell = dataRow.createCell(j);
                if(simpleClassName.equals("Timestamp"))
                    cell.setCellStyle(dateStyle);
                else
                    cell.setCellStyle(dataCellStyle);
                if(value instanceof String)
                    cell.setCellValue((String) value);
                else
                    cell.setCellValue(value.toString());
            }
        }

        HSSFRow row = sheet.createRow(5+dataLists.size()+1);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("制表时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 设置数据内容样式、内容等
     * @param book 文件对象
     * @param sheet sheet页
     * @param withProperties 数据属性名称
     * @param dataLists 数据
     * @param statistics 统计信息 (如：共%s种商品，合计拣货商品价值：%s 【默认合计最后一列数据】)
     * @author qixin
     */
    static void fitContent(HSSFWorkbook book, HSSFSheet sheet, String[] withProperties, List<Map<String,Object>> dataLists, String statistics) {
        CreationHelper ch = book.getCreationHelper();
        CellStyle dateStyle = book.createCellStyle();
        dateStyle = CellStyleTool.setDefaultCellStyle((HSSFCellStyle) dateStyle);
        dateStyle.setDataFormat(ch.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

        HSSFFont dataFont = book.createFont();
        dataFont.setFontHeightInPoints((short)11);
        dataFont.setFontName("SimSun");

        CellStyle dataCellStyle = book.createCellStyle();
        dataCellStyle = CellStyleTool.setDefaultCellStyle((HSSFCellStyle) dataCellStyle);
        dataCellStyle.setFont(dataFont);

        double statistic = 0.00;    //统计数据
        for (int i = 1; i < dataLists.size()+1; i++) {
            Row dataRow = sheet.createRow(++WriteExcelUtil.CREATE_ROW_NUMBER);
            for (int j = 0; j < withProperties.length; j++) {
                String attrName = withProperties[j];
                Map<String,Object> groovyRowResult = dataLists.get(i-1);
                Object value = groovyRowResult.get(attrName);
                if(j==(withProperties.length-1) && value instanceof Number)
                    statistic +=((Number) value).doubleValue();
                Class<?> clazz = groovyRowResult.get(attrName).getClass();
                String simpleClassName = clazz.getSimpleName();

                Cell cell = dataRow.createCell(j);
                if(simpleClassName.equals("Timestamp"))
                    cell.setCellStyle(dateStyle);
                else
                    cell.setCellStyle(dataCellStyle);
                if(value instanceof String)
                    cell.setCellValue((String) value);
                else
                    cell.setCellValue(value.toString());
            }
        }

        dataCellStyle = book.createCellStyle();
        dataCellStyle = CellStyleTool.rightCellStyle((HSSFCellStyle) dataCellStyle);
        dataCellStyle.setFont(dataFont);

        HSSFRow row = sheet.createRow(++WriteExcelUtil.CREATE_ROW_NUMBER);
        for (int i = 0; i < withProperties.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(dataCellStyle);
        }
        row.getCell(0).setCellValue(String.format(statistics,dataLists.size(), NumberFormat.getCurrencyInstance().format(statistic)));
        CellRangeAddress rangeAddress = new CellRangeAddress(row.getRowNum(),row.getRowNum(),0,withProperties.length-1);
        sheet.addMergedRegion(rangeAddress);
    }







    /**云八详情库存导出
     * 设置标题样式、内容等
     * @param book 文件对象
     * @param sheet sheet页
     * @param fileName 标题内容
     * @param witch 标题占几个单元格的宽度
     * @param currentUser 当前用户
     * @author qixin
     */
    public static void setTitle(HSSFWorkbook book, HSSFSheet sheet, String fileName, int witch, String currentUser, String cloud8Name, String address) {
        HSSFFont titleFont = book.createFont();
        titleFont.setFontHeightInPoints((short)24);
        titleFont.setFontName("SimHei");

        HSSFCellStyle titleCellStyle = book.createCellStyle();
        titleCellStyle = (HSSFCellStyle) CellStyleTool.setDefaultCellStyle(titleCellStyle);
        titleCellStyle.setFont(titleFont);

        Row row = sheet.createRow(0);
        Cell title = row.createCell(0);
        title.setCellValue(fileName);
        title.setCellStyle(titleCellStyle);
        CellRangeAddress rangeAddress = new CellRangeAddress(0,1,0,witch);
        sheet.addMergedRegion(rangeAddress);

        row = sheet.createRow(2);
        title = row.createCell(0);
        title.setCellValue("制单时间： "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        rangeAddress = new CellRangeAddress(0,0,0,1);
        sheet.addMergedRegion(rangeAddress);
        title = row.createCell(3);
        title.setCellValue("制单员： "+currentUser);
        rangeAddress = new CellRangeAddress(2,2,3,4);
        sheet.addMergedRegion(rangeAddress);

        row = sheet.createRow(3);
        title = row.createCell(0);
        title.setCellValue("店面名称： "+cloud8Name);
        rangeAddress = new CellRangeAddress(3,3,0,1);
        sheet.addMergedRegion(rangeAddress);

        row = sheet.createRow(4);
        title = row.createCell(0);
        title.setCellValue("店面地址： "+address);
        rangeAddress = new CellRangeAddress(4,4,0,1);
        sheet.addMergedRegion(rangeAddress);
    }

    /**云八详情库存导出
     * 设置表头样式、内容等
     * @param book 文件对象
     * @param sheet sheet页
     * @param headers 内容
     * @author liyanli
     */
    public static void setHeader(HSSFWorkbook book, HSSFSheet sheet, String[] headers, int rowNum) {
        HSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short)12);
        headerFont.setFontName("SimHei");
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle headerCellStyle = book.createCellStyle();
        headerCellStyle = CellStyleTool.setDefaultCellStyle((HSSFCellStyle) headerCellStyle);
        headerCellStyle.setFont(headerFont);
        sheet.setColumnWidth(0,6000);
        sheet.setColumnWidth(1,6000);
        sheet.setColumnWidth(2,6000);
        sheet.setColumnWidth(3,6000);
        sheet.setColumnWidth(4,6000);
        Row header = sheet.createRow(rowNum);
        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerCellStyle);
        }
    }

    static void fitRemarks(HSSFSheet sheet, String[] remarks) {
        Row row = sheet.createRow(++WriteExcelUtil.CREATE_ROW_NUMBER);
        Cell title = row.createCell(0);
        title.setCellValue("说明：");
        for(String str : remarks){
            row = sheet.createRow(++WriteExcelUtil.CREATE_ROW_NUMBER);
            title = row.createCell(0);
            title.setCellValue("　　"+str);
        }
    }
}
