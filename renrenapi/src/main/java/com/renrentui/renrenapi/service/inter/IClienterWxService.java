package com.renrentui.renrenapi.service.inter;

public interface IClienterWxService {

	/**关注  窦海超   2016年2月25日 16:00:43*/
	boolean follow(String openId,String fromUserName,String createTime);
	
	
	/**取消关注 窦海超  2016年2月25日 16:00:51*/
	boolean unfollow(String openId);
}