package com.renrentui.renrenentity.req;

import java.util.Arrays;
import java.util.List;

public class AlipayBatchReq {
	private int type;
	private String data;
    private String optName;
    
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public List<String> getDataList() {
		return Arrays.asList(data.split(","));
	}
}
