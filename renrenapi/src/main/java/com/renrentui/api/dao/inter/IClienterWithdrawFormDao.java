package com.renrentui.api.dao.inter;

import com.renrentui.entity.ClienterWithdrawForm;

public interface IClienterWithdrawFormDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterWithdrawForm record);

    int insertSelective(ClienterWithdrawForm record);

    ClienterWithdrawForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterWithdrawForm record);

    int updateByPrimaryKey(ClienterWithdrawForm record);
}