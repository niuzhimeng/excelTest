package com.nzm.service.impl;

import com.nzm.dao.mapper.BatchExcelMapper;
import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.BatchService;
import com.nzm.service.batch.PoiTest;
import com.nzm.service.file.impl.FileOperationUtilImpl;
import com.nzm.utils.ApiClassFactory;
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

    @Resource
    @Qualifier("BatchExcelMapper")
    private BatchExcelMapper batchExcelMapper;

    @Override
    public JsonResponse<BatchExcel> execute(BatchVo batchVo) throws Exception {
        MultipartFile excel = batchVo.getExcel();
        String account = batchVo.getAccount();
        String batchType = batchVo.getBatchType();

        //获取指定对象
        Class<?> concrete = ApiClassFactory.getConcrete(batchType);
        PoiTest poiTest = (PoiTest) concrete.newInstance();

        //读取excel
        List<String> readExcelList = poiTest.readExcel(batchVo);
        //输出结果
        BatchExcel write = poiTest.write(readExcelList, account, excel);
        //保存文件信息
        batchExcelMapper.insert(write);
        //保存输入excel
        FileOperationUtilImpl.saveInExcel(batchVo);
        return new JsonResponse<BatchExcel>().createSuccess("测试完成，请下载测试结果", write);
    }

}
