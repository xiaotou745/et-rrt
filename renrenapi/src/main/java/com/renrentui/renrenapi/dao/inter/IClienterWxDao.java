package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterWx;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWxModel;
import com.renrentui.renrenentity.req.PagedClienterWxReq;

public interface IClienterWxDao {
	/**
	 * 写入一条微信关注 窦海超 2016年2月25日 15:30:44
	 */
	void insert(ClienterWx model);

	/** 取消关注 窦海超 2016年2月25日 16:08:20 */
	void updateUnFollow(String openId);

	/** 根据opend取绑定用户信息 窦海超 2016年3月2日 16:13:07 */
	ClienterWx getClienterByOpenId(String openId);

	/** 关注 窦海超 2016年3月2日 16:13:49 */
	boolean follow(String openId);
	/*
	 * 是否关注微信 wangchao
	 */
	boolean isAttentionWx(String openid);

	/*
	 * 是否领取过奖励 wangchao
	 */
	int hadFetchRedbag(String openid);

	PagedResponse<ClienterWxModel> getlist(PagedClienterWxReq req);

	ClienterWxModel getByPhone(String phoneNo);

	PagedResponse<ClienterWxModel> getlistForActivityDetail(
			PagedClienterWxReq req);
}
