<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrencore.enums.CBalanceRecordType"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">

	<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">推荐人手机号</label>
							<div class="col-sm-8">								
								<input  type="text" name="PhoneNo" id="PhoneNo" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
								<label class="col-sm-4 control-label">开始日期:</label>
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
					</div>
			</div>

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
	  //初始化时间控件
	  $(' .input-group.date').datepicker({
	        todayBtn: "linked",
	        keyboardNavigation: false,
	        forceParse: false,
	        calendarWeeks: true,
	        autoclose: true
	    });
	
});
	$('#btnSearch').click(function(){
		if($('#PhoneNo').val()=='')
		{
			alert('请输入推荐人手机号!');
			return;
		}
		var url='<%=basePath%>/clienter/rcelationdo';
		var par={"phoneNo":$('#PhoneNo').val(),
				"beginDate":$('#beginDate').val(),
				"endDate":$('#endDate').val()==''?'':$('#endDate').val()+' 23:59:59',
				}
		$.post(url,par,function(d){
			$('#content').html(d);
		});
	});
</script>