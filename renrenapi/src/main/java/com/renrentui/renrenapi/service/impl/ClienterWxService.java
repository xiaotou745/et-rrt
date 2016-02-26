package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IClienterWxDao;
import com.renrentui.renrenapi.dao.inter.IClienterWxLogDao;
import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrenentity.ClienterWx;
import com.renrentui.renrenentity.ClienterWxLog;

@Service
public class ClienterWxService implements IClienterWxService {

	@Autowired
	private IClienterWxDao clienterWxDao;
	@Autowired
	private IClienterWxLogDao clienterWxLogDao;

	/** 关注 窦海超 2016年2月25日 15:52:35 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean follow(String openId, String fromUserName, String createTime) {

		try {
			ClienterWx clienterWx = new ClienterWx();
			clienterWx.setClienterId(0);
			clienterWx.setFollowStatus(1);
			clienterWx.setFromUserName(fromUserName);
			clienterWx.setOpenId(openId);
			clienterWx.setWxId("");
			clienterWxDao.insert(clienterWx);

			ClienterWxLog clienterWxLog = new ClienterWxLog();
			clienterWxLog.setFollowStatus(1);
			clienterWxLog.setOpenId(openId);
			clienterWxLogDao.insert(clienterWxLog);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/** 取消关注 窦海超 2016年2月25日 16:00:51 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean unfollow(String openId) {
		try {

			clienterWxDao.updateUnFollow(openId);
			ClienterWxLog clienterWxLog = new ClienterWxLog();
			clienterWxLog.setFollowStatus(0);// 取消关注
			clienterWxLog.setOpenId(openId);
			clienterWxLogDao.insert(clienterWxLog);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
