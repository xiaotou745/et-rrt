package com.renrentui.renrencore.util;

public class Config {
	public static String adminurl=PropertyUtils.getProperty("java.renrenadmin.url");//获取管理后台域名配置
	public static String interceptSwith=PropertyUtils.getProperty("InterceptSwith");//是否加密 1加，默认0不加
}
