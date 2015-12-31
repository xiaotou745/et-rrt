<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrencore.enums.CBalanceRecordType"%>
<%String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
String phoneNo = (String)request.getAttribute("phoneNo");
String name = (String)request.getAttribute("name");
Double blance = (Double)request.getAttribute("blance");
Double hadWithdraw = (Double)request.getAttribute("hadWithdraw");
Long id = (Long)request.getAttribute("id");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">

	<div class="row">
		<div class="col-lg-12">
		<input type="hidden" name="clienterStatus" id="clienterStatus" value="1"/> 
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">手机号:</label>
							<div class="col-sm-8">						
								<input value="<%=phoneNo%>" type="text"  readonly="readonly"class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">姓名:</label>
							<div class="col-sm-8">								
								<input value="<%=name%>" type="text"  readonly="readonly" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">当前余额:</label>
							<div class="col-sm-8">								
								<input  value="<%=hadWithdraw%>"   type="text"  readonly="readonly" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">累计提现:</label>
							<div class="col-sm-8">								
								<input value="<%=blance%>"  type="text" readonly="readonly" class="form-control"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">交易类型:</label>
							<div class="col-sm-8">
								 <%=HtmlHelper.getSelect("status", EnumHelper.GetEnumItems(CBalanceRecordType.class),"desc", "value", null, "0", "全部")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">资料ID/提现单ID:</label>
							<div class="col-sm-8">								
								<input id="orderId" type="text" name="orderId" id="orderId" class="form-control"/>
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
								<button type="button" class="btn btn-w-m btn-primary" id="export"
							style="margin-left: 3px;height:30px;">导出</button>			 
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
	var jss={
			search:function(currentPage){	
				 var paramaters = { 
						 "status":$('#status').val(),
						 "orderId": $('#orderId').val()==''?0:$('#orderId').val(),
						 "beginDate":$('#beginDate').val(),
						 "endDate": $('#endDate').val()==''?'':$('#endDate').val()+' 23:59:59',
						 "clienterId":<%=id%>,
						 "currentPage":currentPage,
						 };  

				 console.log(paramaters);
			        var url = "<%=basePath%>/clienter/recordlistdo";
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