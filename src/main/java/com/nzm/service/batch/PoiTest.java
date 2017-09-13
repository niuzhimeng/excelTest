package com.nzm.service.batch;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nzm.model.enums.FeedBack;
import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import com.nzm.service.file.impl.FileOperationUtilImpl;
import com.nzm.utils.OkHttpUtils;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量测试抽象类
 * Created by Nzm on 2017/8/2.
 */
public abstract class PoiTest {
    /**
     * 获取授权码的URL（post）
     */
    private static final String getAccessTokenUrl = "http://tianxingshuke.com/api/rest/common/organization/auth";

    /**
     * 读取excel
     *
     * @param batchVo 文件对象
     * @throws Exception
     */
    public List<String> readExcel(BatchVo batchVo) throws Exception {
        //拼接账号信息
        String token = getToken(batchVo.getAccount(), batchVo.getSignature());
        appendAccountInfo(batchVo.getAccount(), token);

        //返回结果集合
        List<String> resultList = new ArrayList<>();
        //一行元素的集合
        List<String> rowList = new ArrayList<>();
        //当前单元格的元素
        String currentCell;

        // 创建对Excel工作簿文件的引用
        XSSFWorkbook workbook = new XSSFWorkbook(batchVo.getExcel().getInputStream());

        // 创建对工作表的引用。
        XSSFSheet sheet = workbook.getSheetAt(0);
        // 便利所有单元格，读取单元格
        int row_num = sheet.getLastRowNum();
        XSSFRow row = sheet.getRow(0);
        int col_num = row.getLastCellNum();
        for (int i = 1; i <= row_num; i++) {
            XSSFRow cells = sheet.getRow(i);

            for (int j = 0; j < col_num; j++) {
                if (cells.getCell(j).getCellType() == 1) {
                    currentCell = cells.getCell(j).getStringCellValue();
                } else {
                    currentCell = NumberToTextConverter.toText(cells.getCell(j).getNumericCellValue());
                }
                rowList.add(currentCell);
            }
            String url = appendUrl(rowList);

            //发送http请求
//            String resultString = OkHttpUtils.get(url);
            Thread.sleep(30);
            String resultString = "{\"success\":false,\"data\":{\"name\":\"王卫卫\",\"idCard\":\"130433198206060127\",\"status\":\"NO_DATA\",\"statusDesc\":\"查询无数据\"}}";
            //如果token过期，重新获取并拼接url
            if (FeedBack.TOKEN_EXPIRED.getStatus().equals(resultString)) {
                token = getToken(batchVo.getAccount(), batchVo.getSignature());
                appendAccountInfo(batchVo.getAccount(), token);
                url = appendUrl(rowList);
                //发送http请求
                resultString = OkHttpUtils.get(url);
            }
            StringBuilder parameterString = new StringBuilder();
            for (String parameter : rowList) {
                parameterString.append(parameter).append("; ");
            }

            resultList.add(parameterString.toString() + "#1#" + resultString);
            System.out.println(url);
            rowList.clear();
        }
        return resultList;
    }

    /**
     * 导出excel
     *
     * @param resultList 返回结果的list<入参,返回json>
     * @throws Exception
     */
    public abstract List<BatchExcel> write(List<String> resultList, String account, MultipartFile excel) throws Exception;

    /**
     * 获取授权码
     */
    private String getToken(String account, String signature) {
        String token = null;
        String url = getAccessTokenUrl + "?account=" + account + "&signature=" + signature;
        String tokenJson = OkHttpUtils.post(url);
        JsonObject jsonObject = new JsonParser().parse(tokenJson).getAsJsonObject();
        if (jsonObject.get("success").getAsBoolean()) {
            token = jsonObject.getAsJsonObject("data").get("accessToken").getAsString();
        }
        //return token;
        return "最新的token";
    }

    /**
     * 拼接url
     *
     * @param cell 单元格中的元素
     * @return 拼接好的url
     */
    public abstract String appendUrl(List<String> cell);

    public abstract void appendAccountInfo(String account, String token);

    /**
     * 拼接账号信息
     *
     * @param account 账号
     * @param token   token
     */
    protected String fatherAppendAccount(String url, String account, String token) {
        return url + ("?account=" + account + "&accessToken=" + token);
    }


}

