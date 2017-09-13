package com.nzm.service.batch.impl.check;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nzm.model.po.BatchExcel;
import com.nzm.service.batch.PoiTest;
import com.nzm.service.file.impl.FileOperationUtilImpl;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 黑名单
 * Created by Nzm on 2017/9/13.
 */
public class BlackList extends PoiTest {

    /**
     * 访问url
     */
    private String url = "http://localhost:8080/tianXingDataApi/rest/riskTip/black";

    @Override
    public String appendUrl(List<String> cell) {
        return url + "&name=" + cell.get(0) + "&idCard=" + cell.get(1);
    }

    @Override
    public void appendAccountInfo(String account, String token) {
        url = super.fatherAppendAccount(this.url, account, token);
    }

    @Override
    public BatchExcel write(List<String> resultList, String account, MultipartFile excel) throws Exception {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        //1.1创建合并单元格对象
        CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 3);//起始行,结束行,起始列,结束列
        //1.2头标题样式
        XSSFCellStyle xssfCellStyle = createCellStyle(workbook, (short) 13);
        //1.3列标题样式
        XSSFCellStyle colStyle = createCellStyle(workbook, (short) 13);
        //2.创建工作表
        XSSFSheet sheet = workbook.createSheet("黑名单测试结果");
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
        cell.setCellValue("黑名单测试结果");

        //3.2创建列标题;并且设置列标题
        XSSFRow row2 = sheet.createRow(1);
        String[] titles = {"姓名", "身份证号码", "查询结果", "查询结果描述"};
        for (int i = 0; i < 4; i++) {
            XSSFCell cell2 = row2.createCell(i);
            //加载单元格样式
            cell2.setCellStyle(colStyle);
            cell2.setCellValue(titles[i]);
        }

        //输出内容
        int row_num = resultList.size();
        for (int i = 0; i < row_num; i++) {
            XSSFRow outRow = sheet.createRow(i + 2);
            String[] ss = resultList.get(i).split("#1#");
            JsonObject asJsonObject = new JsonParser().parse(ss[1]).getAsJsonObject();
            if (asJsonObject.get("success").getAsBoolean()) {
                JsonObject dataObject = asJsonObject.get("data").getAsJsonObject();

                XSSFCell cellOne = outRow.createCell(0);
                cellOne.setCellType(XSSFCell.CELL_TYPE_STRING);
                cellOne.setCellValue(dataObject.get("name").getAsString());

                XSSFCell cellTwo = outRow.createCell(1);
                cellTwo.setCellType(XSSFCell.CELL_TYPE_STRING);
                cellTwo.setCellValue(dataObject.get("idCard").getAsString());

                XSSFCell cellThree = outRow.createCell(2);
                cellThree.setCellType(XSSFCell.CELL_TYPE_STRING);
                cellThree.setCellValue(dataObject.get("status").getAsString());

                XSSFCell cellFour = outRow.createCell(3);
                cellFour.setCellType(XSSFCell.CELL_TYPE_STRING);
                cellFour.setCellValue(dataObject.get("statusDesc").getAsString());

            }
        }
        return FileOperationUtilImpl.saveOutExcel(workbook, account, excel);
    }

    private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontSize) {
        // TODO Auto-generated method stub
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
