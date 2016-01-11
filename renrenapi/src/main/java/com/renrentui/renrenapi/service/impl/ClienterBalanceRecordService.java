package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.SubmissionTip;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;
import com.renrentui.renrenentity.req.GetIncomeReq;
import com.renrentui.renrenentity.req.GetUserCReq;

@Service
public class ClienterBalanceRecordService implements IClienterBalanceRecordService{
@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;
	@Override
	public List<ClienterBalanceRecord> getRecordList(GetIncomeReq req) {
		return clienterBalanceRecordDao.getRecordList(req);
	}
	/**
	 * 地推余额流水分页
	 */
	@Override
	public PagedResponse<ClienterBalanceRecord> getRecordList(
			ClienterBlanceRecordReq req) {
		return clienterBalanceRecordDao.getRecordList(req);
	}

	@Override
	public String getSubmissionTip(Long orderId) {
		List<SubmissionTip> list=clienterBalanceRecordDao.getSubmissionTip(orderId);
		if(list==null||list.size()==0)
		return "无";
		StringBuilder sBuilder=new StringBuilder();
		for (SubmissionTip tip : list) {
			sBuilder.append(tip.getClienterName()+","+tip.getPhoneNo()+",分佣:"+tip.getAmount()+"元<br>");
		}
		return sBuilder.toString();
	}
}
