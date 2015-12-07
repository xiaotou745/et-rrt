<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrenadmin.common.UserContext"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">

	<div class="row">
		<div class="col-lg-12">
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="addversion"
							style="margin-left: 3px;height:30px;">添加版本</button>
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
<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 550px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">用户操作</h4>
			</div>
			<div class="modal-body form-horizontal">
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">客户端类型:</label>
							<div class="col-sm-8" style="padding-top: 5px;">
								<input name="rPlatForm" id="rAndroid" type="radio" value="1"
									checked="checked" /> Android <input name="rPlatForm" id="rIOS"
									type="radio" value="2" /> IOS
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">用户类型:</label>
							<div class="col-sm-8" style="padding-top: 5px;">
								<input name="rUserType" id="rClienter" type="radio" value="1"
									checked="checked" /> 骑士 <input name="rUserType" id="rBusiness"
									type="radio" value="2" /> 门店
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">是否强制更新:</label>
							<div class="col-sm-8" style="padding-top: 5px;">
								<input name="rIsMust" id="rIsMustN" type="radio" value="0"
									checked="checked" /> 否 <input name="rIsMust" id="rIsMustY"
									type="radio" value="1" /> 是
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">发布类型:</label>
							<div class="col-sm-8" style="padding-top: 5px;">
								<input name="rSendType" id="rTiming" type="radio" value="1"
									checked="checked" /> 定时发布 <input name="rSendType"
									id="rRealtime" type="radio" value="0" /> 实时发布
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">发布时间:</label>
							<div class="col-sm-8">
								<input type="text" name="txtSendTime" id="txtSendTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:{%m+5}:%s'})"
									class="form-control" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">版本号:</label>
							<div class="col-sm-8">
								<input name="version" class="form-control" id="version"
									type="text" value="" onkeyup="value=value.replace(/[^\d]/g,'')"
									maxlength="5">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">下载地址:</label>
							<div class="col-sm-8">
								<input name="updateUrl" class="form-control" id="updateUrl"
									type="text" value="">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-9">
						<div class="form-group">
							<label class="col-sm-4 control-label">更新内容:</label>
							<div class="col-sm-8">
								<textarea cols="50" rows="8" id="message"></textarea>
							</div>
						</div>
					</div>
				</div>
				<span id="tip" style="color: red"></span>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				<button type="button" id="saveversion" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
	<input type="hidden" id="versionid" /> 
	<input type="hidden" id="optype" />
<script>
	var jss = {
		search : function(currentPage) {
			var data={"currentPage":currentPage};
			$.post("<%=basePath%>/admintools/appversiondo",data, function(d) {
				$("#content").html(d);
			});
		}
	}
	jss.search(1);
	$("#addversion").click(function(){
		reset();
		$("#versionid").val("0");
		$("#optype").val("3");
		$("input[type='text']").attr("disabled",false);
		$("input[type='radio']").attr("disabled",false);
		$("#message").attr("disabled",false);
		$("#saveversion").show();
		$("#myModal").modal('show');
	});
	function cancelPublish(id){
		if(!confirm("确定要取消发布？")){
			return;
		}
		var paramaters = {
				"id" :  id
			};
		var url = "<%=basePath%>/admintools/cancelversionpublish";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败");
				}
			}
		});
	}
	function getdetail(id){
		var paramaters = {
				"id" :  id
			};
		var url = "<%=basePath%>/admintools/getversionbyid";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if(result.platForm==1){
					$("#rAndroid").prop('checked',true); 
					$("#rIOS").prop('checked',false); 
				}else{
					$("#rIOS").prop('checked',true); 
					$("#rAndroid").prop('checked',false); 
				}
				if(result.userType==1){
					$("#rClienter").prop('checked',true); 
					$("#rBusiness").prop('checked',false); 
				}else{
					$("#rBusiness").prop('checked',true); 
					$("#rClienter").prop('checked',false); 
				}
				if(result.isMust==1){
					$("#rIsMustY").prop('checked',true); 
					$("#rIsMustN").prop('checked',false); 
				}else{
					$("#rIsMustN").prop('checked',true); 
					$("#rIsMustY").prop('checked',false); 
				}
				if(result.isTiming==1){
					$("#rTiming").prop('checked',true); 
					$("#rRealtime").prop('checked',false); 
				}else{
					$("#rRealtime").prop('checked',true); 
					$("#rTiming").prop('checked',false); 
				}
				$("#txtSendTime").val(result.timingDate);
				$("#version").val(result.version);
				$("#updateUrl").val(result.updateUrl);
				$("#message").val(result.message);
			}
		});
	}
	function reset(){

		$("#rAndroid").prop('checked',true); 
		$("#rIOS").prop('checked',false); 

		$("#rClienter").prop('checked',true); 
		$("#rBusiness").prop('checked',false); 

		$("#rIsMustN").prop('checked',true); 
		$("#rIsMustY").prop('checked',false); 

		$("#rTiming").prop('checked',true); 
		$("#rRealtime").prop('checked',false); 
		
		$("#txtSendTime").val("");
		$("#version").val("");
		$("#updateUrl").val("");
		$("#message").val("");
	}
	function getparam(){
		var istiming=$('input[type="radio"][name="rSendType"]:checked').val();
		var timingdate="";
		if(istiming==1){
			timingdate=$("#txtSendTime").val();
		}
		var paramaters = {
				"opType":$("#optype").val(),
				"id" :$("#versionid").val(),
				"platForm":$('input[type="radio"][name="rPlatForm"]:checked').val(),
				"userType":$('input[type="radio"][name="rUserType"]:checked').val(),
				"isMust":$('input[type="radio"][name="rIsMust"]:checked').val(),
				"isTiming":$('input[type="radio"][name="rSendType"]:checked').val(),
				"timingDate":timingdate,
				"version":$("#version").val(),
				"updateUrl":$("#updateUrl").val(),
				"message":$("#message").val()
			};
		return paramaters;
	}
	$('input[type="radio"][name="rSendType"]').change(function() {	
		var istiming=$('input[type="radio"][name="rSendType"]:checked').val();				
		if (istiming == 1) {	//定时发布			
			$("#txtSendTime").attr("disabled",false);
		}else{
			$("#txtSendTime").val("");
			$("#txtSendTime").attr("disabled",true);
		}		
	});
	//type，0是查看，1是修改，2是取消,3是新增
	function ViewDetail(id,type){
		if(type==2){
			cancelPublish(id);
		}else{
			$("#versionid").val(id);
			$("#optype").val(type);
			getdetail(id);
			if(type==0){
				$("input[type='text']").attr("disabled",true);
				$("input[type='radio']").attr("disabled",true);
				$("#message").attr("disabled",true);
				$("#saveversion").hide();
			}else{
				var istiming=$('input[type="radio"][name="rSendType"]:checked').val();
				if(istiming==0){
					$("#txtSendTime").val("");
				}
			
				$("input[type='text']").attr("disabled",false);
				$("input[type='radio']").attr("disabled",false);
				$("#message").attr("disabled",false);
				$("#saveversion").show();
			}
			$("#myModal").modal('show');
		}
	}
	$("#saveversion").click(function(){
		var paramaters=getparam();
		if (paramaters.version.trim().length == 0) {
            alert("版本号不能为空！");
            return;
        }
        if (paramaters.updateUrl.trim().length == 0) {
            alert("下载地址不能为空！");
            return;
        }
        if (paramaters.message.trim().length <5) {
            alert("更新内容长度不能少于5个字符！");
            return;
        }
        var remStr = "是否确认发布版本？";
        if (paramaters.isTiming == 1) {
            if (paramaters.timingDate.trim().length == 0) {
                alert("发布时间不能为空！");
                return;
            }
            remStr = "确定提交吗？";
        }
        if(!confirm(remStr)){
			return;
		}
		var url = "<%=basePath%>/admintools/saveversion";
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败");
				}
			}
		});
	});
</script>