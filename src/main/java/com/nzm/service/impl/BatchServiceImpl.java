package com.nzm.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nzm.dao.mapper.BatchExcelMapper;
import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.BatchService;
import com.nzm.service.batch.PoiTest;
import com.nzm.service.file.impl.FileOperationUtilImpl;
import com.nzm.utils.ApiClassFactory;
import com.nzm.utils.OkHttpUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Nzm on 2017/8/3.
 */
@Service
public class BatchServiceImpl implements BatchService {
    /**
     * 获取授权码的URL（post）
     */
    private static final String getAccessTokenUrl = "http://tianxingshuke.com/api/rest/common/organization/auth";

    @Resource
    @Qualifier("BatchExcelMapper")
    private BatchExcelMapper batchExcelMapper;

    @Override
    public JsonResponse<BatchExcel> execute(BatchVo batchVo) throws Exception {
        MultipartFile excel = batchVo.getExcel();
        String account = batchVo.getAccount();
        String token = getToken(account, batchVo.getSignature());
        String batchType = batchVo.getBatchType();

        //获取指定对象
        Class<?> concrete = ApiClassFactory.getConcrete(batchType);
        PoiTest poiTest = (PoiTest) concrete.newInstance();
        //拼接账号信息
        poiTest.appendAccountInfo(account, token);
        //读取excel
        List<String> readExcelList = poiTest.readExcel(excel.getInputStream());
        //输出结果
        BatchExcel write = poiTest.write(readExcelList, account, excel);
        //保存文件信息
        batchExcelMapper.insert(write);
        //保存输入excel
        FileOperationUtilImpl.saveInExcel(batchVo);
        return new JsonResponse<BatchExcel>().createSuccess("测试完成，请下载测试结果", write);
    }

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
}
