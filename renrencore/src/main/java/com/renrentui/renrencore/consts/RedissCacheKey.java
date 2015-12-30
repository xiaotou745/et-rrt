package com.renrentui.renrencore.consts;


/**
 * redis 緩存 key 值
 * @author CaoHeYang
 * @modify pengyi
 */
public class RedissCacheKey {
	/**
	 * 获取开通城市缓存key
	 * redisservice中会统一增加java前缀和版本前缀
	 */
    public static final String Ets_Service_Provider_Common_GetOpenCity_New = "Ets_Service_Provider_Common_GetOpenCity_";
    public static final String LOGIN_COUNT_B = "LoginCount_B_";//商家登录次数
    public final static String Business_LOGIN_COOKIE = "Business_login_";//登录Cookie的key,对应redis的缓存key
    public final static String GroupBusiness_LOGIN_COOKIE = "GroupBusiness_login_";
    public static final String Order_TimeSpan = "jOrder_TimeSpan_";//商家发单时间戳
    public static final String Menu_Auth = "Menu_Auth_";//用户有权限的菜单
    public static final String GlobalConfig_Key = "GlobalConfig_Key_";
    
    public static final String RR_Clienter_sendcode_register ="RR_sendcode_register_";//C端注册
    public static final String RR_Celitner_sendcode_UpdatePasswrd="RR_Celitner_sendcode_UpdatePasswrd_";//C端修改密码
    public static final String RR_Clienter_sendcode_forgetPassword="RR_Clienter_sendcode_forgetPassword_";//C端忘记密码
    public static final String RR_Clienter_sendcode_bindAliPay="RR_Clienter_sendcode_bindAliPay_";//C端绑定支付宝
    public static final String RR_PublicProvinceCity="RR_PublicProvinceCity";//省份和城市
    
    public static final String RR_PublicProvinceCity_Hot="RR_PublicProvinceCity_Hot";//热门城市和按照26个字母排序的城市
    
    public static final String RR_PublicProvinceCity_Version = "RR_PublicProvinceCity_Version"; //城市版本号
}
