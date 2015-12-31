package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.ITaskShareStatisticsDao;
import com.renrentui.renrenapi.service.inter.ITaskShareStatisticsService;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrencore.util.SystemUtils;
import com.renrentui.renrenentity.TaskShareStatistics;

@Service
public class TaskShareStatisticsService implements ITaskShareStatisticsService {
	@Autowired
	private ITaskShareStatisticsDao taskShareStatisticsDao;

	@Override
	public void insert(TaskShareStatistics record) {
		Thread dThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					taskShareStatisticsDao.insert(record);
				} catch (Exception e) {
					System.out.println("分享或下载类任务记录db时出错：" + e.getMessage());
					String stackTrace = StringUtils.getStackTrace(e);
					List<String> ipinfoList = SystemUtils.getLocalIpInfo();
					String appServerIP = JsonUtil.obj2string(ipinfoList);
					SystemUtils.sendAlertEmail("分享或下载类任务记录db_renrenadmin_java项目预警", "appServerIP:"
									+ appServerIP + "\n" + e.getMessage()+ "\n" + stackTrace);
				}

			}
		});
		dThread.setDaemon(false);
		dThread.start();
	}

}
