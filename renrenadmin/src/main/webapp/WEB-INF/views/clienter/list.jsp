<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrencore.enums.ClienterStatus"%>
<%String basePath =PropertyUtils.getProperty("java.renrenadmin.url");%>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">

	<div class="row">
		<div class="col-lg-12">
		<input type="hidden" name="clienterStatus" id="clienterStatus" value="1"/> 
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">地推员名称:</label>
							<div class="col-sm-8">						
								<input id="txtClienterName" type="text" name="txtClienterName" placeholder="地推员姓名" class="form-control"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">手机号:</label>
							<div class="col-sm-8">								
								<input id="txtPhoneNo" type="text" name="txtPhoneNo" placeholder="手机号" class="form-control"/>
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
	var jss={
			search:function(currentPage){	
                 var clienterName = $("#txtClienterName").val();
                 var status = $("#clienterStatus").val();          
                 var phoneNo=$("#txtPhoneNo").val();  
				 var paramaters = { 
						 "currentPage":currentPage,
						 "clienterName": clienterName,
						 "status": status,
						 "phoneNo": phoneNo,	
						 };        
			        var url = "<%=basePath%>/clienter/listdo";
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