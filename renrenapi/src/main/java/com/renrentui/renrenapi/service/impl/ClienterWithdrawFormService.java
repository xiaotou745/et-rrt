package com.renrentui.renrenapi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;



@Service
public class ClienterWithdrawFormService implements IClienterWithdrawFormService{

	@Autowired
	private IClienterWithdrawFormDao clienterWithdrawFormDao;		
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;	
	@Autowired
	private IClienterBalanceDao clienterBalanceDao;
	

	@Override
	public int Add(ClienterWithdrawForm record) 
	{
		return clienterWithdrawFormDao.insert(record);
	}
	
	@Override
	public	int AuditPass(ClienterWithdrawForm record) 
	{		
		long id=record.getId();//提现单Id
		ClienterBalanceRecord cbrModel= clienterBalanceRecordDao.selectByOrderId(id);
		if(cbrModel.getStatus()!=2) return 0;	
		
		//提现表 更新审核状态
		record.setStatus((short)1);
		record.setAuditName("admin");
		record.setAuditTime(new Date());
		int cwfId= clienterWithdrawFormDao.updateByPrimaryKeySelective(record);		
	
		//流水表 更新提现状态
		ClienterBalanceRecord cbrModelU= new ClienterBalanceRecord();
		cbrModelU.setOrderId(cbrModel.getOrderId());
		cbrModelU.setStatus((short)1);
		clienterBalanceRecordDao.updateStatusByOrderId(cbrModelU);
		
		return 1;
	}
	@Override
	public	int AuditRefuse(ClienterWithdrawForm record) 
	{
		long id=record.getId();//提现单Id
		ClienterBalanceRecord cbrModel= clienterBalanceRecordDao.selectByOrderId(id);
		if(cbrModel.getStatus()!=2) return 0;		
					
		//更新提现单审核状态
		record.setStatus((short)2);
		record.setAuditName("admin");
		record.setAuditTime(new Date());
		int cwfId= clienterWithdrawFormDao.updateByPrimaryKeySelective(record);	
		
		//更新用户余额，可提现余额
		ClienterBalanceReq cBReq=new ClienterBalanceReq();
		cBReq.setUserId(cbrModel.getClienterId());
		cBReq.setAmount(-cbrModel.getAmount());
		int cbId= clienterBalanceDao.updateMoneyByKey(cBReq);
		
		//写入审核拒绝流水
	    ClienterBalanceRecord clienterBalanceRecordModel=new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(cbrModel.getClienterId());
		clienterBalanceRecordModel.setAmount(-cbrModel.getAmount());		
		clienterBalanceRecordModel.setRecordType((short)3);		
		clienterBalanceRecordModel.setOptName("admin");
		clienterBalanceRecordModel.setOrderId((long)101);
		clienterBalanceRecordModel.setRelationNo("001");
		clienterBalanceRecordModel.setRemark("申请拒绝");
		clienterBalanceRecordModel.setStatus((short)1);	
		int cbrId=clienterBalanceRecordDao.insert(clienterBalanceRecordModel);	
		
		//更新提现申请流水
		//把提现中改为提现完成
		cbrModel.setStatus((short)1);
		clienterBalanceRecordDao.updateStatusByOrderId(cbrModel);
		
		return 1;
	}
	@Override
	public	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req)
	{
		return clienterWithdrawFormDao.getList(req);		
	}
}
