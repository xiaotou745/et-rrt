package com.renrentui.renrencore.util;

public class Config {
	public static String adminurl=PropertyUtils.getProperty("java.renrenadmin.url");//获取管理后台域名配置
	public static String interceptSwith=PropertyUtils.getProperty("InterceptSwith");//是否加密 1加，默认0不加
	
	public static String aliBatchNotifyUrl=PropertyUtils.getProperty("AliBatchNotifyUrl");  //支付回调地址
	
	public static String aliBatchRequstUrl=PropertyUtils.getProperty("AliBatchRequstUrl");   //支付请求地址
}
