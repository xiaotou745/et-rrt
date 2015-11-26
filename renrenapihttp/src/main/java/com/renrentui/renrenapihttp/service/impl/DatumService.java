package com.renrentui.renrenapihttp.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.ITaskDatumService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IDatumService;
import com.renrentui.renrencore.enums.DatumAuditStatus;
import com.renrentui.renrencore.enums.TaskCode;
import com.renrentui.renrenentity.domain.TabModel;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.req.TaskDatumReq;
@Service
public class DatumService implements IDatumService{
	@Autowired
	ITaskDatumService taskDatumService;
	@Override
	public HttpResultModel<TabModel<TaskDatumModel>> getMyTaskDatumList(TaskDatumReq req) {
		HttpResultModel<TabModel<TaskDatumModel>> hrm = new HttpResultModel<TabModel<TaskDatumModel>>();
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
		List<TaskDatumModel> result=taskDatumService.getMyTaskDatumList(req);
		TabModel<TaskDatumModel> td = new TabModel<TaskDatumModel>();
		td.setContent(result);
		td.setCount(result.size());
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

}
