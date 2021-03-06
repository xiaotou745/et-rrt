package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.BusinessModel;
import com.renrentui.renrenentity.req.PagedBusinessReq;

public interface IBusinessDao {
    int deleteByPrimaryKey(Long id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectById(Long id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
    PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
    List<Business> getAllList();
}