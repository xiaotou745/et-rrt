package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.req.BusinessBalanceReq;

public interface IBusinessBalanceDao {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessBalance record);

    int insertSelective(BusinessBalance record);

    BusinessBalance selectByBusinessId(Long businessId);

    int updateByPrimaryKeySelective(BusinessBalance record);

    int updateByPrimaryKey(BusinessBalance record);
    
    int updateBalanceByBusinessId(BusinessBalanceReq record);
}