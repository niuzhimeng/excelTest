package com.nzm.dao.mapper;

import com.nzm.model.po.staff;
import org.apache.ibatis.annotations.Param;

public interface staffMapper {
    int deleteByPrimaryKey(String jobNo);

    int insert(staff record);

    int insertSelective(staff record);

    staff selectByPrimaryKey(String jobNo);

    int updateByPrimaryKeySelective(staff record);

    int updateByPrimaryKey(staff record);

    staff selectByMap(@Param("jobNo") String jobNo);
}