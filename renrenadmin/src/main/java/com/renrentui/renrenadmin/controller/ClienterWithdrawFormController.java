package com.renrentui.renrenadmin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IAlipayBatchService;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchClienterWithdrawForm;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.req.AlipayBatchReq;
import com.renrentui.renrenentity.req.PagedAlipayBatchListReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;

@Controller
@RequestMapping("clienterwithdraw")
public class ClienterWithdrawFormController {
	@Autowired
	private IClienterWithdrawFormService clienterWithdrawFormService;		

	@Autowired
	private IAlipayBatchService alipayBatchService;

	/**
	 * 用户提现列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月30日 15:38:27
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "账务管理");
		model.addObject("currenttitle", "提现管理");	
		model.addObject("viewPath", "clienterwithdraw/list");
		return model;
	}	
	
	/**
	 * 用户提现列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月30日 15:38:58
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedClienterWithdrawFormReq req)  {			
		
		PagedResponse<ClienterWithdrawFormDM> resp = clienterWithdrawFormService.getList(req);
		ModelAndView model = new ModelAndView("clienterwithdraw/listdo");		
		model.addObject("listData", resp);
		return model;		
	}		

	/**
	 * 审核通过
	 * @author hulingbo	
	 * @Date 2015年9月30日 17:35:51
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("auditpass")
	@ResponseBody
	public int auditpass(HttpServletRequest request,ClienterWithdrawForm record) {
	
		/*ClienterWithdrawForm record=new ClienterWithdrawForm();
		record.setId((long)id);*/
		
		UserContext context=UserContext.getCurrentContext(request);
		record.setAuditName(context.getUserName());
		return clienterWithdrawFormService.AuditPass(record);			
	}	
	
	/**
	 * 审核拒绝
	 * @author hulingbo	
	 * @Date 2015年10月8日 14:40:33
	 * @param search 查询条件实体
	 * @return	
	 */	
	@RequestMapping("auditrefuse")
	@ResponseBody
	public int auditrefuse(HttpServletRequest request,int  withwardId,String auditFailedReason) {
	
		ClienterWithdrawForm record=new ClienterWithdrawForm();
		record.setId((long)withwardId);
		record.setAuditFailedReason(auditFailedReason);
		UserContext context=UserContext.getCurrentContext(request);
		record.setAuditName(context.getUserName());
		return clienterWithdrawFormService.AuditRefuse(record);	
	}	
	/*
	 * 支付宝批量付款
	 * wangchao
	 */
	@RequestMapping("alipaybatchtransfer")
	@ResponseBody
	public String alipaybatchtransfer(HttpServletRequest request,int type ,String data){
		AlipayBatchReq alipayBatchReq = new AlipayBatchReq();
		alipayBatchReq.setData(data);
		alipayBatchReq.setType(type);
		alipayBatchReq.setOptName(UserContext.getCurrentContext(request).getUserName());
		String html = clienterWithdrawFormService.AlipayBatchTransfer(alipayBatchReq); 
		return html;
	}
	
	/**
	 * 批量付款回调 
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping("alibatchnotifytransfercallback")
	public ModelAndView AliBatchNotifyTransferCallback(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView model = new ModelAndView("clienterwithdraw/batchalinotify");		
		model.addObject("data", clienterWithdrawFormService.AliBatchNotifyTransferCallback(request));
		return model;
		//String outString=clienterWithdrawFormService.AliBatchNotifyTransferCallback(request);
		//System.out.println(outString);
	}
	
	/*
	 * 支付批次查询页面
	 * wangchao
	 */
	@RequestMapping("alibatchlist")
	public ModelAndView alibatchlist(){		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "账务管理");
		model.addObject("currenttitle", "支付宝批次进度查询");	
		model.addObject("viewPath", "clienterwithdraw/alibatchlist");
		return model;
	}
	
	@RequestMapping("alibatchlistdo")
	public ModelAndView alipaybatchlistdo(PagedAlipayBatchListReq req) {
		ModelAndView model = new ModelAndView("clienterwithdraw/alibatchlistdo");
		PagedResponse<AlipayBatchModel> datas=alipayBatchService.getAlipayBatchPagedList(req);
		model.addObject("listData",datas);
		return model;
	}
	 /*
	  * 支付批次查询详情
	  * wangchao
	  */
	@RequestMapping("alipaybatchlistdetail")
	public ModelAndView alipaybatchlistdetail(Long id) {
		ModelAndView model = new ModelAndView("adminView");
		AlipayBatchModel alipayBatch=alipayBatchService.getAlipayBatchById(id);
	    List<AlipayBatchClienterWithdrawForm> withdrawForms=alipayBatchService.getClienterWithdrawFormByBatchNo(id);
		model.addObject("alipayBatch",alipayBatch);
		model.addObject("withdrawForms",withdrawForms);
		model.addObject("subtitle", "财务管理");
		model.addObject("currenttitle", "支付宝批次进度查询> 批次详情");
		model.addObject("viewPath","clienterwithdraw/alipaybatchlistdetail");
		return model;
	}	
}
