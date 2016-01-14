package com.renrentui.renrenapi.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.domain.AlipayClienterWithdrawModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.domain.ClienterWithdrawLog;
import com.renrentui.renrenentity.req.AlipayBatchReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;
import com.renrentui.renrenentity.req.UpdateAlipayBatchReq;


@Repository
public class ClienterWithdrawFormDao extends DaoBase implements IClienterWithdrawFormDao {

	@Override
	public int insert(ClienterWithdrawForm record) {	
		return getMasterSqlSessionUtil().insert(
				"IClienterWithdrawFormDao.insert", record);
	}
	@Override
	public ClienterWithdrawForm selectByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().selectOne(
				"IClienterWithdrawFormDao.selectByPrimaryKey", id);		
	}
	
	public ClienterWithdrawForm selectById(Long id)
	{
		return getMasterSqlSessionUtil().selectOne(
				"IClienterWithdrawFormDao.selectById", id);		
	}
	
	
	@Override
	public	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req){
		PagedResponse<ClienterWithdrawFormDM> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"IClienterWithdrawFormDao.getList",						 
						req);
		return model;
	}
	
	@Override
	public int updateByPrimaryKeySelective(ClienterWithdrawForm record) {	
		return getMasterSqlSessionUtil().update(
				"IClienterWithdrawFormDao.updateByPrimaryKeySelective", record);
	}
	@Override
	public int CheckAlipayBatch(AlipayBatchModel alipayBatchModel) { 
		int i =getMasterSqlSessionUtil().selectOne(
				"IClienterWithdrawFormDao.checkAlipayBatch", alipayBatchModel);
		return i;
	}
	@Override
	public List<AlipayClienterWithdrawModel> GetWithdrawListForAlipay(
			AlipayBatchReq alipayBatchReq) {
		return getMasterSqlSessionUtil().selectList(
				"IClienterWithdrawFormDao.getWithdrawListForAlipay", alipayBatchReq); 
	}
	@Override
	public int InsertLog(ClienterWithdrawLog clienterWithLog) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().insert(
				"IClienterWithdrawFormDao.insertLog", clienterWithLog); 
	}
	@Override
	public int UpdateAlipayBatchNo(UpdateAlipayBatchReq updateAlipayBatchReq) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().update(
				"IClienterWithdrawFormDao.updateAlipaySuccessBatchNo", updateAlipayBatchReq); 
	}
	@Override
	public int InsertAlipayBatch(AlipayBatchModel insertAlipayBatchModel) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().insert(
				"IClienterWithdrawFormDao.insertAlipayBatch", insertAlipayBatchModel); 
	}
	@Override
	public int UpdateAlipayBatchForAgain(
			AlipayBatchModel updateAlipayBatchModel) {
		return getMasterSqlSessionUtil().update(
				"IClienterWithdrawFormDao.updateAlipayBatchForAgain", updateAlipayBatchModel);
	}
	@Override
	public int UpdateAlipayBatchNo(AlipayBatchModel alipayBatchModel) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().update(
				"IClienterWithdrawFormDao.updateAlipayBatchNo", alipayBatchModel);
	}
	
}
