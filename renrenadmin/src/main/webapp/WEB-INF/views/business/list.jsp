<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

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
							<label class="col-sm-4 control-label">公司名称:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" name="txtCompanyName"  id="txtCompanyName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">电话号码:</label>
							<div class="col-sm-8">								
								<input type="text" class="form-control" name="txtPhoneNo"  id="txtPhoneNo" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">登陆名称:</label>
							<div class="col-sm-8">
								
								<input type="text" class="form-control" name="txtLoginName"  id="txtLoginName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">所在城市</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="txtCityName"  id="txtCityName" />
							</div>
						</div>
					</div>
				</div>


			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>		
							<button type="button" class="btn btn-w-m btn-primary" 
							style="margin-left:3px;" data-toggle="modal" data-target="#myModal" onclick="AddShow()" id="btnAdd">添加商户</button>			 
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
<div tabindex="-1" class="modal inmodal" id="addBusiness"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加商户</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
			            <br>
			             <div class="control-group">
			                <label >公司名称：</label> 
			                    <input  name="txtCompanyNameA" id="txtCompanyNameA" type="text">			                    

			            </div>
			            <div class="control-group">
			                <label>电话号码：</label> 
			                <input  name="txtPhoneNoA" id="txtPhoneNoA" type="text">					               
			            </div>
			            <div class="control-group">
			                <label >登录名称：</label> 
			                    <input name="txtLoginNameA" id="txtLoginNameA" type="text">
			            </div> 
			            <div class="control-group">
			                <label >地&nbsp;&nbsp;址：</label> 
			                    <input  name="txtAddressA" id="txtAddressA" type="text">
			            </div> 
			            <div class="control-group">
			                <label >所属城市：</label> 
			                    <input  name="txtCityNameA" id="txtCityNameA" type="text">
			            </div> 
			             <div class="control-group">
			                <label >站&nbsp;&nbsp;点：</label> 
			                    <input  name="txtWebSiteA" id="txtWebSiteA" type="text">
			            </div> 
			        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id=btnModifyGroupBusiness onclick="AddBusiness()">保存</button>
				</div> 
			</small>
		</div>  
	</div>
</div>
<div tabindex="-1" class="modal inmodal" id="businessDeltaShow"
	role="dialog" aria-hidden="true" style="display: none;">		
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">商户冲值</h4>				
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						  <div class="control-group">
		                <label>公司名称：</label>
		                <input name="txtCompanyNameD" id="txtCompanyNameD" disabled="disabled" type="text">
		                <input name="txtBusinessIdD" id="txtBusinessIdD" type="hidden">
		            	</div>
			            <div class="control-group">
			                <label>商户电话：</label>
			                <input name="txtPhoneNoD" id="txtPhoneNoD" disabled="disabled" type="text">
			            </div>
			            <div class="control-group">
			                <label>余&nbsp;&nbsp;额：</label>
			                <input name="txtAmountD" id="txtAmountD" type="text" value="0">元
			            </div>
			            <div class="control-group">
			                <label>备&nbsp;&nbsp;注：</label>
			                <div class="controls">
			                    <textarea cols="45" rows="5" id="txtRemarkD	"></textarea>
			                </div>
			            </div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="txtbusinessDelta" onclick="AddBusinessDelta()">确认</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>

</div>


<script>

var jss={
		search:function(currentPage){	
			 var companyName = $("#txtCompanyName").val();				   
			 var phoneNo = $("#txtPhoneNo").val();
			 var loginName = $("#txtLoginName").val();
			 var cityName = $("#txtCityName").val();
			 var paramaters = { 
					 "currentPage":currentPage,					 
					 "companyName":companyName,
					 "phoneNo":phoneNo,
					 "loginName":loginName,
					 "cityName":cityName,
					 m:Math.round()
					 };
		        var url = "<%=basePath%>/business/listdo";
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


function AddShow(){
    $('#addBusiness').modal('show');
}
//保存商户
function AddBusiness(){
	var companyName= $('#txtCompanyNameA').val().trim();
	var phoneNo= $('#txtPhoneNoA').val().trim();	
    var loginName = $('#txtLoginNameA').val().trim();
    var address= $('#txtAddressA').val().trim();
    var cityName= $('#txtCityNameA').val().trim();
    var webSite= $('#txtWebSiteA').val().trim();    
    var reg=/[\u4e00-\u9fa5]+/;   
    
    if(companyName.trim().length <=4 || companyName.trim().length>30){
    	alert("公司名称必须在5-30个字符");
    	return;
    }
    if (reg.test(loginName)){
    	alert("登录账号不能为中文字符");
    	return;
    }
    if(loginName.trim().length <6 || loginName.trim().length>20){
    	alert("登录账号除中文外6-20位字符");
    	return;
    }   
    
    var paramaters = {
            "companyName": companyName,
            "phoneNo": phoneNo,
            "loginName": loginName,
            "address": address,
            "cityName": cityName,
            "webSite": webSite                 
        };
    var url = "<%=basePath%>/business/addbusiness";
    var la= layer.confirm('是否确认创建商户？', {
 	    btn: ['确认','取消'], //按钮
 	    shade: false //显示遮罩
 	},function(){
 		layer.close(la);
 		$.ajax({
 	           type: 'POST',
 	           url: url,
 	           data: paramaters,
 	           success: function (result) {
 	        	   window.location.href = "<%=basePath%>/business/list";
 	        	   //alert(result.message);
 	             <%--   if (result.responseCode > 0) {
 	                   window.location.href = "<%=basePath%>/business/list";
 	               } --%>
 	        	  
 	           }
 	       });
 	});   	    
}

//保存商户
function AddBusinessDelta(){
	
	var businessId= $('#txtBusinessIdD').val();	
	var balance= $('#txtAmountD').val().trim();	
   
    var paramaters = {
            businessId: businessId,
            balance: balance                   
        };
    
        var url = "<%=basePath%>/business/addbusinessdelta";  	
		$.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {
	        	   window.location.href = "<%=basePath%>/business/list";
	        	   //alert(result.message);
	             <%--   if (result.responseCode > 0) {
	                   window.location.href = "<%=basePath%>/business/list";
	               } --%>
	        	  
	           }
	       });
   	    
}

</script>
