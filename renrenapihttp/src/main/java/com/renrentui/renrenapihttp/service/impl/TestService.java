package com.renrentui.renrenapihttp.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.renrentui.renrencore.util.SmsUtils;

public class TestService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SmsUtils.sendSMS("13426401627", "您的验证码为：1234");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
