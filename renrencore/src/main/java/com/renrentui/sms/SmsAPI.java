package com.renrentui.sms;

import java.net.URL;

import javax.xml.namespace.QName;

public class SmsAPI {

	private static final QName SERVICE_NAME = new QName("http://tempuri.org/",
			"Sms");
	static URL wsdlURL = Sms.WSDL_LOCATION;
	static Sms ss = new Sms(wsdlURL, SERVICE_NAME);
	static SmsSoap port = ss.getSmsSoap();

	private SmsAPI() {
	}

	public static String SendSms(String mobile, String content) {
		return port.sendSmsSaveLog(mobile, content, 1004, "renren");
	}
}
