<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Double"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrenentity.domain.TaskPartnerItem"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%@page import="com.renrentui.renrencore.enums.TaskType"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th >编号</th>
			<th >参与人手机号</th>
			<th >参与人姓名</th>
			<th >任务名称</th>
			<th >任务类型</th>
			<th >任务状态</th>
			<th >领取城市</th>
			<th >领取时间</th>
			<th >总完成次数</th>
			<th >待审核</th>
			<th >审核通过</th>
			<th >审核拒绝</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<TaskPartnerItem> responsePageList = (PagedResponse<TaskPartnerItem>) request.getAttribute("listData");
			List<TaskPartnerItem> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<TaskPartnerItem>();
			}
			String baseOrderPath="";
			for (int i = 0; i < data.size(); i++) {
				baseOrderPath=basePath+"/ordermanage/auditorder?taskTitle="+
						java.net.URLEncoder.encode(data.get(i).getTaskTitle(), "utf-8")+
						"&clienterName="+java.net.URLEncoder.encode(data.get(i).getClienterName(), "utf-8")+
						"&clienterPhoneNo="+data.get(i).getPhoneNo()+
						"&auditStatus=";
		%>
		<tr>
			<td><%=data.get(i).getClienterId()%></td>
			<td><%=data.get(i).getPhoneNo()%></td>
			<td><%=data.get(i).getClienterName()%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getTaskTitle())%></td>
			<td><%=TaskType.getEnum(data.get(i).getTaskType()).desc()%> </td>
			<td><%=TaskStatus.getEnum(data.get(i).getTaskStatus()).desc()%></td>
			<td><%=data.get(i).getCityName()%> </td>
			<td><%=ParseHelper.ToDateString(data.get(i).getReceiveDate())%></td>
			<td>
			<%
				if(data.get(i).getCompleteNum()>0&&data.get(i).getTaskType()==TaskType.ContractTask.value()){%>
					<a href="<%=baseOrderPath+"0"%>" target="_blank"><%=data.get(i).getCompleteNum()%></a>
				<%}else{%>
					<%=data.get(i).getCompleteNum()%>
				<%}
			%>
			
			 </td>
			<td>
				<%if(data.get(i).getWaitAuditNum()>0&&data.get(i).getTaskType()==TaskType.ContractTask.value()){%>
					<a href="<%=baseOrderPath+"1"%>" target="_blank"><%=data.get(i).getWaitAuditNum()%></a>
				<%}else{%>
					<%=data.get(i).getWaitAuditNum()%>
				<%}%>
			
			</td>
			<td>
				<%if(data.get(i).getAuditPassNum()>0&&data.get(i).getTaskType()==TaskType.ContractTask.value()){%>
					<a href="<%=baseOrderPath+"2"%>" target="_blank"><%=data.get(i).getAuditPassNum()%></a>
				<%}else{%>
					<%=data.get(i).getAuditPassNum()%>
				<%}%>
			</td>
			<td>
				<%if(data.get(i).getAuditRefuseNum()>0&&data.get(i).getTaskType()==TaskType.ContractTask.value()){%>
					<a href="<%=baseOrderPath+"3"%>" target="_blank"><%=data.get(i).getAuditRefuseNum()%></a>
				<%}else{%>
					<%=data.get(i).getAuditRefuseNum()%>
				<%}%>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>