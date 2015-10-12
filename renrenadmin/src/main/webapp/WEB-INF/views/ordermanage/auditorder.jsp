<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

<style type="text/css">
#map_contain {
    height: 90%;
    width: 100%;
    max-width: none;
}
label {
    max-width: none;
}

#control {
width: 100%;
}
</style>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户ID:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" name="businessId"  id="businessId" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">订单ID:</label>
							<div class="col-sm-8">								
								<input type="text" class="form-control" name="orderId"  id="orderId" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">公司名称:</label>
							<div class="col-sm-8">
								
								<input type="text" class="form-control" name="companyName"  id="companyName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">审核状态</label>
							<div class="col-sm-8">
								<select id="auditStatus">
								<option value=-1>全部</option>
								<option value=0>待审核</option>
								<option value=2>审核通过</option>
								<option value=3>审核拒绝</option>
								</select>
							</div>
						</div>
					</div>
				</div>


			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>			 
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
<script>

var jss={
		search:function(currentPage){	
			 var businessId = $("#businessId").val();				   
			 var orderId = $("#orderId").val();
			 var companyName = $("#companyName").val();
			 var auditStatus = $("#auditStatus").val();
			 var paramaters = { 
					 "currentPage":currentPage,					 
					 "businessId":businessId,
					 "orderId":orderId,
					 "companyName":companyName,
					 "auditStatus":auditStatus,
					 m:Math.round()
					 };
		        var url = "<%=basePath%>/ordermanage/auditorderdo";
		        $.ajax({
 		            type: 'POST',
 		            url: url,
 		            data: paramaters,
 		            success: function (result) {   	
 		            	$("#content").html(result);               
 		            }
 		        });  
		}
	}	
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});

</script>
