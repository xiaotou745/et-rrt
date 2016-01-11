package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterFinanceAcount;
import com.renrentui.renrenentity.domain.ClienterFinanceAcountModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawLogModel;

public interface IClienterFinanceAcountDao {
	ClienterFinanceAcount select(long clienterid,int accounttype);
    int updateSelective(ClienterFinanceAcount record);
    int insertSelective(ClienterFinanceAcount record);
	boolean ClienterWithdrawPayOk(
			ClienterWithdrawLogModel clienterWithdrawLogModel);
	/*
	 * 修改骑士提款流水状态
	 * wangchao
	 */
	boolean ModifyClienterBalanceRecordStatus(
			ClienterWithdrawLogModel clienterWithdrawLogModel);
	/*
	 * 骑士提现打款确认后修改骑士表累计提款金额
	 * wangchao
	 */
	boolean ModifyClienterTotalAmount(
			ClienterWithdrawLogModel clienterWithdrawLogModel);
	ClienterFinanceAcountModel GetClienterFinanceAccount(Long withdrawId);
	boolean InsertClienterBalanceFailRecord(ClienterWithdrawLogModel cwlModel);
	boolean ClienterWithdrawPayFailed(ClienterWithdrawLogModel cwlModel);
}