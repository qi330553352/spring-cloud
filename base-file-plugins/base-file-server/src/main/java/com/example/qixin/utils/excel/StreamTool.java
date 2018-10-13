package com.example.qixin.utils.excel;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/** 流工具类
 * 创  建   时  间： 2018/10/13 12:52
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
class StreamTool {

    /**
     * 设置输出流
     * @param fileName 文件、标题名 (*不加文件后缀)
     * @param book 文件对象
     * @param response 响应
     * @author qixin
     */
    static void fitStream(String fileName, Workbook book, HttpServletResponse response) {
        try {
            String filename = new String(fileName.getBytes("GB2312"), "ISO8859-1")+ ".xls";
            response.setHeader("Content-disposition", "attachment; filename="  + filename);
        } catch (UnsupportedEncodingException e) {
            log.error("将文件名字符转换出错：", e);
        }
        response.setContentType("application/vnd.ms-excel");
        try {
            OutputStream out = response.getOutputStream();
            book.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("文件输出流异常：",e);
        }
    }




}
