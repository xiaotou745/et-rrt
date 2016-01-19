<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
 <%@page import="com.renrentui.renrencore.enums.UploadForm"%> 
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
String UploadUrl= PropertyUtils.getProperty("UploadUrl");

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
<script src="<%=basePath%>/js/ajaxfileupload.js"></script>

<link href="<%=basePath%>/js/jquery.uploadify-v2.1.0/uploadify.css" rel="stylesheet" />
<link href="<%=basePath%>/js/jquery.uploadify-v2.1.0/example/css/default.css" rel="stylesheet" />
<script src="<%=basePath%>/js/jquery.uploadify-v2.1.0/swfobject.js"></script> 
<script src="<%=basePath%>/js/jquery.uploadify-v2.1.0/jquery.uploadify.v2.1.0.min.js"></script> 
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm" enctype="multipart/form-data">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">商户名称:</label>
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
							<label class="col-sm-4 control-label">登录账号:</label>
							<div class="col-sm-8">
								
								<input type="text" class="form-control" name="txtLoginName"  id="txtLoginName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">所属城市</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="txtCityName"  id="txtCityName" />
							</div>
						</div>
					</div>
				</div>


			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;">查询</button>		
							
							<button type="button" class="btn btn-w-m btn-primary" 
							style="margin-left:3px;"  onclick="AddShow()" id="btnAdd">添加商户</button>									
								 
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
			                <label >商户名称：</label> 
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
			                <label >所属地址：</label> 
			                    <input  name="txtAddressA" id="txtAddressA" type="text">
			            </div> 
			            <div class="control-group">
			                <label >所属城市：</label> 
			                    <input  name="txtCityNameA" id="txtCityNameA" type="text">
			            </div> 
			             <div class="control-group">
			                <label >站点名称：</label> 
			                    <input  name="txtWebSiteA" id="txtWebSiteA" type="text">
			            </div> 
			            <div>
			               <label >图片预览：</label> 
			               <input  name="txtImgShowA" id="txtImgShowA" type=hidden>
			            <img id="imgShowA" src="" width="200px" height="200px" />
			            </div>
			        </fieldset>
				</div>			
				
				
				<div style="margin-left:30px">
		        <div id="fileQueueA" style="height:80px"></div>
	        	<input type="file" name="uploadifyA" id="uploadifyA" />
		        <p>
		        <a href="javascript:jQuery('#uploadifyA').uploadifyUpload()">文件上传</a>&nbsp;
		        <a href="javascript:jQuery('#uploadifyA').uploadifyClearQueue()">取消所有上传</a>
		        </p>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id=btnModifyGroupBusiness onclick="AddBusiness()">保存</button>
				</div> 
				</div>
			</small>
		</div>  
	</div>
</div>

<div tabindex="-1" class="modal inmodal" id="modifyBusiness"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">修改商户</h4> 
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
			            <br>
			             <div class="control-group">
			                <label >商户名称：</label> 
			                    <input  name="txtCompanyNameM" id="txtCompanyNameM" type="text">			                    
							<input name="txtBusinessIdM" id="txtBusinessIdM" type="hidden">
			            </div>
			            <div class="control-group">
			                <label>电话号码：</label> 
			                <input  name="txtPhoneNoM" id="txtPhoneNoM" type="text">					               
			            </div>
			            <div class="control-group">
			                <label >登录账号：</label> 
			                    <input name="txtLoginNameM" id="txtLoginNameM" type="text">
			            </div> 
			            <div class="control-group">
			                <label >所属地址：</label> 
			                    <input  name="txtAddressM" id="txtAddressM" type="text">
			            </div> 
			            <div class="control-group">
			                <label >所属城市：</label> 
			                    <input  name="txtCityNameM" id="txtCityNameM" type="text">
			            </div> 
			             <div class="control-group">
			                <label >站点名称：</label> 
			                    <input  name="txtWebSiteM" id="txtWebSiteM" type="text">
			            </div> 
			            <div>
			               <label >图片预览：</label> 
			               <input  name="txtImgShowM" id="txtImgShowM" type=hidden>
			            <img id="imgShowM" src="" width="200px" height="200px" />
			            </div>
			        </fieldset>
				</div>			
				
				
				<div style="margin-left:30px">
		        <div id="fileQueueM" style="height:80px"></div>
	        	<input type="file" name="uploadifyM" id="uploadifyM" />
		        <p>
		        <a href="javascript:jQuery('#uploadifyM').uploadifyUpload()">文件上传</a>&nbsp;
		        <a href="javascript:jQuery('#uploadifyM').uploadifyClearQueue()">取消所有上传</a>
		        </p>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id=btnModifyGroupBusiness onclick="ModifyBusiness()">保存</button>
				</div> 
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
				<h4 class="modal-title">商户充值</h4>				
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						  <div class="control-group">
		                <label>商户名称：</label>
		                <input name="txtCompanyNameD" id="txtCompanyNameD" disabled="disabled" type="text">
		                <input name="txtBusinessIdD" id="txtBusinessIdD" type="hidden">
		            	</div>
			            <div class="control-group">
			                <label>商户电话：</label>
			                <input name="txtPhoneNoD" id="txtPhoneNoD" disabled="disabled" type="text">
			            </div>
			            <div class="control-group">
			                <label>充值金额：</label>
			                <input name="txtAmountD" id="txtAmountD" text="0" type="text">元
			            </div>
			            <div class="control-group">
			                <label>备&nbsp;&nbsp;注：</label>
			                <div class="controls">
			                    <textarea cols="45" rows="5" id="txtRemarkD"></textarea>
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


$(document).ready(function() {
    $("#uploadifyA").uploadify({
     	'buttonImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/selectFile.gif',
        'uploader':'<%=basePath%>/js/jquery.uploadify-v2.1.0/uploadify.swf',
        'script':'<%=UploadUrl%>/fileupload/uploadimg?uploadFrom=1',//后台处理的请求
        'cancelImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/cancel.png',
        'folder':'uploads',//您想将文件保存到的路径
        'queueID':'fileQueueA',//与下面的id对应
        'queueSizeLimit':1,
        'wmode':'transparent',
        'fileDesc':'图片文件',    
    	'fileExt':'*.jpg;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
       	'auto':false,
        'multi':false,
        'simUploadLimit':1,
        'maxQueueSize': 1,
        'successTimeout':600,
         'buttonText':"BROWSER",
        'fileSizeLimit' : '2MB',
        onComplete: function (event, queueId, fileObj, response, data) {
            var jsonstr = JSON.parse(response);
            $("#imgShowA").attr("src",jsonstr.data.fileUrl);           
            $('#txtImgShowA').val(jsonstr.data.relativePath)   
         /*    $("#imgShowM").attr("src",jsonstr.Result.FileUrl);           
            $('#txtImgShowM').val(jsonstr.Result.RelativePath)      */       

           // document.getElementById(randimg).src="jsonstr.Result.FileUrl";
            // alert("上传成功，地址："+jsonstr.Result.FileUrl);
            
            
//              {"Status":1,"Message":"成功","Result":{"FileUrl":
//             	 "http://192.168.1.38:8999/Business/2015/10/13/23/49452547d2.jpg",
//             	 "RelativePath":"Business/2015/10/13/23/49452547d2.jpg",
//             	 "OriginalName":"Chrysanthemum.jpg","ModifyOriginalName":
//             		 "49452547d2_0_0.jpg"}}
        }
    });
    
    $("#uploadifyM").uploadify({
     	'buttonImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/selectFile.gif',
        'uploader':'<%=basePath%>/js/jquery.uploadify-v2.1.0/uploadify.swf',
        'script':'<%=UploadUrl%>/fileupload/uploadimg?uploadFrom=1',//后台处理的请求
        'cancelImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/cancel.png',
        'folder':'uploads',//您想将文件保存到的路径
        'queueID':'fileQueueM',//与下面的id对应
        'queueSizeLimit':1,
        'wmode':'transparent',
        'fileDesc':'图片文件',    
    	'fileExt':'*.jpg;*.png', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
       	'auto':false,
        'multi':false,
        'simUploadLimit':1,
        'maxQueueSize': 1,
        'successTimeout':600,
         'buttonText':"BROWSER",
        'fileSizeLimit' : '2MB',
        onComplete: function (event, queueId, fileObj, response, data) {
            var jsonstr = JSON.parse(response);
          $("#imgShowM").attr("src",jsonstr.data.fileUrl);           
            $('#txtImgShowM').val(jsonstr.data.relativePath)           

        }
    });
});

function AddShow(){
	$("#imgShowA").attr("src","");
    $('#addBusiness').modal('show');
//     $('#uploadify').uploadify('settings','buttonImg','../js/jquery.uploadify-v2.1.0/selectFile.gif');
}

//添加,保存商户
function AddBusiness(){	
	
	var companyName= $('#txtCompanyNameA').val().trim();
	var phoneNo= $('#txtPhoneNoA').val().trim();	
    var loginName = $('#txtLoginNameA').val().trim();
    var address= $('#txtAddressA').val().trim();
    var cityName= $('#txtCityNameA').val().trim();
    var webSite= $('#txtWebSiteA').val().trim();
    var logo= $('#txtImgShowA').val().trim();        
        
    var reg=/[\u4e00-\u9fa5]+/;       
    if(companyName.trim().length <=4 || companyName.trim().length>30){
    	alert("商户名称必须在5-30个字符");
    	return;
    }
    if (reg.test(loginName)){
    	alert("登录名称不能为中文字符");
    	return;
    }
    if(loginName.trim().length <6 || loginName.trim().length>20){
    	alert("登录账号为6-20位非中文字符");
    	return;
    }   
    
    var paramaters = {
            "companyName": companyName,
            "phoneNo": phoneNo,
            "loginName": loginName,
            "address": address,
            "cityName": cityName,
            "webSite": webSite,
            "logo": logo 
        };
   var url = "<%=basePath%>"+"/business/addbusiness";  
		$.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {        
	        	   
	        	   if (result>0) {
						alert("操作成功");
	        	   window.location.href = "<%=basePath%>/business/list";
					} else {
						alert("操作失败");
					}      
	           }
	       });	 	    
  }
    
  //修改,保存商户
function ModifyBusiness(){	  
	var id= $('#txtBusinessIdM').val().trim();
	var companyName= $('#txtCompanyNameM').val().trim();
	var phoneNo= $('#txtPhoneNoM').val().trim();	
    var loginName = $('#txtLoginNameM').val().trim();
    var address= $('#txtAddressM').val().trim();
    var cityName= $('#txtCityNameM').val().trim();
    var webSite= $('#txtWebSiteM').val().trim();     
    var logo= $('#txtImgShowM').val().trim();        
    
    var reg=/[\u4e00-\u9fa5]+/;       
    
    if(companyName.trim().length <=4 || companyName.trim().length>30){
    	alert("商户名称必须在5-30个字符");
    	return;
    }
    if (reg.test(loginName)){
    	alert("登录名称不能为中文字符");
    	return;
    }
    if(loginName.trim().length <6 || loginName.trim().length>20){
    	alert("登录账号为6-20位非中文字符");
    	return;
    }       
    
    var paramaters = {
    		"id": id,
            "companyName": companyName,
            "phoneNo": phoneNo,
            "loginName": loginName,
            "address": address,
            "cityName": cityName,
            "webSite": webSite,
            "logo": logo
        };
   var url = "<%=basePath%>/business/modifybusiness";  
		$.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {        
	        	   
	        	   if (result>0) {
						alert("操作成功");
	                   window.location.href = "<%=basePath%>/business/list";
					} else {
						alert("操作失败");
					}      
	           }
	       });	 	    
  }
	        	  
//商户充值
function AddBusinessDelta(){

	var businessId= $('#txtBusinessIdD').val();	
	var balance= $('#txtAmountD').val().trim();
	var remark= $('#txtRemarkD').val().trim();	
	
    var reg=/^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
    if (!reg.test(balance)){
    	alert("请输入正确的金额");
    	return;
	           }
    if(balance<1 || balance>1000000)
    	{
    	alert("充值金额有误，请输入正确金额1-1000000");
    	return; 
    	}
   
    var paramaters = {
            businessId: businessId,
            balance: balance,
            remark:remark
        };
    
        var url = "<%=basePath%>/business/addbusinessdelta";  	
		$.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {	        	   
	        	   if (result>0) {
						alert("操作成功");
						window.location.href = "<%=basePath%>/business/list";
					} else {
						alert("操作失败");
					}      	        	  
	           }
	       });
   	    
}

<%--     //上传图片
    function ajaxFileUpload()  
    {      	
     var typeValue=<%=UploadForm.Clienter.value() %>;
	$.ajaxFileUpload({      
    //url:'http://192.168.1.38/upload/uploadimg?uploadFrom=1',  
    secureuri:false,  
    fileElementId:'uploadFileInput',                         //文件选择框的id属性  
    //dataType: 'json',                                     //服务器返回的格式，可以是json  	
    success: function (data, status)             //相当于java中try语句块的用法  
    {     
     	//var json=eval("("+data+")")
     	var url =  data.body.innerText ;  
     	var fullUrl="<%=basePath%>"+"<%=UploadPath%>"+url;     	
     	$("showBusiImage").attr("src",fullUrl);   
     	$("#txtshowBusiImage").val(url);
       // $('#result').html('上传图片成功!请复制图片地址<br/>'+data.src);  

    },  
    error: function (data, status, e)             //相当于java中catch语句块的用法  
    {  
        //$('#result').html('上传图片失败');  
    	alert(e);  
    }  
  }  
);  
    }  --%>
</script>
