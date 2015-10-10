package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskLogDao;
import com.renrentui.renrenentity.RenRenTaskLog;
@Repository
public class RenRenTaskLogDao extends DaoBase implements IRenRenTaskLogDao {

	@Override
	public int insert(RenRenTaskLog record) {
return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IRenRenTaskLogDao.insert", record);
	}

}
