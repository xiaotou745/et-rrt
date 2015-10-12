package com.renrentui.renrenentity;

public class TemplateDetailSnapshot {
    private Long id;

    private Long snapshotTemplateId;

    private Long controlId;

    private Integer orderNum;

    private String name;

    private String title;

    private String defaultValue;

    private String controlData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

	public Long getSnapshotTemplateId() {
		return snapshotTemplateId;
	}

	public void setSnapshotTemplateId(Long snapshotTemplateId) {
		this.snapshotTemplateId = snapshotTemplateId;
	}

	public Long getControlId() {
		return controlId;
	}

	public void setControlId(Long controlId) {
		this.controlId = controlId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getControlData() {
		return controlData;
	}

	public void setControlData(String controlData) {
		this.controlData = controlData;
	}

    
}