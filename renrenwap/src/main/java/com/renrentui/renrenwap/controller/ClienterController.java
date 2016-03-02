package com.renrentui.renrenwap.controller; 
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.FetchRedbagEnum;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.common.ResultModel;
import com.renrentui.renrenentity.req.CSendCodeReq;
import com.renrentui.renrenentity.req.FetchRedbagReq;
import com.renrentui.renrenentity.req.SignUpReq;
 

@Controller
@RequestMapping("clienter")
public class ClienterController {
	
	@Autowired
	private IClienterService clienterService;
	@Autowired
	private IClienterBalanceService clienterBalanceService;
	@Autowired
	private IClienterWxService clienterWxService;
	@Autowired
	private RedisService redisService;
	/*
	 * 领红包页面获取验证码
	 * wangchao
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	public ResponseBase sendcode(CSendCodeReq req){ 
		ResponseBase responseBase= clienterService.sendcode(req);
		
		return responseBase; 
	}
	/*
	 * 领红包页面
	 * wangchao
	 */
	@RequestMapping("fetchredbag")
	public ModelAndView fetchredbag(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		String openid=request.getParameter("openid");
		view.addObject("openid", openid);
		view.addObject("viewPath", "clienter/fetchredbag");
		return view;
	}
	/*
	 * 领红包事件
	 * wangchao
	 */
	@RequestMapping("fetchredbagsubmit")
	@ResponseBody
	public ResultModel<Object> fetchredbagsubmit(HttpServletRequest request,FetchRedbagReq req){
		ResultModel<Object> resultModel = new ResultModel<Object>(); 
		String openid=request.getParameter("openid");
		String truename = request.getParameter("truename");
		String basePath=PropertyUtils.getProperty("java.renrenwap.url");
		if(openid==""||openid==null || truename=="" || truename == null){
			return resultModel.setCode(FetchRedbagEnum.ParaError.value()).setMsg(FetchRedbagEnum.ParaError.desc());
		}
		if(req.getPhoneNo()==""){
			return resultModel.setCode(FetchRedbagEnum.PhoneNoError.value()).setMsg(FetchRedbagEnum.PhoneNoError.desc());
		}
		req.setOpenid(openid);
		req.setTruename(truename);
		String keyString=RedissCacheKey.RR_Clienter_sendcode_fetchRedBag+ req.getPhoneNo();
		String codeRedisString=redisService.get(keyString, String.class);
		if (!req.getCode().equals(codeRedisString)) // 验证码 查缓存
			return resultModel.setCode(FetchRedbagEnum.VerCodeError.value()).setMsg(FetchRedbagEnum.VerCodeError.desc()); 
		int clienterId=clienterService.getClienterIdByPhone(req.getPhoneNo());
		if (clienterId<=0)//验证该手机号是否注册过人人推
			return resultModel.setCode(FetchRedbagEnum.PhoneNotRegister.value()).setMsg(FetchRedbagEnum.PhoneNotRegister.desc()).setData("<div class=\"to_reg c3\"><div class=\"title\">领取失败</div>该手机号尚未注册人人推，请点击下方按钮前往APP注册后 <a href=\"'"+basePath+"/clienter/fetchredbag"+"'\" class=\"rebind stress\">重新绑定</a><a href=\"'"+basePath+"/clienter/register"+"'\" class=\"sub-btn\">马上注册</a></div>");
		else{
			//判断是否绑定过微信
			if(!clienterService.isBindWx(clienterId,req.getOpenid())){
				// 未绑定，添加绑定关系，增加地推员余额，添加流水记录
				boolean result=clienterBalanceService.fetchRedbag(clienterId,req.getOpenid());
				if(result){
					return resultModel.setCode(FetchRedbagEnum.Success.value()).setMsg(FetchRedbagEnum.Success.desc()).setData("<div class=\"success c1\"><p>恭喜您获得现金奖励</p><p class=\"money\">¥ 2 元</p>奖励已放入人人推账号'"+req.getPhoneNo()+"'<a href=\"#\" class=\"sub-btn\">前往查看</a></div>");
				}else{
					return resultModel.setCode(FetchRedbagEnum.Fail.value()).setMsg(FetchRedbagEnum.Fail.desc()).setData("<div class=\"to_reg c3\"><div class=\"title\">领取失败</div>请稍后再试 <a href=\"'"+basePath+"/clienter/fetchredbag"+"'\" class=\"rebind stress\">重新领取</a></div>");
				}
			}else{  //已经绑定过
				return resultModel.setCode(FetchRedbagEnum.HadBindThisActivity.value()).setMsg(FetchRedbagEnum.HadBindThisActivity.desc()).setData("<div class=\"to_reg to_bind c4\"><div class=\"title\">领取失败</div>该手机号已被绑定，使用其他号码重新绑定<a href=\"'"+basePath+"/clienter/fetchredbag"+"'\" class=\"sub-btn rebind\">返回重新绑定</a></div>");
			}		 
		}
	}	
	/*
	 * 验证是否有资格进入领红包页面
	 * wangchao
	 */
	@RequestMapping("validateQualification")
	@ResponseBody
	public ResultModel<Object> validateQualification(HttpServletRequest request){
		ResultModel<Object> resultModel = new ResultModel<Object>();
		resultModel.setCode(FetchRedbagEnum.Success.value()).setMsg(FetchRedbagEnum.Success.desc());
		String openid=request.getParameter("openid");
		String truename = request.getParameter("truename");
		if(openid==""||openid==null || truename=="" || truename == null){
			//有无资格进入该页面 非法
			return resultModel.setCode(FetchRedbagEnum.ParaError.value()).setMsg(FetchRedbagEnum.ParaError.desc()).setData("<div class=\"success c1\"><p>"+FetchRedbagEnum.ParaError.desc()+"</p></div>");
		}
		//是否关注微信公众号
		boolean b=clienterWxService.isAttentionWx(openid);
		if(!b){
			return resultModel.setCode(FetchRedbagEnum.NoAttentionWx.value()).setMsg(FetchRedbagEnum.NoAttentionWx.desc()).setData("<div class=\"rcode_wrap c2\">进入<span class=\"stress\">公众号</span>点击<span class=\"stress\">“绑定账号送现金”</span>参与活动<img src=\"img/rcode.png\" alt=\"\">长按二维码扫描<br>关注[人人推官方平台]</div>");
		}
		//是否领取过奖励
		int clienterId=clienterWxService.hadFetchRedbag(openid);
		if(clienterId>0){
			resultModel.setCode(FetchRedbagEnum.HadFetchRedbag.value()).setMsg(FetchRedbagEnum.HadFetchRedbag.desc());
			Clienter clienter =clienterService.getClienterById((long)clienterId);
			resultModel.setData("<div class=\"success c1\"><p>您已参与过该活动，已获得现金奖励</p><p class=\"money\">¥ 2 元</p>奖励已放入人人推账号"+clienter.getPhoneNo().trim()+"<a href=\"#\" class=\"sub-btn\">前往查看</a></div>");
			return resultModel;
		}
		return resultModel;
	}
	
	/*
	 * 注册页面
	 * wangchao
	 */
	@RequestMapping("register")
	public ModelAndView register(){
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "clienter/register");
		return view;
	}
	
	/*
	 * 注册用户
	 * wangchao
	 */
	@RequestMapping("registersubmit")
	@ResponseBody
	public ResultModel<Object> registersubmit(SignUpReq req){
		ResultModel<Object> resultModel = new ResultModel<Object>();
		resultModel=clienterService.signup(req);
		return resultModel;
	}
}
