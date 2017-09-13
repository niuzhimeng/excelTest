package com.nzm.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

/**
 * Created by Nzm on 2017/9/13.
 */
public class ExcelUtils {
    /**
     * 创建一个代表头的excel对象
     *
     * @param sheetName 表头名称
     * @param titles    列名
     * @return 创建好的excel对象
     */
    public static XSSFWorkbook createExcel(String sheetName, String[] titles) {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //1.1创建合并单元格对象
        CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, titles.length - 1);//起始行,结束行,起始列,结束列
        //1.2头标题样式
        XSSFCellStyle xssfCellStyle = createCellStyle(workbook, (short) 13);
        //1.3列标题样式
        XSSFCellStyle colStyle = createCellStyle(workbook, (short) 13);
        //2.创建工作表
        XSSFSheet sheet = workbook.createSheet(sheetName);
        //2.1加载合并单元格对象
        sheet.addMergedRegion(callRangeAddress);
        //设置默认列宽
        sheet.setDefaultColumnWidth(25);
        //3.创建行
        //3.1创建头标题行;并且设置头标题
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);

        //加载单元格样式
        cell.setCellStyle(xssfCellStyle);
        cell.setCellValue(sheetName);

        //3.2创建列标题;并且设置列标题
        XSSFRow row2 = sheet.createRow(1);
        for (int i = 0; i < 4; i++) {
            XSSFCell cell2 = row2.createCell(i);
            //加载单元格样式
            cell2.setCellStyle(colStyle);
            cell2.setCellValue(titles[i]);
        }
        return workbook;
    }

    /**
     * 设置单元格格式
     *
     * @return
     */
    private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontSize) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints(fontSize);
        //加载字体
        style.setFont(font);
        return style;
    }
}
