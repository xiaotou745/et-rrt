package com.renrentui.renrenapi.quartzservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.common.IJobDo;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.dao.inter.ITaskMsgDao;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.RenRenTask;
import com.renrentui.renrenentity.TaskMsg;

/**
 * 还有一天就要过期的任务发送消息
 * @author ofmyi_000
 *
 */
@Service
public class SendTaskMsgService implements IJobDo{

	@Autowired
	private IRenRenTaskDao renRenTaskDao;
	@Autowired
	private ITaskMsgDao taskMsgDao;
	
	private static Logger renrenAdminLogger = Logger.getLogger("renrenAdminLogger");
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 50)
	public void run() {
		try {
			//获取还有一天过期的任务ID
			System.out.println("站内信开始" +ParseHelper.ToDateString(new Date()));
			List<RenRenTask> tasklist=renRenTaskDao.getTaskSendList();
			if(tasklist!=null&&tasklist.size()>0)//集合有数据
			{
				for (int i = 0; i < tasklist.size(); i++) {//遍历任务
					List<Long> list=renRenTaskDao.getClinerIdList(tasklist.get(i).getId());//获取任务下的所有地推员
					List<TaskMsg> msgList=new ArrayList<TaskMsg> ();
					for (int j = 0; j < list.size(); j++) {
						TaskMsg itemMsg=new TaskMsg();
						itemMsg.setClienterId(list.get(j));
						itemMsg.setTaskId(tasklist.get(i).getId());
						itemMsg.setTitle("任务提醒");
						itemMsg.setCreateName("admin");
						itemMsg.setMsg("您领取的《"+tasklist.get(i).getTaskTitle()+"》还有1天就到期啦，如有未提交的资料，记得尽快提交哦。");
						itemMsg.setMsgType(0);
						msgList.add(itemMsg);
						if(msgList.size()==50||j==list.size()-1)//集合满50个 或者已经没有批次 插入数据库
						{
							taskMsgDao.insertList(msgList);
							msgList.clear();//插完数据库清空集合
						}
					}//任务I已经发完消息,修改状态为已发
					renRenTaskDao.setTaskSend(tasklist.get(i).getId());
				}//任务遍历结束
			}
			System.out.println("站内信结束" +ParseHelper.ToDateString(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
