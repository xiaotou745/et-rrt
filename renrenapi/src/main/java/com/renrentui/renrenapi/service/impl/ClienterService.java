package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ClienterBalanceReq;import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;
import com.renrentui.renrenentity.resp.ClienterResp;
import com.renrentui.renrenentity.resp.MyIncomeResp;
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
	public MyIncomeResp queryClienterBalance(MyIncomeReq req) {
		return clienterDao.queryClienterBalance(req);
	}
	
	/**
	 * 用户提现
	 * 胡灵波
	 * 2015年9月28日 16:58:06
	 * @param req
	 * @return
	 */
	@Override
	public void WithdrawC(ClienterBalanceReq req)
	{
	     //创建提现表
		ClienterWithdrawForm clienterWithdrawFormModel=new ClienterWithdrawForm();
		clienterWithdrawFormModel.setClienterId(req.getUserId());
		clienterWithdrawFormModel.setAmount(req.getAmount());
		clienterWithdrawFormModel.setWithdrawNo("No001");
		clienterWithdrawFormModel.setWithType((short)1);
		clienterWithdrawFormModel.setAccountInfo("010101");
		clienterWithdrawFormModel.setTrueName("张三");
		clienterWithdrawFormModel.setStatus((short)0);				
		clienterWithdrawFormDao.insert(clienterWithdrawFormModel);
		
		ClienterBalanceReq cBReq=new ClienterBalanceReq();
		cBReq.setUserId(req.getUserId());
		cBReq.setAmount(-req.getAmount());
		clienterBalanceDao.updateMoneyByKey(cBReq);
		
	    ClienterBalanceRecord clienterBalanceRecordModel=new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(req.getUserId());
		clienterBalanceRecordModel.setAmount(-req.getAmount());		
		clienterBalanceRecordModel.setRecordType((short)2);		
		clienterBalanceRecordModel.setOptName("admin");
		clienterBalanceRecordModel.setOrderId((long)101);
		clienterBalanceRecordModel.setRelationNo("001");
		clienterBalanceRecordModel.setRemark("提现申请");		
		clienterBalanceRecordDao.insert(clienterBalanceRecordModel);		
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

	
}
