
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrenentity.Business"%>
<%@page import="java.util.List"%><%@page import="com.renrentui.renrencore.util.PropertyUtils"%>

<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead> 
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="10%">公司名称</th>
			<th width="10%">电话号码</th>
			<th width="10%">登陆名称</th>
			<th width="10%">地址</th>
			<th width="10%">所属城市</th>
			<th width="10%">站点</th>
			<th width="10%">操作</th>							
		</tr>
	</thead>

	<tbody>

		<%
			PagedResponse<Business> responsePageList = (PagedResponse<Business>)request.getAttribute("listData");
			List<Business> data=responsePageList.getResultList();
			for (int i = 0; i < data.size(); i++) {			
		%>
		<tr>
			<td><%=data.get(i).getId()%></td>
			<td><%=data.get(i).getCompanyName()%></td>
			<td><%=data.get(i).getPhoneNo()%></td>	
			<td><%=data.get(i).getLoginName()%></td>	
			<td><%=data.get(i).getAddress()%></td>	
			<td><%=data.get(i).getCityName()%></td>
			<td><%=data.get(i).getWebSite()%></td>
			<td>
			<a href="javascript:void(0)"  onclick="BusinessDelta('<%=data.get(i).getId() %>','<%=data.get(i).getCompanyName() %>', '<%=data.get(i).getPhoneNo() %>')" >冲值 </a>
			</td>				
			
						
		</tr>
		<%
			}
		%>

	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
<script type="text/javascript">
   //商户冲值 
   function BusinessDelta(id, name, phone) {
	   $('#txtBusinessIdD').val(0);
	   $('#txtCompanyNameD').val('');
	   $('#txtPhoneNoD').val('');
	   $('#txtRemarkD').val('');
	   $('#txtBusinessIdD').val(id);
	   $('#txtCompanyNameD').val(name);
	   $('#txtPhoneNoD').val(phone);
	   $('#businessDeltaShow').modal('show'); 
   }
  </script>
	

