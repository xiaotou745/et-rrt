package com.renrentui.renrenentity;

import java.util.Date;

public class RenRenTaskLog {
    private Long id;

    private Long renrenTaskId;

    private Short optType;

    private Date createTime;

    private String optName;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRenrenTaskId() {
		return renrenTaskId;
	}

	public void setRenrenTaskId(Long renrenTaskId) {
		this.renrenTaskId = renrenTaskId;
	}

	public Short getOptType() {
		return optType;
	}

	public void setOptType(Short optType) {
		this.optType = optType;
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

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}