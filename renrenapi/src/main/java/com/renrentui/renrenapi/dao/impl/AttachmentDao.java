package com.renrentui.renrenapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IAttachmentDao;
import com.renrentui.renrenentity.Attachment;
@Repository
public class AttachmentDao extends DaoBase implements IAttachmentDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Attachment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Attachment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Attachment selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Attachment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Attachment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertList(List<Attachment> recordList) {
		return getMasterSqlSessionUtil().update(
				"com.renrentui.renrenapi.dao.inter.IAttachmentDao.insertList", recordList);
	}

}
