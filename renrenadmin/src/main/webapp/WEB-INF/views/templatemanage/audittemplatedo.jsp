<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Double"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrenentity.domain.PageTemplateModel"%>
<%@page import="com.renrentui.renrencore.enums.TemplateStatus"%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th>编号</th>
			<th style="width: 200px;">模板名称</th>
			<th style="width: 150px;">创建人</th>
			<th style="width: 150px;">创建时间</th>
			<th style="width: 100px;">商家名称</th>
			<th style="width: 80px;">状态</th>
			<th style="width: 80px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<PageTemplateModel> responsePageList = (PagedResponse<PageTemplateModel>) request
					.getAttribute("listData");
			List<PageTemplateModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<PageTemplateModel>();
			}
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getTemplateName())%> </td>
			<td><%=ParseHelper.ShowString(data.get(i).getCreateName())%> </td>
			<td><%=ParseHelper.ToDateString(data.get(i).getCreateTime())%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getBusinessName())%></td>
			<td><%=TemplateStatus.getEnum(data.get(i).getStatus()).desc()%></td>
			<td>
			<% if(data.get(i).getStatus()==TemplateStatus.Valid.value()){%>
				<a href="javascript:setTemplateStatus('<%=data.get(i).getId()%>',0,1)">置为无效</a>
			<%} 
			if(data.get(i).getStatus()==TemplateStatus.InValid.value()){%>
			<a href="javascript:setTemplateStatus('<%=data.get(i).getId()%>',1,0)">置为有效</a>
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