package com.renrentui.renrenentity.domain;

import java.util.List;

public class TaskDomain {
	private int total;
	
	private int count;
	
	private long nextId;
	
	private List<TaskModel> content;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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

	public List<TaskModel> getContent() {
		return content;
	}

	public void setContent(List<TaskModel> content) {
		this.content = content;
	}
}
