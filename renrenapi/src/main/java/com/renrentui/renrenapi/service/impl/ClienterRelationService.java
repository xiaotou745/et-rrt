package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenapi.service.inter.IClienterRelationService;
import com.renrentui.renrenentity.domain.ClienterRelationLevelModel;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.domain.PartnerItem;
import com.renrentui.renrenentity.req.CRelationReq;
import com.renrentui.renrenentity.req.PagedPartnerListReq;
@Service
public class ClienterRelationService implements IClienterRelationService{
	@Autowired
	private IClienterRelationDao clienterRelationDao;
	@Override
	public List<ClienterRelationModel> getClienterRelationModelsByPhone(
			CRelationReq req) {
		return clienterRelationDao.getClienterRelationModelsByPhone(req);
	}
	/**
	 * 按照级别查询推荐信息
	 */
	@Override
	public List<ClienterRelationLevelModel> getClienterRelationModelsByJibie(
			CRelationReq req) {
		return clienterRelationDao.getClienterRelationModelsByJibie(req);
	}
	@Override
	public List<PartnerItem> getPagedPartnerListByUserId(PagedPartnerListReq req) {
		return clienterRelationDao.getPagedPartnerListByUserId(req);
	}

}
