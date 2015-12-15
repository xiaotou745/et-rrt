package com.renrentui.renrenapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.common.ChildJob;
import com.renrentui.renrenapi.common.QuartzManager;
import com.renrentui.renrenapi.dao.inter.IQuartzServiceDao;
import com.renrentui.renrenapi.service.inter.IQuartzService;
import com.renrentui.renrencore.util.SpringBeanHelper;
import com.renrentui.renrenentity.QuartzServiceModel;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedQuartzServiceReq;
import com.renrentui.renrenentity.req.QuartzUpdateReq;

@Service
public class QuartzService implements IQuartzService {
	@Autowired
	IQuartzServiceDao quartzServiceDao;

	/**
	 * @author hailongzhao
	 * @date 20151211
	 * */
	@Override
	public int mainJob(QuartzUpdateReq req) {
		QuartzServiceModel item=quartzServiceDao.selectById(req.getId());
		String jobName = item.getBeanName();
		if(req.getStatus()==1){
			try {
				SpringBeanHelper.getCustomBean(item.getBeanName());
			} catch (Exception e) {
				return -1;
			}
			Date firstFireTime=QuartzManager.getFirstFireTime(item.getExecTime());
			req.setFirstFireTime(firstFireTime);
			QuartzManager.addJob(jobName, ChildJob.class, item.getExecTime());// 如果库里是开启，本地没开启就新增服务
		}
		else {
			//停止
			QuartzManager.removeJob(jobName);
		}
		return quartzServiceDao.updateStatus(req);
	}

	@Override
	public PagedResponse<QuartzServiceModel> pagedQuery(
			PagedQuartzServiceReq req) {
		PagedResponse<QuartzServiceModel> result= quartzServiceDao.pagedQuery(req);
		List<QuartzServiceModel> temp=result.getResultList().stream().filter(t->t.getIsStart()==1).collect(Collectors.toList());
		QuartzUpdateReq updateReq=new QuartzUpdateReq();
		for (QuartzServiceModel item : temp) {
			int status=QuartzManager.checkJob(item.getBeanName());
			if (status==-1) {
				try {
					QuartzManager.addJob(item.getBeanName(), ChildJob.class, item.getExecTime());// 如果库里是开启，本地没开启就新增服务
				} catch (Exception e) {
					item.setIsStart(0);
					updateReq.setId(item.getId());
					updateReq.setStatus(0);
					updateReq.setUpdateName(item.getUpdateName());
					quartzServiceDao.updateStatus(updateReq);
				}
			}
		}
		return result;
	}

	@Override
	public int insert(QuartzServiceModel record) {
		return quartzServiceDao.insert(record);
	}

	@Override
	public int update(QuartzServiceModel record) {
		return quartzServiceDao.update(record);
	}

	@Override
	public boolean checkCron(String cron) {
		return QuartzManager.isValidExpression(cron);
	}
}