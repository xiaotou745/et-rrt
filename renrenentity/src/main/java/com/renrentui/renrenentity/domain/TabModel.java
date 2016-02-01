package com.renrentui.renrenentity.domain;

import java.util.List;
import java.util.Map;

/**
 * 给手机端提供的通用页签对象
 * 
 * @author hailongzhao
 * @date 20151125
 * @param <T>
 */
public class TabModel<T> {
	private String title;
	private int count;

	private long nextId;
	private long total;
	private long waitTotal;
	private long passTotal;
	private long refuseTotal;
    private int taskStatus;
    private String taskStatusName;
	private List<T> content;
	public long getWaitTotal() {
		return waitTotal;
	}

	public void setWaitTotal(long waitTotal) {
		this.waitTotal = waitTotal;
	}

	public long getPassTotal() {
		return passTotal;
	}

	public void setPassTotal(long passTotal) {
		this.passTotal = passTotal;
	}

	public long getRefuseTotal() {
		return refuseTotal;
	}

	public void setRefuseTotal(long refuseTotal) {
		this.refuseTotal = refuseTotal;
	}



	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getNextId() {
		return nextId;
	}

	public void setNextId(long nextId) {
		this.nextId = nextId;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskStatusName() {
		return taskStatusName;
	}

	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}
}
