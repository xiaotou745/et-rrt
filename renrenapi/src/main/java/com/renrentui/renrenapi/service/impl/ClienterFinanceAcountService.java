package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.impl.ClienterWithdrawFormDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterFinanceAcountDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterFinanceAcountService;
import com.renrentui.renrencore.enums.AccountType;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterFinanceAcount;
import com.renrentui.renrenentity.domain.ClienterFinanceAcountModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawLogModel;
import com.renrentui.renrenentity.req.BindAliPayReq;

@Service
public class ClienterFinanceAcountService implements
		IClienterFinanceAcountService {
	@Autowired
	private IClienterFinanceAcountDao clienterFinanceAcountDao;

	@Autowired
	private IClienterDao clienterDao;
	@Autowired
	private IClienterWithdrawFormDao clienterWithdrawFormDao;
	@Override
	public int bindAliPay(BindAliPayReq req) {
		Clienter clienter = clienterDao.getClienterByPhoneNo(req.getPhoneNo());
		if (clienter.getId().longValue()!=req.getUserId()) {
			return -1;
		}
		ClienterFinanceAcount record = new ClienterFinanceAcount();
		record.setClienterid(clienter.getId().intValue());
		record.setTruename(req.getAliName());
		record.setAccountno(req.getAliAccount());
		record.setAccounttype((short) AccountType.AliPay.value());
		record.setCreateby(clienter.getClienterName());
		record.setUpdateby(clienter.getClienterName());
		record.setIsenable(true);
		record.setBelongtype((short) 0);
		ClienterFinanceAcount old=clienterFinanceAcountDao.select(clienter.getId(),AccountType.AliPay.value());
		if (old==null) {
			return clienterFinanceAcountDao.insertSelective(record);
		}
		record.setId(old.getId());
		return clienterFinanceAcountDao.updateSelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean ClienterWithdrawPayOk(
			ClienterWithdrawLogModel clienterWithdrawLogModel) {
		boolean reg = false;
		if(clienterFinanceAcountDao.ClienterWithdrawPayOk(clienterWithdrawLogModel)){  //更新体现表支付状态 时间等信息并写入日志
			if(clienterFinanceAcountDao.ModifyClienterBalanceRecordStatus(clienterWithdrawLogModel)){ //修改骑士提款流水状态
				if(clienterFinanceAcountDao.ModifyClienterTotalAmount(clienterWithdrawLogModel)){//骑士提现打款确认后修改骑士表累计提款金额
					reg=true;
				}
			}
		}		
		return reg;
	}

	@Override
	public ClienterFinanceAcountModel GetClienterFinanceAccount(Long withdrawId) {
		 return clienterFinanceAcountDao.GetClienterFinanceAccount(withdrawId);
	}
	/*
	 * 骑士提现失败 wangchao
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean ClienterWithdrawPayFail(ClienterWithdrawLogModel cwlModel) {
		boolean reg = false; 
			if(clienterFinanceAcountDao.InsertClienterBalanceFailRecord(cwlModel)){  //骑士提现失败后返现==增加骑士余额流水记录 
				if(clienterFinanceAcountDao.ClienterWithdrawPayFailed(cwlModel)){//更新骑士提现单状态为失败					 
					if(clienterFinanceAcountDao.ModifyClienterAmountInfo(cwlModel)){//加骑士余额可提现余额 
					}
					reg=true; 
				}
			} 
		return false;
	}
}
