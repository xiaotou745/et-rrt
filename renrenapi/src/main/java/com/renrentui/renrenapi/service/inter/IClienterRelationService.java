package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.domain.ClienterRelationLevelModel;
import com.renrentui.renrenentity.domain.ClienterRelationModel;
import com.renrentui.renrenentity.domain.PartnerItem;
import com.renrentui.renrenentity.req.CRelationReq;
import com.renrentui.renrenentity.req.PagedPartnerListReq;

/**
 * 层级关系
 * @author ofmyi_000
 *
 */
public interface IClienterRelationService {
	/**
	 * 通过推荐人手机号统计下面的信息
	 * @param req
	 * @return
	 */
	List<ClienterRelationModel> getClienterRelationModelsByPhone(CRelationReq req);
	/**
	 * 
	 * 按照级别查询推荐信息
	 * @param req
	 * @return
	 */
	List<ClienterRelationLevelModel> getClienterRelationModelsByJibie(CRelationReq req);
	List<PartnerItem> getPagedPartnerListByUserId(PagedPartnerListReq req);

}
