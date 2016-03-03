package com.renrentui.renrenapi.dao.impl;

import java.io.StringWriter;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterWxDao;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.ClienterWx;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWxModel;
import com.renrentui.renrenentity.req.PagedClienterWxReq;

@Repository
public class ClienterWxDao extends DaoBase implements IClienterWxDao {
	/** 根据opend取绑定用户信息 窦海超 2016年3月2日 16:13:07 */
	@Override
	public ClienterWx getClienterByOpenId(String openId) {
		return getMasterSqlSessionUtil().selectOne(
				"IClienterWxDao.getClienterByOpenId", openId);
	}

	/** 关注 窦海超 2016年3月2日 16:13:49 */
	@Override
	public boolean follow(String openId) {
		return getMasterSqlSessionUtil()
				.update("IClienterWxDao.follow", openId) > 0;
	}

	/** 写绑定数据 */
	@Override
	public void insert(ClienterWx model) {
		getMasterSqlSessionUtil().insert("IClienterWxDao.insert", model);
	}

	/** 取消关注 窦海超 2016年2月25日 16:08:20 */
	@Override
	public void updateUnFollow(String openId) {
		getMasterSqlSessionUtil().update("IClienterWxDao.unFollow", openId);
	}

	/*
	 * 是否关注微信公众号 wangchao
	 */
	@Override
	public boolean isAttentionWx(String openId) { 
		String tmpString=getReadOnlySqlSessionUtil().selectOne("IClienterWxDao.isAttentionWx", openId);
		int a =ParseHelper.ToInt(tmpString); 
		return a > 0;
	}

	/*
	 * 是否领取过奖励 wangchao
	 */
	@Override
	public int hadFetchRedbag(String openId) { 
		String tmpString=getReadOnlySqlSessionUtil().selectOne("IClienterWxDao.hadFetchRedbag", openId);
		return ParseHelper.ToInt(tmpString);
	}

	@Override
	public PagedResponse<ClienterWxModel> getlist(PagedClienterWxReq req) { 
		return getReadOnlySqlSessionUtil().selectPageList("IClienterWxDao.getlist", req);
	}

}
