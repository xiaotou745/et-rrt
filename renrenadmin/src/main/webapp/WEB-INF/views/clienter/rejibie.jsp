<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrenentity.Clienter"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
Clienter c =(Clienter)request.getAttribute("clienter");
int JIEBIE =(int)request.getAttribute("JIEBIE");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">

	<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">推荐人电话</label>
							<div class="col-sm-8">								
								<input readonly="readonly"  type="text" class="form-control" value="<%=c.getPhoneNo()%>"/>
								<input id= "CID" type="hidden" class="form-control" value="<%=c.getId()%>"/>
								<input id= "JIEBIE" type="hidden" class="form-control" value="<%=JIEBIE%>"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">推荐人名称</label>
							<div class="col-sm-8">								
								<input readonly="readonly"  type="text" class="form-control" value="<%=c.getClienterName()%>"/>
							</div>
						</div>
					</div>
					
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">被推荐人电话</label>
							<div class="col-sm-8">								
								<input id="PhoneNo" type="text" class="form-control"/>
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

<!-- 弹窗 -->
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
	var url='<%=basePath%>/clienter/rejibiedo';
	var par={"phoneNo":$('#PhoneNo').val(),
			"beginDate":$('#beginDate').val(),
			"endDate":$('#endDate').val()==''?'':$('#endDate').val()+' 23:59:59',
			"jiBie":$('#JIEBIE').val(),
			"myId":$('#CID').val()
			}
	$.post(url,par,function(d){
		$('#content').html(d);
	});
});
$('#btnSearch').click();
</script>