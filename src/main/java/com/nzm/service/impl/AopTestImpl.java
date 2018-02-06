package com.nzm.service.impl;

import com.nzm.model.vo.BatchVo;
import com.nzm.service.AopTestService;
import org.springframework.stereotype.Service;

/**
 * Created by Nzm on 2017/12/27.
 */
@Service
public class AopTestImpl implements AopTestService {
    @Override
    public BatchVo test(String one) {
        BatchVo vo = new BatchVo();
        vo.setAccount(one);
        return vo;
    }
}
