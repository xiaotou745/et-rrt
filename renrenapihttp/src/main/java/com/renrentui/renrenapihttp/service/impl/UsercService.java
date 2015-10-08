package com.renrentui.renrenapihttp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IUsercService;
import com.renrentui.renrencore.cache.redis.RedisService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.ForgotPwdCode;
import com.renrentui.renrencore.enums.GetTaskCode;
import com.renrentui.renrencore.enums.ModifyPwdCode;
import com.renrentui.renrencore.enums.MyIncomeCode;
import com.renrentui.renrencore.enums.SendSmsType;
import com.renrentui.renrencore.enums.SignUpCode;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.util.RandomCodeStrGenerator;
import com.renrentui.renrencore.util.SmsUtils;
import com.renrentui.renrencore.enums.SignInCode;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.req.CSendCodeReq;
import com.renrentui.renrenentity.req.ClienterBalanceReq;import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.GetUserCReq;
import com.renrentui.renrenentity.req.ModifyUserCReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.resp.GetUserCResp;
import com.renrentui.renrenentity.resp.MyIncomeResp;
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
	IClienterService clienterService;
	
	@Autowired
	private IClienterBalanceService clienterBalanceService;	

	@Autowired
	RedisService redisService;

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
		String key=RedissCacheKey.RR_Clienter_sendcode_forgetPassword+ req.getPhoneNo();//RedisKey
		String redisValueString= redisService.get(key, String.class);
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
		if (clienterService.isRightPwd(req.getUserId(), req.getNewPwd()))// 验证旧密码是否正确
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
	 * @author 胡灵波
	 * @date 2015年9月28日 11:30:15
	 * @return
	 */
	@Override	
	public HttpResultModel<Object> withdraw(ClienterBalanceReq req)
	{
		HttpResultModel<Object> resultModel=new HttpResultModel<Object>();
		
		if(req.getUserId()<1)
		{
			resultModel.setCode(WithdrawState.Failure.value());
			resultModel.setMsg(WithdrawState.Failure.desc());
			return resultModel;
		}		
		
		WithdrawState code=clienterService.WithdrawC(req);		
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
		
		String key=RedissCacheKey.RR_Clienter_sendcode_register+ req.getPhoneNo();//注册key
		String redisValueString= redisService.get(key, String.class);
		if (!req.getVerifyCode().equals(redisValueString)) // 验证码 查缓存
			return resultModel.setCode(SignUpCode.VerCodeError.value()).setMsg(
					SignUpCode.VerCodeError.desc());
		long id = clienterService.signup(req);
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
		HttpResultModel<Object> resultModel= new HttpResultModel<Object>();
		SignInResp signInResp=new SignInResp();
		if(req.getPhoneNo().equals("")||req.getPassWord().equals(""))//手机号或密码为空
			return  resultModel.setCode(SignInCode.PhoneOrPwdNull.value()).setMsg(SignInCode.PhoneOrPwdNull.desc());
		if(!clienterService.isExistPhoneC(req.getPhoneNo()))//手机号未注册
			return resultModel.setCode(SignInCode.PhoneUnRegistered.value()).setMsg(SignInCode.PhoneUnRegistered.desc());
		Clienter clienterModel=clienterService.queryClienter(req);
		if(clienterModel==null||clienterModel.getId()<=0)//手机号或密码错误
			return resultModel.setCode(SignInCode.PhoneOrPwdError.value()).setMsg(SignInCode.PhoneOrPwdError.desc());
		signInResp.setUserId(clienterModel.getId());
		signInResp.setUserName(clienterModel.getClienterName());
		return resultModel.setCode(SignInCode.Success.value()).setMsg(SignInCode.Success.desc()).setData(signInResp);
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
			String phoneNo=req.getPhoneNo();
			// 类型 1注册 2修改密码 3忘记密码
			boolean checkPhoneNo=clienterService.isExistPhoneC(phoneNo);
			if (req.getsType() == 1) {
				// 注册
				//手机号存在
				if(checkPhoneNo){
					return resultModel.setCode(SendSmsType.PhoneExists.value()).setMsg(
							SendSmsType.PhoneExists.desc());//该手机号已经存在，不能注册
				}
				key = RedissCacheKey.RR_Clienter_sendcode_register
						+ phoneNo;
			} 
			if(!checkPhoneNo){
				return resultModel.setCode(SendSmsType.PhoneNotExists.value()).setMsg(
						SendSmsType.PhoneNotExists.desc());//该手机号不存在，不能修改或忘记密码
			}
			if (req.getsType() == 2) {
				// 修改密码
				key = RedissCacheKey.RR_Celitner_sendcode_UpdatePasswrd
						+ phoneNo;
			} else if (req.getsType() == 3) {
				// 忘记密码
				key = RedissCacheKey.RR_Clienter_sendcode_forgetPassword
						+ phoneNo;
			}
			if (key == "")
				return resultModel.setCode(SendSmsType.Fail.value()).setMsg(
						SendSmsType.Fail.desc());// 发送失败
			String code = RandomCodeStrGenerator.generateCodeNum(6);//获取随机数
			redisService.set(key, code, 60 * 5);
			long resultValue = SmsUtils.sendSMS(phoneNo, "您的验证码为:"
					+ code);
			if (resultValue <= 0) {
				return resultModel.setCode(SendSmsType.Fail.value()).setMsg(
						SendSmsType.Fail.desc());// 发送失败
			}
			return resultModel.setCode(SendSmsType.Success.value()).setMsg(
					SendSmsType.Success.desc());// 设置成功
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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
	public HttpResultModel<Object> getuserc(GetUserCReq req) {
		HttpResultModel<Object> resultModel= new HttpResultModel<Object>();
		GetUserCResp resp=new GetUserCResp();
		if(!clienterService.isExistUserC(req.getUserId()))//用户不存在
			return  resultModel.setCode(MyIncomeCode.UserIdUnexist.value()).setMsg(MyIncomeCode.UserIdUnexist.desc());
		ClienterDetail clienterModel=clienterService.getUserC(req.getUserId());
		if(clienterModel==null||clienterModel.getId()<=0)//获取信息失败
			return resultModel.setCode(MyIncomeCode.QueryIncomeError.value()).setMsg(MyIncomeCode.QueryIncomeError.desc());
		resp.setUserId(clienterModel.getId());
		resp.setUserName(clienterModel.getClienterName());
		resp.setPhoneNo(clienterModel.getPhoneNo());
		resp.setHeadImage(clienterModel.getHeadImage());
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
		return resultModel.setCode(MyIncomeCode.Success.value()).setMsg(MyIncomeCode.Success.desc()).setData(resp);
		
	}
	
	/**
	* @Des  C端修改个人基础信息
	* @Author CaoHeYang
	* @Date 20151008
	* @param req
	* @Return
	*/
	@Override
	public HttpResultModel<Object> modifyuserc(ModifyUserCReq req) {
		HttpResultModel<Object> resultModel= new HttpResultModel<Object>();
		
		return null;
	}


}
