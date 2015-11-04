package com.renrentui.renrencore.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.renrentui.sms.SendSmsSaveLog;
import com.renrentui.sms.SmsAPI;
import com.renrentui.sms.saltlight.SaltLightSmsAPI;

public class SmsUtils {
	/**
	 * 发送短信 2015年9月28日 13:12:08 窦海超 Mobile 手机号码，多个号码‘,’号隔开 Content 发送内容
	 * **/
	public long testSendSms() {
		try {
			return sendSMS("13426401627", "您的验证码为：1234");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 发送短信 2015年9月28日 13:12:08
	 * 
	 * @author haichao
	 * @param Mobile
	 *            手机号码，多个号码‘,’号隔开
	 * @param Content
	 *            发送内容,如果是验证码可以用{num}直接传参，会产生6位随机数
	 * **/
	public static long sendSMS(String Mobile, String Content)
			throws MalformedURLException, UnsupportedEncodingException {
		SmsAPI.SendSms(Mobile, Content);//易淘通道
//		SaltLightSmsAPI.SendSaliLightSms(Mobile, Content);//盐光通道
		return 1;
	}

}
