
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.domain.OrderChildInfoModel"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

		<%
			OrderChildInfoModel view = (OrderChildInfoModel)request.getAttribute("listData");
			if(view==null||view.getList()==null)
			{
				%>
			<h1>找不到当前订单的合同信息</h1>
				<%}
			else
			{
				%>
				<h3>任务名称:<%=view.getTaskTitle() %></h3>
				<h3>人员名称:<%=view.getClienterName() %></h3>
				<h3>公司名称:<%=view.getCompanyName() %></h3>
				<input type="hidden" id="filename"/>
				<input type="hidden" id="contentid"/>
				<table class="table table-striped table-bordered table-hover dataTables-example">
				<thead>
					<tr class="tdbg">
						<th width="%5">条目</th>
						<th >内容</th>						
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<view.getList().size();i++)
					{
						%>
						<tr>
							<td><%=view.getList().get(i).getTitle()%></td>
							<%if(view.getList().get(i).getControlType().equals("FileUpload"))
							{%>
							<td><img alt="" src="<%=PropertyUtils.getProperty("ImgShowUrl")+view.getList().get(i).getControlValue()%>"></td>
							<%}
							else{%>
							<td><%=view.getList().get(i).getControlValue()%></td>
							<%}%>
							
						</tr>
						<% 
					}
				%>
				</tbody>
				</table>
<%}%>


<script type="text/javascript">

</script>
	