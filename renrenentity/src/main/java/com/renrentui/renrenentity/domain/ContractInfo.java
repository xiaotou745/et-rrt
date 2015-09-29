package com.renrentui.renrenentity.domain;

import java.util.ArrayList;

/**
 * 任务详情  合同信息 ==
 * @author ofmyi_000
 *
 */
public class ContractInfo {
	
	public ContractInfo(){
		this.controlInfo=new ArrayList<ControlInfo>();
	}
	private long id;
	private String title;
	private ArrayList<ControlInfo> controlInfo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<ControlInfo> getOntrolInfo() {
		return controlInfo;
	}
	public void setOntrolInfo(ArrayList<ControlInfo> controlInfo) {
		this.controlInfo = controlInfo;
	}
}
