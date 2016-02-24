<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
String taskTitle = (String) request.getAttribute("taskTitle");
%>
<link rel="stylesheet"
	href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script
	src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"
				id="searchForm">
				<input type="hidden" name="currentPage" id="_hiddenCurrentPage"
					value="1" />
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">任务标题:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="taskTitle" placeholder="任务标题"
									id="taskTitle" value="<%=taskTitle%>"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">地推员名称:</label>
							<div class="col-sm-8">						
								<input id="clienterName" type="text" name="clienterName" placeholder="地推员姓名" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">手机号:</label>
							<div class="col-sm-8">								
								<input id="clienterPhoneNo" type="text" name="clienterPhoneNo" placeholder="手机号" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">领取城市:</label>
							<div class="col-sm-8">								
								<input id="cityName" type="text" name="cityName" placeholder="领取城市" class="form-control"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">领取时间:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> <input type="text"
										class="form-control" value="" name="receiveTimeBegin" id="receiveTimeBegin" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> <input type="text"
										class="form-control" value="" name="receiveTimeEnd" id="receiveTimeEnd" />
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px; height: 30px;">查询</button>

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
 $(function(){
	  $(' .input-group.date').datepicker({
          todayBtn: "linked",
          keyboardNavigation: false,
          forceParse: false,
          calendarWeeks: true,
          autoclose: true
      });
 });
	var jss = {
		search : function(currentPage) {
			if($("#taskTitle").val()==""){
				alert("任务标题不能为空");
				return;
			}
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/taskmanage/partnerlistdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		var receiveTimeBegin = $('#receiveTimeBegin').val();
	    var receiveTimeEnd = $('#receiveTimeEnd').val();
	    if (receiveTimeBegin != "" && receiveTimeEnd != "") {
	        var intStartDate = receiveTimeBegin.replace(/-/g, "");
	        var intEndDate = receiveTimeEnd.replace(/-/g, "");
	        if (intStartDate > intEndDate) {
	            alert('领取时间的开始日期不能大于结束日期');
	            $('#receiveTimeBegin').val("");
	            return;
	        }
	    }
		jss.search(1);
	});
</script>