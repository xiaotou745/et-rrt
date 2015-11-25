package com.renrentui.renrenapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renrentui.renrenapi.dao.inter.ITaskDatumDao;
import com.renrentui.renrenapi.service.inter.ITaskDatumService;
import com.renrentui.renrencore.enums.TemplateGroupType;
import com.renrentui.renrenentity.domain.TaskDatumModel;
import com.renrentui.renrenentity.domain.TaskDatumTitle;
import com.renrentui.renrenentity.req.TaskDatumReq;
@Service
public class TaskDatumService implements ITaskDatumService{
@Autowired
private ITaskDatumDao taskDatumDao;
	@Override
	public List<TaskDatumModel> getMyTaskDatumList(TaskDatumReq req) {
		List<TaskDatumModel> result=taskDatumDao.getMyTaskDatumList(req);
		List<TaskDatumTitle> titlesList=taskDatumDao.getMyTaskDatumTitleList(req);
		for (TaskDatumModel datumModel : result) {
			List<TaskDatumTitle> tempList=titlesList.stream().filter(t->t.getTaskDatumId()==datumModel.getTaskDatumId()).collect(Collectors.toList());
			tempList.sort((a,b)->{return a.getOrderNum()-b.getOrderNum();});
			if (tempList.size()>0) {
				datumModel.setGroupType(tempList.get(0).getGroupType());
				TemplateGroupType datumGroupType=TemplateGroupType.getEnum(tempList.get(0).getGroupType());
				List<String> tempTitles=new ArrayList<>();
				if (datumGroupType==TemplateGroupType.TextGroup) {
					tempTitles.add(tempList.get(0).getControlValue());
				}else if(datumGroupType==TemplateGroupType.ImageGroup||
						datumGroupType==TemplateGroupType.MutliImageGroup){
					List<String> imageList=tempList.stream().map(t->t.getControlValue()).collect(Collectors.toList());
					tempTitles.addAll(imageList);
				}
				datumModel.setTitlesList(tempTitles);
			}
		}
		return result;
	}

}
