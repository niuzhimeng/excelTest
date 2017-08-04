package com.nzm.service;

import com.nzm.model.po.BatchExcel;
import com.nzm.model.vo.BatchVo;
import com.nzm.model.vo.JsonResponse;

/**
 * Created by Nzm on 2017/8/3.
 */
public interface BatchService {
    /**
     * 执行批量测试
     *
     * @param batchVo 载体VO
     * @return
     * @throws Exception
     */
    JsonResponse<BatchExcel> execute(BatchVo batchVo) throws Exception;
}
