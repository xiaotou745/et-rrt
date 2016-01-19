package com.renrentui.renrenadmin.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.SpringBeanHelper;
import com.renrentui.renrenentity.domain.AlipayBatchCallBackModel;

import junit.framework.TestCase;

public class Apptest extends TestCase {
	@Autowired
	IClienterWithdrawFormService c=SpringBeanHelper.getCustomBeanByType(IClienterWithdrawFormService.class);
	public void testAdd() { 
		try {
			String a="{\"notifyTime\":\"2016-01-14 15:07:57\",\"notifyType\":\"batch_trans_notify\",\"notifyId\":\"a5d5c8673f49938ed1ee6a512011658kec\",\"signType\":\"MD5\",\"sign\":\"739f0e7b07ed490f76c28974f4906e1d\",\"batchNo\":\"2016011403133921622051\",\"payUserId\":\"2088911703660069\",\"payUserName\":\"易代送网络科技（北京）有限公司\",\"payAccountNo\":\"20889117036600690156\",\"successDetails\":null,\"failDetails\":\"149^13911245813^杨过^7.00^F^ACCOUN_NAME_NOT_MATCH^20160114538477556^20160114150757|\"}";
			AlipayBatchCallBackModel model=JsonUtil.str2obj(a, AlipayBatchCallBackModel.class);
			c.AliBatchNotifyTransferCallbackBusinessDeal(model);
		} catch (Exception e) {
			int a =0;
			e.printStackTrace();
		}
		
	} 
}
