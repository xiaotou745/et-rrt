<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="margin-bottom: 5px;">
				<input type="text" placeholder="请输入账号名称"
					class="input-sm form-control" id="txtKeyword"
					style="width: 250px; height: 34px;" value="" />
				<button type="button" class="btn btn-w-m btn-primary" id="btnSearch"
					style="margin-left: 3px;">查询</button>
			</div>
		</div>
	</div>
</div>

<div id="content">
键：<input type="text" id="key" name="key"> 
查询类型：<input type="radio" value="1" name="sType" />
模糊查询 <input type="radio" value="2" name="sType" />
精准查询 <input type="submit" value="查询" />
</div>

