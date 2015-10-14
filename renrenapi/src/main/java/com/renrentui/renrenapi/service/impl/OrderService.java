package com.renrentui.renrenapi.service.impl;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IOrderDao;
import com.renrentui.renrenapi.dao.inter.IOrderLogDao;
import com.renrentui.renrenapi.dao.inter.IRenRenTaskDao;
import com.renrentui.renrenapi.service.inter.IOrderService;
import com.renrentui.renrencore.enums.CancelTaskCode;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.OrderLog;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.CheckCancelOrder;
import com.renrentui.renrenentity.domain.OrderAudit;
import com.renrentui.renrenentity.domain.OrderChildInfoModel;
import com.renrentui.renrenentity.req.CancelOrderReq;
import com.renrentui.renrenentity.req.CancelTaskReq;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.OrderAuditReq;
import com.renrentui.renrenentity.req.OrderChildReq;
import com.renrentui.renrenentity.req.PagedAuditorderReq;
@Service
public class OrderService implements IOrderService{
	@Autowired
	private IOrderDao orderDao;	
	@Autowired
	private IOrderLogDao orderLogDao;
	@Autowired
	private IClienterBalanceDao clienterBalanceDao;
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;
	@Autowired
	private IRenRenTaskDao renRenTaskDao;	
	/**
	 * 获取订单审核列表
	 * 茹化肖
	 * 2015年10月9日15:01:25
	 */
	@Override
	public PagedResponse<OrderAudit> getOrderAuditList(PagedAuditorderReq req) {
		return orderDao.getOrderAuditList(req);
	}
	/**
	 * 订单审核
	 * 茹化肖
	 * 2015年10月10日11:09:34
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int orderAudit(OrderAuditReq req) {
		//更改审核状态
		int res= orderDao.orderAudit(req);
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderNo("");
		orderLog.setOrderId(req.getOrderId());
		orderLog.setOptType(Short.valueOf("6"));
		orderLog.setOptName(req.getAuditName());
		String markString=req.getAuditName()+"审核订单,ID:"+req.getOrderId()+",审核结果:"+(req.getAuditStatus()==2?"审核通过":"审核拒绝")+",数据库处理结果:"+(res>0?"成功":"失败");
		orderLog.setRemark(markString);
		int orderlogres=orderLogDao.addOrderLog(orderLog);//记录订单操作日志
		if(req.getAuditStatus()==2)//审核结果为审核通过
		{
			//审核通过 将合同的单价添加到骑士余额和可提现余额
			ClienterBalanceReq cBReq=new ClienterBalanceReq();
			cBReq.setUserId(req.getUserId());
			cBReq.setAmount(req.getAmount());
			int cbId= clienterBalanceDao.updateMoneyByKey(cBReq);
			
		    ClienterBalanceRecord clienterBalanceRecordModel=new ClienterBalanceRecord();
			clienterBalanceRecordModel.setClienterId(req.getUserId());
			clienterBalanceRecordModel.setAmount(req.getAmount());		
			clienterBalanceRecordModel.setRecordType((short)1);		
			clienterBalanceRecordModel.setOptName(req.getAuditName());
			clienterBalanceRecordModel.setOrderId(req.getOrderId());
			clienterBalanceRecordModel.setRelationNo(req.getOrderNo());
			clienterBalanceRecordModel.setRemark("合同审核通过增加佣金");
			clienterBalanceRecordModel.setStatus((short)1);
			int cbrId=clienterBalanceRecordDao.insert(clienterBalanceRecordModel);	
			if(res>0&&orderlogres>0&&cbId>0&&cbrId>0)
			{
				//执行成功
				return 1;
			}
			else {
				throw new RuntimeErrorException(new Error("审核失败"));
			}
			
		}
		if(res>0&&orderlogres>0)
		{	//审核拒绝
			return 1;
		}else {
			throw new RuntimeErrorException(new Error("审核失败"));
		}
	}

	/**
	 * 超时取消订单服务
	 * 
	 * @author CaoHeYang
	 * @date 20151009
	 */
	@Override
	public void outTimeCanelOrder() {
       orderDao.outTimeCanelOrder();
	}
	/**
	 * 获取合同信息
	 * 茹化肖
	 * 2015年10月12日16:07:28
	 * 
	 */
	@Override
	public OrderChildInfoModel getOrderChildInfo(OrderChildReq req) {
		OrderChildInfoModel model=orderDao.getOrderInfo(req);
		if(model!=null)
		{
			model.setList(orderDao.getOrderChildList(req));
		}
		return model;
	}
	/**
	 * 下载合同信息
	 * 茹化肖
	 */
	@Override
	public String downLoadOrderInfo(OrderChildReq req) {
		OrderChildInfoModel model=orderDao.getOrderInfo(req);
		if(model!=null)
		{
			model.setList(orderDao.getOrderChildList(req));
		}
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append(" ");
		sBuilder.append(" <html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sBuilder.append(" <head>");
		sBuilder.append(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		sBuilder.append(" <title>"+req.getOrderId()+"合同信息</title>");
		sBuilder.append(" </head><body>");
		sBuilder.append(" <html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sBuilder.append(" <style type=\"text/css\">");
		sBuilder.append(" .border-table { ");
		sBuilder.append(" border-collapse: collapse;  ");
		sBuilder.append(" border: none;} ");
		sBuilder.append(" .border-table td {");
		sBuilder.append(" border: solid #000 1px;}");
		sBuilder.append(" </style>");
		//拼接内容
		if(model==null||model.getList()==null)
		{
			sBuilder.append("<h3>该订单没有合同信息</h3>");
		}
		else{
			sBuilder.append("<div style=\"text-align:center;\">");			
			sBuilder.append("<h3>任务名称:"+model.getTaskTitle()+"</h3>");
			sBuilder.append("<h3>地推员名称:"+model.getClienterName()+"</h3>");
			sBuilder.append("<h3>公司名称:"+model.getCompanyName()+"</h3>");
			sBuilder.append("</div>");
			sBuilder.append("<div style=\"text-align:center;\">");	
			sBuilder.append("<table align=\"center\" class=\"border-table\"><thead><tr><th>条目</th><th >内容</th></tr></thead><tbody>");
			for(int i=0;i<model.getList().size();i++)
			{
				sBuilder.append("<tr>"); 
				sBuilder.append("<td>"+model.getList().get(i).getTitle()+"</td>");
				if(model.getList().get(i).getControlType().equals("FileUpload"))
				{
					sBuilder.append("<td><img src=\""+PropertyUtils.getProperty("ImgShowUrl")+model.getList().get(i).getControlValue()+"\"/></td>");
					
				}
				else{
					sBuilder.append("<td>"+model.getList().get(i).getControlValue()+"</td>");
				}	
				sBuilder.append("</tr>");
			}
			sBuilder.append("</tbody></table>");
			sBuilder.append("</div>");
		}
		//拼接内容结束
		sBuilder.append("</body></html>");
		return sBuilder.toString();
	}
	/**
	 * 手动取消订单
	 * 茹化肖
	 * 2015年10月14日13:51:23
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int cancelOrder(CancelTaskReq req) {
		//验证订单相关信息
		CheckCancelOrder check=orderDao.checkCancelOrder(req);
		if(check==null)//订单不存在
			return CancelTaskCode.OrderNull.value();
		int res=orderDao.cancelOrder(req);//取消订单
		String optName=req.getRemark();
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderNo(check.getOrderNo());
		orderLog.setOrderId(req.getOrderId());
		orderLog.setOptType(Short.valueOf("1"));
		orderLog.setOptName(optName);
		orderLog.setRemark(optName+"取消订单:"+check.getOrderNo());
		int orderlogres=orderLogDao.addOrderLog(orderLog);//记录订单操作日志
		
		int addres=renRenTaskDao.addTaskAvailableCount(check.getTaskId());//增加任务的剩余量
		if(res>0&&orderlogres>0&&addres>0){
			return CancelTaskCode.Success.value();
		}
		else {
			Error error=new Error("取消任务错误");
			throw new RuntimeErrorException(error);
		}
	}
	@Override
	public Double getOrderTotalAmount(Long taskId) {
		return orderDao.getOrderTotalAmount(taskId);
	}
}
