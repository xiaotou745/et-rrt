package com.renrentui.renrenentity.resp;

import com.renrentui.renrenentity.ClienterBalance;
/**
* @Des 获取用户收入返回实体类 
* @Author WangXuDan
* @Date 2015年9月29日10:19:10
* @Return
*/
public class MyIncomeResp extends ClienterBalance {
	private Double checking;

	public Double getChecking() {
		return checking;
	}

	public void setChecking(Double checking) {
		this.checking = checking;
	}

}
