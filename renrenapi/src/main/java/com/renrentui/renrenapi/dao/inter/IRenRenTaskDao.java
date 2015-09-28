package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.RenRenTask;

public interface IRenRenTaskDao {
    int deleteByPrimaryKey(Long id);

    int insert(RenRenTask record);

    int insertSelective(RenRenTask record);

    RenRenTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RenRenTask record);

    int updateByPrimaryKey(RenRenTask record);
}