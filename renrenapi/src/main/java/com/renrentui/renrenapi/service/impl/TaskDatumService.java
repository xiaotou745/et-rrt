package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.ITaskDatumDao;
import com.renrentui.renrenapi.service.inter.ITaskDatumService;
import com.renrentui.renrencore.enums.TemplateGroupType;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.domain.TaskDatumDetail;
import com.renrentui.renrenentity.domain.TaskDatumDetailGroup;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.domain.TaskDatumTitle;
import com.renrentui.renrenentity.domain.TemplateInfo;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;
import com.renrentui.renrenentity.req.TaskDatumReq;
@Service
public class TaskDatumService implements ITaskDatumService{
@Autowired
private ITaskDatumDao taskDatumDao;
@Autowired
private IRenRenTaskDao renRenTaskDao;
/**
 * 获取我的资料列表
 * @author hailongzhao
 * @date 20151125
 * @param req
 * @return
 */
	@Override
	public List<TaskDatumModel> getMyTaskDatumList(TaskDatumReq req) {
		List<TaskDatumModel> result=taskDatumDao.getMyTaskDatumList(req);
		List<TaskDatumTitle> titlesList=taskDatumDao.getMyTaskDatumTitleList(req);
		for (TaskDatumModel datumModel : result) {
			List<String> tempTitles=new ArrayList<>();
			List<TaskDatumTitle> tempList=titlesList.stream().filter(t->t.getTaskDatumId()==datumModel.getTaskDatumId()).collect(Collectors.toList());
			tempList.sort((a,b)->{return a.getOrderNum()-b.getOrderNum();});
			if (tempList.size()>0) {
				datumModel.setGroupType(tempList.get(0).getGroupType());
				TemplateGroupType datumGroupType=TemplateGroupType.getEnum(tempList.get(0).getGroupType());
				
				if (datumGroupType==TemplateGroupType.TextGroup) {
					tempTitles.add(tempList.get(0).getControlValue());
				}else if(datumGroupType==TemplateGroupType.ImageGroup||
						datumGroupType==TemplateGroupType.MutliImageGroup){
					List<String> imageList=tempList.stream().map(t->t.getControlValue()).collect(Collectors.toList());
					tempTitles.addAll(imageList);
				}
			}
			datumModel.setTitlesList(tempTitles);
		}
		return result;
	}
	/**
	 * 获取资料模板或资料详情
	 * @param req
	 * @author hailongzhao
	 * @date 20151126
	 * @return
	 */
	@Override
	public TemplateInfo getTaskDatumDetail(TaskDatumDetailReq req) {
		if (req.getTaskId()==0) {//获取任务的资料模板
			return null;
		}
		TemplateInfo info=new TemplateInfo();
		RenRenTask task= renRenTaskDao.getTaskDetail(req);//获取任务 任务信息
		List<TaskDatumDetailGroup> result=taskDatumDao.getTaskDatumGroupList(req);
		List<TaskDatumDetail> detailList=taskDatumDao.getTaskDatumDetailList(req);
		for (TaskDatumDetailGroup detailGroup : result) {
			List<TaskDatumDetail> tempDetail=detailList.stream().filter(t->t.getGroupId()==detailGroup.getGroupId()).collect(Collectors.toList());
			detailGroup.setControlList(tempDetail);
		}
		info.setTask(task);
		info.setTemplateGroup(result);
		return info;
	}

}
