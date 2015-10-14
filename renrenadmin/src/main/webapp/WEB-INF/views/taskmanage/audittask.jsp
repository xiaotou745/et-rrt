<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrenentity.Business"%>
<%@page import="com.renrentui.renrenentity.Template"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
List<Template> templatelist = (List<Template>) request.getAttribute("templatelist");
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
								<input type="text" class="form-control" name="taskTitle"
									id="taskTitle" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">创建人:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="createName"
									id="createName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">发布人:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="pusher"
									id="pusher" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户名称:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("businessId", businessData, "companyName", "id", null,-1, "全部")%>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">创建时间:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> <input type="text"
										class="form-control" value="" name="createTimeBegin"
										id="createTimeBegin" />
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
										class="form-control" value="" name="createTimeEnd"
										id="createTimeEnd" />
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">起止日期:</label>
							<div class="col-sm-8">
								<div class="input-group date">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> <input type="text"
										class="form-control" value="" name="beginTime" id="beginTime" />
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
										class="form-control" value="" name="endTime" id="endTime" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">合同模板: </label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("templateId", templatelist, "templateName", "id", null,-1, "全部")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">支付方式: </label>
							<div class="col-sm-8">
								<select id="paymentMethod" name="paymentMethod"
									class="form-control m-b">
									<option value="1">线下支付</option>
									<!--<option value="2">线上支付</option> -->
								</select>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">任务状态:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("status", EnumHelper.GetEnumItems(TaskStatus.class), "desc", "value",null,"-1","全部")%>
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
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post("<%=basePath%>/taskmanage/audittaskdo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#btnSearch").click(function() {
		var startDate = $('#createTimeBegin').val();
	    var endDate = $('#createTimeEnd').val();
	    if (startDate != "" && endDate != "") {
	        var intStartDate = startDate.replace(/-/g, "");
	        var intEndDate = endDate.replace(/-/g, "");
	        if (intStartDate > intEndDate) {
	            alert('创建时间的开始日期不能大于结束日期');
	            $('#createTimeBegin').val("");
	            return;
	        }
	    }
		var startDate = $('#beginTime').val();
	    var endDate = $('#endTime').val();
	    if (startDate != "" && endDate != "") {
	        var intStartDate = startDate.replace(/-/g, "");
	        var intEndDate = endDate.replace(/-/g, "");
	        if (intStartDate > intEndDate) {
	            alert('起止日期的开始日期不能大于结束日期');
	            $('#beginTime').val("");
	            return;
	        }
	    }
		jss.search(1);
	});
	function setTaskStatus(taskID,status,oldStatus,endtime){
		var optype="";
		switch(status){
		case 1:optype="审核通过";
		var end=endtime.replace(/-/g, "");
		var myDate = new Date();
		var nowdate=myDate.getFullYear()+""+(myDate.getMonth()+1)+""+myDate.getDate();
		if(parseInt(end)<parseInt(nowdate)){
			alert("任务已经过期，不能审核");
			return;
		}
		break;
		case 2:optype="驳回";break;
		case 4:optype="终止";break;
		case 5:optype="取消";break;
		}
		if (!confirm("确定要"+optype+"该任务吗？")){
			return;
		}
		var paramaters={"reocrdId":taskID,"status":status,"oldStatus":oldStatus};
		var url = "<%=basePath%>/taskmanage/settaskstatus";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result > 0) {
					alert("操作成功");
				} else {
					alert("操作失败");
				}
				window.location.href = window.location.href;
			}
		});
	};
	function settlementtask(taskId){
		if (!confirm("确定要结算该任务吗？")){
			return;
		}
		var paramaters={"taskId":taskId};
		var url = "<%=basePath%>/taskmanage/settlementtask";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result > 0) {
					alert("操作成功");
				} else {
					alert("操作失败：数据异常，请重试");
				}
				window.location.href = window.location.href;
			}
		});
	}
</script>