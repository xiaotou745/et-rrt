package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterWx;

public interface IClienterWxDao {
	/**
	 * 写入一条微信关注 窦海超 2016年2月25日 15:30:44
	 */
	void insert(ClienterWx model);
	
	/**取消关注 窦海超 2016年2月25日 16:08:20*/
	void updateUnFollow(String openId);

	/*
	 * 是否关注微信
	 * wangchao
	 */
	boolean isAttentionWx(String openid);
	/*
	 * 是否领取过奖励
	 * wangchao
	 */
	int hadFetchRedbag(String openid);
}
