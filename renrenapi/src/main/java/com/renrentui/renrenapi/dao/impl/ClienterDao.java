package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.springframework.stereotype.Repository;



import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.common.TransactionalRuntimeException;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterLoginLog;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.domain.PartnerDetail;
import com.renrentui.renrenentity.domain.PartnerModel;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.PartnerListReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;
import com.renrentui.renrenentity.resp.ClienterResp;
import com.renrentui.renrenentity.resp.MyIncomeResp;

@Repository
public class ClienterDao extends DaoBase implements IClienterDao {

    /**
     *C端 修改骑士信息
     * @author CaoHeYang
     * @param record
     * @date  20151008
     * @return
     */
	@Override
	public int updateByPrimaryKeySelective(Clienter record) {
		String statement = "IClienterDao.updateByPrimaryKeySelective";
		return getMasterSqlSessionUtil().update(statement, record);
	}

	/**
	 * 验证手机号是否存在 茹化肖 2015年9月28日11:39:52
	 * 
	 */
	@Override
	public boolean isExistPhone(String phoneNo) {
		String statement = "IClienterDao.isExistPhone";
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
		String statement = "IClienterDao.forgotPassword";
		int res = getMasterSqlSessionUtil().update(statement, req);
		return res > 0;
	}

	/**
	 * 验证用户密码是否正确
	 */
	@Override
	public boolean isRightPwd(int uid, String md5Pwd) {
		String statement = "IClienterDao.isRightPwd";
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
		String statement = "IClienterDao.modifyPwdUserc";
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
				"IClienterDao.queryClienter",
				req);
		return result;
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public long signup(SignUpReq req) {
		return getMasterSqlSessionUtil().insert("IClienterDao.insert", req);
	}
	/**
	* @Des 根据用户Id判断是否存在  
	* @Author WangXuDan
	* @Date 2015年9月28日17:18:18
	* @Return
	*/
	@Override
	public boolean isExistUserC(long userId) {
		String statement = "IClienterDao.isExistUserC";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		int res= getReadOnlySqlSessionUtil().selectOne(statement,paramMap);
		return res>0;
	}
	/**
	* @Des 获取用户信息
	* @Author WangXuDan
	* @Date 2015年9月28日17:31:59
	* @Return
	*/
	@Override
	public ClienterDetail getUserC(long userId) {
		String statement = "IClienterDao.getUserC";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		return getReadOnlySqlSessionUtil().selectOne(statement,paramMap);
	}
	/**
	* @Des 获取地推员信息列表  
	* @Author WangXuDan
	* @Date 2015年9月29日16:15:39
	* @Return
	*/
	@Override
	public PagedResponse<ClienterResp> queryClienterList(ClienterReq req) {
		PagedResponse<ClienterResp> resp=new PagedResponse<ClienterResp>();
		resp = getReadOnlySqlSessionUtil().selectPageList(
				"IClienterDao.queryClienterList", req);
		return resp;
	}
	/**
	* @Des 修改用户状态 
	* @Author WangXuDan
	* @Date 2015年10月8日11:43:44
	* @Return
	*/
	@Override
	public boolean editClienterStatus(ModifyClienterStatusReq req) {
		String statement = "IClienterDao.editClienterStatus";
		int res = getMasterSqlSessionUtil().update(statement, req);
		return res > 0;
	}
	/**
	 * 
	 * 插入登录日志
	 * 
	 */
	@Override
	public boolean insertLoginLog(ClienterLoginLog log) {
		String statement = "IClienterDao.insertLoginLog";
		int res = getMasterSqlSessionUtil().insert(statement, log);
		return res > 0;
	}
	/**
	 * 通过手机号查询信息
	 */
	@Override
	public Clienter getClienterByPhoneNo(String phoneNo) {
		return getMasterSqlSessionUtil().selectOne("IClienterDao.getClienterByPhoneNo", phoneNo);
	}

	@Override
	public List<PartnerDetail> getClienterListByTaskId(PartnerListReq req) {
		return getReadOnlySqlSessionUtil().selectList("IClienterDao.getClienterListByTaskId", req);
	}

	@Override
	public long getClienterListByTaskIdTotal(long taskId) {
		return getReadOnlySqlSessionUtil().selectOne("IClienterDao.getClienterListByTaskIdTotal", taskId);
	}

	@Override
	public PartnerModel getPartnerInfo(long userId) {
		return getReadOnlySqlSessionUtil().selectOne("IClienterDao.getPartnerInfo", userId);
	}

	@Override
	public Clienter getClienterById(Long cid) {
		return getReadOnlySqlSessionUtil().selectOne("IClienterDao.getClienterById", cid);
	}

	@Override
	public List<String> getClienterheadimg() {
		return getMasterSqlSessionUtil().selectList("IClienterDao.getClienterheadimg");
	}

	@Override
	public boolean isBindWx(int clienterId,String openid) {
		String statement = "IClienterDao.isBindWx";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterId", clienterId);
		paramMap.put("openid", openid);
		int res = getReadOnlySqlSessionUtil().selectOne(statement, paramMap);
		return res > 0;
	}

	@Override
	public boolean updateClienterBindWx(int clienterId, String openid) {
		String statement = "IClienterDao.updateClienterBindWx";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterId", clienterId);
		paramMap.put("openid", openid);
		int res = getMasterSqlSessionUtil().update(statement, paramMap);
		return res>0;
	}

	@Override
	public int getClienterIdByPhone(String phoneNo) {
		String statement = "IClienterDao.getClienterIdByPhone";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("phoneNo", phoneNo);
		Clienter clienter = getReadOnlySqlSessionUtil().selectOne(statement, paramMap);
		if(clienter!=null){
			return ParseHelper.ToInt(clienter.getId());
		}else{
			return 0;
		} 
	}

	@Override
	public boolean isBindWxByClienterId(int clienterId) {
		String statement = "IClienterDao.isBindWxByClienterId";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterId", clienterId);
		int res = getReadOnlySqlSessionUtil().selectOne(statement, paramMap);
		return res > 0;
	}

	@Override
	public boolean isBindWxByOpenId(String openid) {
		String statement = "IClienterDao.isBindWxByOpenId";
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("openid", openid);
		int res = getReadOnlySqlSessionUtil().selectOne(statement, paramMap);
		return res > 0;
	} 
}
