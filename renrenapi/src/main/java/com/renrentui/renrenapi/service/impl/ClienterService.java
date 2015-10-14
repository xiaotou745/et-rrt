package com.renrentui.renrenapi.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Math.*;

import javax.management.RuntimeErrorException;

import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrencore.enums.CBalanceRecordStatus;
import com.renrentui.renrencore.enums.CBalanceRecordType;
import com.renrentui.renrencore.enums.ClienterWithdrawFormStatus;
import com.renrentui.renrencore.enums.ClienterWithdrawFormWithType;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.req.ClienterBalanceReq;import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyUserCReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;
import com.renrentui.renrenentity.resp.ClienterResp;

@Service
public class ClienterService implements IClienterService{
	@Autowired
	private IClienterDao clienterDao;
	
	@Autowired
	private IClienterBalanceDao clienterBalanceDao;
	
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;	
	
	@Autowired
	private IClienterWithdrawFormDao clienterWithdrawFormDao;	
	@Autowired
	private IClienterLogDao clienterLogDao;
	
	
	/**
	 * C端忘记密码
	 */
	@Override
	public boolean forgotPwdUserc(ForgotPwdReq req) {
		ClienterLog log=new ClienterLog();
		log.setClienterId(Long.valueOf("0"));
		log.setOptName("地推员电话:"+req.getPhoneNo());
		log.setRemark("地推员:"+req.getPhoneNo()+"忘记密码修改密码");
		int reslog=clienterLogDao.addClienterLog(log);//记录C端日志
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
		ClienterLog log=new ClienterLog();
		log.setClienterId(Long.valueOf(req.getUserId()));
		log.setOptName("地推员UserID");
		log.setRemark("地推员修改密码");
		int reslog=clienterLogDao.addClienterLog(log);//记录C端日志
		return clienterDao.modifyPwdUserc(req);
	}
		/*
	 * 注册
	 * WangChao
	 */
	@Override
	public long signup(SignUpReq req) {
		return clienterDao.signup(req); 
	} 
	/**
	* @Des 查询C端用户信息  
	* @Author WangXuDan
	* @Date 2015年9月28日16:14:35
	* @Return
	*/
	@Override
	public Clienter queryClienter(SignInReq req) {
		return clienterDao.queryClienter(req);
	}
	/**
	* @Des 根据用户Id判断是否存在  
	* @Author WangXuDan
	* @Date 2015年9月28日17:18:18
	* @Return
	*/
	@Override
	public boolean isExistUserC(long userId) {
		return clienterDao.isExistUserC(userId);
	}
	/**
	* @Des 获取用户收入 
	* @Author WangXuDan
	* @Date 2015年9月28日17:31:59
	* @Return
	*/
	@Override
	public ClienterDetail getUserC(long userId) {
		return clienterDao.getUserC(userId);
	}	
	
	/**
	* @Des 获取地推员信息列表  
	* @Author WangXuDan
	* @Date 2015年9月29日16:15:39
	* @Return
	*/
	@Override
	public PagedResponse<ClienterResp> queryClienterList(ClienterReq req) {
		return clienterDao.queryClienterList(req);
	}
	/**
	* @Des 修改用户状态 
	* @Author WangXuDan
	* @Date 2015年10月8日11:43:44
	* @Return
	*/
	@Override
	public ResponseBase editClienterStatus(ModifyClienterStatusReq req) {
		ResponseBase responseBase = new ResponseBase();
		responseBase.setResponseCode(-1);
		if(!clienterDao.editClienterStatus(req))
		{
			responseBase.setMessage("地推员审核状态更新失败！");
			return responseBase;
		}
		responseBase.setResponseCode(1);
		responseBase.setMessage("地推员审核状态更新成功！");
		return responseBase;
		
	}
/**
	* @Des  C端修改个人基础信息
	* @Author CaoHeYang
	* @Date 20151008
	* @param req
	* @Return
	*/
	@Override
	public int modifyuserc(ModifyUserCReq req) {
		ClienterLog log=new ClienterLog();
		log.setClienterId(Long.valueOf(req.getUserId()));
		log.setOptName("地推员UserID");
		log.setRemark("地推员修改密码");
		int reslog=clienterLogDao.addClienterLog(log);//记录C端日志
		Clienter clienter =new Clienter();
		clienter.setId(req.getUserId());
		clienter.setAge(req.getAge());
		clienter.setSex(req.getSex());
		clienter.setClienterName(req.getUserName());
		clienter.setLastOptName(req.getUserId()+"");
		clienter.setLastOptTime(new Date());
		clienter.setHeadImage(req.getHeadImage());
		return clienterDao.updateByPrimaryKeySelective(clienter);
	}

	
}
