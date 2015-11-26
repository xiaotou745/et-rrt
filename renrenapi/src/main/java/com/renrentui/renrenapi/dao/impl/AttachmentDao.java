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
				"IAttachmentDao.insertList", recordList);
	}

	@Override
	public List<Attachment> selectByTaskId(Long taskId) {
		return getMasterSqlSessionUtil().selectList(
				"IAttachmentDao.selectByTaskId", taskId);
	}

	@Override
	public int deleteByTaskId(Long taskId) {
		return getMasterSqlSessionUtil().delete(
				"IAttachmentDao.deleteByTaskId", taskId);
	}

}
