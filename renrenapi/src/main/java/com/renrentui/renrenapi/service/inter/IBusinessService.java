package com.renrentui.renrenapi.service.inter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.BusinessModel;
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
	PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);	
	
	int add(Business record);
	
	int modify(Business record);

	public int addBalance(BusinessBalanceReq req,String userName);
}
