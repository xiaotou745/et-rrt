package com.renrentui.renrenapihttp.service.inter;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.domain.TaskDatumGroup;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.domain.TemplateInfo;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
import com.renrentui.renrenentity.req.TaskDatumReq;

/**
 * 资料相关模块
 * @author zhaohl
 * @date 20151125
 */
@Path("/taskdatum")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IDatumService {
	/**
	 * 获取资料模板或资料详情
	 * @param req
	 * @author hailongzhao
	 * @date 20151126
	 * @return
	 */
	@POST
	@Path("/gettaskdatumdetail")
	HttpResultModel<TemplateInfo> getTaskDatumDetail(TaskDatumDetailReq req);
	/**
	 * 获取我的资料列表
	 * @author hailongzhao
	 * @date 20151125
	 * @param req
	 * @return
	 */
	@POST
	@Path("/getmytaskdatumlist")
	HttpResultModel<TabModel<TaskDatumModel>> getMyTaskDatumList(TaskDatumReq req);
	/**
	 * 获取我的资料列表
	 * @author hailongzhao
	 * @date 20151125
	 * @param req
	 * @return
	 */
	@POST
	@Path("/getmytaskdatumgrouplist")
	HttpResultModel<TabModel<TaskDatumGroup>> getMyTaskDatumGroupList(TaskDatumReq req);
}
