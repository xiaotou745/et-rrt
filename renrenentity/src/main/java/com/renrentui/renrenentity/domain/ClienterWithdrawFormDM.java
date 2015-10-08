package com.renrentui.renrenentity.domain;


import java.util.Date;

import com.renrentui.renrenentity.ClienterWithdrawForm;

public class ClienterWithdrawFormDM extends ClienterWithdrawForm  {
    private String clienterName;

    private String phoneNo;

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
    
}