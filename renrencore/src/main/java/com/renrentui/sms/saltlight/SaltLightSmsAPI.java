package com.renrentui.sms.saltlight;

import java.net.URL;

import javax.xml.namespace.QName;

public class SaltLightSmsAPI {
	public final static QName SERVICE_NAME = new QName("http://tempuri.org/",
			"Sms");
	static URL wsdlURL = Sms.WSDL_LOCATION;
	static Sms ss = new Sms(wsdlURL, SERVICE_NAME);
	static SmsSoap port = ss.getSmsSoap();

	private SaltLightSmsAPI() {
	}
	//盐光短信
	public static String SendSaliLightSms(String mobile, String content) {
		return port.sendSmsSaveLog(mobile, content, 1004, "RRDT");
	}
}
