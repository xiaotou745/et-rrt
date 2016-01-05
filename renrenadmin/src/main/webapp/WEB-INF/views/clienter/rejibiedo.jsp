
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.ClienterBalanceRecord"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrenentity.domain.ClienterRelationLevelModel"%>
<%String basePath = PropertyUtils.getProperty("java.renrenadmin.url");%>
<%List<ClienterRelationLevelModel> data = (List<ClienterRelationLevelModel>) request.getAttribute("listData");%>
<% if(data==null||data.size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">编号</th>
						<th width="%5">手机号</th>
						<th width="%5">姓名</th>
						<th width="%5">累计完成任务</th>
						<th width="%5">累计获得佣金</th>
						<th width="%5">累计获得分佣</th>
				</tr>
			</thead>
			<tbody>                           
		<%for (int i = 0; i < data.size(); i++) {%>  
			 <tr> 
			    <td><%=data.get(i).getClienterId()%></td>
                <td><%=data.get(i).getPhoneNo()%></td>
                <td><%=data.get(i).getClienterName()%></td>
                <td><%=data.get(i).getCompleteCount()%>次</td>
                <td><%=ParseHelper.digitsNum(data.get(i).getTotalAmount() ,2)%>元</td>
                <td><%=ParseHelper.digitsNum(data.get(i).getTotalSubAmount(),2)%>元</td>
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>
<script>

</script>

