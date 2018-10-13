package com.example.qixin.utils.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 创  建   时  间： 2018/10/13 12:33
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */

public class ReadExcelUtil {

    private static Logger log = LoggerFactory.getLogger(ReadExcelUtil.class);

    public static void main(String[] args) {
        String filePath = "C:\\Users\\lenovo\\Desktop\\工单模板.xlsx";
        String columns[] = {"name","age","score"};
        Workbook wb = readExcel(filePath);
        if(wb ==null) return;

        //用来存放表中数据
        List<Map<String,Object>> list = new ArrayList<>();
        //获取第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        Row row = sheet.getRow(0);
        //获取最大列数
        int colnum = row.getPhysicalNumberOfCells();
        for (int i = 1; i<rownum; i++) {
            Map<String,Object> map = new LinkedHashMap<>();
            row = sheet.getRow(i);
            if(row !=null){
                for (int j=0;j<colnum;j++){
                    Object cellData = getCellValue(row.getCell(j));
                    map.put(columns[j], cellData);
                }
            }else{
                break;
            }
            list.add(map);
        }

        //遍历解析出来的list
        for (Map<String,Object> map : list) {
            for (Map.Entry<String,Object> entry : map.entrySet()) {
                System.out.print(entry.getKey()+":"+entry.getValue()+",");
            }
            System.out.println();
        }

    }


    /**
     * 将excel文件转换成 Workbook 对象
     * @param filePath 文件路径
     * @return Workbook对象
     */
    private static Workbook readExcel(String filePath){
        if(StringUtils.isEmpty(filePath)) return null;
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        try {
            InputStream inp = new FileInputStream(filePath);
            if(".xls".equals(suffix)){
                return new HSSFWorkbook(inp);
            }else if(".xlsx".equals(suffix)){
                return WorkbookFactory.create(inp);
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得值
     * @param cell 单元格
     * @return 单元格值
     */
    private static Object getCellValue(Cell cell){
        if(cell == null) return "";
        Object cellValue = null;
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType){
            case BLANK:{
                cellValue = "";
                log.debug("excel出现空值");
                break;
            }case STRING:{
                cellValue = cell.getStringCellValue();
                break;
            }case BOOLEAN:{
                cellValue = cell.getBooleanCellValue();
                break;
            }case NUMERIC:{
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue();
                    break;
                }else {// 纯数字
                    BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                    //解决1234.0  去掉后面的.0
                    if(null != big.toString() && !"".equals(big.toString().trim())){
                        String[] item = big.toString().split("[.]");
                        if(1<item.length && "0".equals(item[1])){
                            cellValue=item[0];
                        }
                    }
                    break;
                }
            }case FORMULA:{
                //读公式计算值
                cellValue = String.valueOf(cell.getNumericCellValue());
                if (cellValue.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    cellValue = cell.getStringCellValue();
                }
                break;
            }case ERROR:{
                cellValue = "";
                log.debug("excel出现故障");
                break;
            }default:{
                cellValue = "";
            }
        }
        return cellValue;
    }
}
