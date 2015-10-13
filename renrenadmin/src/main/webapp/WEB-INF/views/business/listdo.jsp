
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
			<th width="15%">商户名称</th>
			<th width="15%">电话号码</th>
			<th width="15%">登录名称</th>
			<th width="15%">地址</th>
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
			<a href="javascript:void(0)"  onclick="BusinessDelta('<%=data.get(i).getId() %>','<%=data.get(i).getCompanyName() %>', '<%=data.get(i).getPhoneNo() %>')" >充值 </a>
			<a href="javascript:void(0)"  onclick="BusinessModify('<%=data.get(i).getId() %>','<%=data.get(i).getCompanyName() %>', '<%=data.get(i).getPhoneNo() %>', '<%=data.get(i).getLoginName() %>', '<%=data.get(i).getAddress() %>', '<%=data.get(i).getCityName() %>', '<%=data.get(i).getWebSite() %>')" >修改 </a>
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
   //商户充值 
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
   //商户修改
   function BusinessModify(id, name, phone,loginName,address,cityName,webSite) {
	   $('#txtBusinessIdM').val(0);
	   $('#txtCompanyNameM').val('');	   
	   $('#txtPhoneNoM').val('');	   
	   $('#txtLoginNameM').val('');
	   $('#txtAddressM').val('');
	   $('#txtCityNameM').val('');
	   $('#txtWebSiteM').val('');	    

	   $('#txtBusinessIdM').val(id);
	   $('#txtCompanyNameM').val(name);	   
	   $('#txtPhoneNoM').val(phone);	   
	    $('#txtLoginNameM').val(loginName);
	   $('#txtAddressM').val(address);
	   $('#txtCityNameM').val(cityName);
	   $('#txtWebSiteM').val(webSite);	 	   

	   $('#modifyBusiness').modal('show');	   	   
   }
  </script>
	

