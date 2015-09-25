package com.renrentui.api.dao.inter;

import com.renrentui.entity.RenRenTask;

public interface IRenRenTaskDao {
    int deleteByPrimaryKey(Long id);

    int insert(RenRenTask record);

    int insertSelective(RenRenTask record);

    RenRenTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RenRenTask record);

    int updateByPrimaryKey(RenRenTask record);
}