<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.AccountInfo"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<fieldset>
		<legend>模板基本信息</legend>
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">所属商家: </label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("businessId", null, "name", "id", 0,"0", "全部", "width:143px")%>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">模板名称:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="tempName"
									id="tempName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">模板说明:</label>
							<div class="col-sm-8">
								<textarea rows="5" cols="50" id="tempRemark">
								</textarea>
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
						<th>说明文本（必须唯一）</th>
						<th>名称（英文，必须唯一）</th>
						<th>默认值（可空）</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr id="tr1">
						<td>1</td>
						<td><input type="text" class="form-control" value="年龄" id="title1" /></td>
						<td><input type="text" class="form-control" value="age" id="name1" /></td>
						<td><input type="text" class="form-control" value="18" id="defaultvalue1" /></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</fieldset>
	<div class="row">
		<div class="col-lg-4">
			<button type="button" class="btn btn-w-m btn-primary" id="save"
				style="margin-left: 3px; height: 30px;">保存</button>
			<button type="button" class="btn btn-w-m btn-primary" id="addrow"
				style="margin-left: 3px; height: 30px;">新增一行</button>
		</div>
	</div>
</div>

<script>
$("#addrow").click(function(){
	var rowNum=$("#templatetable tr").length-1;
	var newRowNum=rowNum+1;
	var row = $("<tr></tr>");
	row.append("<td>"+newRowNum+"</td>");
	row.append("<td><input type='text' class='form-control' value='' id='title"+newRowNum+"' /></td>");
	row.append("<td><input type='text' class='form-control' value='' id='name"+newRowNum+"' /></td>");
	row.append("<td><input type='text' class='form-control' value='' id='defaultvalue"+newRowNum+"' /></td>");
	row.append("<td><a href='javascript:void(0)' onclick='deleterow(this)'>删除</a></td>");
	$("#templatetable").append(row);
});
function deleterow(delobj){
	var trs=$("#templatetable tr");
	var oldRowNum=trs.length-1;
	var deltr=$(delobj).parent().parent();
	var rownum=parseInt(deltr.children('td').eq(0).html());
	deltr.remove();
	//将要删除的行的下面的所有行的行号重置
	if(rownum<oldRowNum){
		 for(var i=rownum+1;i<oldRowNum+1;i++){ 
		     var tr=$(trs[i]);
		     var td=tr.children('td').eq(0);
		     $(td).html(i-1);
		 }
	}
}
$("#save").click(function(){
	if($("#tempName").val()==""){
		alert("模板名称不能为空");
		return;
	}
	if($("#tempRemark").val()==""){
		alert("模板说明不能为空");
		return;
	}
	var trs=$("#templatetable tr");
	var oldRowNum=trs.length-1;
	var haserror=false;
	var childparamaters="";
	for(var i=1;i<=oldRowNum;i++){ 
	     var rownum=$(trs[i]).children('td').eq(0).html();
	     childparamaters+=("#orderNum="+rownum+";");
	     $(trs[i]).find("input").each(function(index,e){
	    	 if(e.id.indexOf("title")>=0){
		    	 childparamaters+=("title="+$(e).val()+";");
	    	 }else if(e.id.indexOf("defaultvalue")>=0){
	    		 childparamaters+=("defaultvalue="+$(e).val()+";");
	    	 }else{
	    		 childparamaters+=("name="+$(e).val()+";");
	    	 }
	    	 if($(e).val()==""&&e.id.indexOf("defaultvalue")<0){
		    	 if(e.id.indexOf("title")>=0){
		    		 alert("第"+rownum+"行说明文本不能为空");
		    	 }else{
		    		 alert("第"+rownum+"行名称不能为空");
		    	 }
		    	 haserror=true;
		    	 return false;
	    	 }
	     });
	     if(haserror){
	    	 return;
	     }
	 }
	var inputNames=$("input[id^='title']");
	inputNames.each(function(index,e){
		inputNames.each(function(index2,e2){
			if($(e).val()==$(e2).val()&&index!=index2){
				 alert("第"+(index+1)+"行的说明文本不能和第"+(index2+1)+"的说明文本一致");
				 haserror=true;
		    	 return false;
			}
		});
	     if(haserror){
	    	 return false;
	     }
	});
    if(haserror){
   	  return;
    }
	var inputNames=$("input[id^='name']");
	inputNames.each(function(index,e){
		inputNames.each(function(index2,e2){
			if($(e).val()==$(e2).val()&&index!=index2){
				 alert("第"+(index+1)+"行的名称不能和第"+(index2+1)+"的名称一致");
				 haserror=true;
		    	 return false;
			}
		});
	     if(haserror){
	    	 return false;
	     }
	});
    if(haserror){
   	  return;
    }
	var paramaters = {
			"tempName" :  $("#tempName").val(),
			"tempRemark" : $("#tempRemark").val(),
			"businessId":$("#businessId").val(),
			"child":childparamaters
		};
		var url = "<%=basePath%>/templatemanage/savetemplate";
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