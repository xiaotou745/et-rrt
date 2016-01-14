
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.resp.ClienterResp"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrenentity.domain.ClienterRelationModel"%>
<%String basePath = PropertyUtils.getProperty("java.renrenadmin.url");%>
<%List<ClienterRelationModel>  data =(List<ClienterRelationModel>) request.getAttribute("listData");%>
<% if(data==null||data.size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">层级</th>
						<th width="%5">地推员数量</th>
						<th width="%5">累计完成任务</th>
						<th width="%5">累计完成佣金</th>
						<th width="%5">累计获得分佣</th>
						<th width="%5">操作</th>
				</tr>
			</thead>
			<tbody>                           
		<%
           for (int i = 0; i < data.size(); i++) {%>  
			 <tr> 
			  <td><%=data.get(i).getClienterLevel()%>级</td>
			  <td><%=data.get(i).getClienterCount()%></td>
			  <td><%=data.get(i).getCompleteCount()%></td>
			  <td><%=ParseHelper.digitsNum(data.get(i).getTotalAmount(),2)%></td>
			  <td><%=ParseHelper.digitsNum(data.get(i).getTotalSubAmount(),2)%></td>
			  <td><a target="_blank"  href="<%=basePath%>/clienter/rejibie?cid=<%=data.get(i).getMyId()%>&jibie=<%=data.get(i).getClienterLevel()%>">地推员列表</a></td>
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>

