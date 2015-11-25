package com.renrentui.renrenapihttp.service.inter;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.req.TaskMsgReq;
import com.renrentui.renrenentity.req.TaskMsgUpdateReq;
@Path("/msg")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IMsgService {
	/**
	 * 查询我的消息列表
	 * @param req
	 * @return
	 */
	@POST
	@Path("/getmymsglist")
	HttpResultModel<TabModel<TaskMsg>> getMyMsgList(TaskMsgReq req);
	/**
	 * 删除消息或将消息置为已读状态
	 * @param req
	 * @return
	 */
	@POST
	@Path("/updatemsg")
	HttpResultModel<String> updateMsg(TaskMsgUpdateReq req);
}
