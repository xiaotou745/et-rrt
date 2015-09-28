package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;

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
}