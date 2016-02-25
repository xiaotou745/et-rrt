package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.ClienterRelation;
import com.renrentui.renrenentity.domain.ClienterRelationLevelModel;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.domain.PartnerItem;
import com.renrentui.renrenentity.req.CRelationReq;
import com.renrentui.renrenentity.req.PagedPartnerListReq;

public interface IClienterRelationDao {
	
	/**
	 * 通过骑士ID查询自己的上级关系
	 * @param clienterId flag  >0  查询带0根  查询不带0根的关系
	 * @return
	 */
	List<ClienterRelation> getRelastionListByClienterId(Long clienterId,int flag);
	
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
	/**
	 * 
	 * 按照级别查询信息
	 * @param req
	 * @return
	 */
	List<ClienterRelationLevelModel> getClienterRelationModelsByJibie(CRelationReq req);
	List<PartnerItem> getPagedPartnerListByUserId(PagedPartnerListReq req);

}
