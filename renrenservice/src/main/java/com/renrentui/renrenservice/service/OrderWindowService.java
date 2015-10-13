package com.renrentui.renrenservice.service;


import com.renrentui.renrenapi.service.inter.IOrderService;
import com.renrentui.renrencore.util.SpringBeanHelper;

/**
 * 订单相关服务
 * 
 * @author CaoHeYang
 * @date 20151009
 */
public class OrderWindowService {
	 IOrderService orderService = (IOrderService)SpringBeanHelper.getCustomBean("orderService");
	/**
	 * 超时取消订单服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	public void outTimeCanelOrder() {
		orderService.outTimeCanelOrder();
		System.out.println("================");
	}
}
