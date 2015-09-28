package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;

@Repository
public class ClienterDao extends DaoBase implements IClienterDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Clienter selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 验证手机号是否存在 茹化肖 2015年9月28日11:39:52
	 * 
	 */
	@Override
	public boolean isExistPhone(String phoneNo) {
		String statement = "com.renrentui.renrenapi.dao.inter.IClienterDao.isExistPhone";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("phoneNo", phoneNo);
		int res = getReadOnlySqlSessionUtil().selectOne(statement, paramMap);
		return res > 0;
	}

	/**
	 * 忘记密码 2015年9月28日12:57:38 茹化肖
	 */
	@Override
	public boolean forgotPassword(ForgotPwdReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IClienterDao.forgotPassword";
		int res = getMasterSqlSessionUtil().update(statement, req);
		return res > 0;
	}

	/**
	 * 验证用户密码是否正确
	 */
	@Override
	public boolean isRightPwd(int uid, String md5Pwd) {
		String statement = "com.renrentui.renrenapi.dao.inter.IClienterDao.isRightPwd";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("md5Pwd", md5Pwd);
		int res = getReadOnlySqlSessionUtil().selectOne(statement, paramMap);
		return res > 0;

	}

	/**
	 * UID 修改密码 2015年9月28日15:14:36 茹化肖
	 */
	@Override
	public boolean modifyPwdUserc(ModifyPwdReq req) {
		String statement = "com.renrentui.renrenapi.dao.inter.IClienterDao.modifyPwdUserc";
		int res = getMasterSqlSessionUtil().update(statement, req);
		return res > 0;
	}
	/**
	* @Des 查询C端用户信息  
	* @Author WangXuDan
	* @Date 2015年9月28日16:14:35
	* @Return
	*/
	@Override
	public Clienter queryClienter(SignInReq req) {
		Clienter result = getReadOnlySqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IClienterDao.queryClienter",
				req);
		return result;
	}
	

	@Override
	public int signup(SignUpReq req) {
		String statement = "com.renrentui.api.dao.inter.IClienterDao.insert";
		int res = getMasterSqlSessionUtil().insert(statement, req);
		return req.getId();
	}

}
