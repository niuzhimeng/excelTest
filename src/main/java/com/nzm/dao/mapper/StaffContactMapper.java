package com.nzm.dao.mapper;

import com.nzm.model.po.StaffContact;

public interface StaffContactMapper {
    int deleteByPrimaryKey(String id);

    int insert(StaffContact record);

    int insertSelective(StaffContact record);

    StaffContact selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StaffContact record);

    int updateByPrimaryKey(StaffContact record);
}