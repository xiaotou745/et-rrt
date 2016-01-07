package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAlipayBatchDao;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchClienterWithdrawForm;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.req.PagedAlipayBatchListReq;
 
@Repository
public class AlipayBatchDao extends DaoBase  implements IAlipayBatchDao {
  
	@Override
	public PagedResponse<AlipayBatchModel>  getAlipayBatchPagedList(PagedAlipayBatchListReq req){
		PagedResponse<AlipayBatchModel> result = new PagedResponse<AlipayBatchModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"IAlipayBatchDao.getAlipayBatchPagedList", req);
		return result;
	}
	@Override
	public AlipayBatchModel getAlipayBatchById(Long id) {
		return getReadOnlySqlSessionUtil().selectOne(
				"IAlipayBatchDao.getAlipayBatchById", id);
	}
	@Override
	public List<AlipayBatchClienterWithdrawForm> getClienterWithdrawFormByBatchNo(
			Long id) {
		return getReadOnlySqlSessionUtil().selectList(
				"IAlipayBatchDao.getClienterWithdrawFormByBatchNo", id);
	}
}