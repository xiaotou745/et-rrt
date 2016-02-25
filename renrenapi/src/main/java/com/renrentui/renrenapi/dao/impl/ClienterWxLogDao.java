package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterWxLogDao;
import com.renrentui.renrenentity.ClienterWxLog;

@Repository
public class ClienterWxLogDao extends DaoBase implements IClienterWxLogDao {

	/** 写一个日志 窦海超  2016年2月25日 15:39:52*/
	@Override
	public void insert(ClienterWxLog model) {
		getMasterSqlSessionUtil().insert("IClienterWxLogDao.insert", model);
	}

}
