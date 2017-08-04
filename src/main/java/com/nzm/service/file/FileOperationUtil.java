package com.nzm.service.file;

import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Nzm on 2017/8/3.
 */
public interface FileOperationUtil {
    /**
     * 保存输入excel
     *
     * @param batchVo 载体VO
     */
    void saveInExcel(BatchVo batchVo);

    /**
     * 保存输出文件
     *
     * @param workbook excel对象
     * @param account  账号
     * @param excel    文件
     */
    BatchExcel saveOutExcel(XSSFWorkbook workbook, String account, MultipartFile excel) throws Exception;

    /**
     * 文件下载
     *
     * @param filePath 路径
     * @param fileName 名称
     * @return
     * @throws IOException
     */
    ResponseEntity<byte[]> downloadFile(String filePath, String fileName) throws IOException;
}
