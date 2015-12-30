package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterFinanceAcount;

public interface IClienterFinanceAcountDao {
	ClienterFinanceAcount select(long clienterid,int accounttype);
    int updateSelective(ClienterFinanceAcount record);
    int insertSelective(ClienterFinanceAcount record);
}