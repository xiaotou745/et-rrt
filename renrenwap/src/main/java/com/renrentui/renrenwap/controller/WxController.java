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
import com.alibaba.fastjson.JSONObject;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.util.CookieUtils;
import com.renrentui.renrencore.util.HttpUtil;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrenentity.WxAccess_token;
import com.renrentui.renrenentity.WxToken;
import com.renrentui.renrenentity.WxUserInfo;
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
	private static final String TOKEN = "renrentui2016";

	private static final String appid = "wx6fb3e7dcd50074f3";
	private static final String secret = "de459685df302141ff8feb4451f47ae8";

	/** 跳转授权地址 */
	private static final String redirectUrlDomain = PropertyUtils
			.getProperty("java.renrenwap.url");

	private static final String cookieDomain = redirectUrlDomain.replace(
			"http://", "");

	@Autowired
	IClienterWxService clienterWxService;
	@Autowired
	private RedisService redisService;

	/** 跳转 窦海超 2016年2月26日 11:46:43 */
	@RequestMapping("redirect")
	private void redirect(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=" + appid + "&redirect_uri=http://"
				+ redirectUrlDomain.replace("http://", "") + "/wx/getuserinfo&"
				+ "response_type=code&" + "scope=snsapi_base&" + "state=123"
				+ "#wechat_redirect";
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
		// 这里把当前的openid存到cookie里
		// 为的是让打开页面时判断传的参和cookie的是否一至
		System.out.println("==========opendId:" + openId);

		CookieUtils.setCookie(request, response, RedissCacheKey.cookieOpenId,
				openId, 60 * 60 * 24, false, cookieDomain, null);
		response.sendRedirect(redirectUrlDomain
				+ "/clienter/fetchredbag?openid=" + openId);
	}

	/** 通过openid获取用户信息 窦海超 2016年3月2日 17:39:35 */
	@SuppressWarnings("unused")
	private WxUserInfo getUserInfoByOpenId(String openId) {
		// System.out.println("output token:" + getToken());
		System.out.println("output openid:" + openId);
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ getToken() + "&openid=" + openId + "&lang=zh_CN";
		String data = HttpUtil.sendGet(url, "");
		System.out.println("last info:" + data);

		return JSONObject.parseObject(data, WxUserInfo.class);
	}

	private String getToken() {
		String tokenKey = RedissCacheKey.TokenKey;
		// String tokenStr = "";
		String tokenStr = redisService.get(tokenKey, String.class);
		if (!StringUtils.isEmpty(tokenStr)) {
			System.out.println("缓存：" + tokenStr);
			return tokenStr;
		}
		String httpUrl = "https://api.weixin.qq.com/cgi-bin/token";
		String getData = HttpUtil.sendGet(httpUrl,
				"grant_type=client_credential&appid=" + appid + "&secret="
						+ secret);

		WxToken wxToken = JsonUtil.str2obj(getData, WxToken.class);
		tokenStr = wxToken.getAccess_token();
		redisService.set(tokenKey, tokenStr, wxToken.getExpires_in() - 1000);// 担心会时间不准，把无效时间往前一点
		System.out.println("非缓存：" + tokenStr);
		return tokenStr;
	}

	@RequestMapping("createmenu")
	public void createMenu() {
		String token = getToken();
		String menuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
				+ token;

		String hdBindUrl = redirectUrlDomain + "/wx/redirect";

		String menuData = "{	\"button\": "
				+ "[{		\"type\": \"view\",		\"name\": \"APP下载\",		\"url\": \"http://a.app.qq.com/o/simple.jsp?pkgname=com.renrentui.app\"	}, "
				+ "{		\"type\": \"view\",		\"name\": \"绑账号送现金\",		\"url\": \""
				+ hdBindUrl
				+ "\"	}, "
				+ "{		\"name\": \"推广合作\",		"
				+ "\"sub_button\": "
				+ "[{			\"type\": \"view\",			\"name\": \"企业推广\",			\"url\": \"https://jinshuju.net/f/Pq7ncs\"		}, "
				+ "{			\"type\": \"view\",			\"name\": \"团队入驻\",			\"url\": \"https://jinshuju.net/f/ptT2lj\"		}, "
				+ "{			\"type\": \"view\",			\"name\": \"人人推吧\",			\"url\": \"http://tieba.baidu.com/f?kw=%C8%CB%C8%CB%CD%C6&fr=ala0&tpl=5\"		}"
				+ "]	}]}";
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
		// doGet(request, response);
		// return;
		try {
			System.out.println("进入方法了");
			WxRecvMsg msg = WeiXinTools.recv(request.getInputStream());
			WxSendMsg sendMsg = WeiXinTools.builderSendByRecv(msg);

			/** -------------------2.接受到的事件消息-------------------------- */
			if (msg instanceof WxRecvEventMsg) {
				WxRecvEventMsg recvMsg = (WxRecvEventMsg) msg;
				String event = recvMsg.getEvent();
				String openId = recvMsg.getFromUser();// 关注 人的OPENID
				String createTime = recvMsg.getCreateDt();// 操作时间
				System.out.println("订阅:openId" + openId + ",createTime:"
						+ createTime);
				if ("subscribe".equals(event)) {

					WxUserInfo wxUserInfo = getUserInfoByOpenId(openId);
					// 订阅消息
					String gzStr = "什么？\n"
							+ "你还不知道人家是干嘛的？人人推是专门做推广的啦."
							+ "\n\n"
							+ "我们APP上有很多赚钱的任务，例如下载注册一个APP得到5-40元佣金，签约一个周围的店上口碑外卖300元等等啦，年后会有大量BAT等一线互联网企业靠谱任务上线，欢迎大家体验人人推APP哟。"
							+ "\n\n" + "PS：合伙人分佣系统上线，让团队作战更有价值！";
					sendMsg = new WxSendTextMsg(sendMsg, gzStr);
					WeiXinTools.send(sendMsg, response.getOutputStream());
					System.out.println("订阅");
					clienterWxService.follow(openId, wxUserInfo.getNickname(),
							createTime);
					return;
				} else if ("unsubscribe".equals(event)) {
					// 取消订阅
					System.out.println("取消订阅");

					clienterWxService.unfollow(openId);
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
