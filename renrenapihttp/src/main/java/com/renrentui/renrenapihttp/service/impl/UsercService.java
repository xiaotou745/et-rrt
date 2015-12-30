package com.renrentui.renrenapihttp.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterFinanceAcountService;
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
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.RandomCodeStrGenerator;
import com.renrentui.renrencore.util.SmsUtils;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrencore.enums.SignInCode;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.domain.BalanceRecordModel;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.req.BindAliPayReq;
import com.renrentui.renrenentity.req.CSendCodeReq;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.GetUserCReq;
import com.renrentui.renrenentity.req.ModifyUserCReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.resp.GetUserCResp;
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
			resultModel.setCode(WithdrawState.Failure.value());
			resultModel.setMsg(WithdrawState.Failure.desc());
			return resultModel;
		}
		if (req.getAmount() < 10) {
			resultModel.setCode(WithdrawState.ParaError.value());
			resultModel.setMsg(WithdrawState.ParaError.desc());
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
		if (req.getVerifyCode().equals(""))// 验证码不能为空
			return resultModel.setCode(SignUpCode.VerCodeNull.value()).setMsg(
					SignUpCode.VerCodeNull.desc());
		if (req.getName() == null) {
			req.setName("");
		}
		String key = RedissCacheKey.RR_Clienter_sendcode_register
				+ req.getPhoneNo();// 注册key
		String redisValueString = "1234";//redisService.get(key, String.class); //todo 测试暂时把验证码设计1234
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
	public HttpResultModel<GetUserCResp> getuserc(GetUserCReq req) {
		HttpResultModel<GetUserCResp> hrm = new HttpResultModel<GetUserCResp>();
		GetUserCResp resp = new GetUserCResp();
		if (!clienterService.isExistUserC(req.getUserId()))// 用户不存在
			return hrm.setCode(MyIncomeCode.UserIdUnexist.value()).setMsg(
					MyIncomeCode.UserIdUnexist.desc());
		ClienterDetail clienterModel = clienterService
				.getUserC(req.getUserId());
		if (clienterModel == null || clienterModel.getId() <= 0)// 获取信息失败
			return hrm.setCode(MyIncomeCode.QueryIncomeError.value()).setMsg(
					MyIncomeCode.QueryIncomeError.desc());

		// 这里写的很恶心啊，本来是想改的，但是app端已经开始调和接口了，没法改列属性，由于本次上线急，暂时不改，
		// 改的时候要和APP把属性从新更新，全啊以数据库列为准

		resp.setUserId(clienterModel.getId());
		resp.setUserName(clienterModel.getClienterName());
		resp.setPhoneNo(clienterModel.getPhoneNo());
		resp.setHeadImage(clienterModel.getHeadImage());
		// 全路径
		String fullHeadImage = "";
		if (!StringUtils.isEmpty(clienterModel.getHeadImage()))
			fullHeadImage = PropertyUtils.getProperty("ImgShowUrl")
					+ clienterModel.getHeadImage();
		resp.setFullHeadImage(fullHeadImage);

		resp.setCityCode(clienterModel.getCityCode());
		resp.setCityName(clienterModel.getCityName());
		resp.setSex(clienterModel.getSex());
		resp.setAge(clienterModel.getAge());
		resp.setEducation(clienterModel.getEducation());
		resp.setStatus(clienterModel.getStatus());
		resp.setBalance(clienterModel.getBalance());
		resp.setWithdraw(clienterModel.getWithdraw());
		resp.setHadWithdraw(clienterModel.getHadWithdraw());
		resp.setChecking(clienterModel.getChecking());
		resp.setWithdrawing(clienterModel.getWithdrawing());
		resp.setTotalAmount(clienterModel.getTotalAmount());
		return hrm.setCode(MyIncomeCode.Success.value())
				.setMsg(MyIncomeCode.Success.desc()).setData(resp);

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
		if (req.getAge() == null || req.getAge() <= 0) {
			return resultModel.setCode(ModifyUserCReturnCode.AgeError.value())
					.setMsg(ModifyUserCReturnCode.AgeError.desc());
		}
		if (req.getSex() == null || (req.getSex() != 1 && req.getSex() != 2)) {
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
		if (req.getPhoneNo() == null || req.getPhoneNo().equals(""))// 手机号为空
			return resultModel.setCode(ForgotPwdCode.PhoneNull.value()).setMsg(
					ForgotPwdCode.PhoneNull.desc());
		if (!clienterService.isExistPhoneC(req.getPhoneNo()))// 手机号不正确
			return resultModel.setCode(ForgotPwdCode.PhoneError.value())
					.setMsg(ForgotPwdCode.PhoneError.desc());
		if (req.getVerifyCode() == null || req.getVerifyCode().equals(""))// 验证码为空
			return resultModel.setCode(ForgotPwdCode.VerCodeNull.value())
					.setMsg(ForgotPwdCode.VerCodeNull.desc());
		String key = RedissCacheKey.RR_Clienter_sendcode_bindAliPay
				+ req.getPhoneNo();// RedisKey
		String redisValueString = redisService.get(key, String.class);
		if (!req.getVerifyCode().equals(redisValueString))// 验证码不正确
			return resultModel.setCode(ForgotPwdCode.VerCodeError.value())
					.setMsg(ForgotPwdCode.VerCodeError.desc());
		if (clienterFinanceAcountService.bindAliPay(req))// 修改密码成功
			return resultModel.setCode(ForgotPwdCode.Success.value()).setMsg(
					ForgotPwdCode.Success.desc());
		return resultModel.setCode(ForgotPwdCode.Fail.value()).setMsg(
				ForgotPwdCode.Fail.desc());// 设置失败
	}

	@Override
	public HttpResultModel<BalanceRecordModel> getRecordList(GetUserCReq req) {
		HttpResultModel<BalanceRecordModel> hrm = new HttpResultModel<BalanceRecordModel>();

		if (req.getUserId() <= 0) {
			return hrm.setCode(MyRecordCode.UserIdInValid.value()).setMsg(
					MyRecordCode.UserIdInValid.desc());
		}
		if (!clienterService.isExistUserC(req.getUserId())) {
			return hrm.setCode(MyRecordCode.UserIdUnexist.value()).setMsg(
					MyRecordCode.UserIdUnexist.desc());
		}

		List<ClienterBalanceRecord> records = clienterBalanceRecordService
				.getRecordList(req.getUserId());
		List<ClienterBalanceRecord> incomeList = records.stream()
				.filter(t -> t.getAmount() >= 0).collect(Collectors.toList());
		List<ClienterBalanceRecord> expensesList = records.stream()
				.filter(t -> t.getAmount() < 0).collect(Collectors.toList());

		incomeList.sort((b, a) -> {
			return a.getOperateTime().compareTo(b.getOperateTime());
		});
		expensesList.sort((b, a) -> {
			return a.getOperateTime().compareTo(b.getOperateTime());
		});
		BalanceRecordModel resp = new BalanceRecordModel();
		resp.setExpensesList(expensesList);
		resp.setInComeList(incomeList);
		hrm.setData(resp);
		return hrm;
	}

	/**
	 * 上传文件
	 * 
	 * @author 胡灵波
	 * @date 2015年10月12日 15:58:42
	 * @return
	 */
	/*
	 * @Override public HttpResultModel<Object> FileUpload(FileUploadReq req) {
	 * HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
	 * 
	 * byte [] bytes=req.getBytes(); String fileName=req.getFileName(); int
	 * uploadForm=req.getUploadForm();
	 * 
	 * FileOutputStream fos = null; try{ fos = new
	 * FileOutputStream("F:\\"+fileName);
	 * 
	 * //将字节数组bytes中的数据，写入文件输出流fos中 fos.write(bytes); fos.flush(); }catch
	 * (Exception e){ e.printStackTrace(); }finally{ try { fos.close(); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 * 
	 * return resultModel; }
	 */

}
