package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterRelation;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.req.CRelationReq;

public interface IClienterRelationDao {
	
	/**
	 * 通过骑士ID查询自己的上级关系
	 * @param clienterId
	 * @return
	 */
	List<ClienterRelation> getRelastionListByClienterId(Long clienterId);
	
	/***
	 * 插入一个关系
	 * @param model
	 * @return
	 */
	int insertClienterRelation(ClienterRelation model);
	
	int getLevelByClienterId(Long clienterId);
	
	/**
	 * 通过推荐人手机号统计下面的信息
	 * @param req
	 * @return
	 */
	List<ClienterRelationModel> getClienterRelationModelsByPhone(CRelationReq req);

}
