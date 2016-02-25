package com.renrentui.renrenadmin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.util.HttpUtil;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrenentity.WxToken;
import com.using.weixin.common.ApiTools;
import com.using.weixin.wxtools.WeiXinTools;
import com.using.weixin.wxtools.vo.recv.WxRecvEventMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvGeoMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvPicMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvTextMsg;
import com.using.weixin.wxtools.vo.recv.WxRecvVoiceMsg;
import com.using.weixin.wxtools.vo.send.WxSendMsg;
import com.using.weixin.wxtools.vo.send.WxSendMusicMsg;
import com.using.weixin.wxtools.vo.send.WxSendNewsMsg;
import com.using.weixin.wxtools.vo.send.WxSendTextMsg;

@Controller
@RequestMapping("wx")
public class WxController {

	// token标识
	private static final String TOKEN = "chenkai2016";

	@Autowired
	private RedisService redisService;

	private String getToken() {
		String tokenKey = RedissCacheKey.TokenKey;
		// String tokenStr="";
		String tokenStr = redisService.get(tokenKey, String.class);
		if (!StringUtils.isEmpty(tokenStr)) {
			System.out.println("缓存：" + tokenStr);
			return tokenStr;
		}
		String httpUrl = "https://api.weixin.qq.com/cgi-bin/token";
		String getData = HttpUtil
				.sendGet(
						httpUrl,
						"grant_type=client_credential&appid=wxb7e12931e4cbea51&secret=fa062d7088cc474b55148b86fc419820");

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
	 * */
	public void eventListen(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			WxRecvMsg msg = WeiXinTools.recv(request.getInputStream());
			WxSendMsg sendMsg = WeiXinTools.builderSendByRecv(msg);

			/** -------------------1.接受到的文本消息，回复处理-------------------------- */
			if (msg instanceof WxRecvTextMsg) {
				WxRecvTextMsg recvMsg = (WxRecvTextMsg) msg;
				// 用户输入的内容
				String text = recvMsg.getContent().trim();

				/** ----------- 消息回复示例：文字回复、单(多)图文回复、音乐回复 begin------------- */
				if (text.equals("笑话") || text.equals("1")) {
					// 文本消息回复
					sendMsg = new WxSendTextMsg(sendMsg, ApiTools.jokeApi());
					WeiXinTools.send(sendMsg, response.getOutputStream());
					return;
				}

				else if (text.equals("段子") || text.equals("2")) {
					// 文本消息回复
					sendMsg = new WxSendTextMsg(sendMsg, ApiTools.duanziApi());
					WeiXinTools.send(sendMsg, response.getOutputStream());
					return;
				}

				else if (text.equals("图文")) {
					sendMsg = new WxSendNewsMsg(sendMsg).addItem("图文标题",
							"图文描述", "http://www.baidu.com/img/bdlogo.gif",
							"http://www.baidu.com/");

					// 多图文内容 --示例
					// .addItem("图文标题", "图文描述" ,
					// "http://www.baidu.com/img/bdlogo.gif",
					// "http://www.baidu.com/");
					WeiXinTools.send(sendMsg, response.getOutputStream());
					return;
				}

				else if (text.equals("音乐")) {
					sendMsg = new WxSendMusicMsg(sendMsg, "音乐消息测试", "音乐消息测试内容",
							"音乐地址.mp3", "高质量音乐地址.mp3");
					WeiXinTools.send(sendMsg, response.getOutputStream());
					return;
				} else {
					// 文本消息回复
					sendMsg = new WxSendTextMsg(sendMsg, "您发的内容是：" + text);
					WeiXinTools.send(sendMsg, response.getOutputStream());
					return;
				}

				/** ----------- 消息回复示例：文字回复、单(多)图文回复、音乐回复 end ------------- */

			}

			/** -------------------2.接受到的事件消息-------------------------- */
			else if (msg instanceof WxRecvEventMsg) {
				WxRecvEventMsg recvMsg = (WxRecvEventMsg) msg;
				String event = recvMsg.getEvent();

				if ("subscribe".equals(event)) {
					// 订阅消息
					sendMsg = new WxSendTextMsg(sendMsg, "谢谢您的订阅。");
					WeiXinTools.send(sendMsg, response.getOutputStream());
					return;
				} else if ("unsubscribe".equals(event)) {
					// 取消订阅

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
