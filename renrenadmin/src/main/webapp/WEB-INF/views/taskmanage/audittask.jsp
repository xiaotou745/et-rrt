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
<%@page import="com.renrentui.renrencore.enums.TaskType"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
List<Template> templatelist = (List<Template>) request.getAttribute("templatelist");
%>
<script src="<%=basePath%>/js/util.js"></script>
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
							<label class="col-sm-4 control-label">商家名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="pusher"
									id="pusher" />
							</div>
						</div>
					</div>
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
				</div>
				<div class="row">
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
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">任务状态:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("status", EnumHelper.GetEnumItems(TaskStatus.class), "desc", "value",null,"-1","全部")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">任务类型:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("taskType", EnumHelper.GetEnumItems(TaskType.class), "desc", "value",null,"-1","全部")%>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">领取城市:</label>
							<div class="col-sm-8">
								<input id="cityName" type="text" name="cityName"
									placeholder="领取城市" class="form-control" />
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
<div tabindex="-1" class="modal inmodal" id="regiontipdiv" role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h5 class="modal-title" id="regiontipdivtitle"></h5>				
			</div>
			<small class="font-bold">
				<div class="modal-body">
				投放城市:
					 <div class="ibox-content" id="regiontipdivcontent">
                     </div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>	
</div>
<script>
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
	function showregiondetail(tasktitle,hideid){
		$("#regiontipdivtitle").html(base64decode(tasktitle));
		$("#regiontipdivcontent").html(base64decode($("#"+hideid).val()));
		$('#regiontipdiv').modal('show');
	}
	function setTaskStatus(taskID,status,oldStatus,endtime){
		var optype="";
		switch(status){
		case 1:optype="审核通过";
		//var end=endtime.replace(/-/g, "");
		var pageEnd=new Date(endtime);
		var a=pageEnd.getFullYear()+"";
		var b=(pageEnd.getMonth()+1)<10?("0"+(pageEnd.getMonth()+1)):(pageEnd.getMonth()+1)+"";
		var c=pageEnd.getDate()<10?("0"+pageEnd.getDate()):pageEnd.getDate()+"";
		var intEndDate=a+""+b+""+c;
		var myDate = new Date();
		var nowdate=myDate.getFullYear()+""+((myDate.getMonth()+1)<10?("0"+(myDate.getMonth()+1)):(myDate.getMonth()+1))+""+(myDate.getDate()<10?("0"+myDate.getDate()):myDate.getDate());
		if(parseInt(intEndDate)<parseInt(nowdate)){
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
	
	function Export(taskid)
	{
		var url = "<%=basePath%>/taskmanage/taskexport?taskId="+taskid;
		window.open(url);
	    return true;
	}
</script>