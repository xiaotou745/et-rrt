<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
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
							<label class="col-sm-4 control-label">地推员手机号:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" name="clienterPhone"  id="clienterPhone" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">地推员名称:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" name="clienterName"  id="clienterName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">资料编号:</label>
							<div class="col-sm-8">								
								<input type="text" class="form-control" name="orderNo"  id="orderNo" />
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
					
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">审核状态</label>
							<div class="col-sm-8">
								<select id="auditStatus" class="form-control m-b">
								<option value=-1>全部</option>
								<option value=1>待审核</option>
								<option value=2>审核通过</option>
								<option value=3>审核拒绝</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">任务名称:</label>
							<div class="col-sm-8">								
								<input type="text" class="form-control" name="taskName"  id="taskName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">提交开始日期:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span> 
										<input type="text" class="form-control"  name="beginDate" id="beginDate" value=""/>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">结束日期:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span> 
										<input type="text"	class="form-control" name="endDate" id="endDate" value=""/>
									</div>
								</div>
							</div>
						</div>
				</div>

			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>	
						<button type="button" class="btn btn-w-m btn-primary" id="exportorder"
							style="margin-left: 3px;height:30px;">导出数据</button>				 
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

<div tabindex="-1" class="modal inmodal" id="alertbox"
	role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">资料信息</h4>				
			</div>
			<small class="font-bold">
				<div class="modal-body" id="infobox">
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btndown">下载资料</button>
				</div>
			</small>
		</div>
	</div>
</div>
<div id="TipBox" style="position:absolute;display:none;border:1px solid silver;background:pink;">
dasda
</div>
<script>
$(function(){
	  //初始化时间控件
	  $(' .input-group.date').datepicker({
	        todayBtn: "linked",
	        keyboardNavigation: false,
	        forceParse: false,
	        calendarWeeks: true,
	        autoclose: true
	    });
	
});
var jss={
		search:function(currentPage){	
			 var clienterName = $("#clienterName").val();				   
			 var orderNo = $("#orderNo").val();
			 var companyName = $("#companyName").val();
			 var auditStatus = $("#auditStatus").val();
			 var clienterPhone=$('#clienterPhone').val();
			 var taskName=$('#taskName').val();
			 var beginDate=$('#beginDate').val();
			 var endDate=$('#endDate').val();
			 var paramaters = { 
					 "currentPage":currentPage,					 
					 "clienterName":clienterName,
					 "orderNo":orderNo,
					 "companyName":companyName,
					 "auditStatus":auditStatus,
					 "clienterPhone":clienterPhone,
					 "taskName":taskName,
					 "beginDate":beginDate,
					 "endDate":endDate==''?'':endDate+' 23:59:59',
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
//导出数据
$('#exportorder').click(function(){
	var clienterName = $("#clienterName").val();				   
	 var orderNo = $("#orderNo").val();
	 var companyName = $("#companyName").val();
	 var auditStatus = $("#auditStatus").val();
	 var clienterPhone=$('#clienterPhone').val();
	 var taskName=$('#taskName').val();
	 var beginDate=$('#beginDate').val();
	 var endDate=$('#endDate').val();
	 if(beginDate==''||endDate=='')
	{
		 alert('导出数据开始时间或结束时间不能为空!');
		 return;
	}
	  endDate=endDate==''?'':endDate+' 23:59:59';
	   var url = "<%=basePath%>/ordermanage/auditorderexport?currentPage=1&clienterName="
		+clienterName
		+"&orderNo"+orderNo
		+"&companyName="+companyName
		+"&auditStatus="+auditStatus
		+"&clienterPhone="+clienterPhone
		+"&taskName="+taskName
		+"&beginDate="+beginDate
		+"&endDate="+endDate;
	window.location.href = url;
    return true;  
});
</script>
