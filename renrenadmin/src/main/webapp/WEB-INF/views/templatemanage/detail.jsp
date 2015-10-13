<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.Business"%>
<%@page import="com.renrentui.renrenentity.TemplateDetail"%>
<%@page import="com.renrentui.renrenentity.domain.TemplateModel"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
TemplateModel templateInfo = (TemplateModel) request.getAttribute("templateInfo");
String oldDetailList="";
String temp="";
for (TemplateDetail detail : templateInfo.getDetailList()) {
	temp=detail.getControlId()+"#"+detail.getTitle()+"#"+detail.getName()+"#"+detail.getDefaultValue();
	if(oldDetailList==""){
		oldDetailList=temp;
	}else{
		oldDetailList+=(";"+temp);
	}
}
%>
<script src="<%=basePath%>/js/renrentemplate.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
<form method="POST" action="#" class="form-horizontal" id="searchForm">
<input type="hidden" id="id" name="id" value="<%=templateInfo.getId() %>" />
	<fieldset>
		<legend>模板基本信息</legend>
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">所属商家: </label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("businessId", businessData, "companyName", "id", templateInfo.getBusinessId(),null, "全部")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">模板名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="tempName"
									id="tempName" value="<%=templateInfo.getTemplateName()%>"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">模板说明:</label>
							<div class="col-sm-8">
								<textarea rows="3" cols="20" class="form-control" id="tempRemark"><%=templateInfo.getRemark()%></textarea>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</fieldset>
	<fieldset>
		<legend>模板配置</legend>
		<div class="row">
			<table id="templatetable" class="table table-striped table-bordered table-hover dataTables-example">
				<thead>
					<tr>
						<th width="5%">续号</th>
						<th>控件类型</th>
						<th>说明文本（必须唯一）</th>
						<th>名称（英文，必须唯一）</th>
						<th>默认值（可空）</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</fieldset>
	<div class="row">
		<div class="col-lg-4">
			<button type="button" class="btn btn-w-m btn-primary" id="save"
				style="margin-left: 3px; height: 30px;">保存</button>
			<button type="button" class="btn btn-w-m btn-primary" id="resetpage"
				style="margin-left: 3px; height: 30px;">重置</button>
			<button type="button" class="btn btn-w-m btn-primary" id="addrow"
				style="margin-left: 3px; height: 30px;">新增一行文本框</button>
			<button type="button" class="btn btn-w-m btn-primary" id="addImgRow"
				style="margin-left: 3px; height: 30px;">新增一行图片上传</button>
		</div>
	</div>
</form>
</div>

<script>
var oldParam="";
$(function(){
var oldChild="<%=oldDetailList%>";
var detailList=oldChild.split(";");
for(var i=0;i<detailList.length;i++){
	var details=detailList[i].split("#");
	if(details[0]=="1"){
		$("#addrow").click();
		$("#title"+(i+1)).val(details[1]);
		$("#name"+(i+1)).val(details[2]);
		$("#defaultvalue"+(i+1)).val(details[3]);
	}else if(details[0]=="3"){
		$("#addImgRow").click();
		$("#title"+(i+1)).val(details[1]);
		$("#name"+(i+1)).val(details[2]);
	}
}
oldParam=$("#searchForm").serialize();
});
$("#save").click(function(){
	var childparamaters=validPage();
	if(childparamaters==""){
		return;
	}
	var newParam=$("#searchForm").serialize();
	if(oldParam==newParam){
		alert("没有任何变更，不需要保存");
		return;
	}
	var paramaters = {
			"id" :  $("#id").val(),
			"tempName" :  $("#tempName").val(),
			"tempRemark" : $("#tempRemark").val(),
			"businessId":$("#businessId").val(),
			"child":childparamaters
		};
		var url = "<%=basePath%>/templatemanage/updatetemplate";
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
	});
</script>