<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.AccountInfo"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="com.renrentui.renrenentity.Business"%>
<%@page import="com.renrentui.renrenentity.Template"%>
<%@page import="com.renrentui.renrenentity.Attachment"%>
<%@page import="com.renrentui.renrenentity.TaskCityRelation"%>
<%@page import="com.renrentui.renrenentity.PublicProvinceCity"%>
<%@page import="com.renrentui.renrenentity.domain.RenRenTaskDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%@page import="com.renrentui.renrenadmin.common.UserContext"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
String UploadPath= PropertyUtils.getProperty("UploadUrl");
String ImgShowUrl= PropertyUtils.getProperty("ImgShowUrl");

boolean uPDATE_TASK=UserContext.getCurrentContext(request).isHasAuthByCode("UPDATE_TASK");
RenRenTaskDetail taskInfo = (RenRenTaskDetail) request.getAttribute("taskInfo");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
String templatelist = (String) request.getAttribute("templatelist");
List<PublicProvinceCity> provincelist = (List<PublicProvinceCity>) request.getAttribute("provincelist");
String pro_city = (String) request.getAttribute("pro_city");
String city_region = (String) request.getAttribute("city_region");
String oldRegionCode="";
for (TaskCityRelation cityRelation : taskInfo.getCityRelationList()) {
	if(oldRegionCode==""){
		oldRegionCode=cityRelation.getCityCode().toString();
	}else{
		oldRegionCode+=(";"+cityRelation.getCityCode().toString());
	}
}

String oldAttachmentFiles="";
String temp="";
for (Attachment attach : taskInfo.getAttachmentsList()) {
	temp=attach.getAttachmentName()+"#"+attach.getAttachUrl();
	if(oldAttachmentFiles==""){
		oldAttachmentFiles=temp;
	}else{
		oldAttachmentFiles+=(";"+temp);
	}
}
TaskStatus detailStatus=TaskStatus.getEnum(taskInfo.getTaskInfo().getStatus());
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="<%=basePath%>/js/ajaxfileupload.js"></script>
<script src="<%=basePath%>/js/renrentask.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" id="id" name="id" value="<%=taskInfo.getTaskInfo().getId() %>" />
		<fieldset>
			<legend>基本信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务标题: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskTitle" value="<%=taskInfo.getTaskInfo().getTaskTitle() %>"
										id="taskTitle" />
								</div>
							</div>
						</div>


						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">审核周期: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="auditCycle" value="<%=taskInfo.getTaskInfo().getAuditCycle() %>"
										id="auditCycle" />
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   天
								</div>
							</div>
						</div>
				<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务周期: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="taskCycle" value="<%=taskInfo.getTaskInfo().getTaskCycle() %>"
										id="taskCycle" />
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   小时
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务总数: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="taskTotalCount" value="<%=taskInfo.getTaskInfo().getTaskTotalCount() %>"
										id="taskTotalCount" />
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   个
								</div>
							</div>
						</div>
</div>
						<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">开始日期:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i></span> <input type="text"
											class="form-control" value="<%=ParseHelper.ToDateString(taskInfo.getTaskInfo().getBeginTime(), "yyyy-MM-dd") %>" name="beginDate" id="beginDate" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">结束日期:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i></span> <input type="text"
											class="form-control" value="<%=ParseHelper.ToDateString(taskInfo.getTaskInfo().getEndTime(), "yyyy-MM-dd") %>" name="endDate" id="endDate" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">单次佣金: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="amount" value="<%=ParseHelper.digitsNum(taskInfo.getTaskInfo().getAmount(),2) %>"
										id="amount" />
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   元
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">支付方式: </label>
								<div class="col-sm-8">
								<label class="control-label">线下支付</label>
								<input type="hidden"  name="paymentMethod" value="1" id="paymentMethod" />
<!-- 									<select id="paymentMethod" name="paymentMethod"  class="form-control m-b"> -->
<!-- 										<option value="1">线下支付</option> -->
<!-- 										<option value="2">线上支付</option>  -->
<!-- 									</select> -->

								</div>
							</div>
						</div>
						</div>
						<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">相关链接: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="link" id="link" value="<%=taskInfo.getTaskInfo().getLink() %>"/>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务公告: </label>
								<div class="col-sm-8">
									<textarea maxlength="200"  rows="3" cols="20" class="form-control" name="taskNotice" 
										id="taskNotice"><%=taskInfo.getTaskInfo().getTaskNotice() %></textarea>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务描述: </label>
								<div class="col-sm-8">
									<textarea maxlength="200"  rows="3" cols="20" class="form-control" name="taskGeneralInfo" 
										id="taskGeneralInfo"><%=taskInfo.getTaskInfo().getTaskGeneralInfo() %></textarea>
								</div>
							</div>
						</div>
						
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">注意事项: </label>
								<div class="col-sm-8">
								<textarea maxlength="200"  rows="3" cols="20" class="form-control" name="taskNote" 
										id="taskNote"><%=taskInfo.getTaskInfo().getTaskNote() %></textarea>
								</div>
							</div>
						</div>
						</div>
						<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">公司简介: </label>
								<div class="col-sm-8">
									<textarea maxlength="200"  rows="3" cols="20" class="form-control" name="companySummary" 
										id="companySummary"><%=taskInfo.getTaskInfo().getCompanySummary() %></textarea>
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
			<input type="hidden" name="attachmentfiles" id="attachmentfiles" value="<%=oldAttachmentFiles %>" />
				<table id="uploadfiletable"
					class="table table-striped table-bordered table-hover dataTables-example">
					<thead>
						<tr>
							<th width="5%">序号</th>
							<th>文件名称</th>
							<th>预览</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
					<%for (int i = 0; i < taskInfo.getAttachmentsList().size(); i++) {%>
						<tr>
						<td><%=(i+1) %></td>
						<td><%=taskInfo.getAttachmentsList().get(i).getAttachmentName() %></td>
						<td><a target="_blank" href='<%=ImgShowUrl+taskInfo.getAttachmentsList().get(i).getAttachUrl() %>'>预览</a></td>
						<td><a href="javascript:void(0)" onclick="deleterow(this)">删除</a></td>
					</tr>
					<%}%>
					</tbody>
				</table>
			</div>
		<div class="row" id="uploadDiv">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group"> 
							<div id="fileQueue" style="height:80px"></div>
	        	<input type="file" name="uploadify" id="uploadify" />
		        <p>
		        <a href="javascript:jQuery('#uploadify').uploadifyUpload()">文件上传</a>&nbsp;
		        <a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消上传</a>
		        </p>
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
					<input type="hidden" name="targetPeople" value="1" id="targetPeople" />
<!-- 						<div class="col-lg-3"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label class="col-sm-4 control-label">指派群体: </label> -->
<!-- 								<div class="col-sm-8"> -->
<!-- 									<select id="targetPeople" name="targetPeople"  class="form-control m-b"> -->
<%-- 										<option value="1" <%=taskInfo.getTaskInfo().getTargetPeople()==1?"selected='selected'":"" %>>所有用户</option> --%>
<!-- 										<option value="2">大望路用户群</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">关联商户: </label>
								<div class="col-sm-8">
									<%=HtmlHelper.getSelect("businessId", businessData, "companyName", "id", taskInfo.getTaskInfo().getBusinessId(),null, "全部")%>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">当前账户余额: </label>
								<div class="col-sm-8">
									<label class="control-label" id="businessBalance">0元</label>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">合同模板: </label>
								<div class="col-sm-8">
								<select id="snapshotTemplateId" name="snapshotTemplateId"  class='form-control m-b'></select>
							</div>
						</div>
					</div>
					<div class="row">
					<div class="col-lg-5">
							<div class="form-group" style="color:red">
								<label class="col-sm-2 control-label">注意: </label>
								<div class="col-sm-10">
									<label class="control-label">被关联商户账号将作为合同审核和支付佣金的账号，请慎重选择</label>
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
									<%=HtmlHelper.getSelect("provinceCode", provincelist, "name", "code", null,-1, "全部")%>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">城市: </label>
								<div class="col-sm-8">
									<select id="cityCode" name="cityCode"  class="form-control m-b">
										<option value="-1">全部城市</option>
									</select>
								</div>
							</div>
						</div>
<!-- 						<div class="col-lg-3"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label class="col-sm-4 control-label"></label> -->
<!-- 								<div class="col-sm-8"> -->
<!-- 									<input type="checkbox" name="checkAll" style="margin-top: 2px;" -->
<!-- 										id="selectAll" />全选/取消 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
<!-- 			<div class="row"> -->
<!-- 				<div class="col-lg-12"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-lg-10"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label class="col-sm-4 control-label">区域: </label> -->
<!-- 								<div class="col-sm-12" id="divregion"></div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</fieldset>
			<div class="row">
			<div class="col-lg-4">
				<button type="button"  class="btn btn-w-m btn-primary" id="save" onclick="savetask()"
					style="margin-left: 3px; height: 30px;">保存</button>
			</div>
		</div>
	</form>

	<input type="hidden" id="pro_city" value="<%=pro_city %>" /> 
	<input type="hidden" id="city_region" value="<%=city_region %>" />
</div>

<script>
$(document).ready(function() {
    $("#uploadify").uploadify({
     	'buttonImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/selectFile.gif',
        'uploader':'<%=basePath%>/js/jquery.uploadify-v2.1.0/uploadify.swf',
        'script':'<%=UploadPath%>/Upload/UploadFile?uploadFrom=1',//后台处理的请求
        'cancelImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/cancel.png',
        'folder':'uploads',//您想将文件保存到的路径
        'queueID':'fileQueue',//与下面的id对应
        'queueSizeLimit':1,
        'wmode':'transparent',
        'fileDesc':'文件',    
    	'fileExt':'*.*', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
       	'auto':false,
        'multi':false,
        'simUploadLimit':1,
        'maxQueueSize': 1,
        'successTimeout':600,
         'buttonText':"BROWSER",
        'fileSizeLimit' : '2MB',
        onComplete: function (event, queueId, fileObj, response, data) {
            var jsonstr = JSON.parse(response);
             if(jsonstr.Status==1){
            	 var fileinfo=jsonstr.Result.OriginalName+"#"+jsonstr.Result.RelativePath+"#"+jsonstr.Result.FileUrl;
            	 appendAttachRow(fileinfo);
             }else{
            	 alert("上传失败");
             }
            
//              {"Status":1,"Message":"成功","Result":{"FileUrl":
//             	 "http://192.168.1.38:8999/Business/2015/10/13/23/49452547d2.jpg",
//             	 "RelativePath":"Business/2015/10/13/23/49452547d2.jpg",
//             	 "OriginalName":"Chrysanthemum.jpg","ModifyOriginalName":
//             		 "49452547d2_0_0.jpg"}}
        }
    });
});
function lockpage(){
	var hasAuth="<%=uPDATE_TASK%>";
	var canedit="<%=(detailStatus==TaskStatus.WaitAudit||detailStatus==TaskStatus.Reject)%>";
	if(canedit!="true"||hasAuth!="true"){
	    $("input[type='text']").each(function (i, each) {
	        each.disabled = true;
	    });
		$("textarea").each(function(index,e){
			 e.disabled = true;
		});
	    $("select").each(function (i, each) {
	        each.disabled = true;
	    });
	    $("button").each(function (i, each) {
	        each.disabled = true;
	    });
	    $("#uploadDiv").hide();
	    $('#uploadfiletable tr').find('td:eq(3)').hide();
	    $('#uploadfiletable tr').find('th:eq(3)').hide();
	}
}
function initRegion(){
	var regionList="<%=oldRegionCode%>";
	if(regionList!=""&&regionList!="-1"){
		var codes=regionList.split(";");
		if(codes.length==1){
			var provinceCode=$("#provinceCode").html();
			if(provinceCode.indexOf(codes[0])>=0){//如果是省份
				$("#provinceCode").val(codes[0]);
				$("#provinceCode").change();
				return;
			}else{
				 var tmpProvince=new Array();  
				 var pro_city=$("#pro_city").val().split("#");
				  for(var j=0;j<pro_city.length;j++){
						tmpProvince=pro_city[j].split("=");
						if(tmpProvince[1].indexOf(codes[0])>=0){
						   $("#provinceCode").val(tmpProvince[0]);
						   $("#provinceCode").change();
							$("#cityCode").val(codes[0]);
							$("#cityCode").change();
						   return;
						}
				  }
			}
		}
	}
}

function businessChange(){  
	var templateList="<%=templatelist%>";
	var selectedTemplateId="<%=taskInfo.getTemplateId()%>";
	initSelectTemplate(templateList,selectedTemplateId);
	var paramaters={"businessId":$("#businessId").val()};
	var url = "<%=basePath%>/taskmanage/getbusinessbanlance";
	$.ajax({
		type : 'POST',
		url : url,
		data : paramaters,
		success : function(result) {
			$("#businessBalance").html(result+"元");
		}
	});
};
var oldTotalFee=0;
var oldBusinessId="";
function initFunction(){
	initRegion();
	$("#businessId").on("change",businessChange);
	$("#businessId").change();
	lockpage();
	oldBusinessId=$("#businessId").val();
	oldTotalFee=parseFloat($("#amount").val())*parseFloat($("#taskTotalCount").val());
}
function realDeleteFiles(){
	if(deleteFiles!=""){
		var tempFiles=deleteFiles.split(";");
		for(var i=0;i<tempFiles.length;i++){
			var s=tempFiles[i].split("#");
			var url = "<%=UploadPath%>/upload/deletefile?fileName="+s[1];
			$.ajax({
				type : 'POST',
				url : url,
				data : "",
				success : function(result) {
		            //alert(result.Status);
				}
		});
		}
	}
}
function savetask(){
	if(!validPage(false)){
		return;
	}
	var totalFee=parseFloat($("#amount").val())*parseFloat($("#taskTotalCount").val());
	var balance=parseFloat($("#businessBalance").html().replace("元",""));
	if($("#businessId").val()==oldBusinessId){
		if(totalFee>oldTotalFee&&totalFee-oldTotalFee>balance){
			alert("当前商户账户余额不足，不能保存任务");
			return false;
		}
	}else{
		if(balance<totalFee){
			alert("当前商户账户余额不足，不能保存任务");
			return false;
		}
	}
	var paramaters=$("#searchForm").serialize();
		var url = "<%=basePath%>/taskmanage/updatetask";
		$.ajax({
					type : 'POST',
					url : url,
					data : paramaters,
					success : function(result) {
						if (result > 0) {
							realDeleteFiles();
							alert("操作成功");
							window.location.href = window.location.href;
						} else {
							alert("操作失败:当前商户账户余额不足，不能保存任务");
						}
					}
		});
}
</script>