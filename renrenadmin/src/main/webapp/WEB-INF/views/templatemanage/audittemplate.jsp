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
<%@page import="com.renrentui.renrencore.enums.TemplateStatus"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
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
							<label class="col-sm-4 control-label">模板名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="templateName"
									id="templateName" />
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
							<label class="col-sm-4 control-label">创建日期:</label>
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
							<label class="col-sm-4 control-label">商户名称:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("businessId", businessData, "companyName", "id", null,-1, "全部")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">模板状态:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("status", EnumHelper.GetEnumItems(TemplateStatus.class), "desc", "value",null,"-1","全部")%>
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
			$.post("<%=basePath%>/templatemanage/audittemplatedo",data, function(d) {
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
	            alert('创建日期的开始日期不能大于结束日期');
	            $('#createTimeBegin').val("");
	            return;
	        }
	    }
		jss.search(1);
	});
	function setTemplateStatus(templateID,status){
		var optype="";
		switch(status){
		case 0:optype="无效";break;
		case 1:optype="有效";break;
		}
		if (!confirm("确定要将该模板置为"+optype+"吗？")){
			return;
		}
		var paramaters={"templateID":templateID,"status":status};
		var url = "<%=basePath%>/templatemanage/settemplatestatus";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result > 0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败");
				}
			}
		});
	}
</script>