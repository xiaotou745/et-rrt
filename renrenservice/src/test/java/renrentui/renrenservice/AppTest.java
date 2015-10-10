package renrentui.renrenservice;

import com.renrentui.renrenservice.service.OrderWindowService;
import com.renrentui.renrenservice.service.TaskWindowService;

import junit.framework.TestCase;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
   //TaskWindowService windowService;
	
	public AppTest() {
		/*windowService = SpringBeanHelper
				.getCustomBeanByType(TaskWindowService.class);*/
	}
	public void test1(){

		new    TaskWindowService ().outTimeCanelTask();
	}
	
	public void test2(){

		new    OrderWindowService().outTimeCanelOrder();;
	}
}
