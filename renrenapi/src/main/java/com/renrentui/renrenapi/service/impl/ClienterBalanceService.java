package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrencore.enums.CBalanceRecordStatus;
import com.renrentui.renrencore.enums.CBalanceRecordType;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.domain.SubmissionTip;
import com.renrentui.renrenentity.req.ClienterBalanceReq;



@Service
public class ClienterBalanceService implements IClienterBalanceService{

	@Autowired
	private IClienterBalanceDao clienterBalanceDao;	
	@Autowired
	private IClienterBalanceRecordService clienterBalanceRecordService;
	@Autowired
	private IClienterService clienterService; 
	@Override
	public  ClienterBalance selectByPrimaryKey(Long id)
	{
		ClienterBalance model=clienterBalanceDao.selectByPrimaryKey(id);		

		return model;	
	}


	/*
	 * 添加绑定微信记录和日志, 增加骑士余额，增加流水记录
	 * wangchao
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean fetchRedbag(int clienterId,String openid) {
		boolean reg = false; 
		boolean bindresult=clienterService.clienterBindWx(clienterId,openid);  //绑定地推员和微信关系
		if(bindresult){
			int a=insertBalanceRecordByWx(clienterId);  //增加流水
			if(a>0){
				int hongbao= modifyClienterBalanceByWx(clienterId); //增加余额
				if(hongbao>0){
					reg=true;
				}
			}		
		}
		return reg;
	}
	
	/*
	 * 绑定微信后增加地推员余额
	 * wangchao
	 */
	public int modifyClienterBalanceByWx(int clienterId){
		ClienterBalanceReq cbr=new ClienterBalanceReq();
		cbr.setAmount(2); // 红包2元，去哪里读取这个 红包金额？？哪里设置的
		cbr.setUserId(clienterId);
		return clienterBalanceDao.updateMoneyByKey(cbr);
	}
	 
	public int insertBalanceRecordByWx(int clienterId){
		ClienterBalanceRecord cbr = new ClienterBalanceRecord();
		cbr.setClienterId((long)clienterId);
		cbr.setAmount(2.0);
		cbr.setRecordType((short)CBalanceRecordType.ActivityRewards.value());
		cbr.setOptName("系统操作");
		cbr.setRemark("活动奖励");
		cbr.setStatus((short)CBalanceRecordStatus.Success.value());
		cbr.setOrderId(0l); 
		cbr.setRelationNo("");
		return clienterBalanceRecordService.insert(cbr);		
	}


}
