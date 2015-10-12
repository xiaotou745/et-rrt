package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.ITemplateDetailSnapshotDao;
@Repository
public class TemplateDetailSnapshotDao extends DaoBase implements ITemplateDetailSnapshotDao {

	@Override
	public int copySnapshot(Long templateId,Long snapshotId) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("templateId", templateId);
		map.put("snapshotId", snapshotId);
		return getMasterSqlSessionUtil()
				.insert("com.renrentui.renrenapi.dao.inter.ITemplateDetailSnapshotDao.copySnapshot",
						map);
	}

	@Override
	public int deleteBySnapshotTemplateId(Long snapshotTemplateId) {
		return getMasterSqlSessionUtil()
				.delete("com.renrentui.renrenapi.dao.inter.ITemplateDetailSnapshotDao.deleteByTemplateId",
						snapshotTemplateId);
	}

}
