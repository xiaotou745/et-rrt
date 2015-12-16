package com.renrentui.renrenapi.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IQuartzServiceDao;
import com.renrentui.renrenentity.QuartzServiceModel;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedQuartzServiceReq;
import com.renrentui.renrenentity.req.QuartzUpdateReq;

@Repository
public class QuartzServiceDao extends DaoBase implements IQuartzServiceDao {

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	@Override
	public int updateStatus(QuartzUpdateReq req) {
		return getMasterSqlSessionUtil().update(
				"IQuartzServiceDao.updateStatus", req);
	}

	@Override
	public PagedResponse<QuartzServiceModel> pagedQuery(
			PagedQuartzServiceReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList("IQuartzServiceDao.pagedQuery",req);
	}

	@Override
	public QuartzServiceModel selectById(long id) {
		return getReadOnlySqlSessionUtil().selectOne("IQuartzServiceDao.selectById", id);
	}

	@Override
	public int insert(QuartzServiceModel record) {
		return getMasterSqlSessionUtil().insert("IQuartzServiceDao.insert", record);
	}

	@Override
	public int update(QuartzServiceModel record) {
		return getMasterSqlSessionUtil().update(
				"IQuartzServiceDao.update", record);
	}
	@Override
	public List<QuartzServiceModel> queryStartList() {
		return getReadOnlySqlSessionUtil().selectList("IQuartzServiceDao.queryStartList");
	}
}
