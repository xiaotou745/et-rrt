package com.renrentui.api.dao.inter;

import com.renrentui.entity.Clienter;

public interface IClienterDaoDao {
    int deleteByPrimaryKey(Long id);

    int insert(Clienter record);

    int insertSelective(Clienter record);

    Clienter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);
}