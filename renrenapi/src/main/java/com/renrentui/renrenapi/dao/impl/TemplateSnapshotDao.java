package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao;
import com.renrentui.renrenentity.req.TemplateSnapshotReq;
@Repository
public class TemplateSnapshotDao extends DaoBase implements ITemplateSnapshotDao{

	@Override
	public int copySnapshot(TemplateSnapshotReq req) {
		return getMasterSqlSessionUtil()
				.insert("com.renrentui.renrenapi.dao.inter.ITemplateSnapshotDao.copySnapshot",
						req);
	}

}
