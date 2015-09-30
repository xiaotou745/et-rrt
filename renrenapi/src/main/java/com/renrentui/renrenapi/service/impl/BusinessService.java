package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IBusinessBalanceDao;
import com.renrentui.renrenapi.dao.inter.IBusinessBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IBusinessDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.BusinessBalanceRecord;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;
@Service
public class BusinessService implements IBusinessService{
	@Autowired
	private IBusinessDao businessDao;
	
	@Autowired
	private IBusinessBalanceDao businessBalanceDao;
	
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;

	@Override
	public PagedResponse<Business> getBusinessList(PagedBusinessReq req) {
		// TODO Auto-generated method stub
		return businessDao.getBusinessList(req);
	}

	/**
	 * 添加商户
	 * 胡灵波
	 * 2015年9月29日 16:58:06
	 * @param req
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int Add(Business record) {
		record.setPassWord("111111");
		record.setLogo("");
		record.setCityCode(0);			
		businessDao.insert(record);
		
		BusinessBalance businessBalanceModel=new BusinessBalance();
		businessBalanceModel.setBusinessId(record.getId());
		businessBalanceModel.setBalance(0.0);
		businessBalanceDao.insert(businessBalanceModel);
		return 1;
	}
	
	/**
	 * 商户冲值
	 * 胡灵波
	 * 2015年9月29日 16:58:06
	 * @param req
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int AddBalance(BusinessBalanceReq req)
	{
		businessBalanceDao.updateBalanceByBusinessId(req);
		
		BusinessBalanceRecord businessBalanceRecordModel=new BusinessBalanceRecord();
		businessBalanceRecordModel.setBusinessId(req.getBusinessId());
		businessBalanceRecordModel.setAmount(req.getBalance());		
		businessBalanceRecordModel.setRecordType((short)2);		
		businessBalanceRecordModel.setOptName("admin");
		businessBalanceRecordModel.setOrderId((long)101);
		businessBalanceRecordModel.setRelationNo("001");
		businessBalanceRecordModel.setRemark("商户冲值");		
		businessBalanceRecordDao.insert(businessBalanceRecordModel);	
		return 1;
	}
	
}
