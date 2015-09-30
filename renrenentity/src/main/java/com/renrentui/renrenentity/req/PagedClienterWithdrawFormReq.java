package com.renrentui.renrenentity.req;

import com.renrentui.renrenentity.common.PagedRequestBase;

/**
 * 
 * @Des 管理后台提现列表，查询参数
 * @author  胡灵波
 * @Date 2015年9月30日 15:09:04
 * @Return
 */
public class PagedClienterWithdrawFormReq extends PagedRequestBase {

	   	private String withdrawNo;
	   
	   	private String clienterName;

	    private String phoneNo;

	    
		public String getWithdrawNo() {
			return withdrawNo;
		}

		public void setWithdrawNo(String withdrawNo) {
			this.withdrawNo = withdrawNo;
		}

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
