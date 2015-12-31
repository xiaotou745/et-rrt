
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.ClienterBalanceRecord"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.enums.CBalanceRecordType"%>
<%String basePath = PropertyUtils.getProperty("java.renrenadmin.url");%>
<%PagedResponse<ClienterBalanceRecord> data = (PagedResponse<ClienterBalanceRecord>) request.getAttribute("listData");%>
<% if(data.getResultList()==null||data.getResultList().size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">交易类型</th>
						<th width="%5">资料ID/提现单ID</th>
						<th width="%5">收支金额</th>
						<th width="%5">余额</th>
						<th width="%5">状态</th>
						<th width="%5">时间</th>
						<th width="%5">操作人</th>
						<th width="%5">备注</th>
				</tr>
			</thead>
			<tbody>                           
		<%List<ClienterBalanceRecord> list = data.getResultList();
           for (int i = 0; i < list.size(); i++) {%>  
			 <tr> 
			    <td><%=CBalanceRecordType.getEnum(list.get(i).getRecordType()).desc()%></td>
                <td><%=list.get(i).getOrderId() %></td>
                <td><%=ParseHelper.digitsNum(list.get(i).getAmount() ,2)%></td>
                <td><%=ParseHelper.digitsNum(list.get(i).getAfterAmount(),2)%></td>
                <td><%=list.get(i).getStatus()==1?"成功":"交易中"%></td>
                <td><%=ParseHelper.ToDateString(list.get(i).getOperateTime())%></td>
                <td><%=list.get(i).getOptName()%></td>
                <td><%=list.get(i).getRemark()%></td>
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script>

</script>

