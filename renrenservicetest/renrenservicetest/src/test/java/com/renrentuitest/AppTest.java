package com.renrentuitest;

import junit.framework.TestCase;
import renrentui.renrenservicetest.services.OneService;

public class AppTest extends TestCase {
	public AppTest() {
	}

	public void test1() {
		OneService service = new OneService();
		service.Write();
	}

}
