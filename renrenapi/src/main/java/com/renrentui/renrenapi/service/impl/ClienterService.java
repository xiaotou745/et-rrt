package com.renrentui.renrenapi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.Math.*;

import javax.management.RuntimeErrorException;

import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenapi.common.TransactionalRuntimeException;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrencore.enums.CBalanceRecordStatus;
import com.renrentui.renrencore.enums.CBalanceRecordType;
import com.renrentui.renrencore.enums.ClienterWithdrawFormStatus;
import com.renrentui.renrencore.enums.ClienterWithdrawFormWithType;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.domain.PartnerDetail;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.ClienterLoginLog;
import com.renrentui.renrenentity.ClienterRelation;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyUserCReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
import com.renrentui.renrenentity.req.PartnerListReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;
import com.renrentui.renrenentity.resp.ClienterResp;

@Service
public class ClienterService implements IClienterService {
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
	@Autowired
	private IClienterRelationDao clienterRelationDao;
	/**
	 * C端忘记密码
	 */
	@Override
	public boolean forgotPwdUserc(ForgotPwdReq req) {
		ClienterLog log = new ClienterLog();
		log.setClienterId(Long.valueOf("0"));
		log.setOptName("地推员电话:" + req.getPhoneNo());
		log.setRemark("地推员:" + req.getPhoneNo() + "忘记密码修改密码");
		int reslog = clienterLogDao.addClienterLog(log);// 记录C端日志
		return clienterDao.forgotPassword(req);
	}

	/**
	 * 手机号是否存在 C 茹化肖 2015年9月28日11:35:30
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
		ClienterLog log = new ClienterLog();
		log.setClienterId(Long.valueOf(req.getUserId()));
		log.setOptName("地推员UserID");
		log.setRemark("地推员修改密码");
		int reslog = clienterLogDao.addClienterLog(log);// 记录C端日志
		return clienterDao.modifyPwdUserc(req);
	}

	/**
	 * 
	 * 注册 茹化肖修改
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public long signup(SignUpReq req) {
		//1.判断有没有填写推荐人
		List<ClienterRelation> creRelations =null;
		Clienter recomClienter=null;
		if(req.getRecommendPhone()!=null&&!req.getRecommendPhone().equals(""))
		{
			//查询推荐人ID
			 recomClienter=clienterDao.getClienterByPhoneNo(req.getRecommendPhone());
			if(recomClienter==null)
			{
				return -1;//推荐人不存在
			}
			//推荐人存在 查询推荐人的关系
			creRelations=clienterRelationDao.getRelastionListByClienterId(recomClienter.getId());
			if(creRelations==null||creRelations.size()==0)
			{
				return -2;//推荐人没有推荐关系
			}
		}
		else {
			//设置推荐人为空
			req.setRecommendPhone("");
		}
		//2.注册骑士
		clienterDao.signup(req);
		//3.添加层级关系
		if(creRelations==null||creRelations.size()==0||req.getRecommendPhone()==null||req.getRecommendPhone().equals(""))
		{
			//没有推荐人 或者没有推荐人关系
			ClienterRelation cRelation=new ClienterRelation();
			cRelation.setClienterId(req.getId());
			cRelation.setOtherClienterId(Long.valueOf("0"));//自己相对于根0 
			cRelation.setOcJibie(1);//相对与根  0  0 是自己的向上1级
			cRelation.setClienterLevel(1);//自己所处的树 层为1 层  这里标注 0为根 为0层
			clienterRelationDao.insertClienterRelation(cRelation);
		}
		else {
			//自己的推荐人有层级关系,遍历推荐人的关系
			for(int  i=0;i<creRelations.size();i++)
			{
				ClienterRelation cRelation=new ClienterRelation();
				cRelation.setClienterId(req.getId());
				cRelation.setOtherClienterId(creRelations.get(i).getOtherClienterId());//别人的ID
				cRelation.setOcJibie((creRelations.get(i).getOcJibie()+1));//自己和别人的级别关系 就是 自己推荐人的级别关系+1
				cRelation.setClienterLevel(creRelations.size()+1);//自己所处的树 层为推荐人所有关系总和 (因为算了0这个ID了)
				clienterRelationDao.insertClienterRelation(cRelation);
			}
			//插入完自己和推荐人上级的关系
			ClienterRelation cRelation=new ClienterRelation();
			cRelation.setClienterId(req.getId());
			cRelation.setOtherClienterId(recomClienter.getId());//推荐人的ID
			cRelation.setOcJibie(1);//推荐人是自己的向上1级
			cRelation.setClienterLevel(creRelations.size()+1);
			clienterRelationDao.insertClienterRelation(cRelation);
		}
		long id = req.getId();
		if (id > 0)
		{
			//注册成功 初始化账户余额
			if (clienterBalanceDao.insert(id) <= 0) 
			{
				throw new TransactionalRuntimeException("添加新用户余额记录失败");
			}
			
		} 
		else
		{
			throw new TransactionalRuntimeException("添加新用户失败");
		}
		return id;
	}

	/**
	 * @Des 查询C端用户信息
	 * @Author WangXuDan
	 * @Date 2015年9月28日16:14:35
	 * @Return
	 */
	@Override
	public Clienter queryClienter(SignInReq req) {
		Clienter clienter= clienterDao.queryClienter(req);
		ClienterLoginLog log=new ClienterLoginLog();//登录日志
		log.setSSID(req.getsSID());
		log.setOperSystem(req.getOperSystem());
		log.setOperSystemModel(req.getOperSystemModel());
		log.setPhoneType(req.getPhoneType());
		log.setPhoneNo(req.getPhoneNo());
		log.setAppVersion(req.getAppVersion());
		if (clienter == null || clienter.getId() <= 0)// 手机号或密码错误
		{
			log.setClienterId(0);
			log.setDescription("登录失败");
			log.setIsSuccess(0);
		}
		else {//登录成功
			log.setClienterId(Integer.valueOf(clienter.getId().toString()));
			log.setDescription("登录成功");
			log.setIsSuccess(1);
		}
		try {
			//不能因为插入日志出错影响正常登录流程
			clienterDao.insertLoginLog(log);
		} catch (Exception e) {
			
		}
		
		return clienter;
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
		if (!clienterDao.editClienterStatus(req)) {
			responseBase.setMessage("地推员审核状态更新失败！");
			return responseBase;
		}
		responseBase.setResponseCode(1);
		responseBase.setMessage("地推员审核状态更新成功！");
		return responseBase;

	}

	/**
	 * @Des C端修改个人基础信息
	 * @Author CaoHeYang
	 * @Date 20151008
	 * @param req
	 * @Return
	 */
	@Override
	public int modifyuserc(ModifyUserCReq req) {
		ClienterLog log = new ClienterLog();
		log.setClienterId(Long.valueOf(req.getUserId()));
		log.setOptName("地推员UserID");
		log.setRemark("地推员修改密码");
		int reslog = clienterLogDao.addClienterLog(log);// 记录C端日志
		Clienter clienter = new Clienter();
		clienter.setId(req.getUserId());
		clienter.setAge(req.getAge());
		clienter.setSex(req.getSex());
		clienter.setClienterName(req.getUserName());
		clienter.setLastOptName(req.getUserId() + "");
		clienter.setLastOptTime(new Date());
		clienter.setHeadImage(req.getHeadImage());
		return clienterDao.updateByPrimaryKeySelective(clienter);
	}

	@Override
	public List<PartnerDetail> getClienterListByTaskId(PartnerListReq req) {
		return clienterDao.getClienterListByTaskId(req);
	}
	@Override
	public long getClienterListByTaskIdTotal(long taskID) {
		return clienterDao.getClienterListByTaskIdTotal(taskID);
	}
}
