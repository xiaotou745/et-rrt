package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;


/**
 * 
 * 商户户相关
 * @author 
 *
 */
public interface IBusinessService {
	public List<Business> getAllList();
	/**
	 * 查询商家分页列表
	 * @return
	 */
	PagedResponse<Business> getBusinessList(PagedBusinessReq req);	
	
	int Add(Business record);
	
	int AddBalance(BusinessBalanceReq req);
}
