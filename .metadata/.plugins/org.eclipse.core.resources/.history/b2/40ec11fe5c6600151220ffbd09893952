<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				<input type="text" placeholder="请输公司名称"
					class="input-sm form-control" id="txtCompanyName"
					style="width: 250px; height: 34px;" value="" />
				<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
					style="margin-left: 3px;">查询</button>
			</div>
		</div>
	</div>
</div>

<div id="content"></div>


<script>
var jss={
		search:function(currentPage){
			$("#_hiddenCurrentPage").val(currentPage);
			 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/business/listdo",data,function(d){
				$("#content").html(d);
			});
		}
	}
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
</script>
