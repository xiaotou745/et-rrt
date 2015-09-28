package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterLog;

public interface IClienterLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterLog record);

    int insertSelective(ClienterLog record);

    ClienterLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterLog record);

    int updateByPrimaryKey(ClienterLog record);
}