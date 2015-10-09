<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.AccountInfo"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="com.renrentui.renrenentity.Business"%>
<%@page import="com.renrentui.renrenentity.Template"%>
<%@page import="com.renrentui.renrenentity.PublicProvinceCity"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
List<Template> templatelist = (List<Template>) request.getAttribute("templatelist");
List<PublicProvinceCity> provincelist = (List<PublicProvinceCity>) request.getAttribute("provincelist");
String pro_city = (String) request.getAttribute("pro_city");
String city_region = (String) request.getAttribute("city_region");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="<%=basePath%>/js/ajaxfileupload.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<fieldset>
			<legend>基本信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务标题: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskTitle"
										id="taskTitle" />
								</div>
							</div>
						</div>

						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">起止时间:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i></span> <input type="text"
											class="form-control" value="" name="beginDate" id="beginDate" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">到:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i></span> <input type="text"
											class="form-control" value="" name="endDate" id="endDate" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">审核周期: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="auditCycle"
										id="auditCycle" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务总数: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskTotalCount"
										id="taskTotalCount" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">单次佣金: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="amount"
										id="amount" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">支付方式: </label>
								<div class="col-sm-8">
									<select id="paymentMethod" name="paymentMethod">
										<option value="1">线下支付</option>
										<!--<option value="2">线上支付</option> -->
									</select>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务公告: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskNotice"
										id="taskNotice" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务介绍: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskGeneralInfo"
										id="taskGeneralInfo" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">相关链接: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="link" id="link" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">注意事项: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskNote"
										id="taskNote" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">公司简介: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="companySummary"
										id="companySummary" />
								</div>
							</div>
						</div>

					</div>

				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>相关附件</legend>

			<div class="row">
			<input type="hidden" name="attachmentfiles" id="attachmentfiles" value="" />
				<table id="uploadfiletable"
					class="table table-striped table-bordered table-hover dataTables-example">
					<thead>
						<tr>
							<th width="5%">序号</th>
							<th>文件名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"></label>
								<div class="col-sm-8">
								<input id="file1" type="file" name="file1">
									<button type="button" class="btn btn-w-m btn-primary" id="uploadfile"
										style="margin-left: 3px; height: 30px;">上传</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</fieldset>
		<fieldset>
			<legend>关联设置</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">指派群体: </label>
								<div class="col-sm-8">
									<select id="targetPeople" name="targetPeople">
										<option value="1">所有用户</option>
										<!-- 									<option value="2">大望路用户群</option> -->
									</select>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">关联商户: </label>
								<div class="col-sm-8">
									<%=HtmlHelper.getSelect("businessId", businessData, "companyName", "id", null,null, "全部", "width:143px")%>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">合同模板: </label>
								<div class="col-sm-8">
									<%=HtmlHelper.getSelect("templateId", templatelist, "templateName", "id", null,null, "全部", "width:143px")%>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

		</fieldset>
				<fieldset>
			<legend>投放范围</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">省份: </label>
								<div class="col-sm-8">
									<%=HtmlHelper.getSelect("provinceCode", provincelist, "name", "code", null,-1, "全部", "width:143px")%>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">城市: </label>
								<div class="col-sm-8">
									<select id="cityCode" name="cityCode">
										<option value="-1">全部城市</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"></label>
								<div class="col-sm-8">
									<input type="checkbox" name="checkAll" style="margin-top: 2px;"
										id="selectAll" />全选/取消
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-10">
							<div class="form-group">
								<label class="col-sm-4 control-label">区域: </label>
								<div class="col-sm-12" id="divregion"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<div class="row">
			<div class="col-lg-4">
				<button type="button" class="btn btn-w-m btn-primary" id="save" onclick="savetask()"
					style="margin-left: 3px; height: 30px;">保存</button>

			</div>
		</div>
	</form>

	<input type="hidden" id="pro_city" value="<%=pro_city %>" /> 
	<input type="hidden" id="city_region" value="<%=city_region %>" />
</div>

<script>
$(function(){
	  $(' .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });
});
$("#uploadfile").click(function(){
	if ($("#file1").val().length <= 0) {
        alert("请选择文件！");
        return;
    }
    var url = "<%=basePath%>/taskmanage/uploadfile";
    
    $.ajaxFileUpload({
        type: 'POST',
        secureuri: false, //一般设置为false
        fileElementId: 'file1', //文件上传空间的id属性  <input type="file" id="file" name="file" />
        url: url,
        data: "", //此参数非常严谨，写错一个引号都不行
        dataType: "HTML", //此参数非常严谨，写错一个引号都不行
        success: function (data, status) {
        	appendAttachRow(data);
        },
        error:function(errordata){
        	alert(errordata);
        }
    });
	
});
function appendAttachRow(fileinfo){
	var rowNum=$("#uploadfiletable tr").length-1;
	var newRowNum=rowNum+1;
	var row = $("<tr></tr>");
	row.append("<td>"+newRowNum+"</td>");
	row.append("<td>"+fileinfo.split("#")[0]+"</td>");
	row.append("<td><a href='javascript:void(0)' onclick='deleterow(this)'>删除</a></td>");
	$("#uploadfiletable").append(row);
	var oldAttach=$("#attachmentfiles").val();
	if(oldAttach==""){
		$("#attachmentfiles").val(fileinfo);
	}else{
		$("#attachmentfiles").val(oldAttach+";"+fileinfo);
	}
}
function deleterow(delobj){
	var trs=$("#uploadfiletable tr");
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
$("#provinceCode").change(function(){  
    try{  
        var pro=$(this).val();  
        var pro_city=$("#pro_city").val().split("#");
        
        var i,j,tmpprocity=new Array();  
        var tmpkeyvalue=new Array();  
        for(i=1;i<pro_city.length;i++){
        	tmpcity=pro_city[i].split("=");
            if(pro==tmpcity[0]){  
                tmpcity=tmpcity[1].split(";");  
                $("#cityCode").html("<option value='-1'>全部城市</option>");  
                for(j=1;j<tmpcity.length;j++){  
                	tmpkeyvalue=tmpcity[j].split("|");
                    $("#cityCode").append("<option value='"+tmpkeyvalue[0]+"'>"+tmpkeyvalue[1]+"</option>");     
                }  
            }  
        }
        $("#divregion").html(""); 
        $("#selectAll").prop("checked",false);
    }catch(e){  
        alert(e);     
    }  
}); 
$("#cityCode").change(function(){  
    try{  
        $("#divregion").html("");  
        $("#selectAll").prop("checked",false);
    	var pro=$(this).val();  
        var pro_city=$("#city_region").val().split("#");
        
        var i,j,tmpprocity=new Array();  
        var tmpkeyvalue=new Array();  
        for(i=1;i<pro_city.length;i++){
        	tmpcity=pro_city[i].split("=");
            if(pro==tmpcity[0]){  
                tmpcity=tmpcity[1].split(";");  
                $("#divregion").html("");  
                for(j=1;j<tmpcity.length;j++){  
                	tmpkeyvalue=tmpcity[j].split("|");
                    $("#divregion").append("<input type='checkbox' name='regionCode"+tmpkeyvalue[0]+"' onclick='chanageSelectAll()' value='"+tmpkeyvalue[0]+"' /> <label>"+tmpkeyvalue[1]+"</label>");     
                }  
            }  
        } 
    }catch(e){  
        alert(e);     
    }  
}); 
//全选全消
$('#selectAll').on('click', function (e) {
	var checkedOfAll = $("#selectAll").prop("checked");
	$("#divregion input[type='checkbox']").prop("checked", checkedOfAll);
});
function chanageSelectAll(){
	var allNum=$("#divregion input[type='checkbox']").length;
	var checkedNum=$("#divregion input[type='checkbox']:checked").length;
	if(allNum==checkedNum){
		$("#selectAll").prop("checked",true);
	}else{
		$("#selectAll").prop("checked",false);
	}
};
function savetask(){
	var hasempty=false;
	$("input[type='text']").each(function(index,e){
		if(e.id=="taskNotice"||e.id=="link"||e.id=="companySummary"||e.id=="taskNote"){
			return true;
		}
		if($(e).val()==""){
			if(e.id=="beginTime"||e.id=="EndTime"){
				alert("起止日期不能为空");
			}else{
				alert($(this).parent().prev().html().replace(": ","")+"不能为空");
			}
			hasempty=true;
			return false;
		}
	});
	if(hasempty){
		return;
	}
	var checkedNum=$("#divregion input[type='checkbox']:checked").length;
	if($("#cityCode").val()!="-1"&&checkedNum==0){
		alert("城市不是全部城市时,请至少选择一个区域");
		return;
	}
	var paramaters=$("#searchForm").serialize();
		var url = "<%=basePath%>/taskmanage/savetask";
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
}
</script>