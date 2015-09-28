package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Clienter;

public interface IClienterDaoDao {
    int deleteByPrimaryKey(Long id);

    int insert(Clienter record);

    int insertSelective(Clienter record);

    Clienter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);
}