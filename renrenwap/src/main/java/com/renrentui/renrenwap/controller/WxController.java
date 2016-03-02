package com.renrentui.renrenwap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.util.HttpUtil;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.WxAccess_token;
import com.renrentui.renrenentity.WxToken;
import com.using.weixin.wxtools.WeiXinTools;
import com.using.weixin.wxtools.vo.recv.WxRecvEventMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvGeoMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvPicMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvVoiceMsg;
import com.using.weixin.wxtools.vo.send.WxSendMsg;
import com.using.weixin.wxtools.vo.send.WxSendTextMsg;

@Controller
@RequestMapping("wx")
public class WxController {

	/** 微信里配置的服务器第一次用到的token标识 */
	private static final String TOKEN = "chenkai2016";

	private static final String appid = "wxb7e12931e4cbea51";
	private static final String secret = "fa062d7088cc474b55148b86fc419820";

	/** 跳转授权地址 */
	// private static final String redirectUrlDomain =
	// "http://eds_m.yitaoyun.net";

	private static final String redirectUrlDomain = PropertyUtils
			.getProperty("java.renrenwap.url");
	@Autowired
	IClienterWxService clienterWxService;
	@Autowired
	private RedisService redisService;

	/** 跳转 窦海超 2016年2月26日 11:46:43 */
	@RequestMapping("redirect")
	private void redirect(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=" + appid + "&redirect_uri=" + redirectUrlDomain
				+ "/wx/getuserinfo&" + "response_type=code&"
				+ "scope=snsapi_base&" + "state=1" + "#wechat_redirect";
		System.out.println(url);
		response.sendRedirect(url);
	}

	@SuppressWarnings("unused")
	private String getOpenId(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=" + appid + "&secret=" + secret + "&code=" + code
				+ "&grant_type=authorization_code";
		String data = HttpUtil.sendGet(url, "");

		System.out.println("=====获取openid：" + data);
		return data;
	}

	@RequestMapping("getuserinfo")
	public void getUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");

		if (null == code || "".equals(code)) {
			System.out.println("==============[OAuthServlet]获取网页授权openId失败！");
			return;
		}

		WxAccess_token wxAccess_token = JSON.parseObject(getOpenId(code),
				WxAccess_token.class);
		String openId = wxAccess_token.getOpenid();
		if (openId == null || openId == "") {
			System.out.println("======get openid error=======");
			return;
		}

		System.out.println("output token:" + getToken());
		System.out.println("output openid:" + wxAccess_token.getOpenid());
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ getToken()
				+ "&openid="
				+ wxAccess_token.getOpenid()
				+ "&lang=zh_CN";
		String data = HttpUtil.sendGet(url, "");
		System.out.println("last info:" + data);

		response.sendRedirect(redirectUrlDomain+"/clienter/fetchredbag?openid=" + wxAccess_token.getOpenid());
	}

	private String getToken() {
		String tokenKey = RedissCacheKey.TokenKey;
		String tokenStr = "";
		// String tokenStr = redisService.get(tokenKey, String.class);
		// if (!StringUtils.isEmpty(tokenStr)) {
		// System.out.println("缓存：" + tokenStr);
		// return tokenStr;
		// }
		String httpUrl = "https://api.weixin.qq.com/cgi-bin/token";
		String getData = HttpUtil.sendGet(httpUrl,
				"grant_type=client_credential&appid=" + appid + "&secret="
						+ secret);

		WxToken wxToken = JsonUtil.str2obj(getData, WxToken.class);
		tokenStr = wxToken.getAccess_token();
		// redisService.set(tokenKey, tokenStr, wxToken.getExpires_in() -
		// 1000);// 担心会时间不准，把无效时间往前一点
		System.out.println("非缓存：" + tokenStr);
		return tokenStr;
	}

	@RequestMapping("createmenu")
	public void createMenu() {
		String token = getToken();
		String menuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ token;
		String menuData = "{     \"button\":[     {      \"name\":\"活动\",	      \"sub_button\":[		{		 \"type\":\"view\",		       \"name\":\"投票\",		       \"url\":\"http://mp.weixin.qq.com/s?__biz=MzA5NzQzOTk1Ng==&mid=402831696&idx=1&sn=ed73a8e94465a7ec20115a91d152b7c1#rd\"		},		{		 \"type\":\"view\",		       \"name\":\"绑定\",		       \"url\":\"http://mp.weixin.qq.com/s?__biz=MzA5NzQzOTk1Ng==&mid=402831696&idx=1&sn=ed73a8e94465a7ec20115a91d152b7c1#rd\"		}	      ]      },      {        \"type\":\"view\",               \"name\":\"合伙人招募\",               \"url\":\"https://jinshuju.net/f/ptT2lj\"	},	{        \"type\":\"view\",               \"name\":\"APP下载\",               \"url\":\"http://a.app.qq.com/o/simple.jsp?pkgname=com.renrentui.app\"	}	] }";
		String resultData = HttpUtil.sendPost(menuUrl, menuData,
				"application/json");
		System.out.println(resultData);
	}

	public void delMenu() {
		String token = getToken();
		String menuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="
				+ token;
		String resultData = HttpUtil.sendGet(menuUrl, "");
		System.out.println(resultData);
	}

	/**
	 * 事件监听 窦海超 2016年2月25日 11:21:52
	 * 
	 * @throws JDOMException
	 * */
	@RequestMapping("eventlisten")
	public void eventListen(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JDOMException {
		try {
			System.out.println("进入方法了");
			WxRecvMsg msg = WeiXinTools.recv(request.getInputStream());
			WxSendMsg sendMsg = WeiXinTools.builderSendByRecv(msg);

			/** -------------------2.接受到的事件消息-------------------------- */
			if (msg instanceof WxRecvEventMsg) {
				WxRecvEventMsg recvMsg = (WxRecvEventMsg) msg;
				String event = recvMsg.getEvent();
				String fromUserName = recvMsg.getFromUser();// 关注 人的OPENID
				String createTime = recvMsg.getCreateDt();// 操作时间
				System.out.println("订阅:fromUserName" + fromUserName
						+ ",createTime:" + createTime);
				if ("subscribe".equals(event)) {
					// 订阅消息
					sendMsg = new WxSendTextMsg(sendMsg, "谢谢您的订阅。");
					WeiXinTools.send(sendMsg, response.getOutputStream());
					System.out.println("订阅");
					clienterWxService.follow(fromUserName, fromUserName,
							createTime);
					return;
				} else if ("unsubscribe".equals(event)) {
					// 取消订阅
					System.out.println("取消订阅");

					clienterWxService.unfollow(fromUserName);
					return;

				} else if ("CLICK".equals(event)) {
					// 自定义菜单点击事件
					String eventKey = recvMsg.getEventKey();

					// 判断自定义菜单中的key回复消息
					if ("自定义菜单中的key".equals(eventKey)) {

						return;
					}
				} else {
					// 无法识别的事件消息
					return;
				}

			}

			/** -------------------3.接受到的地理位置信息-------------------------- */
			else if (msg instanceof WxRecvGeoMsg) {
				WxRecvGeoMsg recvMsg = (WxRecvGeoMsg) msg;

				return;
			}

			/** -------------------4.接受到的音频消息-------------------------- */
			else if (msg instanceof WxRecvVoiceMsg) {
				WxRecvVoiceMsg recvMsg = (WxRecvVoiceMsg) msg;

				return;
			}

			/** -------------------5.接受到的图片消息-------------------------- */
			else if (msg instanceof WxRecvPicMsg) {
				WxRecvPicMsg recvMsg = (WxRecvPicMsg) msg;

				return;
			}
			/** ------------------6.接受到的未能识别的消息-------------------- */
			else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get请求进行验证服务器是否正常
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 进行接口验证
		 */
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if (null != timestamp && null != nonce && null != echostr
				&& null != signature) {
			if (WeiXinTools.access(TOKEN, signature, timestamp, nonce)) {
				response.getWriter().write(echostr);
				return;
			}
			return;
		} else {
			return;
		}
	}

}
