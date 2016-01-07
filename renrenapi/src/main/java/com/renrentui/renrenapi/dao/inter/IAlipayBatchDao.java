package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchClienterWithdrawForm;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.req.PagedAlipayBatchListReq;

public interface IAlipayBatchDao {
	PagedResponse<AlipayBatchModel>  getAlipayBatchPagedList(PagedAlipayBatchListReq req);
	 
	AlipayBatchModel  getAlipayBatchById(Long id);

	List<AlipayBatchClienterWithdrawForm> getClienterWithdrawFormByBatchNo(
			Long id);
}
