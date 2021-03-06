
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrenentity.domain.BusinessModel"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>

<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
String ImgShowUrl= PropertyUtils.getProperty("ImgShowUrl");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="15%">商户名称</th>
			<th width="10%">电话号码</th>
			<th width="15%">登录名称</th>
			<th width="15%">所属地址</th>
			<th width="10%">所属城市</th>
			<th width="10%">站点名称</th>
			<th width="10%">金额</th>
			<th width="10%">操作</th>							
		</tr>
	</thead>

	<tbody>

		<%
			PagedResponse<BusinessModel> responsePageList = (PagedResponse<BusinessModel>)request.getAttribute("listData");
			List<BusinessModel> data=responsePageList.getResultList();
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
			<td><%=ParseHelper.digitsNum(data.get(i).getBalance(),2)%></td>
			<td>
			<a href="javascript:void(0)"  onclick="BusinessDelta('<%=data.get(i).getId() %>','<%=data.get(i).getCompanyName() %>', '<%=data.get(i).getPhoneNo() %>')" >充值 </a>
			<a href="javascript:void(0)"  onclick="BusinessModify('<%=data.get(i).getId() %>','<%=data.get(i).getCompanyName() %>', '<%=data.get(i).getPhoneNo() %>', '<%=data.get(i).getLoginName() %>', '<%=data.get(i).getAddress() %>', '<%=data.get(i).getCityName() %>', '<%=data.get(i).getWebSite() %>', '<%=data.get(i).getLogo() %>')" >修改 </a>
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
   function BusinessModify(id, name, phone,loginName,address,cityName,webSite,logo) {
	   $('#txtBusinessIdM').val(0);
	   $('#txtCompanyNameM').val('');	   
	   $('#txtPhoneNoM').val('');	   
	   $('#txtLoginNameM').val('');
	   $('#txtAddressM').val('');
	   $('#txtCityNameM').val('');
	   $('#txtWebSiteM').val('');
	   $('#txtImgShowM').val('');
	   $("#imgShowM").attr("src","");  
	   
	   $('#txtBusinessIdM').val(id);
	   $('#txtCompanyNameM').val(name);	   
	   $('#txtPhoneNoM').val(phone);	   
	   $('#txtLoginNameM').val(loginName);
	   $('#txtAddressM').val(address);
	   $('#txtCityNameM').val(cityName);
	   $('#txtWebSiteM').val(webSite);	 	
	   $('#txtImgShowM').val(logo);	
	   
	   var logoUrl="<%=ImgShowUrl%>"+logo ;	
	   $("#imgShowM").attr("src",logoUrl); 

	   $('#modifyBusiness').modal('show');	   	   
   }
  </script>
	

