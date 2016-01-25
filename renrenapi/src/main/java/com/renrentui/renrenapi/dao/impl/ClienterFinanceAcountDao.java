package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterFinanceAcountDao;
import com.renrentui.renrenentity.ClienterFinanceAcount;
import com.renrentui.renrenentity.domain.ClienterFinanceAcountModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawLogModel;

@Repository
public class ClienterFinanceAcountDao extends DaoBase implements
		IClienterFinanceAcountDao {

	/**
	 * 绑定支付宝账号，有就更新，没有则insert
	 */
	@Override
	public int insertSelective(ClienterFinanceAcount record) {
		return getMasterSqlSessionUtil().insert(
				"IClienterFinanceAcountDao.insertSelective", record);
	}

	@Override
	public ClienterFinanceAcount select(long clienterid, int accounttype) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterid", clienterid);
		paramMap.put("accounttype", accounttype);
		return getReadOnlySqlSessionUtil().selectOne(
				"IClienterFinanceAcountDao.select", paramMap);
	}

	@Override
	public int updateSelective(ClienterFinanceAcount record) {
		return getMasterSqlSessionUtil().update(
				"IClienterFinanceAcountDao.updateSelective", record);
	}
	/*
	 * 确认打款后更新打款状态 打款时间 写日志
	 * wangchao
	 */
	@Override
	public boolean ClienterWithdrawPayOk(
			ClienterWithdrawLogModel clienterWithdrawLogModel) {
		return getMasterSqlSessionUtil().update(
				"IClienterFinanceAcountDao.clienterWithdrawPayOk", clienterWithdrawLogModel)>0;
	}
	/*
	 * 修改骑士提款流水状态
	 * wangchao
	 */
	@Override
	public boolean ModifyClienterBalanceRecordStatus(
			ClienterWithdrawLogModel clienterWithdrawLogModel) {
		return getMasterSqlSessionUtil().update(
				"IClienterFinanceAcountDao.modifyClienterBalanceRecordStatus", clienterWithdrawLogModel)>0;
	}
	/*
	 * 修改骑士金额 wangchao
	 */
	@Override
	public boolean ModifyClienterTotalAmount(
			ClienterWithdrawLogModel clienterWithdrawLogModel) {
		return getMasterSqlSessionUtil().update(
				"IClienterFinanceAcountDao.modifyClienterTotalAmount", clienterWithdrawLogModel)>0;
	}
	/*
	 * 获取骑士提现单信息 wangchao
	 */
	@Override
	public ClienterFinanceAcountModel GetClienterFinanceAccount(Long withdrawId) {
		return getMasterSqlSessionUtil().selectOne("IClienterFinanceAcountDao.getClienterFinanceAccount", withdrawId);
	}
	/*
	 * 提现失败插入失败流水 记录 wangchao
	 */
	@Override
	public boolean InsertClienterBalanceFailRecord(
			ClienterWithdrawLogModel cwlModel) {
		return getMasterSqlSessionUtil().insert(
				"IClienterBalanceRecordDao.insertClienterBalanceFailRecord", cwlModel)>0;
	}
	/*
	 * 更改提现状态为失败 wangchao
	 */
	@Override
	public boolean ClienterWithdrawPayFailed(ClienterWithdrawLogModel cwlModel) {
		int k= getMasterSqlSessionUtil().update(
				"IClienterFinanceAcountDao.clienterWithdrawPayFailed", cwlModel);
		return k>0;
	}

	@Override
	public boolean ModifyClienterAmountInfo(ClienterWithdrawLogModel cwlModel) {
		int k= getMasterSqlSessionUtil().update(
				"IClienterFinanceAcountDao.modifyClienterAmountInfo", cwlModel);
		return k>0;
	}

}
