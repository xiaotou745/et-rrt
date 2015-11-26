package com.renrentui.renrenapihttp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.ITaskMsgService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IMsgService;
import com.renrentui.renrencore.enums.MsgOpType;
import com.renrentui.renrencore.enums.TaskCode;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.req.TaskMsgReq;
import com.renrentui.renrenentity.req.TaskMsgUpdateReq;
/**
 * app消息接口
 * @author hailongzhao
 * @date 20151125
 */
@Service
public class MsgService implements IMsgService{
@Autowired
private ITaskMsgService taskMsgService;
/**
 * 我的消息列表
 * @author hailongzhao
 * @date 20151125
 */
@Override
public HttpResultModel<TabModel<TaskMsg>> getMyMsgList(TaskMsgReq req) {
	HttpResultModel<TabModel<TaskMsg>> hrm = new HttpResultModel<TabModel<TaskMsg>>();
	hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
	if(req.getUserId()<=0){
		hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
		return hrm;
	} 
  
	List<TaskMsg> taskModelList= taskMsgService.getMyMsgList(req);
	TabModel<TaskMsg> td = new TabModel<TaskMsg>();
	td.setContent(taskModelList);
	td.setCount(taskModelList.size());
	if(taskModelList!=null && taskModelList.size()>0){
		td.setNextId(taskModelList.get(taskModelList.size()-1).getId());
	}
	hrm.setData(td); 
	return hrm;
}
/**
 * 删除或阅读消息
 * @author hailongzhao
 * @date 20151125
 */
@Override
public HttpResultModel<String> updateMsg(TaskMsgUpdateReq req) {
	HttpResultModel<String> hrm = new HttpResultModel<String>();
	hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
	if(req.getUserId()<=0){
		hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
		return hrm;
	} 
	if(req.getOpType()!=MsgOpType.DeleteMsg.value()&&
	   req.getOpType()!=MsgOpType.ReadMsg.value()){
		hrm.setCode(TaskCode.MsgOpType.value()).setMsg(TaskCode.MsgOpType.desc());			
		return hrm;
	} 
	if(taskMsgService.updateMsg(req)==0){
		hrm.setCode(TaskCode.MsgId.value()).setMsg(TaskCode.MsgId.desc());
	}
	return hrm;
}


}
