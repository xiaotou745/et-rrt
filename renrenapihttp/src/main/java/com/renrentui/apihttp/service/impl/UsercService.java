package com.renrentui.apihttp.service.impl;

import org.springframework.stereotype.Service;

import com.renrentui.apihttp.common.HttpResultModel;
import com.renrentui.apihttp.service.inter.IUsercService;
import com.renrentui.core.enums.ForgotPwdCode;
import com.renrentui.entity.req.ForgotPwdReq;
/**
 * 用户相关 
 * @author 茹化肖
 * @date 2015年9月28日09:59:42
 */
@Service
public class UsercService implements IUsercService{
	/**
	 * C端忘记密码 
	 * 茹化肖
	 * 2015年9月28日10:44:52
	 * 
	 */
	@Override
	public HttpResultModel<Object> forgotPwd(ForgotPwdReq req) {
		HttpResultModel<Object> resultModel= new HttpResultModel<Object>();
		if(req.getPhoneNo().equals(""))//手机号为空
			return  resultModel.setCode(ForgotPwdCode.PhoneNull.value()).setMsg(ForgotPwdCode.PhoneNull.desc());
		if(req.getPhoneNo().equals(""))//手机号不正确   TODO 查库验证手机号是否纯在
			return resultModel.setCode(ForgotPwdCode.PhoneError.value()).setMsg(ForgotPwdCode.PhoneError.desc());
		if(req.getVerifyCode().equals(""))//验证码为空
			return resultModel.setCode(ForgotPwdCode.VerCodeNull.value()).setMsg(ForgotPwdCode.VerCodeNull.desc());
		if(req.getPhoneNo().equals(""))//验证码不正确   TODO 查缓存看验证码正确
			return resultModel.setCode(ForgotPwdCode.VerCodeError.value()).setMsg(ForgotPwdCode.VerCodeError.desc());
		return resultModel;
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

}
