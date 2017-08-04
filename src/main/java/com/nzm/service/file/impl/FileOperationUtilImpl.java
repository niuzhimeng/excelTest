package com.nzm.service.file.impl;

import com.nzm.dao.mapper.BatchExcelMapper;
import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import com.nzm.service.file.FileOperationUtil;
import com.nzm.utils.General;
import com.nzm.utils.UploadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Nzm on 2017/8/3.
 */
@Component
public class FileOperationUtilImpl implements FileOperationUtil {

    private static final String ENCODING = "utf-8";
    /**
     * 配置文件名称
     */
    private static final String PROPERTIES_NAME = "/mis.properties";

    private static final String KEY_NAME = "outExcelSavePath";

    @Resource
    @Qualifier("BatchExcelMapper")
    private BatchExcelMapper batchExcelMapper;

    @Override
    public void saveInExcel(BatchVo batchVo) {
        //读取输入文件的保存位置
        String inExcelSavePath = UploadUtils.getPropertyValue(null, "inExcelSavePath");

        MultipartFile excel = batchVo.getExcel();
        String account = batchVo.getAccount();
        String savePath = inExcelSavePath + "/" + account + "/" + UploadUtils.getFileDate()
                + "/" + excel.getOriginalFilename();

        File saveFile = new File(savePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        try {
            excel.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BatchExcel saveOutExcel(XSSFWorkbook workbook, String account, MultipartFile excel) throws Exception {
        //输出excel的保存位置
        String outExcelSavePath = UploadUtils.getPropertyValue(null, "outExcelSavePath");

        String filename = excel.getOriginalFilename();
        filename = filename.replaceAll("\\s*", "");
        //获取文件名（不带后缀名）
        String originName = UploadUtils.getFileName(filename);
        //获取扩展名
        String fileExtName = UploadUtils.getExtName(filename);

        String id = General.getUid();

        String outPath = outExcelSavePath + "/" + account + "/" + UploadUtils.getFileDate() + "/"
                + id + "." + fileExtName;
        String filePath = "/" + outExcelSavePath.substring(outExcelSavePath.lastIndexOf("/") + 1) +
                "/" + account + "/" + UploadUtils.getFileDate() + "/" + id + "." + fileExtName;

        File file = new File(outPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fOut = new FileOutputStream(file);
        // 把相应的Excel 工作簿存盘
        workbook.write(fOut);
        fOut.flush();
        // 操作结束，关闭文件
        fOut.close();

        BatchExcel batchExcel = new BatchExcel();
        batchExcel.setId(id);
        batchExcel.setBelongAccount(account);
        batchExcel.setCreatedTime(General.getCurrentTime());
        batchExcel.setExtName(fileExtName);
        batchExcel.setFileName(originName + "_测试结果");
        batchExcel.setPath(filePath);

        batchExcelMapper.insert(batchExcel);
        return batchExcel;
    }

    public ResponseEntity<byte[]> downloadFile(String filePath, String fileName) throws IOException {
        //从配置文件中读取保存路径
        String fileSavePath = UploadUtils.getPropertyValue(PROPERTIES_NAME, KEY_NAME);
        String path = fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
        filePath = path + filePath;
        return downloadAssist(filePath, fileName);
    }

    /**
     * 文件下载辅助
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    private ResponseEntity<byte[]> downloadAssist(String filePath, String fileName) throws IOException {
        File file = new File(filePath);
        if (!file.isFile() || !file.exists()) {
            throw new IllegalArgumentException("filePath 参数必须是真实存在的文件路径:" + filePath);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, ENCODING));
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
