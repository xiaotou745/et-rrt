package com.renrentui.renrenentity.domain;

import java.util.List;
import com.renrentui.renrenentity.RenRenTask;

/**
 * 任务详情实体 
 * @author ofmyi_000
 *
 */
public class TaskDetail {
	private RenRenTask task;
	private List<TaskSetp> taskSetps;
	private long partnerTotal;
	private List<PartnerDetail> partnerList;
	public RenRenTask getTask() {
		return task;
	}
	public void setTask(RenRenTask task) {
		this.task = task;
	}
	public List<TaskSetp> getTaskSetps() {
		return taskSetps;
	}
	public void setTaskSetps(List<TaskSetp> taskSetps) {
		this.taskSetps = taskSetps;
	}
	public List<PartnerDetail> getPartnerList() {
		return partnerList;
	}
	public void setPartnerList(List<PartnerDetail> partnerList) {
		this.partnerList = partnerList;
	}
	public long getPartnerTotal() {
		return partnerTotal;
	}
	public void setPartnerTotal(long partnerTotal) {
		this.partnerTotal = partnerTotal;
	}
	
}
