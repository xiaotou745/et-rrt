<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">公司名称:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" name="txtCompanyName"  id="txtCompanyName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">电话号码:</label>
							<div class="col-sm-8">								
								<input type="text" class="form-control" name="txtPhoneNo"  id="txtPhoneNo" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">登陆名称:</label>
							<div class="col-sm-8">
								
								<input type="text" class="form-control" name="txtLoginName"  id="txtLoginName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">所在城市</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="txtCityName"  id="txtCityName" />
							</div>
						</div>
					</div>
				</div>


			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>		
							<button type="button" class="btn btn-w-m btn-primary" 
							style="margin-left:3px;" data-toggle="modal" data-target="#myModal" onclick="AddShow()" id="btnAdd">添加商户</button>			 
					</div>
			</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div> 