package com.renrentui.renrenapihttp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.core.enums.ForgotPwdCode;
import com.renrentui.entity.req.CSendCodeReq;
import com.renrentui.entity.req.CWithdrawFormReq;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IUsercService;
import com.renrentui.renrencore.util.SmsUtils;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.req.ForgotPwdReq;
/**
 * 用户相关 
 * @author 茹化肖
 * @date 2015年9月28日09:59:42
 */
@Service
public class UsercService implements IUsercService{
	
	
	@Autowired 
	IClienterService  clienterService;
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
		if(!clienterService.isExistPhoneC(req.getPhoneNo()))//手机号不正确
			return resultModel.setCode(ForgotPwdCode.PhoneError.value()).setMsg(ForgotPwdCode.PhoneError.desc());
		if(req.getVerifyCode().equals(""))//验证码为空
			return resultModel.setCode(ForgotPwdCode.VerCodeNull.value()).setMsg(ForgotPwdCode.VerCodeNull.desc());
		if(req.getPhoneNo().equals(""))//验证码不正确   TODO 查缓存看验证码正确
			return resultModel.setCode(ForgotPwdCode.VerCodeError.value()).setMsg(ForgotPwdCode.VerCodeError.desc());
		if(clienterService.forgotPwdUserc(req))//修改密码成功
			return resultModel.setCode(ForgotPwdCode.Success.value()).setMsg(ForgotPwdCode.Success.desc());
		return resultModel.setCode(ForgotPwdCode.Fail.value()).setMsg(ForgotPwdCode.Fail.desc());//设置失败
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
	@Override
	public HttpResultModel<Object> withdraw(CWithdrawFormReq req) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取验证码
	 * @author haichao
	 * @date 2015年9月28日 14:49:55
	 * @return 
	 * */
	@Override
	public HttpResultModel<Object> sendcode() {//CSendCodeReq req
		try {
			HttpResultModel<Object> resultModel= new HttpResultModel<Object>();
			long resultValue=  SmsUtils.sendSMS("13426401627", "您的验证码是：1234");
			if(resultValue<=0)
		{	
				return resultModel.setCode(ForgotPwdCode.Fail.value()).setMsg(ForgotPwdCode.Fail.desc());//设置失败
		}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

