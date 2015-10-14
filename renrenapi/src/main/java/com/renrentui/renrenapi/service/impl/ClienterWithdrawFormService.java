package com.renrentui.renrenapi.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrencore.enums.CBalanceRecordStatus;
import com.renrentui.renrencore.enums.CBalanceRecordType;
import com.renrentui.renrencore.enums.ClienterWithdrawFormStatus;
import com.renrentui.renrencore.enums.ClienterWithdrawFormWithType;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrenentity.ClienterBalance;
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
	
	/**
	 * @Des 用户提现 申请
	 * @Author 胡灵波
	 * @Date 2015年9月28日 16:58:06
	 * @param req
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public WithdrawState WithdrawC(ClienterBalanceReq req)
	{		
		ClienterBalance clienterBalanceModel= clienterBalanceDao.selectByClienterId(req.getUserId());

		double amount=clienterBalanceModel.getWithdraw();
		if(req.getAmount()>amount)
		{	
			return WithdrawState.MoneyError;
		}		
		
	     //创建提现表
		ClienterWithdrawForm clienterWithdrawFormModel=new ClienterWithdrawForm();
		clienterWithdrawFormModel.setClienterId(req.getUserId());
		clienterWithdrawFormModel.setAmount(req.getAmount());
		String no=OrderNoHelper.generateOrderCode(req.getUserId());
		clienterWithdrawFormModel.setWithdrawNo(no);
		clienterWithdrawFormModel.setWithType((short)ClienterWithdrawFormWithType.Alipay.value());//支付宝
		clienterWithdrawFormModel.setAccountInfo(req.getAccountInfo());
		clienterWithdrawFormModel.setTrueName(req.getTrueName());
		clienterWithdrawFormModel.setStatus((short)ClienterWithdrawFormStatus.UnAudit.value());//待审核				
		int cwfId= clienterWithdrawFormDao.insert(clienterWithdrawFormModel);
		
		ClienterBalanceReq cBReq=new ClienterBalanceReq();
		cBReq.setUserId(req.getUserId());
		cBReq.setAmount(-req.getAmount());
		int cbId= clienterBalanceDao.updateMoneyByKey(cBReq);
		
	    ClienterBalanceRecord clienterBalanceRecordModel=new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(req.getUserId());
		clienterBalanceRecordModel.setAmount(-Math.abs(req.getAmount()));		
		clienterBalanceRecordModel.setRecordType((short)CBalanceRecordType.ApplicationFor.value());//提现申请		
		clienterBalanceRecordModel.setOptName(req.getTrueName());
		clienterBalanceRecordModel.setOrderId((long)clienterWithdrawFormModel.getId());
		clienterBalanceRecordModel.setRelationNo(no);
		clienterBalanceRecordModel.setRemark("提现申请");
		clienterBalanceRecordModel.setStatus((short)CBalanceRecordStatus.Trading.value());//交易中
		int cbrId=clienterBalanceRecordDao.insert(clienterBalanceRecordModel);				
		
		if(cwfId>0&&cbId>0&&cbrId>0)
		{
			return WithdrawState.Success;
		}
		/*else
		{
			Error error=new Error("提现出错");
			throw new RuntimeErrorException(error);
		}	*/
		
		return WithdrawState.Failure;
	}
	
	/**
	 * @Des 审核通过
	 * @Author 胡灵波
	 * @Date 2015年9月28日 16:58:06
	 * @param 当前只传提现单Id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public	int AuditPass(ClienterWithdrawForm record) 
	{		
		long id=record.getId();//提现单Id
		
		ClienterBalanceRecord cbrModel= clienterBalanceRecordDao.selectByOrderId(id);
		if(cbrModel.getStatus()!=2) return 0;	
		
		//提现表 更新审核状态
		record.setStatus((short)ClienterWithdrawFormStatus.Audited.value());//审核通过	
		record.setAuditTime(new Date());
		int cwfId= clienterWithdrawFormDao.updateByPrimaryKeySelective(record);		
	
		//流水表 更新提现状态
		ClienterBalanceRecord cbrModelU= new ClienterBalanceRecord();
		cbrModelU.setOrderId(cbrModel.getOrderId());
		cbrModelU.setStatus((short)CBalanceRecordStatus.Success.value());//交易成功
		int cbrId= clienterBalanceRecordDao.updateStatusByOrderId(cbrModelU);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", record.getAmount());
		params.put("clienterId", record.getClienterId());		
		//更新累积提现
		int cbId=clienterBalanceDao.updateHadWithdrawByClienterId(params);
		
		if(cwfId>0 && cbrId>0 && cbId>0)
			return 1;
		else
		{
			Error error=new Error("审核通过失败");
			throw new RuntimeErrorException(error);
		}			
		
	}
	
	/**
	 * @Des 审核拒绝
	 * @Author 胡灵波
	 * @Date 2015年9月28日 16:58:06
	 * @param req
	 * @return
	 */
	@Override
	public	int AuditRefuse(ClienterWithdrawForm record) 
	{
		long id=record.getId();//提现单Id
		ClienterBalanceRecord cbrModel= clienterBalanceRecordDao.selectByOrderId(id);
		if(cbrModel.getStatus()!=2) return 0;		
					
		//更新提现单审核状态
		record.setStatus((short)ClienterWithdrawFormStatus.AuditRefuse.value());//审核拒绝
		record.setAuditTime(new Date());
		int cwfId= clienterWithdrawFormDao.updateByPrimaryKeySelective(record);	
		
		//流水表 更新提现状态
		ClienterBalanceRecord cbrModelU= new ClienterBalanceRecord();
		cbrModelU.setOrderId(cbrModel.getOrderId());
		cbrModelU.setStatus((short)CBalanceRecordStatus.Success.value());//交易成功
		int cbrId= clienterBalanceRecordDao.updateStatusByOrderId(cbrModelU);		
		
		//更新用户余额，可提现余额
		ClienterBalanceReq cBReq=new ClienterBalanceReq();
		cBReq.setUserId(cbrModel.getClienterId());
		cBReq.setAmount(-cbrModel.getAmount());
		int cbId= clienterBalanceDao.updateMoneyByKey(cBReq);
		
		//写入审核拒绝流水
	    ClienterBalanceRecord clienterBalanceRecordModel=new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(cbrModel.getClienterId());
		clienterBalanceRecordModel.setAmount(-cbrModel.getAmount());		
		clienterBalanceRecordModel.setRecordType((short)CBalanceRecordType.DenialOf.value());//		
		clienterBalanceRecordModel.setOptName(record.getAuditName());//
		clienterBalanceRecordModel.setOrderId((long)cbrModel.getOrderId());//
		clienterBalanceRecordModel.setRelationNo(cbrModel.getRelationNo());//
		clienterBalanceRecordModel.setRemark("申请拒绝失败");
		clienterBalanceRecordModel.setStatus((short)CBalanceRecordStatus.Success.value());	
		int cbrIdInsert=clienterBalanceRecordDao.insert(clienterBalanceRecordModel);			
	
		if(cwfId>0 && cbrId>0 &&  cbId>0 && cbrIdInsert>0)
			return 1;
		else
		{
			Error error=new Error("审核拒绝");
			throw new RuntimeErrorException(error);
		}			
		
	}
	@Override
	public	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req)
	{
		return clienterWithdrawFormDao.getList(req);		
	}
}
