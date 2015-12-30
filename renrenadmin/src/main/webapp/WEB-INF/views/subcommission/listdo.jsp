<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.Strategy"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrencore.enums.StrategyStatus"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
	List<Strategy> list=(List<Strategy>) request.getAttribute("list");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th style="width: 80px;">编号</th>
			<th >策略名称</th>
			<th >分佣层级</th>
			<th >累计分红</th>
			<th >创建时间</th>
			<th >操作人</th>
			<th >状态</th>
			<th >操作</th>
		</tr>
	</thead>
	<tbody>
		<%
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
				%>
				<tr>
				<td><%=list.get(i).getId()%></td>
				<td><a href="<%=basePath+"/subcommission/detail?id="+list.get(i).getId()%>" target="_blank"><%=list.get(i).getStrategyName()%></a></td>
				<td><%=list.get(i).getLevalCount()%></td>
				<td><%=list.get(i).getPercentage()%></td>
				<td><%=ParseHelper.ToDateString(list.get(i).getCreateDate())%></td>
				<td><%=list.get(i).getOptName()%></td>
				<td><%=StrategyStatus.getEnum(list.get(i).getStatus()).desc()%></td>
				<td><%
				if(list.get(i).getStatus()==StrategyStatus.Enable.value())
				{
					//当前启用   ,,操作禁用 
					%>
					<a href="javascript:void(0)" onclick="updateStatus(<%=list.get(i).getId()%>,1)">禁用</a>
					<%
				}
				else if(list.get(i).getStatus()==StrategyStatus.UnEnable.value())
				{
					//当前禁用 操作启用
					%>
					<a href="javascript:void(0)" onclick="updateStatus(<%=list.get(i).getId()%>,2)">启用</a>
					<%
				}
				%>
				<a href="javascript:void(0)" onclick="updateStatus(<%=list.get(i).getId()%>,3)">删除</a>
				</td>
				</tr>
				<%
			}
		}
		%>
	</tbody>
</table>