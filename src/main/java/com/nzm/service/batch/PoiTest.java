package com.nzm.service.batch;

import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.file.FileOperationUtil;
import com.nzm.utils.UploadUtils;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量测试抽象类
 * Created by Nzm on 2017/8/2.
 */
public abstract class PoiTest {

    @Resource
    private FileOperationUtil fileOperationUtil;

    /**
     * 读取excel
     *
     * @param inputStream 文件对象
     * @throws Exception
     */
    public JsonResponse<BatchExcel> readAndWrite(InputStream inputStream, String account, MultipartFile file) throws Exception {
        //返回结果集合
        List<String> resultList = new ArrayList<>();
        //一行元素的集合
        List<String> rowList = new ArrayList<>();
        //当前单元格的元素
        String currentCell;

        // 创建对Excel工作簿文件的引用
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

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
            if (null == url) {
                return new JsonResponse<BatchExcel>().createError("参数个数错误");
            }
            //发送http请求
//            String resultString = OkHttpUtils.get(url);
            String resultString = "{\"HasData\":true,\"Records\":[{\"Time\":\"2017-07-11 10:12:00\",\"Location\":\"迎宾路1001公里\",\"Reason\":\"机动车违反禁令标志指示的\",\"count\":\"100\",\"status\":\"0\",\"department\":null,\"Degree\":\"3\",\"Code\":\"1344\",\"Archive\":\"5110k80000157463\",\"Telephone\":null,\"Excutelocation\":null,\"Excutedepartment\":null,\"Category\":\"\",\"Latefine\":\"0\",\"Punishmentaccording\":null,\"Illegalentry\":null,\"Locationid\":5110,\"LocationName\":\"四川内江\",\"DataSourceID\":9981,\"RecordType\":\"实时数据\",\"Poundage\":\"0\",\"CanProcess\":\"0\",\"UniqueCode\":\"9c8281f2b502861e4dfa0701c58637bc\",\"SecondaryUniqueCode\":\"709346\",\"DegreePoundage\":\"0\",\"Other\":\"0\",\"CanprocessMsg\":null,\"CooperPoundge\":null,\"ActivePoundge\":\"-1\"}],\"ErrorCode\":0,\"Success\":true,\"ErrMessage\":\"\",\"ResultType\":\"实时数据\",\"LastSearchTime\":\"2017-07-21 10:17:10\",\"Other\":\"\"}";
            StringBuilder parameterString = new StringBuilder();
            for (String parameter : rowList) {
                parameterString.append(parameter).append("; ");
            }

            resultList.add(parameterString.toString() + "#1#" + resultString);
            Thread.sleep(30);
            System.out.println(url);
            rowList.clear();
        }
        //调用输出方法
        BatchExcel write = write(resultList, account, file);
        return new JsonResponse<BatchExcel>().createSuccess("测试成功!请下载测试结果。", write);
    }

    /**
     * 导出excel
     *
     * @param resultList 返回结果的list<入参,返回json>
     * @throws Exception
     */
    private BatchExcel write(List<String> resultList, String account, MultipartFile excel) throws Exception {


        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值
        XSSFSheet sheet = workbook.createSheet();

        int col_num = resultList.get(0).split("#1#")[0].split("; ").length;
        int row_num = resultList.size();
        for (int i = 0; i < row_num; i++) {
            XSSFRow row = sheet.createRow(i);
            String[] ss = resultList.get(i).split("#1#");
            String[] params = ss[0].split("; ");
            for (int j = 0; j < col_num; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(params[j]);
            }
            XSSFCell cell = row.createCell(col_num + 1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(ss[1]);
        }

        //保存输出结果
        return fileOperationUtil.saveOutExcel(workbook, account, excel);

    }


    /**
     * 钩子方法
     *
     * @param cell 单元格中的元素
     * @return 拼接好的url
     */
    public abstract String appendUrl(List<String> cell);

    /**
     * 拼接账号信息
     *
     * @param account 账号
     * @param token   token
     */
    protected String appendAccountInfo(String url, String account, String token) {
        return url += "?account=" + account + "&accessToken=" + token;
    }
}

