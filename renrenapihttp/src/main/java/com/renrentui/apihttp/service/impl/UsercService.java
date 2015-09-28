package com.renrentui.apihttp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.api.service.inter.IClienterBalanceService;
import com.renrentui.api.service.inter.IClienterWithdrawFormService;
import com.renrentui.apihttp.common.HttpResultModel;
import com.renrentui.apihttp.service.inter.IUsercService;
import com.renrentui.core.enums.WithdrawState;
import com.renrentui.entity.ClienterBalance;
import com.renrentui.entity.ClienterWithdrawForm;
import com.renrentui.entity.req.CWithdrawFormReq;
import com.renrentui.entity.req.ForgotPwdReq;
/**
 * 用户相关 
 * @author 茹化肖
 * @date 2015年9月28日09:59:42
 */
@Service
public class UsercService implements IUsercService{
	
	@Autowired
	private IClienterBalanceService clienterBalanceService;
	
	
	@Autowired
	private IClienterWithdrawFormService clienterWithdrawFormService;
	/**
	 * C端忘记密码 
	 * 茹化肖
	 * 2015年9月28日10:44:52
	 * 
	 */
	@Override
	public HttpResultModel<Object> forgotPwd(ForgotPwdReq req) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * C端修改密码
	 * 2015年9月28日10:45:04
	 * 茹化肖
	 * 
	 */
	@Override
	public HttpResultModel<Object> modifyPwd() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * C申请提现
	 * @author 胡灵波
	 * @date 2015年9月28日 11:30:15
	 * @return
	 */
	@Override	
	public HttpResultModel<Object> withdraw(CWithdrawFormReq req)
	{
		HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
		
		if(req.getUserId()<1)
		{
			resultModel.setCode(WithdrawState.Failure.value());
			resultModel.setMsg(WithdrawState.Failure.desc());
			return resultModel;
		}
		ClienterBalance clienterBalanceModel=clienterBalanceService.selectByPrimaryKey(req.getUserId());
		double amount=clienterBalanceModel.getWithdraw();
		if(req.getAmount()>amount)
		{
			resultModel.setCode(WithdrawState.MoneyError.value());
			resultModel.setMsg(WithdrawState.MoneyError.desc());
			return resultModel;
		}
		
		
		return resultModel;
	}
}
