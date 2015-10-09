package com.renrentui.renrenentity;

import java.util.Date;

public class Template {
    private Long id;

    private Long businessId;

    private String templateName;
    private String createName;

    private Date createTime;

    private String lastOptName;

    private Date lastOptTime;

    private Short status;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastOptName() {
		return lastOptName;
	}

	public void setLastOptName(String lastOptName) {
		this.lastOptName = lastOptName;
	}

	public Date getLastOptTime() {
		return lastOptTime;
	}

	public void setLastOptTime(Date lastOptTime) {
		this.lastOptTime = lastOptTime;
	}

	public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
}