package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterWithdrawForm;

public interface IClienterWithdrawFormDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterWithdrawForm record);

    int insertSelective(ClienterWithdrawForm record);

    ClienterWithdrawForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterWithdrawForm record);

    int updateByPrimaryKey(ClienterWithdrawForm record);
}