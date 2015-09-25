package com.renrentui.api.dao.inter;

import com.renrentui.entity.ClienterLog;

public interface IClienterLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterLog record);

    int insertSelective(ClienterLog record);

    ClienterLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterLog record);

    int updateByPrimaryKey(ClienterLog record);
}