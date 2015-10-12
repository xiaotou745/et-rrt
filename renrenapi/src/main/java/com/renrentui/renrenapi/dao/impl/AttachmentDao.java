package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAttachmentDao;
import com.renrentui.renrenentity.Attachment;
@Repository
public class AttachmentDao extends DaoBase implements IAttachmentDao{

	@Override
	public int insertList(List<Attachment> recordList) {
		return getMasterSqlSessionUtil().update(
				"com.renrentui.renrenapi.dao.inter.IAttachmentDao.insertList", recordList);
	}

	@Override
	public List<Attachment> selectByTaskId(Long taskId) {
		return getMasterSqlSessionUtil().selectList(
				"com.renrentui.renrenapi.dao.inter.IAttachmentDao.selectByTaskId", taskId);
	}

}
