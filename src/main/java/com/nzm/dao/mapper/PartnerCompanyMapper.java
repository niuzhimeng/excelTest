package com.nzm.dao.mapper;

import com.nzm.model.po.PartnerCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PartnerCompanyMapper")
public interface PartnerCompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(PartnerCompany record);

    int insertSelective(PartnerCompany record);

    PartnerCompany selectByPrimaryKey(String id);

    List<PartnerCompany> selectAll();

    int updateByPrimaryKeySelective(PartnerCompany record);

    int updateByPrimaryKey(PartnerCompany record);
}