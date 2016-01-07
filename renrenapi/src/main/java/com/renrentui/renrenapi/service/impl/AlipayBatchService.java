package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAlipayBatchDao;
import com.renrentui.renrenapi.service.inter.IAlipayBatchService;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchClienterWithdrawForm;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.req.PagedAlipayBatchListReq;
 
@Service
public class AlipayBatchService extends DaoBase implements IAlipayBatchService {
	
	/**
	 * 支付宝批次 
	 * @author wangchao 
	 */
	@Autowired
	private IAlipayBatchDao alipayBatchDao;
	/**
	 * 支付宝批次分页数据
	 * @author wangchao
	 * @param req
	 * @return
	 */
	@Override
	public PagedResponse<AlipayBatchModel>  getAlipayBatchPagedList(PagedAlipayBatchListReq req){
		return alipayBatchDao.getAlipayBatchPagedList(req);
	}
	/**
	*根据id获取 支付宝批次
	 * @author wangchao
	 * @param req
	 * @return
	 */
	@Override
	public AlipayBatchModel getAlipayBatchById(Long id) {
		return alipayBatchDao.getAlipayBatchById(id);
	}
	@Override
	public List<AlipayBatchClienterWithdrawForm> getClienterWithdrawFormByBatchNo(
			Long id) {
		return alipayBatchDao.getClienterWithdrawFormByBatchNo(id);
	}
}