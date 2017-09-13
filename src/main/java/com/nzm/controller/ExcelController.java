package com.nzm.controller;

import com.nzm.dao.mapper.BatchExcelMapper;
import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.BatchService;
import com.nzm.service.file.FileOperationUtil;
import com.nzm.service.file.impl.FileOperationUtilImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Nzm on 2017/8/3.
 */
@RequestMapping(value = "test")
@RestController
public class ExcelController {

    @Resource
    private BatchService batchService;

    @Resource
    private BatchExcelMapper batchExcelMapper;

    @RequestMapping(value = "batchTest", method = RequestMethod.POST)
    public JsonResponse<List<BatchExcel>> batchTest(BatchVo batchVo) throws Exception {

        return batchService.execute(batchVo);
    }

    /**
     * 文件下载
     *
     * @param fileId 文件id
     * @return ResponseEntity
     */
    @RequestMapping(value = "fileDownload", method = RequestMethod.GET)
    public ResponseEntity<byte[]> fileDownload(@RequestParam String fileId) throws Exception {
        //根据fileId查询文件信息
        BatchExcel fileInfo = batchExcelMapper.selectFileById(fileId);
        String fileName = fileInfo.getFileName() + "." + fileInfo.getExtName();
        String path = fileInfo.getPath();
        return FileOperationUtilImpl.downloadFile(path, fileName);
    }
}
