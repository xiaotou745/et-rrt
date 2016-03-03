package com.renrentui.renrenapi.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.common.ImageCuter;
import com.renrentui.renrenapi.common.TransactionalRuntimeException;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterLogDao;
import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.CodeType;
import com.renrentui.renrencore.enums.SendSmsType;
import com.renrentui.renrencore.enums.SignUpCode;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.RandomCodeStrGenerator;
import com.renrentui.renrencore.util.SmsUtils;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterLog;
import com.renrentui.renrenentity.ClienterLoginLog;
import com.renrentui.renrenentity.ClienterRelation;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.common.ResultModel;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.domain.PartnerDetail;
import com.renrentui.renrenentity.domain.PartnerModel;
import com.renrentui.renrenentity.req.CSendCodeReq;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.ModifyUserCReq;
import com.renrentui.renrenentity.req.PartnerListReq;
import com.renrentui.renrenentity.req.SignInReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.resp.ClienterResp;
import com.renrentui.renrenentity.resp.SignUpResp;

@Service
public class ClienterService implements IClienterService {
	@Autowired
	private IClienterDao clienterDao;
	
	@Autowired
	private RedisService redisService;
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
		log.setOptName(req.getUserId()+"");
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
	public ResultModel<Object> signup(SignUpReq req) {
		ResultModel<Object> resultModel =new ResultModel<Object>();
		if (req.getPhoneNo().equals("")) {
			resultModel.setCode(SignUpCode.PhoneNull.value()).setMsg(
					SignUpCode.PhoneNull.desc());
		}
		if (isExistPhoneC(req.getPhoneNo()))// 手机号不正确
			return resultModel.setCode(SignUpCode.PhoneFormatError.value())
					.setMsg(SignUpCode.PhoneFormatError.desc());
		if(req.getOperSystem() == null || req.getOperSystem().equals("")){
			return resultModel.setCode(SignUpCode.NoOperSystem.value())
					.setMsg(SignUpCode.NoOperSystem.desc());
		}
		if (req.getVerifyCode().equals(""))// 验证码不能为空
			return resultModel.setCode(SignUpCode.VerCodeNull.value()).setMsg(
					SignUpCode.VerCodeNull.desc());
		if (req.getName() == null) {
			req.setName("");
		}
		String key = RedissCacheKey.RR_Clienter_sendcode_register
				+ req.getPhoneNo();// 注册key
		String redisValueString = redisService.get(key, String.class);
		if (!req.getVerifyCode().equals(redisValueString)) // 验证码 查缓存
			return resultModel.setCode(SignUpCode.VerCodeError.value()).setMsg(
					SignUpCode.VerCodeError.desc()); 
		//1.判断有没有填写推荐人
		List<ClienterRelation> creRelations =null;
		Clienter recomClienter=null;
		if(req.getRecommendPhone()!=null&&!req.getRecommendPhone().equals(""))
		{
			//查询推荐人ID
			 recomClienter=clienterDao.getClienterByPhoneNo(req.getRecommendPhone());
			if(recomClienter==null)
			{
				return resultModel.setCode(SignUpCode.RecommendPhoneNoExist.value()).setMsg(
						SignUpCode.RecommendPhoneNoExist.desc());//推荐人不存在
			}
			//推荐人存在 查询推荐人的关系
			creRelations=clienterRelationDao.getRelastionListByClienterId(recomClienter.getId(),1);
			if(creRelations==null||creRelations.size()==0)
			{
				return resultModel.setCode(SignUpCode.RecommendPhoneNoRelation.value()).setMsg(
						SignUpCode.RecommendPhoneNoRelation.desc());//推荐人没有推荐关系
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
			}else {
				SignUpResp sur = new SignUpResp();
				sur.setUserId(id);
				sur.setUserName(req.getName());
				resultModel.setData(sur).setCode(SignUpCode.Success.value())
						.setMsg(SignUpCode.Success.desc());
				return resultModel; 
			}
		} 
		else
		{
			return resultModel.setCode(SignUpCode.Fail.value()).setMsg(
					SignUpCode.Fail.desc());
			//throw new TransactionalRuntimeException("添加新用户失败");
		}
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
		List<String> head=clienterDao.getClienterheadimg();
		String imgPath=PropertyUtils.getProperty("FileUploadPath");
		for (String string : head) {
			try {
				String diskPath = imgPath+"/"+ string;
				File uploadedFile = new File(diskPath);
				if (uploadedFile.exists()) {
					int index=diskPath.lastIndexOf(".");
					String src=diskPath.substring(0, index)+"_0_0"+diskPath.substring(index);
					String des=diskPath.substring(0, index)+"_95_95"+diskPath.substring(index);
					File srcFile = new File(src);
					File desFile = new File(des);
					if (srcFile.exists()&&!desFile.exists()) {
						ImageCuter.cut(src, des, 95, 95);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
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
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int modifyuserc(ModifyUserCReq req) {
		ClienterLog log = new ClienterLog();
		log.setClienterId(Long.valueOf(req.getUserId()));
		log.setOptName("地推员UserID");
		log.setRemark("地推员修改个人基础信息");
		int reslog = clienterLogDao.addClienterLog(log);// 记录C端日志
		Clienter clienter = new Clienter();
		clienter.setId(req.getUserId());
		clienter.setAge(req.getAge());
		clienter.setSex(req.getSex());
		clienter.setClienterName(req.getUserName());
		clienter.setLastOptName(req.getUserId() + "");
		clienter.setLastOptTime(new Date());
		clienter.setHeadImage(req.getHeadImage());
		if (req.getBirthDay()!=null&&!req.getBirthDay().isEmpty()) {
			clienter.setBirthDay(ParseHelper.ToDate(req.getBirthDay()));
		}

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

	@Override
	public PartnerModel getPartnerInfo(long userId) {
		return clienterDao.getPartnerInfo(userId);
	}
	/**
	 * 
	 * 根据id查询地推员信息
	 */
	@Override
	public Clienter getClienterById(Long cid) {
		return clienterDao.getClienterById(cid);
	}

	@Override
	public ResponseBase sendcode(CSendCodeReq req) {
		ResponseBase resultModel = new ResponseBase();
		String key = "";
		String phoneNo = req.getPhoneNo();
		String content = "";// "欢迎您的使用，验证码：#验证码#，请妥善保管相关信息。若非您本人操作，请忽略。";
		// 类型 1注册 2修改密码 3忘记密码
		CodeType codeType=CodeType.getEnum(req.getsType());
		if (codeType==null) {
			 resultModel.setResponseCode(SendSmsType.CodeTypeError.value());
			 resultModel.setMessage(SendSmsType.CodeTypeError.desc());
			 return resultModel;
		}
		boolean checkPhoneNo = isExistPhoneC(phoneNo);
		if (codeType!=CodeType.Register&&!checkPhoneNo) {
			 resultModel.setResponseCode(SendSmsType.PhoneNotExists.value());
			 resultModel.setMessage(SendSmsType.PhoneNotExists.desc());// 该手机号不存在，不能修改或忘记密码
			 return resultModel;
		}
		switch (codeType) {
		case Register:
			if (checkPhoneNo) { // 手机号存在
				resultModel.setResponseCode(SendSmsType.PhoneExists.value());
				resultModel.setMessage(SendSmsType.PhoneExists.desc());// 该手机号已经存在，不能注册
				return resultModel;
			}
			key = RedissCacheKey.RR_Clienter_sendcode_register + phoneNo;
			content = "您的验证码：#验证码#，请在5分钟内填写。此验证码只用于注册，如非本人操作，请不要理会";
			break;
		case UpdatePasswrd:
			key = RedissCacheKey.RR_Celitner_sendcode_UpdatePasswrd+ phoneNo;
			content = "您的验证码：#验证码#，请在5分钟内填写。此验证码只用于修改密码，如非本人操作，请不要理会";
			break;
		case ForgetPassword:
			key = RedissCacheKey.RR_Clienter_sendcode_forgetPassword+ phoneNo;
			content = "您的验证码：#验证码#，请在5分钟内填写。此验证码只用于找回密码，如非本人操作，请不要理会";
			break;
		case BindAliPay:
			key = RedissCacheKey.RR_Clienter_sendcode_bindAliPay+ phoneNo;
			content = "您的验证码：#验证码#，请在5分钟内填写。此验证码只用于绑定支付宝，如非本人操作，请不要理会";
			break;
		case FetchRedBag:
			key = RedissCacheKey.RR_Clienter_sendcode_fetchRedBag+ phoneNo;
			content = "您的验证码：#验证码#，请在5分钟内填写。此验证码只用于绑定微信领奖励，如非本人操作，请不要理会";
			break;
		default:
			break;
		}

		String code = RandomCodeStrGenerator.generateCodeNum(6);// 获取随机数
		content = content.replace("#验证码#", code);
		redisService.set(key, code, 60 * 5);
		try{
			long resultValue = SmsUtils.sendSMS(phoneNo, content);
			if (resultValue <= 0) {
				 resultModel.setResponseCode(SendSmsType.Fail.value());
				 resultModel.setMessage(
						SendSmsType.Fail.desc());// 发送失败
				return resultModel;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		resultModel.setResponseCode(SendSmsType.Success.value());
		resultModel.setMessage(
				SendSmsType.Success.desc());// 设置成功
		return resultModel;
	}

	@Override
	public boolean isBindWx(int clienterId,String openid) {
		return clienterDao.isBindWx(clienterId,openid);
	}

	@Override
	public boolean clienterBindWx(int clienterId, String openid) {
		return clienterDao.updateClienterBindWx(clienterId,openid);
	}

	@Override
	public int getClienterIdByPhone(String phoneNo) {
		return clienterDao.getClienterIdByPhone(phoneNo);
	}
}
