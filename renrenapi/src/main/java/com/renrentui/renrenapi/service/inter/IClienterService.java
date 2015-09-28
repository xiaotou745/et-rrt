package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;


/**
 * 
 * C端用户相关
 * @author ofmyi_000
 *
 */
public interface IClienterService {
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
	
	/**
	 * 验证用户ID 和密码是否正确
	 * @return
	 */
	boolean isRightPwd(int uid,String md5Pwd);
	/**
	 * 根据UID 修改密码
	 * 茹化肖
	 * 2015年9月28日15:02:52
	 * @param req
	 * @return
	 */
	boolean modifyPwdUserc(ModifyPwdReq req);

	boolean signup(SignUpReq req);
}
