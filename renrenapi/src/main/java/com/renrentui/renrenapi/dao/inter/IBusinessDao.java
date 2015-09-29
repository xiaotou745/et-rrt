package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedBusinessReq;

public interface IBusinessDao {
    int deleteByPrimaryKey(Long id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
    
    PagedResponse<Business> getBusinessList(PagedBusinessReq req);
}