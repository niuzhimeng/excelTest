package com.nzm.service.impl;

import com.nzm.dao.mapper.staffMapper;
import com.nzm.model.po.staff;
import com.nzm.service.StaffService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Nzm on 2018/2/6.
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Resource
    private staffMapper staMapper;

    @Override
    @CachePut(value = "common", key = "'staff:'+#sta.getAccount()+'-'+#sta.getJobNo()")
    public staff update(staff sta) {
        staMapper.updateByPrimaryKeySelective(sta);
        return sta;
    }

    @Override
    public staff select(String jobNo) {
        return staMapper.selectByPrimaryKey(jobNo);
    }

    @Override
    public staff insert(staff sta) {
        staMapper.insertSelective(sta);
        return sta;
    }
}
