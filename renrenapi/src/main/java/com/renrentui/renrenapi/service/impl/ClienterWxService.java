package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.impl.ClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterWxDao;
import com.renrentui.renrenapi.dao.inter.IClienterWxLogDao;
import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrenentity.ClienterWx;
import com.renrentui.renrenentity.ClienterWxLog;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterWxModel;
import com.renrentui.renrenentity.req.PagedClienterWxReq;

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
			ClienterWx resultClienterWx = clienterWxDao
					.getClienterByOpenId(openId);
			if (resultClienterWx == null || resultClienterWx.getId() <= 0) {
				// 第一次绑定
				ClienterWx clienterWx = new ClienterWx();
				clienterWx.setClienterId(0);
				clienterWx.setFollowStatus(1);
				clienterWx.setFromUserName(fromUserName);
				clienterWx.setOpenId(openId);
				clienterWx.setWxId("");
				clienterWx.setClienterId(0);
				clienterWxDao.insert(clienterWx);
			}
			else if (resultClienterWx.getFollowStatus() == 1) {
				// 当前用户已经绑定过了
				return false;
			} else if (resultClienterWx.getFollowStatus() == 0) {
				// 取消后再次绑定的
				clienterWxDao.follow(openId);
			}
			
			ClienterWxLog clienterWxLog = new ClienterWxLog();
			clienterWxLog.setFollowStatus(1);
			clienterWxLog.setOpenId(openId);
			clienterWxLogDao.insert(clienterWxLog);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
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
			return false;
		}
		return true;
	}

	@Override
	public boolean isAttentionWx(String openid) {
		return clienterWxDao.isAttentionWx(openid);
	}

	@Override
	public int hadFetchRedbag(String openid) {
		return clienterWxDao.hadFetchRedbag(openid);
	}

	@Override
	public PagedResponse<ClienterWxModel> getlist(PagedClienterWxReq req) { 
		return clienterWxDao.getlist(req);
	}

	@Override
	public ClienterWxModel getByPhone(String phoneNo) {
		return clienterWxDao.getByPhone(phoneNo);
	}

}
