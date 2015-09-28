package com.renrentui.renrenentity;

import java.util.Date;

public class ClienterLog {
    private Long id;

    private Long clienterId;

    private Date createTime;

    private String optName;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Long getClienterId() {
		return clienterId;
	}

	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}
}