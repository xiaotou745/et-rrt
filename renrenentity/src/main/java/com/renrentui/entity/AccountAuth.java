package com.renrentui.entity;

import java.util.Date;

public class AccountAuth {
    private Integer id;

    public Integer getAccoutId() {
		return accoutId;
	}

	public void setAccoutId(Integer accoutId) {
		this.accoutId = accoutId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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

	private Integer accoutId;

    private Integer menuId;

    private Date createTime;

    private String optName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}