package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterWxDao;
import com.renrentui.renrenentity.ClienterWx;

@Repository
public class ClienterWxDao extends DaoBase implements IClienterWxDao {

	/**写绑定数据*/
	@Override
	public void insert(ClienterWx model) {
		getMasterSqlSessionUtil().insert(
				"IClienterWxLogDao.insert", model);
	}

	/**取消关注 窦海超 2016年2月25日 16:08:20*/
	@Override
	public void updateUnFollow(String openId) {
		getMasterSqlSessionUtil().update(
				"IClienterWxLogDao.unFollow", openId);
	}
	/*
	 * 是否关注微信公众号
	 * wangchao
	 */
	@Override
	public boolean isAttentionWx(String openid) {
		int a= getReadOnlySqlSessionUtil().selectOne("IClienterWxDao.isAttentionWx",openid);
		return a>0;
	}
	/*
	 * 是否领取过奖励
	 * wangchao
	 */
	@Override
	public int hadFetchRedbag(String openid) {
		return getReadOnlySqlSessionUtil().selectOne("IClienterWxDao.hadFetchRedbag",openid);
	}

}
