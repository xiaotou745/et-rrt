package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
@Service
public class ClienterService implements IClienterService{
	@Autowired
	private IClienterDao clienterDao;
	
	/**
	 * C端忘记密码
	 */
	@Override
	public boolean forgotPwdUserc(ForgotPwdReq req) {
		return clienterDao.forgotPassword(req);
	}
	/**
	 * 手机号是否存在 	C
	 * 茹化肖
	 * 2015年9月28日11:35:30
	 * 
	 */
	@Override
	public boolean isExistPhoneC(String phoneNo) {
		return clienterDao.isExistPhone(phoneNo);
	}
	/**
	 * 根据用户ID 验证密码是否正确
	 */
	@Override
	public boolean isRightPwd(int uid, String md5Pwd) {
		
		return clienterDao.isRightPwd(uid, md5Pwd);
	}
	/**
	 * C端修改密码
	 */
	@Override
	public boolean modifyPwdUserc(ModifyPwdReq req) {
		return clienterDao.modifyPwdUserc(req);
	}
		/*
	 * 注册
	 * WangChao
	 */
	@Override
	public boolean signup(SignUpReq req) {
		return clienterDao.signup(req); 
	} 
	

	
}
