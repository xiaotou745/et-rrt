package com.renrentui.api.dao.inter;

import com.renrentui.entity.BusinessLog;

public interface IBusinessLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessLog record);

    int insertSelective(BusinessLog record);

    BusinessLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessLog record);

    int updateByPrimaryKey(BusinessLog record);
}