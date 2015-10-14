<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Double"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrenentity.domain.RenRenTaskModel"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 80px;">编号</th>
			<th style="width: 200px;">任务标题</th>
			<th style="width: 150px;">创建人</th>
			<th style="width: 150px;">创建时间</th>
			<th style="width: 200px;">起止日期</th>
			<th style="width: 150px;">发布人</th>
			<th style="width: 100px;">商家名称</th>
			<th style="width: 100px;">模板名称</th>
			<th style="width: 80px;">任务总数</th>
		    <th style="width: 80px;">剩余数量</th>
			<th style="width: 80px;">单次佣金</th>
			<th style="width: 80px;">状态</th>
			<th style="width: 80px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<RenRenTaskModel> responsePageList = (PagedResponse<RenRenTaskModel>) request
					.getAttribute("listData");
			List<RenRenTaskModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<RenRenTaskModel>();
			}
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><a class="blue2" href="<%=basePath%>/taskmanage/detail?taskId=<%=data.get(i).getId()%>"><%=ParseHelper.ShowString(data.get(i).getTaskTitle())%></a></td>
			<td><%=ParseHelper.ShowString(data.get(i).getCreateName())%> </td>
			<td><%=ParseHelper.ToDateString(data.get(i).getCreateTime())%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getBeginTime(),"yyyy-MM-dd")+"/"+ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-MM-dd")%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getPusher())%></td>
			<td><%=data.get(i).getBusinessName()%></td>
			<td><%=data.get(i).getTemplateName()%></td>
			<td><%=data.get(i).getTaskTotalCount()%></td>			
			<td><%=data.get(i).getAvailableCount()%></td>
			<td><%=ParseHelper.digitsNum(data.get(i).getAmount(),2)%></td>
			<td><%=TaskStatus.getEnum(data.get(i).getStatus()).desc()%></td>
			<td>
			<% if(data.get(i).getStatus()==TaskStatus.WaitAudit.value()){%>
				<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',1,0,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">审核通过</a>
				<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',2,0,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">驳回</a>
			<%} 
			if(data.get(i).getStatus()==TaskStatus.Audited.value()){%>
			<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',4,1,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">终止</a>
			<%}
		    if(data.get(i).getStatus()==TaskStatus.WaitAudit.value()||data.get(i).getStatus()==TaskStatus.Reject.value()){%>
			<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',5,<%=data.get(i).getStatus() %>,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">取消</a>
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