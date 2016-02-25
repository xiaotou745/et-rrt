package com.renrentui.renrenadmin.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IClienterBalanceRecordService;
import com.renrentui.renrenapi.service.inter.IClienterBalanceService;
import com.renrentui.renrenapi.service.inter.IOrderService;
import com.renrentui.renrenapi.service.inter.ITaskDatumService;
import com.renrentui.renrencore.util.ExcelUtils;
import com.renrentui.renrencore.util.HttpRequestUtil;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.OrderChildInfoModel;
import com.renrentui.renrenentity.domain.TemplateInfo;
import com.renrentui.renrenentity.req.CancelOrderReq;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.ClienterBlanceRecordReq;
import com.renrentui.renrenentity.req.OrderAuditReq;
import com.renrentui.renrenentity.req.OrderChildReq;
import com.renrentui.renrenentity.req.PagedAuditorderReq;
import com.renrentui.renrenentity.req.TaskDatumDetailReq;


@Controller
@RequestMapping("ordermanage")
public class OrderController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private ITaskDatumService taskDatumService;
	@Autowired IClienterBalanceRecordService clienterBalanceRecordService;
	/**
	 * 订单管理页面 
	 * @author 茹化肖
	 * @Date 2015年9月29日 11:17:28
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("auditorder")
	public ModelAndView list(String taskTitle,String clienterName,
			String clienterPhoneNo,Integer auditStatus){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "资料管理");
		model.addObject("currenttitle", "资料审核");	
		model.addObject("viewPath", "ordermanage/auditorder");
		model.addObject("taskTitle", taskTitle==null?"":taskTitle);
		model.addObject("clienterName", clienterName==null?"":clienterName);
		model.addObject("clienterPhoneNo", clienterPhoneNo==null?"":clienterPhoneNo);
		model.addObject("auditStatus", auditStatus==null?"0":auditStatus);
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
	
//	/**
//	 * 取消订单 
//	 * @author 茹化肖
//	 * @Date 2015年9月30日 15:35:12
//	 * @param search 查询条件实体
//	 * @return	
//	 */	
//	@RequestMapping("cancelorder")
//	@ResponseBody
//	public int cancelOrder(HttpServletRequest request,CancelTaskReq req) {
//		req.setRemark(UserContext.getCurrentContext(request).getUserName());
//		return orderService.cancelOrder(req);
//	}
	
	/**
	 * 获取合同信息 
	 * @author 茹化肖
	 * @Date 2015年9月29日 11:17:53
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("orderchildInfo")
	public ModelAndView getorderchiid(TaskDatumDetailReq req,String tag,String name)  {			
		TemplateInfo model=taskDatumService.getTaskDatumDetail(req);
		ModelAndView view = new ModelAndView("ordermanage/orderchildinfo");		
		view.addObject("listData", model);
		view.addObject("Tag", tag);
		view.addObject("Name", name);
		return view;		
	}
	
	/**
	 * 下载合同信息 
	 * @author 茹化肖
	 * @Date 2015年9月29日 11:17:53
	 * @param search 查询条件实体
	 * @return	
	 * @throws Exception 
	 */	
	@RequestMapping("orderdownload")
	@ResponseBody
	public void  orderdownload(TaskDatumDetailReq req,HttpServletResponse response,HttpServletRequest request,String name) throws Exception  {			
		 try {
			 	String url=PropertyUtils.getProperty("java.renrenadmin.url")+"/ordermanage/orderchildInfo";
			 	Map<String, String> map=new HashMap<String, String>();
			 	map.put("userId", req.getUserId()+"");
			 	map.put("taskId", req.getTaskId()+"");
			 	map.put("taskDatumId", req.getTaskDatumId()+"");
			 	map.put("tag","1");
			 	map.put("name",name);
			 	List<Cookie> Cookies=new ArrayList<Cookie>();
			 	Collections.addAll(Cookies, request.getCookies());
	            String contentString=HttpRequestUtil.httpRequestPost(url, map,Cookies).getHttpResponseText();
	            ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(contentString.getBytes("utf-8"));
	            String filename = "OrderInfo_"+req.getTaskDatumId()+".html";
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
	}
	
	/**
	 * 获取分佣TipHtml
	 * @param orderId
	 */
	@RequestMapping("getsubtip")
	@ResponseBody
	public String  getsubtip(Long orderId){			
		return  clienterBalanceRecordService.getSubmissionTip(orderId);
	}
	
	@RequestMapping("auditorderexport")
	public void auditorderexport(PagedAuditorderReq req,HttpServletRequest request,HttpServletResponse response) throws Exception{	
		req.setPageSize(25535);
		req.setCurrentPage(1);
		PagedResponse<OrderAudit> resp = orderService.getOrderAuditList(req);
		List<OrderAudit> records=resp.getResultList(); 
				String fileName = "人人地推-%s-数据";
				fileName = String.format(fileName, req.getBeginDate()+ "到" + ParseHelper.ToDateString(req.getEndDate(),"yyyy-MM-dd"));
				LinkedHashMap<String, String> columnTitiles = new LinkedHashMap<String, String>();
				columnTitiles.put("资料编号", "id");
				columnTitiles.put("地推员", "clienterInfo");
				columnTitiles.put("公司名称", "pusher");
				columnTitiles.put("任务名称", "taskTitle");
				columnTitiles.put("该地推员完成次数", "completeNum");
				columnTitiles.put("总佣金/次", "totalAmount");
				columnTitiles.put("地推员佣金/次", "amountStr");
				columnTitiles.put("上级累计分佣", "subCommisson");
				columnTitiles.put("盈亏", "profitAmount");
				columnTitiles.put("提交时间", "finishTime");
				columnTitiles.put("终审时间", "auditTime");
				columnTitiles.put("审核状态", "auditStatus");
				ExcelUtils.export2Excel(fileName, "资料审核列表", columnTitiles,records, request, response);
				return;
	}
}
