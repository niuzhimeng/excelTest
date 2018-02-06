package com.nzm.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.nzm.dao.mapper.BatchExcelMapper;
import com.nzm.dao.mapper.PartnerCompanyMapper;
import com.nzm.model.po.BatchExcel;
import com.nzm.model.po.PartnerCompany;
import com.nzm.model.vo.BatchVo;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.BatchService;
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

    @Resource
    private PartnerCompanyMapper partnerCompanyMapper;

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

    @RequestMapping(value = "testInsert", method = RequestMethod.GET)
    public JsonResponse testInsert() throws Exception {
        Gson gson = new Gson();
        String json = "{\"success\":true,\"data\":[{\"id\":null,\"companyId\":\"66115c835cdf47e8936cd8d503d0ce37\",\"name\":\"nzm\",\"tenure\":1,\"experience\":1,\"type\":1,\"testList\":[]},{\"id\":\"7fbb372a76924e24a1c8dad80ecc90de\",\"companyId\":\"c183c954a31e486093720a00aa533807\",\"name\":\"nzm\",\"tenure\":1,\"experience\":1,\"type\":1,\"testList\":[{\"money\":null},{\"size\":11,\"money\":null,\"age\":13}]}]}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String typeJson = jsonObject.get("data").toString();


        List<PartnerCompany> list = gson.fromJson(typeJson, new TypeToken<List<PartnerCompany>>() {
        }.getType());


        return new JsonResponse<>().createSuccess(list);
    }
}
