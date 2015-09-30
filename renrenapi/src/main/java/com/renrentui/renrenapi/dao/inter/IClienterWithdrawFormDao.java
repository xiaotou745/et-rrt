package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;

public interface IClienterWithdrawFormDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterWithdrawForm record);

    int insertSelective(ClienterWithdrawForm record);

    ClienterWithdrawForm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterWithdrawForm record);

    int updateByPrimaryKey(ClienterWithdrawForm record);
    
    PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req);
}