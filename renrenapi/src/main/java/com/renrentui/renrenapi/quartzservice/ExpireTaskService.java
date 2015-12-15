package com.renrentui.renrenapi.quartzservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.common.IJobDo;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
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
		renRenTaskDao.outTimeCanelTask();
	}
}
