package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWxModel;
import com.renrentui.renrenentity.req.PagedClienterWxReq;

public interface IClienterWxService {

	/**关注  窦海超   2016年2月25日 16:00:43*/
	boolean follow(String openId,String fromUserName,String createTime);
	
	
	/**取消关注 窦海超  2016年2月25日 16:00:51*/
	boolean unfollow(String openId);


	boolean isAttentionWx(String openid);


	int hadFetchRedbag(String openid);


	PagedResponse<ClienterWxModel> getlist(PagedClienterWxReq req);
}
