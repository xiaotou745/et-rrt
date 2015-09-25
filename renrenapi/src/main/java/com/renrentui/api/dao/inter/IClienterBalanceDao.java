package com.renrentui.api.dao.inter;

import com.renrentui.entity.ClienterBalance;

public interface IClienterBalanceDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalance record);

    int insertSelective(ClienterBalance record);

    ClienterBalance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterBalance record);

    int updateByPrimaryKey(ClienterBalance record);
}