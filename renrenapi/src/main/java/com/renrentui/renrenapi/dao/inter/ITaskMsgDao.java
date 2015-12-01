package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.req.TaskMsgReq;
import com.renrentui.renrenentity.req.TaskMsgUpdateReq;

/**
 * 任务的消息接口
 * @author hailongzhao
 * @date 20151125
 */
public interface ITaskMsgDao {
	/**
	 * 查询我的消息列表
	 * @param req
	 * @return
	 */
	List<TaskMsg> getMyMsgList(TaskMsgReq req);
	/**
	 * 删除消息或将消息置为已读状态
	 * @param req
	 * @return
	 */
	int updateMsg(TaskMsgUpdateReq req);
	/**
	 * 新增一条消息
	 * @param record
	 * @return
	 */
	int insert(TaskMsg record);
	/**
	 * 批量新增消息
	 * @param msgList
	 * @return
	 */
	int insertList(List<TaskMsg> msgList);
}
