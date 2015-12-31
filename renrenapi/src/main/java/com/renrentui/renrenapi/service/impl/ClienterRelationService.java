package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IClienterRelationDao;
import com.renrentui.renrenapi.service.inter.IClienterRelationService;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.req.CRelationReq;
@Service
public class ClienterRelationService implements IClienterRelationService{
	@Autowired
	private IClienterRelationDao clienterRelationDao;
	@Override
	public List<ClienterRelationModel> getClienterRelationModelsByPhone(
			CRelationReq req) {
		return clienterRelationDao.getClienterRelationModelsByPhone(req);
	}

}
