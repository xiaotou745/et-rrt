
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.domain.TemplateInfo"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

		<%
		TemplateInfo view = (TemplateInfo)request.getAttribute("listData");
			if(view==null||view.getTemplateGroup().size()<=0)
			{
				%>
			<h1>找不到当前订单的资料信息</h1>
				<%}
			else
			{
				%>
				<!DOCTYPE html>
				<html>
				<head>
				<meta charset="utf-8"></meta>
				<%if("1".equals((String)request.getAttribute("Tag")))
					{
					%>
					<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
					<%
				}%>
				<head>
				<body>
				<div style="text-align:center;">
				<h3>任务名称:<%=view.getTask().getTaskTitle() %></h3>
				<h3>人员名称:<%=(String)request.getAttribute("Name")%></h3>
				<h3>公司名称:<%=view.getTask().getPusher()%></h3>
				</div>
				<div style="text-align:center;">
				<%for(int i=0;i<view.getTemplateGroup().size();i++)
				{
					if(view.getTemplateGroup().get(i).getGroupType()==1)
					{
						%>
						<table class="table table-striped table-bordered table-hover dataTables-example" style="text-align:center;">
						<thead>
						<caption><%=view.getTemplateGroup().get(i).getTitle()%></caption>
						<tr >
						<th width="50%">条目</th>
						<th >内容</th>						
						</tr>
						</thead>
						<tbody>
						<% 
						for(int j=0;j<view.getTemplateGroup().get(i).getControlList().size();j++)
						{
							%>
							<tr><td><%=view.getTemplateGroup().get(i).getControlList().get(j).getControlTitle()%></td><td><%=view.getTemplateGroup().get(i).getControlList().get(j).getControlValue()%></td></tr>
							<%
						}
						%>
						</tbody>
						</table>
						<%
						
					}
					else if (view.getTemplateGroup().get(i).getGroupType()==2)
					{
						%>
						<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
						<caption><%=view.getTemplateGroup().get(i).getTitle()%></caption>
						<tr >
						<th width="50%">条目</th>
						<th >内容</th>						
						</tr>
						</thead>
						<tbody>
						<% 
						for(int j=0;j<view.getTemplateGroup().get(i).getControlList().size();j++)
						{
							%>
							<tr><td><%=view.getTemplateGroup().get(i).getControlList().get(j).getControlTitle()%></td>
							<td>
							<a href="<%=ParseHelper.getBigPic(view.getTemplateGroup().get(i).getControlList().get(j).getControlValue()) %>" target="_blanck">
							<img alt="" src="<%=view.getTemplateGroup().get(i).getControlList().get(j).getControlValue().equals("")?"":view.getTemplateGroup().get(i).getControlList().get(j).getControlValue()%>">
							</a></td></tr>
							<%
						}
						%>
						</tbody>
						</table>
						<%
					}
					else if (view.getTemplateGroup().get(i).getGroupType()==3)
					{
						%>
						<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
						<caption><%=view.getTemplateGroup().get(i).getTitle()%></caption>
						<tr >
						<th >图片</th>						
						</tr>
						</thead>
						<tbody>
						<% 
						for(int j=0;j<view.getTemplateGroup().get(i).getControlList().size();j++)
						{
							%>
							<td>
							<a href="<%=ParseHelper.getBigPic(view.getTemplateGroup().get(i).getControlList().get(j).getControlValue()) %>" target="_blanck">
							<img alt="" src="<%=view.getTemplateGroup().get(i).getControlList().get(j).getControlValue().equals("")?"":view.getTemplateGroup().get(i).getControlList().get(j).getControlValue()%>">
							</a></td></tr>
							<%
						}
						%>
						</tbody>
						</table>
						
						<%
					}
				}%>
			
<%}%>
</div>
</body>
<script type="text/javascript">

</script>
	