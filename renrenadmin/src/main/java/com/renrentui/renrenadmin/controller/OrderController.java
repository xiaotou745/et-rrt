package com.renrentui.renrenadmin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IOrderService;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.OrderChildInfoModel;
import com.renrentui.renrenentity.req.OrderAuditReq;
import com.renrentui.renrenentity.req.OrderChildReq;
import com.renrentui.renrenentity.req.PagedAuditorderReq;


@Controller
@RequestMapping("ordermanage")
public class OrderController {
	@Autowired
	private IOrderService orderService;
	/**
	 * 订单管理页面 
	 * @author 茹化肖
	 * @Date 2015年9月29日 11:17:28
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("auditorder")
	public ModelAndView list(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "订单管理");
		model.addObject("currenttitle", "订单审核");	
		model.addObject("viewPath", "ordermanage/auditorder");
		return model;
	}	
	
	/**
	 * 订单管理页面异步列表 
	 * @author 茹化肖
	 * @Date 2015年9月29日 11:17:53
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("auditorderdo")
	public ModelAndView listdo(PagedAuditorderReq req)  {			
		
		PagedResponse<OrderAudit> resp = orderService.getOrderAuditList(req);
		ModelAndView model = new ModelAndView("ordermanage/auditorderdo");		
		model.addObject("listData", resp);
		return model;		
	}
	
	/**
	 * 订单审核 
	 * @author 茹化肖
	 * @Date 2015年9月30日 15:35:12
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("orderaudit")
	@ResponseBody
	public int orderAudit(HttpServletRequest request,OrderAuditReq req) {
		req.setAuditName(UserContext.getCurrentContext(request).getUserName());
		return orderService.orderAudit( req);
	}
	
	/**
	 * 获取合同信息 
	 * @author 茹化肖
	 * @Date 2015年9月29日 11:17:53
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("orderchildInfo")
	public ModelAndView getorderchiid(OrderChildReq req)  {			
		OrderChildInfoModel model=orderService.getOrderChildInfo(req);
		ModelAndView view = new ModelAndView("ordermanage/orderchildinfo");		
		view.addObject("listData", model);
		return view;		
	}
	
	/**
	 * 下载合同信息 
	 * @author 茹化肖
	 * @Date 2015年9月29日 11:17:53
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("orderdownload")
	public HttpServletResponse  orderdownload(OrderChildReq req,HttpServletResponse response)  {			
		 try {
	            String contentString=orderService.downLoadOrderInfo(req);
	            ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(contentString.getBytes("utf-8"));
	            String filename = "OrderInfo_"+req.getOrderId()+".html";
	            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
	           
	            
	            InputStream fis = tInputStringStream;
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8")));
	            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	            response.setContentType("application/octet-stream;charset=UTF-8");
	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return response;		
	}
}
