package com.nzm.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.BatchService;
import com.nzm.service.batch.impl.PoliceIdentity;
import com.nzm.service.batch.impl.Traffic;
import com.nzm.service.file.FileOperationUtil;
import com.nzm.utils.OkHttpUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

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
    private PoliceIdentity policeIdentity;

    @Resource
    private Traffic traffic;

    /**
     * 文件保存工具类
     */
    @Resource
    private FileOperationUtil fileOperationUtil;

    @Override
    public JsonResponse<BatchExcel> execute(BatchVo batchVo) throws Exception {
        MultipartFile excel = batchVo.getExcel();
        String account = batchVo.getAccount();
        String token = getToken(account, batchVo.getSignature());
        String batchType = batchVo.getBatchType();

        InputStream inputStream = excel.getInputStream();
        //保存输入excel
        fileOperationUtil.saveInExcel(batchVo);

        if (inputStream == null) {
            return new JsonResponse<BatchExcel>().createError("未选择文件");
        }

        if ("traffic".equals(batchType)) {
            traffic.appendAccountInfo(account, token);
            return traffic.readAndWrite(inputStream, account, excel);
        } else if ("policeIdentity".equals(batchType)) {
            policeIdentity.appendAccountInfo(account, token);
            return policeIdentity.readAndWrite(inputStream, account, excel);
        }
        return new JsonResponse<BatchExcel>().createError("暂不支持该接口的批量测试");
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
