package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.QuartzServiceModel;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedQuartzServiceReq;
import com.renrentui.renrenentity.req.QuartzUpdateReq;

/**
 * @author haichao
 *
 */
public interface IQuartzServiceDao {
	PagedResponse<QuartzServiceModel> pagedQuery(PagedQuartzServiceReq req);
	QuartzServiceModel selectById(long id);
	int insert(QuartzServiceModel record);
	int update(QuartzServiceModel record);
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	int updateStatus(QuartzUpdateReq req);
	List<QuartzServiceModel> queryStartList();
}
