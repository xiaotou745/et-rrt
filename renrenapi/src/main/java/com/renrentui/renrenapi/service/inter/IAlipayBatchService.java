package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchClienterWithdrawForm;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.req.PagedAlipayBatchListReq;

public interface IAlipayBatchService {
	/**
	 * 支付宝批次分页数据
	 * @author wangchao
	 * @param req
	 * @return
	 */
	PagedResponse<AlipayBatchModel>  getAlipayBatchPagedList(PagedAlipayBatchListReq req);
	/**
	*根据id获取 支付宝批次
	 * @author wangchao
	 * @param req
	 * @return
	 */
	AlipayBatchModel  getAlipayBatchById(Long id);
	List<AlipayBatchClienterWithdrawForm> getClienterWithdrawFormByBatchNo(Long id);
}
