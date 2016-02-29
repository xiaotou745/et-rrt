package com.renrentui.renrenapihttp.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterFinanceAcountService;
import com.renrentui.renrenapi.service.inter.IClienterRelationService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IUsercService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.CodeType;
import com.renrentui.renrencore.enums.ForgotPwdCode;
import com.renrentui.renrencore.enums.ModifyPwdCode;
import com.renrentui.renrencore.enums.ModifyUserCReturnCode;
import com.renrentui.renrencore.enums.MyIncomeCode;
import com.renrentui.renrencore.enums.MyRecordCode;
import com.renrentui.renrencore.enums.SendSmsType;
import com.renrentui.renrencore.enums.SignUpCode;
import com.renrentui.renrencore.enums.TaskCode;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.RandomCodeStrGenerator;
import com.renrentui.renrencore.util.SmsUtils;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrencore.enums.SignInCode;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.domain.AlipayBatchCallBackModel;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.domain.PartnerDetail;
import com.renrentui.renrenentity.domain.PartnerItem;
import com.renrentui.renrenentity.domain.PartnerModel;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.domain.TaskModel;
import com.renrentui.renrenentity.req.BindAliPayReq;
import com.renrentui.renrenentity.req.CSendCodeReq;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.GetIncomeReq;
import com.renrentui.renrenentity.req.GetUserCReq;
import com.renrentui.renrenentity.req.ModifyUserCReq;
import com.renrentui.renrenentity.req.PagedPartnerListReq;
import com.renrentui.renrenentity.req.PartnerListReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.resp.SignInResp;
import com.renrentui.renrenentity.resp.SignUpResp;
import com.renrentui.renrenentity.req.SignInReq;

/**
 * 用户相关
 * 
 * @author 茹化肖
 * @date 2015年9月28日09:59:42
 */
@Service
public class UsercService implements IUsercService {

	@Autowired
	private IClienterService clienterService;

	@Autowired
	private IClienterBalanceService clienterBalanceService;

	@Autowired
	private IClienterWithdrawFormService clienterWithdrawFormService;

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private IClienterFinanceAcountService clienterFinanceAcountService;

	@Autowired
	private IClienterBalanceRecordService clienterBalanceRecordService;
	@Autowired
	private IClienterRelationService clienterRelationService;
	/**
	 * C端忘记密码 茹化肖 2015年9月28日10:44:52
	 * 
	 */
	@Override
	public HttpResultModel<Object> forgotPwd(ForgotPwdReq req) {
		HttpResultModel<Object> resultModel = new HttpResultModel<Object>();
		if (req.getPhoneNo() == null || req.getPhoneNo().equals(""))// 手机号为空
			return resultModel.setCode(ForgotPwdCode.PhoneNull.value()).setMsg(
					ForgotPwdCode.PhoneNull.desc());
		if (!clienterService.isExistPhoneC(req.getPhoneNo()))// 手机号不正确
			return resultModel.setCode(ForgotPwdCode.PhoneError.value())
					.setMsg(ForgotPwdCode.PhoneError.desc());
		if (req.getVerifyCode() == null || req.getVerifyCode().equals(""))// 验证码为空
			return resultModel.setCode(ForgotPwdCode.VerCodeNull.value())
					.setMsg(ForgotPwdCode.VerCodeNull.desc());
		String key = RedissCacheKey.RR_Clienter_sendcode_forgetPassword
				+ req.getPhoneNo();// RedisKey
		String redisValueString = redisService.get(key, String.class);
		if (!req.getVerifyCode().equals(redisValueString))// 验证码不正确
			return resultModel.setCode(ForgotPwdCode.VerCodeError.value())
					.setMsg(ForgotPwdCode.VerCodeError.desc());
		if (clienterService.forgotPwdUserc(req))// 修改密码成功
			return resultModel.setCode(ForgotPwdCode.Success.value()).setMsg(
					ForgotPwdCode.Success.desc());
		return resultModel.setCode(ForgotPwdCode.Fail.value()).setMsg(
				ForgotPwdCode.Fail.desc());// 设置失败
	}

	/**
	 * C端修改密码 2015年9月28日10:45:04 茹化肖
	 * 
	 */
	@Override
	public HttpResultModel<Object> modifyPwd(ModifyPwdReq req) {
		HttpResultModel<Object> model = new HttpResultModel<Object>();
		Boolean yzStatus=clienterService.isRightPwd(req.getUserId(), req.getOldPwd());
		if (!yzStatus)// 验证旧密码是否正确
			return model.setCode(ModifyPwdCode.OldPwdError.value()).setMsg(
					ModifyPwdCode.OldPwdError.desc());
		if (clienterService.modifyPwdUserc(req))
			return model.setCode(ModifyPwdCode.Success.value()).setMsg(
					ModifyPwdCode.Success.desc());
		return model.setCode(ModifyPwdCode.Fail.value()).setMsg(
				ModifyPwdCode.Fail.desc());
	}

	/**
	 * 用户申请提现
	 * 
	 * @author 胡灵波
	 * @date 2015年9月28日 11:30:15
	 * @return
	 */
	@Override
	public HttpResultModel<Object> withdraw(ClienterBalanceReq req) {
		HttpResultModel<Object> resultModel = new HttpResultModel<Object>();

		if (req.getUserId() < 1) {
			resultModel.setCode(WithdrawState.UserIDError.value());
			resultModel.setMsg(WithdrawState.UserIDError.desc());
			return resultModel;
		}
		
		if (req.getAmount() < 10) {
			resultModel.setCode(WithdrawState.MoreThenTen.value());
			resultModel.setMsg(WithdrawState.MoreThenTen.desc());
			return resultModel;
		}
		if (req.getAmount() > 1000) {
			resultModel.setCode(WithdrawState.LessThenOneThousand.value());
			resultModel.setMsg(WithdrawState.LessThenOneThousand.desc());
			return resultModel;
		}
		if(req.getAmount()%10>0){
			resultModel.setCode(WithdrawState.MustIntegralMultiple.value());
			resultModel.setMsg(WithdrawState.MustIntegralMultiple.desc());
			return resultModel;
		}
		WithdrawState code = clienterWithdrawFormService.WithdrawC(req);
		resultModel.setCode(code.value());
		resultModel.setMsg(code.desc());
		return resultModel;
	}

	/*
	 * C端注册 WangChao
	 */
	@Override
	public HttpResultModel<Object> signup(SignUpReq req) {
		HttpResultModel<Object> resultModel = new HttpResultModel<Object>();
		if (req.getPhoneNo().equals("")) {
			resultModel.setCode(SignUpCode.PhoneNull.value()).setMsg(
					SignUpCode.PhoneNull.desc());
		}
		if (clienterService.isExistPhoneC(req.getPhoneNo()))// 手机号不正确
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
		long id = clienterService.signup(req);
		if(id==(long)-1)
		{
			return resultModel.setCode(SignUpCode.RecommendPhoneNoExist.value()).setMsg(
					SignUpCode.RecommendPhoneNoExist.desc());
		}
		if(id==(long)-2)
		{
			return resultModel.setCode(SignUpCode.RecommendPhoneNoRelation.value()).setMsg(
					SignUpCode.RecommendPhoneNoRelation.desc());
		}
		if (id > 0) {// 注册成功
			SignUpResp sur = new SignUpResp();
			sur.setUserId(id);
			sur.setUserName(req.getName());
			resultModel.setData(sur).setCode(SignUpCode.Success.value())
					.setMsg(SignUpCode.Success.desc());
			return resultModel;
		}
		return resultModel.setCode(SignUpCode.Fail.value()).setMsg(
				SignUpCode.Fail.desc());// 注册失败
	}

	/**
	 * @Des C端登陆
	 * @Author WangXuDan
	 * @Date 2015年9月28日15:55:58
	 * @Return
	 */
	@Override
	public HttpResultModel<Object> signin(SignInReq req) {
		HttpResultModel<Object> resultModel = new HttpResultModel<Object>();
		SignInResp signInResp = new SignInResp();
		if (req.getPhoneNo().equals("") || req.getPassWord().equals(""))// 手机号或密码为空
			return resultModel.setCode(SignInCode.PhoneOrPwdNull.value())
					.setMsg(SignInCode.PhoneOrPwdNull.desc());
		if (!clienterService.isExistPhoneC(req.getPhoneNo()))// 手机号未注册
			return resultModel.setCode(SignInCode.PhoneUnRegistered.value())
					.setMsg(SignInCode.PhoneUnRegistered.desc());
		Clienter clienterModel = clienterService.queryClienter(req);
		if (clienterModel == null || clienterModel.getId() <= 0)// 手机号或密码错误
			return resultModel.setCode(SignInCode.PhoneOrPwdError.value())
					.setMsg(SignInCode.PhoneOrPwdError.desc());
		signInResp.setUserId(clienterModel.getId());
		signInResp.setUserName(clienterModel.getClienterName());
		return resultModel.setCode(SignInCode.Success.value())
				.setMsg(SignInCode.Success.desc()).setData(signInResp);
	}

	/**
	 * 获取验证码
	 * 
	 * @author haichao
	 * @date 2015年9月28日 14:49:55
	 * @return
	 * */
	@Override
	public HttpResultModel<Object> sendcode(CSendCodeReq req) {
		try {
			HttpResultModel<Object> resultModel = new HttpResultModel<Object>();
			String key = "";
			String phoneNo = req.getPhoneNo();
			String content = "";// "欢迎您的使用，验证码：#验证码#，请妥善保管相关信息。若非您本人操作，请忽略。";
			// 类型 1注册 2修改密码 3忘记密码
			CodeType codeType=CodeType.getEnum(req.getsType());
			if (codeType==null) {
				return resultModel.setCode(SendSmsType.CodeTypeError.value()).setMsg(SendSmsType.CodeTypeError.desc());
			}
			boolean checkPhoneNo = clienterService.isExistPhoneC(phoneNo);
			if (codeType!=CodeType.Register&&!checkPhoneNo) {
				return resultModel.setCode(SendSmsType.PhoneNotExists.value())
						.setMsg(SendSmsType.PhoneNotExists.desc());// 该手机号不存在，不能修改或忘记密码
			}
			switch (codeType) {
			case Register:
				if (checkPhoneNo) { // 手机号存在
					return resultModel.setCode(SendSmsType.PhoneExists.value())
							.setMsg(SendSmsType.PhoneExists.desc());// 该手机号已经存在，不能注册
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
			default:
				break;
			}

			String code = RandomCodeStrGenerator.generateCodeNum(6);// 获取随机数
			content = content.replace("#验证码#", code);
			redisService.set(key, code, 60 * 5);
			long resultValue = SmsUtils.sendSMS(phoneNo, content);
			if (resultValue <= 0) {
				return resultModel.setCode(SendSmsType.Fail.value()).setMsg(
						SendSmsType.Fail.desc());// 发送失败
			}
			return resultModel.setCode(SendSmsType.Success.value()).setMsg(
					SendSmsType.Success.desc());// 设置成功
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	/**
	 * @Des 获取用户收入
	 * @Author WangXuDan
	 * @Date 2015年9月28日17:31:59
	 * @Return
	 */
	@Override
	public HttpResultModel<ClienterDetail> getuserc(GetUserCReq req) {
		HttpResultModel<ClienterDetail> hrm = new HttpResultModel<ClienterDetail>();
		if (req.getUserId()<=0) {
			return hrm.setCode(MyIncomeCode.UserIdError.value()).setMsg(
					MyIncomeCode.UserIdError.desc());
		}
		if (!clienterService.isExistUserC(req.getUserId())){// 用户不存在
			return hrm.setCode(MyIncomeCode.UserIdUnexist.value()).setMsg(
					MyIncomeCode.UserIdUnexist.desc());
		}
		ClienterDetail clienterModel = clienterService.getUserC(req.getUserId());
		if (clienterModel == null || clienterModel.getId() <= 0){// 获取信息失败
			return hrm.setCode(MyIncomeCode.QueryIncomeError.value()).setMsg(
					MyIncomeCode.QueryIncomeError.desc());
		}
		if (!StringUtils.isEmpty(clienterModel.getHeadImage())){
		clienterModel.setFullHeadImage(PropertyUtils.getProperty("ImgShowUrl")+ clienterModel.getHeadImage());
		}
		return hrm.setCode(MyIncomeCode.Success.value())
				.setMsg(MyIncomeCode.Success.desc()).setData(clienterModel);
	}

	/**
	 * @Des C端修改个人基础信息
	 * @Author CaoHeYang
	 * @Date 20151008
	 * @param req
	 * @Return
	 */
	@Override
	public HttpResultModel<Object> modifyuserc(ModifyUserCReq req) {
		HttpResultModel<Object> resultModel = new HttpResultModel<Object>();
//		if (req.getAge() == null || req.getAge().intValue() <= 0) {
//			return resultModel.setCode(ModifyUserCReturnCode.AgeError.value())
//					.setMsg(ModifyUserCReturnCode.AgeError.desc());
//		}
		if (req.getSex() == null || (req.getSex().intValue() != 1 && req.getSex().intValue() != 2)) {
			return resultModel.setCode(ModifyUserCReturnCode.SexError.value())
					.setMsg(ModifyUserCReturnCode.SexError.desc());
		}
		if (req.getUserName() == null || req.getUserName().isEmpty()) {
			return resultModel.setCode(
					ModifyUserCReturnCode.UserNameError.value()).setMsg(
					ModifyUserCReturnCode.UserNameError.desc());
		}
		if (req.getUserId() == 0 || clienterService.modifyuserc(req) <= 0) {
			return resultModel.setCode(ModifyUserCReturnCode.UserError.value())
					.setMsg(ModifyUserCReturnCode.UserError.desc());
		}
		return resultModel.setCode(ModifyUserCReturnCode.Success.value())
				.setMsg(ModifyUserCReturnCode.Success.desc());
	}

	@Override
	public HttpResultModel<Object> bindAliPay(BindAliPayReq req) {
		HttpResultModel<Object> resultModel = new HttpResultModel<Object>();
		if (req.getPhoneNo() == null || req.getPhoneNo().equals(""))
			return resultModel.setCode(ForgotPwdCode.PhoneNull.value()).setMsg(
					ForgotPwdCode.PhoneNull.desc());
		if (req.getVerifyCode() == null || req.getVerifyCode().equals(""))
			return resultModel.setCode(ForgotPwdCode.VerCodeNull.value())
					.setMsg(ForgotPwdCode.VerCodeNull.desc());
		if (req.getAliAccount() == null || req.getAliAccount().equals(""))
			return resultModel.setCode(ForgotPwdCode.AliAccountNull.value())
					.setMsg(ForgotPwdCode.AliAccountNull.desc());
		if (req.getAliName() == null || req.getAliName().equals(""))
			return resultModel.setCode(ForgotPwdCode.AliNameNull.value())
					.setMsg(ForgotPwdCode.AliNameNull.desc());
		if (req.getUserId()<=0)
			return resultModel.setCode(ForgotPwdCode.UserIdError.value())
					.setMsg(ForgotPwdCode.UserIdError.desc());
		if (!clienterService.isExistPhoneC(req.getPhoneNo()))
			return resultModel.setCode(ForgotPwdCode.PhoneError.value())
					.setMsg(ForgotPwdCode.PhoneError.desc());
		String key = RedissCacheKey.RR_Clienter_sendcode_bindAliPay
				+ req.getPhoneNo();// RedisKey
		String redisValueString = redisService.get(key, String.class);
		if (!req.getVerifyCode().equals(redisValueString))
			return resultModel.setCode(ForgotPwdCode.VerCodeError.value())
					.setMsg(ForgotPwdCode.VerCodeError.desc());
		int result=clienterFinanceAcountService.bindAliPay(req);
		if (result>0){
			return resultModel.setCode(ForgotPwdCode.Success.value()).setMsg(
					ForgotPwdCode.Success.desc());
		}
		else {
			return resultModel.setCode(ForgotPwdCode.OtherPhone.value()).setMsg(
					ForgotPwdCode.OtherPhone.desc());
		}
	}

	@Override
	public HttpResultModel<TabModel<ClienterBalanceRecord>> getRecordList(GetIncomeReq req) {
		HttpResultModel<TabModel<ClienterBalanceRecord>> hrm = new HttpResultModel<TabModel<ClienterBalanceRecord>>();

		if (req.getUserId() <= 0) {
			return hrm.setCode(MyRecordCode.UserIdInValid.value()).setMsg(
					MyRecordCode.UserIdInValid.desc());
		}
		if (req.getRecordType() <1||req.getRecordType()>2) {
			return hrm.setCode(MyRecordCode.RecordTypeError.value()).setMsg(
					MyRecordCode.RecordTypeError.desc());
		}
		if (!clienterService.isExistUserC(req.getUserId())) {
			return hrm.setCode(MyRecordCode.UserIdUnexist.value()).setMsg(
					MyRecordCode.UserIdUnexist.desc());
		}

		List<ClienterBalanceRecord> records = clienterBalanceRecordService.getRecordList(req);
		TabModel<ClienterBalanceRecord> resp = new TabModel<ClienterBalanceRecord>();
		resp.setCount(records.size());
		if(records!=null && records.size()>0){
			resp.setNextId(records.get(records.size()-1).getId());
		}
		resp.setContent(records);
		hrm.setData(resp);
		return hrm;
	}

	@Override
	public HttpResultModel<TabModel<PartnerDetail>> getClienterListByTaskId(
			PartnerListReq req) {
		HttpResultModel<TabModel<PartnerDetail>> hrm = new HttpResultModel<TabModel<PartnerDetail>>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getTaskId()<=0){
			hrm.setCode(TaskCode.TaskId.value()).setMsg(TaskCode.TaskId.desc());			
			return hrm;
		} 

		List<PartnerDetail> result=clienterService.getClienterListByTaskId(req);
		long partnerTotal=clienterService.getClienterListByTaskIdTotal(req.getTaskId());
		TabModel<PartnerDetail> td = new TabModel<PartnerDetail>();
		td.setContent(result);
		td.setCount(result.size());
		td.setTotal(partnerTotal);
		if(result!=null && result.size()>0){
			td.setNextId(result.get(result.size()-1).getCtId());
		}
		hrm.setData(td); 
		return hrm;
	}

	@Override
	public HttpResultModel<PartnerModel> getPartnerInfo(GetUserCReq req) {
		HttpResultModel<PartnerModel> hrm = new HttpResultModel<PartnerModel>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getUserId()<=0){
			return hrm.setCode(MyIncomeCode.UserIdError.value()).setMsg(
					MyIncomeCode.UserIdError.desc());
		} 
		PartnerModel detail=clienterService.getPartnerInfo(req.getUserId());
		hrm.setData(detail);
		return hrm;
	}

	@Override
	public HttpResultModel<TabModel<PartnerItem>> getPartnerList(
			PagedPartnerListReq req) {
		HttpResultModel<TabModel<PartnerItem>> hrm = new HttpResultModel<TabModel<PartnerItem>>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getUserId()<=0){
			hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
			return hrm;
		} 

		List<PartnerItem> result=clienterRelationService.getPagedPartnerListByUserId(req);

		TabModel<PartnerItem> td = new TabModel<PartnerItem>();
		td.setContent(result);
		td.setCount(result.size());
		if(result.size()>0){
			td.setNextId(result.get(result.size()-1).getId());
			PartnerModel detail=clienterService.getPartnerInfo(req.getUserId());
			td.setTotal(detail.getPartnerNum());
		}
		hrm.setData(td); 
		return hrm;
	}
}
