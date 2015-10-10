package com.renrentui.renrenservice.service;

import com.renrentui.renrenapi.service.inter.IRenRenTaskService;
import com.renrentui.renrencore.util.SpringBeanHelper;

/**
 * 人人地推任务相关服务
 * @author CaoHeYang
 * @date 20151009
 */
public class TaskWindowService {
	 IRenRenTaskService iRenRenTaskService = (IRenRenTaskService) SpringBeanHelper .getCustomBean("RenRenTaskService");
	/**
	 * 超时取消任务服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	public void outTimeCanelTask() {
		iRenRenTaskService.outTimeCanelTask();
	}
}
