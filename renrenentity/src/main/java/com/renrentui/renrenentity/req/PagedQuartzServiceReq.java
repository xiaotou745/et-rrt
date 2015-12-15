package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

public class PagedQuartzServiceReq extends PagedRequestBase{
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
