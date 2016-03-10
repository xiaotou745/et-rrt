package com.renrentui.renrenwap.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IActivityService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapi.service.inter.ITaskTagService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.ActivityEnum;
import com.renrentui.renrencore.enums.FetchRedbagEnum;
import com.renrentui.renrencore.util.CookieUtils;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.Activity;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.common.ResultModel;
import com.renrentui.renrenentity.domain.ClienterWxModel;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.ActivityReq;
import com.renrentui.renrenentity.req.CSendCodeReq;
import com.renrentui.renrenentity.req.FetchRedbagReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;

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
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IRenRenTaskService renRenTaskService;
	/*
	 * 领红包页面获取验证码 wangchao
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	public ResponseBase sendcode(CSendCodeReq req) {
		ResponseBase responseBase = clienterService.sendcode(req);
		return responseBase;
	}

	/*
	 * 领红包页面 wangchao
	 */
	@RequestMapping("fetchredbag")
	public ModelAndView fetchredbag(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelAndView view = new ModelAndView();
		String openid = request.getParameter("openid");
		String cookieOpenId = CookieUtils.getCookie(request,
				RedissCacheKey.cookieOpenId);
		//
		if (openid == null || !openid.equals(cookieOpenId)) {
			view.addObject("openid", "");
		} else {
			view.addObject("openid", openid);
		}
		view.addObject("viewPath", "clienter/fetchredbag");
		return view;
	}

	/*
	 * 领红包事件 wangchao
	 */
	@RequestMapping("fetchredbagsubmit")
	@ResponseBody
	public ResultModel<Object> fetchredbagsubmit(FetchRedbagReq req) {
		ResultModel<Object> resultModel = new ResultModel<Object>();

		String basePath = PropertyUtils.getProperty("java.renrenwap.url");
		if (req.getOpenid() == "" || req.getOpenid() == null) {
			return resultModel.setCode(FetchRedbagEnum.ParaError.value())
					.setMsg(FetchRedbagEnum.ParaError.desc());
		}
		if (req.getPhoneNo() == "") {
			return resultModel.setCode(FetchRedbagEnum.PhoneNoError.value())
					.setMsg(FetchRedbagEnum.PhoneNoError.desc());
		}
		String keyString = RedissCacheKey.RR_Clienter_sendcode_fetchRedBag
				+ req.getPhoneNo();
		String codeRedisString = redisService.get(keyString, String.class);
		if (!req.getCode().equals(codeRedisString)) // 验证码 查缓存
			return resultModel.setCode(FetchRedbagEnum.VerCodeError.value())
					.setMsg(FetchRedbagEnum.VerCodeError.desc());
		int clienterId = clienterService.getClienterIdByPhone(req.getPhoneNo());
		if (clienterId <= 0)// 验证该手机号是否注册过人人推
			return resultModel
					.setCode(FetchRedbagEnum.PhoneNotRegister.value())
					.setMsg(FetchRedbagEnum.PhoneNotRegister.desc())
					.setData(
							"<div class=\"to_reg c3\"><div class=\"title\">领取失败</div>该手机号尚未注册人人推，请点击下方按钮前往APP注册后 <a href=\""
									+ basePath
									+ "/clienter/fetchredbag"
									+ "\" class=\"rebind stress\">重新绑定</a><a href=\""
									+ basePath
									+ "/clienter/register"
									+ "\" class=\"sub-btn\">马上注册</a></div>");
		else {
			// 判断是否绑定过微信
			if (!clienterService.isBindWxByClienterId(clienterId)) {
				if (!clienterService.isBindWxByOpenId(req.getOpenid())) {
					return resultModel
							.setCode(FetchRedbagEnum.HadBindThisActivity.value())
							.setMsg(FetchRedbagEnum.HadBindThisActivity.desc())
							.setData(
									"<div class=\"to_reg to_bind c4\"><div class=\"title\">领取失败</div>该微信号已被绑定，使用其他号码重新绑定<a href=\""
											+ basePath
											+ "/clienter/fetchredbag"
											+ "\" class=\"sub-btn rebind\">返回重新绑定</a></div>");
				}
				// 未绑定，添加绑定关系，增加地推员余额，添加流水记录
				boolean result = clienterBalanceService.fetchRedbag(clienterId,
						req.getOpenid(),req.getActivityid());
				if (result) {
					return resultModel
							.setCode(FetchRedbagEnum.Success.value())
							.setMsg(FetchRedbagEnum.Success.desc())
							.setData(
									"<div class=\"success c1\"><p>恭喜您获得现金奖励</p><p class=\"money\">¥ 2 元</p>奖励已放入人人推账号"
											+ ParseHelper.hideCenterFourPhoneNo(req.getPhoneNo())
											+ "<a href=\"http://a.app.qq.com/o/simple.jsp?pkgname=com.renrentui.app\" class=\"sub-btn\">前往查看</a></div>");
				} else {
					return resultModel
							.setCode(FetchRedbagEnum.Fail.value())
							.setMsg(FetchRedbagEnum.Fail.desc())
							.setData(
									"<div class=\"to_reg c3\"><div class=\"title\">领取失败</div>请稍后再试 <a href=\""
											+ basePath
											+ "/clienter/fetchredbag"
											+ "\" class=\"rebind stress\">重新领取</a></div>");
				}
			} else { // 已经绑定过
				return resultModel
						.setCode(FetchRedbagEnum.HadBindThisActivity.value())
						.setMsg(FetchRedbagEnum.HadBindThisActivity.desc())
						.setData(
								"<div class=\"to_reg to_bind c4\"><div class=\"title\">领取失败</div>该手机号已被绑定，使用其他号码重新绑定<a href=\""
										+ basePath
										+ "/clienter/fetchredbag"
										+ "\" class=\"sub-btn rebind\">返回重新绑定</a></div>");
			}
		}
	}

	/*
	 * 验证是否有资格进入领红包页面 wangchao
	 */
	@RequestMapping("validatequalification")
	@ResponseBody
	public ResultModel<Object> validateQualification(FetchRedbagReq req) {
		ResultModel<Object> resultModel = new ResultModel<Object>();
		resultModel.setCode(FetchRedbagEnum.Success.value()).setMsg(
				FetchRedbagEnum.Success.desc());
		String basePath = PropertyUtils.getProperty("java.renrenwap.url");
		String openid = req.getOpenid();
		if (openid == "" || openid == null) {
			// 有无资格进入该页面 非法
			return resultModel
					.setCode(FetchRedbagEnum.ParaError.value())
					.setMsg(FetchRedbagEnum.ParaError.desc())
					.setData(
							"<div class=\"success c1\"><p>"
									+ FetchRedbagEnum.ParaError.desc()
									+ "</p></div>");
		}
		ActivityReq activityReq=new ActivityReq();
		activityReq.setId(ActivityEnum.BindWeiXinFetchRedBag.value());
		Activity activity= activityService.getSingleActivity(activityReq); //绑定微信领奖励活动
		if(activity==null || activity.getStatus() == 0){
			return resultModel
					.setCode(FetchRedbagEnum.ParaError.value())
					.setMsg(FetchRedbagEnum.ParaError.desc())
					.setData(
							"<div class=\"success c1\"><p>"
									+ "活动已失效"
									+ "</p></div>");
		}
		if(activity.getStartTime().after(new Date())){
			return resultModel
					.setCode(FetchRedbagEnum.ParaError.value())
					.setMsg(FetchRedbagEnum.ParaError.desc())
					.setData(
							"<div class=\"success c1\"><p>"
									+ "活动尚未开始"
									+ "</p></div>");
		}
		if(activity.getEndTime().before(new Date())){
			return resultModel
					.setCode(FetchRedbagEnum.ParaError.value())
					.setMsg(FetchRedbagEnum.ParaError.desc())
					.setData(
							"<div class=\"success c1\"><p>"
									+ "活动已经结束"
									+ "</p></div>");
		}
		// 是否关注微信公众号
		boolean b = clienterWxService.isAttentionWx(openid);
		if (!b) {
			return resultModel
					.setCode(FetchRedbagEnum.NoAttentionWx.value())
					.setMsg(FetchRedbagEnum.NoAttentionWx.desc())
					.setData(
							"<div class=\"rcode_wrap c2\">进入<span class=\"stress\">公众号</span>点击<span class=\"stress\">“绑定账号送现金”</span>参与活动<img src=\""
									+ basePath
									+ "/img/rcode.png\" alt=\"\">长按二维码扫描<br>关注[人人推官方平台]</div>");
		}
		// 是否领取过奖励
		int clienterId = clienterWxService.hadFetchRedbag(openid);
		if (clienterId > 0) {
			resultModel.setCode(FetchRedbagEnum.HadFetchRedbag.value()).setMsg(
					FetchRedbagEnum.HadFetchRedbag.desc());
			Clienter clienter = clienterService
					.getClienterById((long) clienterId);
			resultModel
					.setData("<div class=\"success c1\"><p>您已参与过该活动，已获得现金奖励</p><p class=\"money\">¥ 2 元</p>奖励已放入人人推账号"
							+ ParseHelper.hideCenterFourPhoneNo(clienter.getPhoneNo().trim())
							+ "<a href=\"http://a.app.qq.com/o/simple.jsp?pkgname=com.renrentui.app\" class=\"sub-btn\">前往查看</a></div>");
			return resultModel;
		}
		return resultModel;
	}

	/*
	 * 注册页面 wangchao
	 */
	@RequestMapping("register")
	public ModelAndView register() {
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "clienter/register");
		return view;
	}

	/*
	 * 注册用户 wangchao
	 */
	@RequestMapping("registersubmit")
	@ResponseBody
	public ResultModel<Object> registersubmit(SignUpReq req) {
		ResultModel<Object> resultModel = new ResultModel<Object>();
		resultModel = clienterService.signup(req);
		return resultModel;
	}
	/*
	 * 注册成功页面 wangchao
	 */
	@RequestMapping("registersuccess")
	public ModelAndView registersuccess(String phoneNo) {
		ModelAndView view = new ModelAndView();
		view.addObject("phoneNo",phoneNo);
		view.addObject("viewPath", "clienter/registersuccess");
		return view;
	}
	/*
	 * 分享任务页面 wangchao
	 */
	@RequestMapping("sharetask")
	public ModelAndView sharetask(HttpServletRequest request,Long taskId) {
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "clienter/sharetask");
		TaskDatumDetailReq req=new TaskDatumDetailReq();
		req.setTaskId(taskId);
		req.setUserId(0);
		TaskDetail task= renRenTaskService.getTaskDetail(req); //获取任务
		view.addObject("task",task);
		return view;
	}	
}
