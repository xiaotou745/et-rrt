package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.req.ForgotPwdReq;


/**
 * 
 * C端用户相关
 * @author ofmyi_000
 *
 */
public interface IUsercService {
	/**
	 * 忘记密码
	 * @param req
	 * @return
	 */
	boolean forgotPwdUserc(ForgotPwdReq req);
	
	/**
	 * 手机号是否存在
	 * @param phoneNo
	 * @return
	 */
	boolean isExistPhoneC(String phoneNo);
}
