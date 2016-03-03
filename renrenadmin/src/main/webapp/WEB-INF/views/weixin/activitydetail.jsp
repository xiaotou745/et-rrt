<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.Activity"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrenentity.domain.ClienterWxModel"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
List<ClienterWxModel> list = (List<ClienterWxModel>)request.getAttribute("listdata");
%>
<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">微信用户名</th>
						<th width="%5">微信号</th>
						<th width="%5">微信ID</th>
						<th width="%5">已绑定人人推账号</th>
						<th width="%25">关注时间（最近一次）</th> 
						<th width="%5">关注状态</th>
				</tr>
			</thead>
			<tbody>                           
		<% 
           for (int i = 0; i < list.size(); i++) {%>  
			 <tr> 
			     <td><%=list.get(i).getFromUserName() %></td>
			     <td><%=list.get(i).getWxId() %></td>
			     <td><%=list.get(i).getOpenId() %></td>
			     <td><%=list.get(i).getClienterPhoneNo() %></td>			     
			     <td><%=ParseHelper.ToDateString(list.get(i).getFollowTime()) %></td>
			     <td><%=list.get(i).getFollowStatusString() %></td>
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>