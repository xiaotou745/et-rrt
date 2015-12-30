package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterBalanceRecord;

public interface IClienterBalanceRecordService {
	List<ClienterBalanceRecord> getRecordList(Long clienterId);
}
