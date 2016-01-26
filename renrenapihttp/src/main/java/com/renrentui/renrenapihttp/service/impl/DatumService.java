package com.renrentui.renrenapihttp.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrenapi.service.inter.ITaskDatumService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IDatumService;
import com.renrentui.renrencore.enums.DatumAuditStatus;
import com.renrentui.renrencore.enums.TaskCode;
import com.renrentui.renrencore.enums.TaskStatus;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.domain.TaskDatumGroup;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.domain.TemplateInfo;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
import com.renrentui.renrenentity.req.TaskDatumReq;
@Service
public class DatumService implements IDatumService{
	@Autowired
	ITaskDatumService taskDatumService;
	@Autowired
	IRenRenTaskService  renRenTaskService;
	/**
	 * 获取资料模板或资料详情
	 * @param req
	 * @author hailongzhao
	 * @date 20151126
	 * @return
	 */
	@Override
	public HttpResultModel<TemplateInfo> getTaskDatumDetail(
			TaskDatumDetailReq req) {
		HttpResultModel<TemplateInfo> hrm = new HttpResultModel<TemplateInfo>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getUserId()<=0){
			hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
			return hrm;
		} 
		if (req.getTaskId()<=0) {
			hrm.setCode(TaskCode.TaskId.value()).setMsg(TaskCode.TaskId.desc());			
			return hrm;
		}
		TemplateInfo result=taskDatumService.getTaskDatumDetail(req);
		hrm.setData(result);
		return hrm;
	}
	/**
	 * 获取我的资料列表
	 * @author hailongzhao
	 * @date 20151125
	 * @param req
	 * @return
	 */
	@Override
	public HttpResultModel<TabModel<TaskDatumModel>> getMyTaskDatumList(TaskDatumReq req) {
		HttpResultModel<TabModel<TaskDatumModel>> hrm = new HttpResultModel<TabModel<TaskDatumModel>>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getUserId()<=0){
			hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
			return hrm;
		} 
		if(req.getTaskId()<=0){
			hrm.setCode(TaskCode.TaskId.value()).setMsg(TaskCode.TaskId.desc());			
			return hrm;
		} 
		DatumAuditStatus auditStatus=DatumAuditStatus.getEnum(req.getAuditStatus());
		if(auditStatus!=DatumAuditStatus.Audited&&
				auditStatus!=DatumAuditStatus.WaitAudit&&
						auditStatus!=DatumAuditStatus.Refuse){
					hrm.setCode(TaskCode.DatumAuditStatus.value()).setMsg(TaskCode.DatumAuditStatus.desc());			
					return hrm;
				} 
		RenRenTask taskInfo=renRenTaskService.getTaskInfo((long)req.getTaskId());
		if (taskInfo==null) {
			hrm.setCode(TaskCode.TaskNotExit.value()).setMsg(TaskCode.TaskNotExit.desc());			
			return hrm;
		}
		TabModel<TaskDatumModel> td = new TabModel<TaskDatumModel>();
		td.setTaskStatus(taskInfo.getStatus());
		if (taskInfo.getStatus()==TaskStatus.Audited.value()) {
			td.setTaskStatusName("进行中");
		}else {
			td.setTaskStatusName("已过期");
		}
		List<TaskDatumModel> result=taskDatumService.getMyTaskDatumList(req);
		td.setContent(result);
		td.setCount(result.size());
		List<Map<String, Integer>> totalResult=taskDatumService.getMyTaskDatumListTotal(req);
		for (Map<String, Integer> map : totalResult) {
			DatumAuditStatus datumStatus=DatumAuditStatus.getEnum(map.get("status"));
			if (datumStatus==DatumAuditStatus.Audited) {
				td.setPassTotal(map.get("totalNum"));
			}
			else if(datumStatus==DatumAuditStatus.WaitAudit){
				td.setWaitTotal(map.get("totalNum"));
			}
			else if(datumStatus==DatumAuditStatus.Refuse){
				td.setRefuseTotal(map.get("totalNum"));
			}
		}
		if(result!=null && result.size()>0){
			td.setNextId(result.get(result.size()-1).getTaskDatumId());
		}

		if (auditStatus==DatumAuditStatus.Audited) {
			td.setTitle("已通过("+td.getCount()+")");
		}
		else if(auditStatus==DatumAuditStatus.WaitAudit){
			td.setTitle("审核中("+td.getCount()+")");
		}
		else if(auditStatus==DatumAuditStatus.Refuse){
			td.setTitle("未通过("+td.getCount()+")");
		}
		hrm.setData(td); 
		return hrm;
	}
	@Override
	public HttpResultModel<TabModel<TaskDatumGroup>> getMyTaskDatumGroupList(TaskDatumReq req) {
		HttpResultModel<TabModel<TaskDatumGroup>> hrm = new HttpResultModel<TabModel<TaskDatumGroup>>();
		hrm.setCode(TaskCode.Success.value()).setMsg(TaskCode.Success.desc());
		if(req.getUserId()<=0){
			hrm.setCode(TaskCode.UserIdErr.value()).setMsg(TaskCode.UserIdErr.desc());			
			return hrm;
		} 
		DatumAuditStatus auditStatus=DatumAuditStatus.getEnum(req.getAuditStatus());
		if(auditStatus!=DatumAuditStatus.Audited&&
				auditStatus!=DatumAuditStatus.WaitAudit&&
						auditStatus!=DatumAuditStatus.Refuse){
					hrm.setCode(TaskCode.DatumAuditStatus.value()).setMsg(TaskCode.DatumAuditStatus.desc());			
					return hrm;
				} 
		List<TaskDatumGroup> result=taskDatumService.getMyTaskDatumGroupList(req);
		TabModel<TaskDatumGroup> td = new TabModel<TaskDatumGroup>();
		td.setContent(result);
		td.setCount(result.size());
		List<Map<String, Integer>> totalResult=taskDatumService.getMyTaskDatumListTotal(req);
		for (Map<String, Integer> map : totalResult) {
			DatumAuditStatus datumStatus=DatumAuditStatus.getEnum(map.get("status"));
			if (datumStatus==DatumAuditStatus.Audited) {
				td.setPassTotal(map.get("totalNum"));
			}
			else if(datumStatus==DatumAuditStatus.WaitAudit){
				td.setWaitTotal(map.get("totalNum"));
			}
			else if(datumStatus==DatumAuditStatus.Refuse){
				td.setRefuseTotal(map.get("totalNum"));
			}
		}
		if(result!=null && result.size()>0){
			td.setNextId(result.get(result.size()-1).getTaskId());
		}
		if (auditStatus==DatumAuditStatus.Audited) {
			td.setTitle("已通过("+td.getPassTotal()+")");
		}
		else if(auditStatus==DatumAuditStatus.WaitAudit){
			td.setTitle("审核中("+td.getWaitTotal()+")");
		}
		else if(auditStatus==DatumAuditStatus.Refuse){
			td.setTitle("未通过("+td.getRefuseTotal()+")");
		}
		hrm.setData(td); 
		return hrm;
	}
}
