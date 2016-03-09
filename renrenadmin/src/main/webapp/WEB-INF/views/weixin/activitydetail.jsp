<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.Activity"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrenentity.Activity"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
Activity activity = (Activity)request.getAttribute("singleActivity");
%>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
<div class="row">
<div class="col-lg-12">
<div class="row">
<div class="col-lg-3">
		<div class="form-group">
			<label class="col-sm-4 control-label">活动：</label> 
				<label class="col-sm-4 control-label"><%=activity.getActivityName() %>【<%=activity.getStatusString() %>】</label> 
		</div>
	</div>
	<div class="col-lg-3">
		<div class="form-group">
			<label class="col-sm-4 control-label">已发放数量：</label> 
				<label class="col-sm-4 control-label"><%=activity.getHadRewardCount() %></label> 
		</div>
	</div>
	<div class="col-lg-3">
		<div class="form-group">
			<label class="col-sm-4 control-label">总金额：</label>
			<div class="col-sm-8">						
				<label class="col-sm-4 control-label"><%=activity.getHadRewardMoney() %>元</label>
			</div>
		</div>
	</div>
</div>
<div class="row">
<div class="col-lg-3">
		<div class="form-group">
			<label class="col-sm-4 control-label">开始时间：</label>
				<label class="col-sm-4 control-label"><%=ParseHelper.ToDateString(activity.getStartTime()) %></label>
		</div>
	</div>
	<div class="col-lg-3">
		<div class="form-group">
			<label class="col-sm-4 control-label">结束时间：</label> 
				<label class="col-sm-4 control-label"><%=ParseHelper.ToDateString(activity.getEndTime())  %></label> 
		</div>
	</div> 
</div>
</div>
</div>
	<div class="row">
		<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">微信用户名：</label>
							<div class="col-sm-8">						
								<input id="txtWxName" type="text" name="txtWxName" placeholder="微信用户名" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">微信号：</label>
							<div class="col-sm-8">								
								<input id="txtWxNo" type="text" name="txtWxNo" placeholder="微信号" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">当前关注状态：</label> 
							<div class="col-sm-8">
								<select id="selAttentionStatus" class="form-control m-b">
								<option value="-1">全部</option>
								<option value="1">已关注</option>
								<option value="0">已取消关注</option>
								</select> 
							</div>
						</div>
					</div> 
				</div>
			    <div class="row"> 
				    <div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">人人推账号：</label>
								<div class="col-sm-8">								
									<input id="txtClienterPhoneNo" type="text" name="txtClienterPhoneNo" placeholder="人人推账号" class="form-control"/>
								</div>
							</div>
					</div>
					 <div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">活动参与时间：</label>
							<div class="col-sm-8">
								<div class="input-group ">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> 
										<input type="text" class="form-control" value="" name="attentionstartDate" id="attentionstartDate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'attentionendDate\')||\'2030-10-01\'}'})"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
								<div class="input-group ">
									<span class="input-group-addon"><i
										class="fa fa-calendar"></i></span> 
										<input type="text" class="form-control" value="" name="attentionendDate" id="attentionendDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'attentionstartDate\')}',maxDate:'2030-10-01'})"/>
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
<!-- 						<div class="col-lg-3"> -->
<!-- 							<button type="button" class="btn btn-w-m btn-primary" id="exportdata" -->
<!-- 								style="margin-left: 3px;height:30px;">导出数据</button>			  -->
<!-- 						</div>  -->
			</div>

		</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div> 
</div>
<script>
	var jss={
			search:function(currentPage){	
                 var wxName = $("#txtWxName").val();
                 var wxId = $("#txtWxId").val();          
                 var wxNo=$("#txtWxNo").val();
                 var attentionStatus=$("#selAttentionStatus").val();
                 var attentionstartDate=$("#attentionstartDate").val();
                 var attentionendDate=$("#attentionendDate").val();
                 var txtclienterPhoneNo=$("#txtClienterPhoneNo").val(); 
				 var paramaters = { 
						 "wxName":wxName,
						 "wxId": wxId,
						 "wxNo": wxNo,
						 "followStatus": attentionStatus,
						 "beiginAttentionDate":attentionstartDate,
						 "endAttentionDate":attentionendDate,
						 "clienterPhoneNo":txtclienterPhoneNo,
						 "currentPage":currentPage
						 };        
			        var url = "<%=basePath%>/weixin/activitydetaillistdo";
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