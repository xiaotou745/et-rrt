
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrenentity.domain.OrderAudit"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="%5">订单号</th>
			<th >地推员</th>
			<th >公司名称</th>
			<th >任务名称</th>
			<th >任务状态</th>
			<th >剩余订单次数</th>
			<th>该地推员<br/>完成次数</th>
			<th >佣金/次</th>
			<th >订单提交<br/>审核时间</th>
			<th >终审时间</th>
<!-- 			<th >分钟前</th> -->
			<th >订单状态</th>
			<th >审核状态</th>
			<th >操作</th>		
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<OrderAudit> responsePageList = (PagedResponse<OrderAudit>)request.getAttribute("listData");
			List<OrderAudit> data=responsePageList.getResultList();
			for (int i = 0; i < data.size(); i++) {			
		%>
		<tr>
			<td><%=data.get(i).getOrderId()%></td>
			<td><%=data.get(i).getOrderNo()%></td>
			<td><%=data.get(i).getClienterName()%></td>	
			<td><%=data.get(i).getCompanyName()%></td>
			<td><%=data.get(i).getTaskTitle()%></td>	
			<td><%=data.get(i).getTaskStatus()%></td>	
			<td><%=data.get(i).getAvailableCount()%></td>
			<td><%=data.get(i).getCompCount()%></td>	
			<td><%=data.get(i).getAmountStr()%></td>
			<td><%=data.get(i).getFinishTime()%></td>	
			<td><%=data.get(i).getAuditTime()%></td>	
<%-- 			<td><%=data.get(i).getTimeAfter()%></td> --%>
			<td><%=data.get(i).getOrderStatus()%></td>	
			<td><%=data.get(i).getAuditStatus()%></td>
			<td>
			<%if(data.get(i).getAuditStatusCode()==0&&data.get(i).getOrderStatusCode()==1) 
			{%>
			<a  href="javascript:void(0)"  onclick="Audit(<%=data.get(i).getOrderId()%>,2,<%=data.get(i).getClienterId()%>,<%=data.get(i).getAmount()%>,<%=data.get(i).getOrderNo()%>)">审核通过</a>
		    <a  href="javascript:void(0)"  onclick="Audit(<%=data.get(i).getOrderId()%>,3,<%=data.get(i).getClienterId()%>,<%=data.get(i).getAmount()%>,<%=data.get(i).getOrderNo()%>)">审核拒绝</a>
			<%} else if(data.get(i).getAuditStatusCode()==3&&data.get(i).getOrderStatusCode()==1) {%>
			<a href="javascript:void(0)"  onclick="Audit(<%=data.get(i).getOrderId()%>,2,<%=data.get(i).getClienterId()%>,<%=data.get(i).getAmount()%>,<%=data.get(i).getOrderNo()%>)">审核通过</a>

			<%}
			if(!data.get(i).getFinishTime().equals(""))
			{
			%>
			<a href="javascript:void(0)"  onclick="ShowInfo(<%=data.get(i).getOrderId()%>)">查看</a>
			<a href="javascript:void(0)"  onclick="saveFile(<%=data.get(i).getOrderId()%>)">下载</a>
			<%	
			}
			%>
			<%
			//已经完成 但是审核未通过, 都是可以取消订单的
			if((!data.get(i).getFinishTime().equals(""))
					&&(data.get(i).getAuditStatusCode()!=2)
					&&(data.get(i).getOrderStatusCode()!=2)
					&&(data.get(i).getOrderStatusCode()!=3))
			{
			%>
			<a href="javascript:void(0)"  onclick="CancelOrder(<%=data.get(i).getOrderId()%>,<%=data.get(i).getClienterId()%>)">取消订单</a>
			<%	
			}
			%>
			</td>						
		</tr>
		<%
			}
		%>

	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
<script type="text/javascript">
   //订单审核
   function Audit(orderId,auditStatus,userId,amount,orderNo){
	   if(!confirm("确定操作该审核结果?")){
		   return false;
		   }
	   var paramaters = { 				 
				 "auditStatus":auditStatus,
				 "orderId":orderId,
				 "userId":userId,
				 "amount":amount,
				 "orderNo":orderNo
				 };
		   var url = "<%=basePath%>/ordermanage/orderaudit";
		   $.ajax({
		        type: 'POST',
		        url: url,
		        data: paramaters,
		        success: function (result) {   	
		        	if(result=='1'||result==1){
		        		alert('操作成功!')
		        		jss.search(1);
		        		}
		        }
		    });
   }
   function ShowInfo(orderId){
	   var paramaters = {"orderId":orderId};
	   $('#btndown').unbind("click");
	   $('#btndown').click(function(){
		   saveFile(orderId);
	   });
		   var url = "<%=basePath%>/ordermanage/orderchildInfo";
		   $.ajax({
		        type: 'POST',
		        url: url,
		        data: paramaters,
		        success: function (result) {   	
		        	//alert(result);
		        	$('#infobox').html(result);
		        	$('#contentid').val(result);
		        	$('#filename').val('123.html');
		        	$('#alertbox').modal('show'); 
		        	
		        }
		    });
	   
   }
   //取消订单
   function CancelOrder(orderId,userId){
	   if(!confirm("确定取消该订单吗?")){
		   return false;
		   }
	   var paramaters = {"orderId":orderId,"userId":userId};
		   var url = "<%=basePath%>/ordermanage/cancelorder";
		   $.ajax({
		        type: 'POST',
		        url: url,
		        data: paramaters,
		        success: function (result) {   	
		        	if(result=='200'||result==200)
		        	{
		        		alert('取消成功!')
		        		jss.search(1);
		        	}
		        	else
		        		{
		        		alert('取消任务失败!');
		        		}
		        }
		    });
	   
   }
 //保存
   function saveFile(orderId){
	   var url = '<%=basePath%>/ordermanage/orderdownload?orderId='+orderId;
	   window.open(url);
   }
</script>
	