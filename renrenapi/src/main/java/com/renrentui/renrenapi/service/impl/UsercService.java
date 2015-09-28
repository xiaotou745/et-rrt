package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IUsercService;
import com.renrentui.renrenentity.req.ForgotPwdReq;
@Service
public class UsercService implements IUsercService{
	//@Autowired
		//private IMenuInfoDao dao;
	
	/**
	 * C端忘记密码
	 */
	@Override
	public boolean forgotPwdUserc(ForgotPwdReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 手机号是否存在 	C
	 * 茹化肖
	 * 2015年9月28日11:35:30
	 * 
	 */
	@Override
	public boolean isExistPhoneC(String phoneNo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
}
