package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;
import com.renrentui.renrenentity.req.GetIncomeReq;
import com.renrentui.renrenentity.req.GetUserCReq;

public interface IClienterBalanceRecordService {
	List<ClienterBalanceRecord> getRecordList(GetIncomeReq req);
	/**
	 * 地推余额流水分页
	 * @param req
	 * @return
	 */
	PagedResponse<ClienterBalanceRecord> getRecordList (ClienterBlanceRecordReq req);
	
	/**
	 * 获取分佣提示
	 * @param orderId
	 * @return
	 */
	String getSubmissionTip(Long orderId);
}
