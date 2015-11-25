package com.renrentui.renrenentity.domain;

import java.util.List;

/**
 * 给手机端提供的通用页签对象
 * @author hailongzhao
 * @date 20151125
 * @param <T>
 */
public class TabModel<T> {
	private String title;
	private int count;
	
	private long nextId;
	
	private List<T> content;

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
}
