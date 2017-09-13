package com.nzm.service.batch.impl.check;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nzm.model.po.BatchExcel;
import com.nzm.service.batch.PoiTest;
import com.nzm.service.file.impl.FileOperationUtilImpl;
import com.nzm.utils.ExcelUtils;
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
        //第二行列名字
        String[] titles = {"姓名", "身份证号码", "查询结果", "查询结果描述"};
        XSSFWorkbook xssfWorkbook = ExcelUtils.createExcel("黑名单测试结果", titles);
        //获得创建好表头的sheet
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //输出内容
        int row_num = resultList.size();
        for (int i = 0; i < row_num; i++) {
            //从第三行开始输入(一二行是表头)
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
        return FileOperationUtilImpl.saveOutExcel(xssfWorkbook, account, excel);
    }
}
