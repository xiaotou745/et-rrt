package com.renrentui.renrenapihttp.service.impl;

import org.springframework.stereotype.Service;

import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IUsercService;
import com.renrentui.renrenentity.req.ForgotPwdReq;
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

}
