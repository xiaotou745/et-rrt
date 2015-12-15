package com.renrentui.renrenapi.quartzservice;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.common.IJobDo;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrencore.util.ParseHelper;
/**
 * 
 * 超时关闭任务服务
 * @author ofmyi_000
 *
 */
@Service
public class ExpireTaskService implements IJobDo{
	@Autowired
	private IRenRenTaskDao renRenTaskDao;
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 50)
	public void run() {
		System.out.println("关闭任务开始" +ParseHelper.ToDateString(new Date()));
		renRenTaskDao.outTimeCanelTask();
		System.out.println("关闭任务结束" +ParseHelper.ToDateString(new Date()));
	}
}
