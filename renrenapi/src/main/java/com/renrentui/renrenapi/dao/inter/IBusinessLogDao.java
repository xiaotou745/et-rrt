package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.BusinessLog;

public interface IBusinessLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessLog record);

    int insertSelective(BusinessLog record);

    BusinessLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessLog record);

    int updateByPrimaryKey(BusinessLog record);
}