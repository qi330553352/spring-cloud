package com.example.qixin.utils.excel;

import com.example.qixin.utils.BigNumberUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 创  建   时  间： 2018/10/13 12:46
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
public class WriteExcelUtil {

    static Integer CREATE_ROW_NUMBER = 0;
    private static final String companyName = "七智星科技有限公司";

    public WriteExcelUtil() {
        CREATE_ROW_NUMBER = 4;
    }

    /**
     * 文件导出
     * @param fileName 文件、标题名 (*不加文件后缀)
     * @param headers 表头
     * @param withProperties 数据属性名称 (*与表头顺序一至)
     * @param dataLists 数据 (*别名与数据属性一至)
     * @author qixin
     */
    public static void export(String fileName, String[] headers, String[] withProperties, List<Map<String,Object>> dataLists, HttpServletResponse response){

        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("Sheet1");
        sheet.setDefaultColumnWidth(2*sheet.getDefaultColumnWidth());

        fitTitle(book, sheet, fileName, headers.length-1);  //设置标题
        fitHeader(book, sheet, headers);                     //设置表头
        fitContent(book, sheet, withProperties, dataLists);  //设置内容
        StreamTool.fitStream(fileName, book, response);                 //设置输出流
    }

    /**
     * 文件导出
     * @param fileName 文件、标题名 (*不加文件后缀)
     * @param headers 表头
     * @param withProperties 数据属性名称 (*与表头顺序一至)
     * @param dataLists 数据 (*别名与数据属性一至)
     * @param currentUser 当前用户
     * @author qixin
     */
    public void export(String fileName, String[] headers, String[] withProperties, List<Map<String,Object>> dataLists, String currentUser, HttpServletResponse response){

        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("Sheet1");
        sheet.setDefaultColumnWidth(2*sheet.getDefaultColumnWidth());

        InstallTool.fitTitle(book, sheet, fileName, headers.length-1, currentUser);  //设置标题
        InstallTool.fitHeader(book, sheet, headers);                     //设置表头
        InstallTool.fitContent(book, sheet, withProperties, dataLists);  //设置内容
        StreamTool.fitStream(fileName, book, response);                 //设置输出流
    }

    /**
     * 文件导出
     * @param fileName 文件、标题名 (*不加文件后缀)
     * @param headers 表头
     * @param treeMapList 自定义表格上面信息
     * @param treeMapData 自定义表格上面信息数据
     * @param withProperties 数据属性名称 (*与表头顺序一至)
     * @param dataLists 数据 (*别名与数据属性一至)
     * @param statistics 统计信息 (如：共%s种商品，合计拣货商品价值：%s 【默认合计最后一列数据】)
     * @param remarks 备注、说明
     * @param response 响应请求
     * @author qixin
     */
    public void export(String fileName, String[] headers, List<LinkedHashMap<String,String>> treeMapList, Map<String,Object> treeMapData, String[] withProperties, List<Map<String,Object>> dataLists, String statistics, String[] remarks, HttpServletResponse response){
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("Sheet1");
        sheet.setDefaultColumnWidth(2*sheet.getDefaultColumnWidth());

        InstallTool.fitTitle(book, sheet, fileName, headers.length-1);  //设置标题
        InstallTool.fitInfos(book,sheet,treeMapList,treeMapData);        //设置信息
        InstallTool.fitHeader(book, sheet, headers);                     //设置表头
        InstallTool.fitContent(book, sheet, withProperties, dataLists,statistics);  //设置内容
        InstallTool.fitRemarks(sheet,remarks);                           //设置备注、说明
        StreamTool.fitStream(fileName, book, response);                 //设置输出流
    }

    /**
     * 生成excel文件并返回文件访问地址
     * @param fileName 文件名称
     * @param titleName 文件标题
     * @param headers 标题
     * @param withProperties 属性
     * @param sumTotal 合计属性
     * @param dataLists 数据集合
     * @param accessAddress 显示地址
     * @param exportExcelTempPath 临时文件夹
     * @return 文件访问地址
     */
    public static String export(String fileName, String titleName, String[] headers, String[] withProperties, String[] sumTotal, List<Map<String,Object>> dataLists,String date2str, String accessAddress, String exportExcelTempPath) {
        HSSFWorkbook book = new HSSFWorkbook();
        int maxrownum = 10000;  //每个sheet页显示10000条数据

        if(dataLists.size()>maxrownum){
            Integer tagnum = (dataLists.size()/maxrownum)+1;
            for(int i=0;i<tagnum;i++){
                int start = (i*maxrownum);
                int end = (i+1)*maxrownum>dataLists.size()?dataLists.size():(i+1)*maxrownum;
                List<Map<String,Object>> sublist = dataLists.subList(start,end);
                HSSFSheet sheet = book.createSheet("Sheet"+i);
                sheet.setDefaultColumnWidth(sheet.getDefaultColumnWidth());
                fitTitle(book, sheet, fileName,titleName, headers.length-1);  //设置标题
                fitHeader(book, sheet, headers);                     //设置表头
                fitContent(book, sheet, withProperties,sumTotal, sublist);  //设置内容
            }
        }else{
            HSSFSheet sheet = book.createSheet("Sheet1");
            sheet.setDefaultColumnWidth(sheet.getDefaultColumnWidth());
            fitTitle(book, sheet, fileName,titleName, headers.length-1);  //设置标题
            fitHeader(book, sheet, headers);                     //设置表头
            fitContent(book, sheet, withProperties,sumTotal, dataLists);  //设置内容
        }
        return fitWrite(date2str,fileName,book,accessAddress,exportExcelTempPath);   //生成文件
    }

    public static String export(String fileName, String titleName, String[] headers, String[] withProperties, String[] sumTotal, List<Map<String,Object>> dataLists,String date2str, String exportExcelTempPath) {
        HSSFWorkbook book = new HSSFWorkbook();
        int maxrownum = 10000;  //每个sheet页显示10000条数据

        if(dataLists.size()>maxrownum){
            Integer tagnum = (dataLists.size()/maxrownum)+1;
            for(int i=0;i<tagnum;i++){
                int start = (i*maxrownum);
                int end = (i+1)*maxrownum>dataLists.size()?dataLists.size():(i+1)*maxrownum;
                List<Map<String,Object>> sublist = dataLists.subList(start,end);
                HSSFSheet sheet = book.createSheet("Sheet"+i);
                sheet.setDefaultColumnWidth(sheet.getDefaultColumnWidth());
                fitTitle(book, sheet, fileName,titleName, headers.length-1);  //设置标题
                fitHeader(book, sheet, headers);                     //设置表头
                fitContent(book, sheet, withProperties,sumTotal, sublist);  //设置内容
            }
        }else{
            HSSFSheet sheet = book.createSheet("Sheet1");
            sheet.setDefaultColumnWidth(sheet.getDefaultColumnWidth());
            fitTitle(book, sheet, fileName,titleName, headers.length-1);  //设置标题
            fitHeader(book, sheet, headers);                     //设置表头
            fitContent(book, sheet, withProperties,sumTotal, dataLists);  //设置内容
        }
        return fitWrite(date2str,fileName,book,exportExcelTempPath);   //生成文件
    }

    private static String fitWrite(String date2str,String fileName,HSSFWorkbook book, String accessAddress, String exportExcelTempPath) {
        String path = fitDirs(date2str,exportExcelTempPath);     //创建完整目录
        String filename = path+ File.separator+fileName + ".xls";
        try {
            String filenameEncode = accessAddress +"/"+date2str+"/"+ URLEncoder.encode(fileName, "UTF-8") + ".xls";
            OutputStream out = new FileOutputStream(new File(filename));
            book.write(out);
            out.flush();
            out.close();
            return filenameEncode;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String fitWrite(String date2str,String fileName,HSSFWorkbook book, String exportExcelTempPath) {
        String path = fitDirs(date2str,exportExcelTempPath);     //创建完整目录
        String filename = path+ File.separator+fileName + ".xls";
        try {
            OutputStream out = new FileOutputStream(new File(filename));
            book.write(out);
            out.flush();
            out.close();
            return filename;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 在指定目录下创建文件夹
     * @param exportExcelTempPath 指定地址
     * @return 完整目录
     * @author qixin
     */
    private static String fitDirs(String date2str,String exportExcelTempPath) {
        if(!exportExcelTempPath.endsWith(File.separator)){
            exportExcelTempPath = exportExcelTempPath + File.separator+date2str;
        }else{
            exportExcelTempPath = exportExcelTempPath+date2str;
        }
        File dir = new File(exportExcelTempPath);
        if (!dir.exists()) {   //如果目录不存在
            dir.mkdirs();
        }
        return exportExcelTempPath;
    }

    /**
     * 设置数据内容样式、内容等
     * @param withProperties 数据属性名称
     * @param dataLists 数据
     * @author qixin
     */
    private static void fitContent(HSSFWorkbook book, HSSFSheet sheet, String[] withProperties, List<Map<String,Object>> dataLists) {
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

        for (int i = 1; i < dataLists.size()+1; i++) {
            Row dataRow = sheet.createRow(4+i);
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
    }

    private static void fitContent(HSSFWorkbook book, HSSFSheet sheet, String[] withProperties,String[] sumTotal, List<Map<String,Object>> dataLists) {
        final int startnum = 5; //数据从哪行开始记录
        CreationHelper ch = book.getCreationHelper();
        HSSFFont dataFont = book.createFont();
        HSSFCellStyle dateStyle = book.createCellStyle();
        dateStyle = CellStyleTool.setDefaultCellStyle(dateStyle);
        dateStyle.setDataFormat(ch.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

        Map<String,Double> sumMap = new HashMap<>();
        for (int i = 1; i < dataLists.size()+1; i++) {
            Row dataRow = sheet.createRow(startnum+i);
            for (int j = 0; j < withProperties.length; j++) {
                String attrName = withProperties[j];
                Map<String,Object> groovyRowResult = dataLists.get(i-1);
                Object value = groovyRowResult.get(attrName);
                Cell cell = dataRow.createCell(j);
                cell.setCellStyle(CellStyleTool.setDefaultCellStyle(dataFont,dateStyle,"Arial",10));
                cell.setCellValue(String.valueOf(value));

                //合计：
                String sumtotal = sumTotal[j];
                if(StringUtils.isEmpty(sumtotal) || StringUtils.isEmpty(String.valueOf(value))) continue;
                if(sumtotal.equals(attrName)){
                    if(sumMap.containsKey(sumtotal)){
                        Double val = sumMap.get(attrName)+Double.valueOf(String.valueOf(value).replace(",",""));
                        sumMap.put(attrName,val);
                    }else{
                        sumMap.put(attrName,Double.valueOf(String.valueOf(value).replace(",","")));
                    }
                }
            }
        }
        //设置列宽
        int tableWidth = 0;
        for (int i = 0; i < dataLists.size(); i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) / 4 * 5);
            tableWidth += sheet.getColumnWidth(i);
        }
        if (tableWidth < 15500)
            sheet.setColumnWidth(1, 15500);
        //合计
        HSSFRow sumrow = sheet.createRow(dataLists.size()+startnum+1);
        HSSFCell sumcell = sumrow.createCell(0);
        sumcell.setCellValue("合计: "+dataLists.size()+" 条数据");
        sumcell.setCellStyle(CellStyleTool.setDefaultCellStyle(dataFont,dateStyle,"SimHei",10));
        for(int i=0;i<sumTotal.length;i++){
            if(i==0){
                sumcell = sumrow.createCell(0);
                sumcell.setCellValue("合计: "+dataLists.size()+" 条数据");
                sumcell.setCellStyle(CellStyleTool.setDefaultCellStyle(dataFont,dateStyle,"SimHei",10));
            }else if(sumMap.containsKey(sumTotal[i])){
                sumcell = sumrow.createCell(i);
                sumcell.setCellValue(BigNumberUtil.format(BigDecimal.valueOf(sumMap.get(sumTotal[i])),2));
                sumcell.setCellStyle(CellStyleTool.setDefaultCellStyle(dataFont,dateStyle,"SimHei",10));
            }else{
                sumcell = sumrow.createCell(i);
                sumcell.setCellValue("");
                sumcell.setCellStyle(CellStyleTool.setDefaultCellStyle(dataFont,dateStyle,"SimHei",10));
            }
        }

        CellRangeAddress rangeAddress = new CellRangeAddress(dataLists.size()+startnum+2,dataLists.size()+startnum+7,0,0);
        sheet.addMergedRegion(rangeAddress);
        //备注
        HSSFRow remarksRow1 = sheet.createRow(dataLists.size()+startnum+2);
        HSSFCell reCelll1 = remarksRow1.createCell(0);
        reCelll1.setCellValue("备注");
        reCelll1.setCellStyle(CellStyleTool.setCellStyle(book.createCellStyle()));

        HSSFCell reCell12 = remarksRow1.createCell(1);
        reCell12.setCellValue("1、官费发票由国家知识产权局开具，代理费发票由广州微专信息科技有限公司开具。付款后，请将如下开票信息发送邮箱至zlbm@gzweizhuan.com，请备注好订单号；");

        HSSFRow remarksRow2 = sheet.createRow(dataLists.size()+startnum+3);
        HSSFCell reCell22 = remarksRow2.createCell(1);
        reCell22.setCellValue("      开票信息需包括：1、开票抬头及税号（请备注普票或增票）；2、快递地址、快递联系人、联系电话；3、订单号；");

        HSSFRow remarksRow3 = sheet.createRow(dataLists.size()+startnum+4);
        HSSFCell reCell32 = remarksRow3.createCell(1);
        reCell32.setCellValue("2、我方在收款后第2-3周内完成代缴年费以及快递发票。");

        HSSFRow remarksRow4 = sheet.createRow(dataLists.size()+startnum+5);
        HSSFCell reCell42 = remarksRow4.createCell(1);
        reCell42.setCellValue("3、若不用本系统线上支付，请将本缴费清单发送邮箱至zlbm@gzweizhuan.com，并通过对公或对私银行转账或其他付款方式；具体收款银行账户及其他收款方式，请查看网页右下角-代缴协议。");

        HSSFRow remarksRow5 = sheet.createRow(dataLists.size()+startnum+6);
        HSSFCell reCell52 = remarksRow5.createCell(1);
        reCell52.setCellValue("    温馨提醒：请提前2-3个月缴费。  若离缴费截止日不到15个工作日，会产生加急费，加急天数越少，加急费越高。具体收费请查看网页右下角-费用代缴协议。");

        HSSFRow remarksRow6 = sheet.createRow(dataLists.size()+startnum+7);
        HSSFCell reCell62 = remarksRow6.createCell(1);
        reCell62.setCellValue("    缴费热线：020-29030826（专利保姆 www.gzweizhuan.com）");
    }

    /**
     * 设置表头样式、内容等
     * @param headers 内容
     * @author qixin
     */
    private static void fitHeader(HSSFWorkbook book, HSSFSheet sheet, String[] headers) {
        HSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short)12);
        headerFont.setFontName("SimHei");
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        CellStyle headerCellStyle = book.createCellStyle();
        headerCellStyle = CellStyleTool.setDefaultCellStyle((HSSFCellStyle) headerCellStyle);
        headerCellStyle.setFont(headerFont);

        Row header = sheet.createRow(5);
        for (int i = 0; i < headers.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerCellStyle);
        }
    }

    /**
     * 设置标题样式、内容等
     * @param fileName 标题内容
     * @param witch 标题占几个单元格的宽度
     * @author qixin
     */
    private static void fitTitle(HSSFWorkbook book, HSSFSheet sheet, String fileName, int witch) {
        HSSFFont titleFont = book.createFont();
        titleFont.setFontHeightInPoints((short)24);
        titleFont.setFontName("SimHei");

        HSSFCellStyle titleCellStyle = book.createCellStyle();
        titleCellStyle = CellStyleTool.setDefaultCellStyle(titleCellStyle);
        titleCellStyle.setFont(titleFont);

        Row row = sheet.createRow(0);
        Cell title = row.createCell(0);
        title.setCellValue(fileName);
        title.setCellStyle(titleCellStyle);
        CellRangeAddress rangeAddress = new CellRangeAddress(0,3,0,witch);
        sheet.addMergedRegion(rangeAddress);
        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle);
        row = sheet.createRow(1);
        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle);
        row = sheet.createRow(2);
        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle);
        row = sheet.createRow(3);
        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle);
    }

    private static void fitTitle(HSSFWorkbook book, HSSFSheet sheet, String fileName,String titleName, int witch) {
        HSSFFont titleFont = book.createFont();
        titleFont.setFontHeightInPoints((short)14);
        titleFont.setFontName("SimHei");

        HSSFCellStyle titleCellStyle = book.createCellStyle();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        //titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //titleCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle.setFont(titleFont);

        Row row = sheet.createRow(0);
        Cell title = row.createCell(0);
        title.setCellValue(companyName);
        title.setCellStyle(titleCellStyle);
        CellRangeAddress rangeAddress = new CellRangeAddress(0,1,0,witch);
        sheet.addMergedRegion(rangeAddress);
        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle);
        row = sheet.createRow(1);
        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle);

        HSSFFont titleFont2 = book.createFont();
        titleFont2.setFontHeightInPoints((short)12);
        titleFont2.setFontName("SimHei");

        HSSFCellStyle titleCellStyle2 = book.createCellStyle();
        titleCellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //titleCellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //titleCellStyle2.setTopBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
        //titleCellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //titleCellStyle2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle2.setRightBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle2.setFont(titleFont2);

        row = sheet.createRow(2);
        title = row.createCell(0);
        title.setCellValue(titleName);
        title.setCellStyle(titleCellStyle2);
        rangeAddress = new CellRangeAddress(2,3,0,witch);
        sheet.addMergedRegion(rangeAddress);

        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle2);
        row = sheet.createRow(3);
        title = row.createCell(witch);
        title.setCellStyle(titleCellStyle2);

        HSSFFont titleFont3 = book.createFont();
        titleFont3.setFontHeightInPoints((short)10);
        titleFont3.setFontName("SimHei");

        HSSFCellStyle titleCellStyle3 = book.createCellStyle();
        titleCellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        titleCellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //titleCellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //titleCellStyle3.setTopBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle3.setRightBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleCellStyle3.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle3.setRightBorderColor(IndexedColors.BLACK.getIndex());
        titleCellStyle3.setFont(titleFont3);

        row = sheet.createRow(4);
        title = row.createCell(witch);
        //title.setCellValue("制表时间:"+DateKit.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        title.setCellStyle(titleCellStyle3);
        //rangeAddress = new CellRangeAddress(4,4,0,witch);
        //sheet.addMergedRegion(rangeAddress);
    }
}
