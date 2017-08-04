package com.nzm.dao.mapper;

import com.nzm.model.po.BatchExcel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("BatchExcelMapper")
public interface BatchExcelMapper {
    int insert(BatchExcel record);

    int insertSelective(BatchExcel record);

    BatchExcel selectFileById(@Param("id") String id);
}