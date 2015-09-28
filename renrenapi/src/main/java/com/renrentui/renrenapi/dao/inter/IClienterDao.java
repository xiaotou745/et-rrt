package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.SignUpReq;

public interface IClienterDao {
    int deleteByPrimaryKey(Long id);

    int insert(Clienter record);

    int insertSelective(Clienter record);

    Clienter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);
    
	/**
	 * 验证手机号是否存在
	 * 茹化肖
	 * 2015年9月28日11:39:52
	 * 
	 */
    boolean isExistPhone(String phoneNo);
    /**
	 * 验证手机号是否存在
	 * 茹化肖
	 * 2015年9月28日11:39:52
	 * 
	 */
    boolean forgotPassword(ForgotPwdReq req);

	boolean signup(SignUpReq req);
}