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
	    private int withType;  
		private String startDate;
	    private String endDate;
	    private int status;
		public int getStatus() {
			return status;
		}
		public int getWithType() {
			return withType;
		}

		public void setWithType(int withType) {
			this.withType = withType;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public void setStatus(int status) {
			this.status = status;
		}

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
