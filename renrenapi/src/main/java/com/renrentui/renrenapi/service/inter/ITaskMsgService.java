package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.req.TaskMsgReq;
import com.renrentui.renrenentity.req.TaskMsgUpdateReq;

public interface ITaskMsgService {
	/**
	 * 查询我的消息列表
	 * @param req
	 * @Author zhaohl
	 * @return
	 */
	List<TaskMsg> getMyMsgList(TaskMsgReq req);
	/**
	 * 查询我的消息列表未读消息的个数
	 * @param req
	 * @return
	 */
	Integer getMyMsgCount(Integer userId);
	/**
	 * 删除消息或将消息置为已读状态
	 * @param req
	 * @Author zhaohl
	 * @return
	 */
	int updateMsg(TaskMsgUpdateReq req);
	
	/**
	 * 新增消息
	 * @param req
	 * @Author zhaohl
	 * @return
	 */
	int insertMsg(String title,String msg,String createName,
			int msgType,long clienterId,long taskId,long taskDatumId);
}
