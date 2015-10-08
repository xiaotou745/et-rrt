package com.renren.api;

import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrencore.util.SpringBeanHelper;
import com.renrentui.renrenentity.req.SignInReq;

import junit.framework.TestCase;

public class APPTest extends TestCase{
	IClienterService clienterService;
	
	public APPTest() {
		clienterService = SpringBeanHelper
				.getCustomBeanByType(IClienterService.class);
	}
	public void test1(){
		System.out.println("aa");
	}
	public void testSignin() {
		SignInReq req=new SignInReq();
		req.setPassWord("13241969288");
		req.setPhoneNo("22222");
		clienterService.queryClienter(req);
		
	}

}
