package com.renrentui.renrenwap;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renrentui.renrenapi.service.inter.IClienterWxService;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.SpringBeanHelper;

@Component
public class OneTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	// @Autowired
	// IClienterWxService clienterWxService;

	// public static void main(String[] args) {
	@Test
	public void testMain() {
		String redirectUrlDomain = PropertyUtils
				.getProperty("java.renrenwap.url");
		
		System.out.println("zz"+redirectUrlDomain);
		String cookieDomain=
				redirectUrlDomain.replace("http://", "").substring(0, redirectUrlDomain.replace("http://", "").indexOf("/"));
		
		System.out.println(cookieDomain);
//		IClienterWxService clienterWxService = SpringBeanHelper
//				.getCustomBeanByType(IClienterWxService.class);
//		// TODO Auto-generated method stub
//		// 关注
//		boolean b = clienterWxService.follow("openid", "fromUserName",
//				"createTime");
//		System.out.println(b);
//		assertEquals(b, true);
//		// 取消关注
//		boolean c = clienterWxService.unfollow("openid");
//		System.out.println(c);
//		assertEquals(c, true);
	}
}
