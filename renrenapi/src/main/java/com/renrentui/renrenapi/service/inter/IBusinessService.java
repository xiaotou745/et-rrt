package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedBusinessReq;


/**
 * 
 * 商户户相关
 * @author 
 *
 */
public interface IBusinessService {
	/**
	 * 查询商家分页列表
	 * @return
	 */
	PagedResponse<Business> getBusinessList(PagedBusinessReq req);	
	
	int Add(Business record);
}
