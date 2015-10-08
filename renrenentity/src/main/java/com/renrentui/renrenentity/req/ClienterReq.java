package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

/**
* @Des  地推员列表获取入参实体类
* @Author WangXuDan
* @Date 2015年9月29日17:01:53
* @Return
*/
public class ClienterReq extends PagedRequestBase  {
	private String clienterName;

    private String phoneNo;

    private Integer status;

	public String getClienterName() {
		return clienterName;
	}

	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
