package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;

public interface IClienterBalanceRecordService {
	List<ClienterBalanceRecord> getRecordList(Long clienterId);
	/**
	 * 地推余额流水分页
	 * @param req
	 * @return
	 */
	PagedResponse<ClienterBalanceRecord> getRecordList (ClienterBlanceRecordReq req);
}
