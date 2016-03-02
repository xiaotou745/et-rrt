<%@page import="java.util.ArrayList"%>
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
<%@page import="com.renrentui.renrenentity.RenRenTask"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%@page import="com.renrentui.renrenadmin.common.UserContext"%>
<%@page import="com.renrentui.renrenentity.domain.TaskSetp"%>
<%@page import="com.renrentui.renrenentity.domain.TemplateGroup"%>
<%@page import="com.renrentui.renrenentity.PublicProvinceCity"%>
<%@page import="com.renrentui.renrenentity.TaskTag"%>

<%
String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
RenRenTask taskInfo = (RenRenTask) request.getAttribute("taskInfo");
List<TaskSetp> taskSetps=(List<TaskSetp>) request.getAttribute("taskSetps");
List<TemplateGroup> groups=(List<TemplateGroup>) request.getAttribute("groups");
List<PublicProvinceCity> provincelist = (List<PublicProvinceCity>) request.getAttribute("provincelist");//省份
Map<Integer, List<PublicProvinceCity>> provinceCityMap = (Map<Integer, List<PublicProvinceCity>>) request.getAttribute("provinceCityMap");//省份
String taskCityInfo=request.getAttribute("taskCityInfo")==null?"":(String) request.getAttribute("taskCityInfo");
List<TaskTag> tagList=(List<TaskTag>)request.getAttribute("tagList");
%>
<script src="<%=basePath%>/js/ajaxfileupload.js"></script>
<script src="<%=basePath%>/js/renrentask.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" id="id" name="id" value="<%=taskInfo.getId() %>" />
		<fieldset>
			<legend>基本信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务标题: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskTitle" value="<%=taskInfo.getTaskTitle() %>"
										id="taskTitle" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务描述: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskGeneralInfo" id="taskGeneralInfo" value="<%=taskInfo.getTaskGeneralInfo() %>" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">审核周期: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="auditCycle" id="auditCycle" value="<%=taskInfo.getAuditCycle()%>"/>
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   天
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">地推员佣金: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="amount" id="amount" value="<%=ParseHelper.digitsNum(taskInfo.getAmount(),2) %>" />
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   元
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务总佣金: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="totalAmount" id="totalAmount" value="<%=ParseHelper.digitsNum(taskInfo.getTotalAmount(),2) %>"/>
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   元
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">预计完成消耗: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="estimatedTime" id="estimatedTime" value="<%=taskInfo.getEstimatedTime() %>"/>
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   分钟
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
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span> 
										<input type="text" class="form-control" value="<%=ParseHelper.ToDateString(taskInfo.getBeginTime(), "yyyy-MM-dd") %>" name="beginDate" id="beginDate" />
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
										<input type="text"	class="form-control" value="<%=ParseHelper.ToDateString(taskInfo.getEndTime(), "yyyy-MM-dd") %>" name="endDate" id="endDate" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">咨询热线: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="hotline" id="hotline" value="<%=taskInfo.getHotLine()%>"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">任务类型: </label>
								<div class="col-sm-8">
									<input id="rTaskType1" name="rTaskType" type="radio" value="1" <%=taskInfo.getTaskType()==1?"checked" : ""%>> 
									<label>签约任务</label>
									<input id="rTaskType2" name="rTaskType" type="radio" value="2" <%=taskInfo.getTaskType()==2?"checked" : ""%>> 
									<label>分享任务</label>
									<input id="rTaskType3" name="rTaskType" type="radio" value="3" <%=taskInfo.getTaskType()==3?"checked" : ""%>> 
									<label>下载任务</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务标签: </label>
								<div class="col-sm-8">
								<%
								for (int i = 0; i < tagList.size(); i++) {
								%>
									<input id="taskTag<%=i%>" name="rTaskTagId" type="radio" value="<%=tagList.get(i).getId() %>" 
									 <%=(taskInfo.getTagId()==tagList.get(i).getId().longValue()?"checked" : "")%>> 
									<label><%=tagList.get(i).getTagName() %></label>
									<%}%>
								</div>
							</div>
						</div>
					</div>
					<% if(taskInfo.getTaskType()!=1)
					{%>
					
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">下载地址: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="downUrl" id="downUrl" value="<%=taskInfo.getDownUrl()%>"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">扫码说明: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="scanTip" id="scanTip" value="<%=taskInfo.getScanTip()%>"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">温馨提示: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="reminder" id="reminder" value="<%=taskInfo.getReminder()%>"/>
								</div>
							</div>
						</div>
					</div>
					
					<%} 
					%>
					
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>任务流程</legend>
			<span style="color:red;">*</span><span>完成步骤（请详细说明完成任务需要哪几个步骤，方便地推员按要求完成任务）</span>
			<div class="orderBox dn" id="setpbox">
			<%  int num=0;
				for(int i=0;i<taskSetps.size();i++)
				{ 
					if(taskSetps.get(i).getSetpType()==1){
						num++;
						%>
						<div class="copy">
							<div class="row">
								<div class="col-lg-3">
									<div class="form-group">
										<label class="col-sm-4 control-label">步骤<%=num%>: </label>
										<div class="col-sm-8">
											<input type="text" class="form-control" value="<%=taskSetps.get(i).getContent()%>"/>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%
					}
				}%>
				
			</div>
			<hr align="center" width="50%" style="color:#333333;">
			<span>补充说明（如果任务有特殊说明，或者注意事项，可在此处进行补充）</span>
			<div class="orderBox dn" id="setpbox2">
				<%num=0; 
				for(int i=0;i<taskSetps.size();i++)
				{ 
					if(taskSetps.get(i).getSetpType()==2){
						num++;
						%>
						<div class="copy2">
							<div class="row">
								<div class="col-lg-3">
									<div class="form-group">
										<label class="col-sm-4 control-label"><%=num%>、 </label>
										<div class="col-sm-8">
											<input type="text" class="form-control" value="<%=taskSetps.get(i).getContent()%>" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<%
					}
				}%>
				
			</div>
			<hr align="center" width="50%" style="color:#333333;">
			<span>细则（添加超链接，打开新页面）</span>
			<div class="orderBox dn" id="setpbox3" >
			<table class="table table-striped table-bordered table-hover dataTables-example" style="width:40%;">
				<thead>
					<tr><th>序号</th><th>链接文字</th><th>链接地址</th></tr>
				</thead>
				<tbody>
				<%num=0;
				for(int i=0;i<taskSetps.size();i++)
				{ 
					if(taskSetps.get(i).getSetpType()==3){
						num++;
						%>
						<tr class="copy3">
						<td><label><%=num%></label></td>
						<td><input type="text"  style="width:200px;" class="eltitle" value="<%=taskSetps.get(i).getLinkTitle()%>"></td>
						<td><input type="text"  style="width:200px;" class="elurl" value="<%=taskSetps.get(i).getContent()%>"></td>
						</tr>
						<%
					}
				}%>
				</tbody>
			</table>
			</div>
		</fieldset>
		<%if(groups!=null){ %>
		<fieldset>
			<legend>提交审核模板</legend>
			<div id="templateBox">
				<% int num2=0; 
				for(int i=0;i<groups.size();i++)
				{	num2++;
					if(groups.get(i).getGroupType()==1)
					{
						%>
						<div class="templateGroupText template" style="border: 3px solid #DDDDDD;margin-top: 2px;width: 40%;">
							<label class="boxno"><%=num2%>.</label><span>文本组标题:</span><input type="text" value="<%=groups.get(i).getTitle()%>" class="cltxt">	
							<div class="textGroup">
								<% 
									for(int j=0;j<groups.get(i).getTemplateList().size();j++)
									{
										%>
											<div class="textitem">
											说明文本:<input type="text" class="cltitle" value="<%=groups.get(i).getTemplateList().get(j).getTitle()%>">
<%-- 											默认值:<input type="text" class="cldefval" value="<%=groups.get(i).getTemplateList().get(j).getDefaultValue()%>"> --%>
											</div>
										<%
									}
								%>
								
							</div>
						</div>
						<%
					}
					else if (groups.get(i).getGroupType()==2)
					{
						%>
						<div class="templateGroupImg template"  style="border: 3px solid #DDDDDD;margin-top: 2px;width: 40%;">
							<label class="boxno"><%=num2%>.</label><span>图片组标题</span><input type="text" value="<%=groups.get(i).getTitle()%>"  class="climg">
							<div class="imgGroup">
							<% 
									for(int j=0;j<groups.get(i).getTemplateList().size();j++)
									{
										%>
											<div class="imgitem">图片说明:<input type="text" class="cltitle" value="<%=groups.get(i).getTemplateList().get(j).getTitle()%>"></div>
										<%
									}
								%>
							</div>
						</div>
						<%
					}
					else if (groups.get(i).getGroupType()==3)
					{
						%>		
						<div class="templateGroupMoreImg template"  style="border: 3px solid #DDDDDD;margin-top: 2px;width: 40%;">
							<label class="boxno"><%=num2%>.</label><span>多图组标题</span><input type="text" value="多图组标题" class="clmoreimg" value="<%=groups.get(i).getTitle()%>">
							<div class="imgGroup">
								<div class="imgitemnum">图片数量:<input type="text" class="imgitemnumn" value="<%=groups.get(i).getTemplateList().size()%>"></div>
							</div>
						</div>
						<%
					}
				}%>

			</div>
		</fieldset>
		<%} %>
		<fieldset>
			<legend>关联设置</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
					<input type="hidden" name="targetPeople" value="1" id="targetPeople" />
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">关联商户: </label>
								<div class="col-sm-8">
									<input type="text" value="<%=taskInfo.getPusher()%>"/>
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
							<div class="controls">
							已选择的区域:<div id="selectedregions"></div>
							<button class="btn btn-success" id="btnExpanAll" type="button">展开/折叠</button>
						</div>
					</div>
					<div class="row">
						<div id="taskregion" style="height: 300px; overflow: auto; width: 800px;">
							<%for(int i=0;i<provincelist.size();i++){%>
								<span style="cursor: pointer;" name="prospan" id="<%=provincelist.get(i).getCode() %>">+</span>
								<input id="chkTaskPro<%=provincelist.get(i).getCode() %>" name="chkTaskPro" type="checkbox" value="<%=provincelist.get(i).getCode()%>"> 
								<label><%=provincelist.get(i).getName()%></label>
								<br/>
									<div class="citydiv" style="display: none;" id="taskCity<%=provincelist.get(i).getCode() %>">
										<%List<PublicProvinceCity> cityList=provinceCityMap.get(provincelist.get(i).getCode());
										for(int j=0;j<cityList.size();j++){%>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="chkTaskCity<%=cityList.get(j).getCode()%>" parentCode="<%=provincelist.get(i).getCode() %>" name="chkTaskCity" type="checkbox" value="<%=cityList.get(j).getCode()%>"> 
											<label><%=cityList.get(j).getName()%></label>
											<%if(j!=0&&j%5==0){%>
												<br/>
											<%} %>
										<%}%>
									</div>
							<%}%>
						</div>
					</div>
				</div>
			</div>

		</fieldset>
	</form>
</div>

<script>
//展开/折叠
var expandstatus=0;
$('#btnExpanAll').on('click', function (e) {
	if(expandstatus==0){
		expandstatus=1;
		//所有的城市都展开(显示)出来
		 $(".citydiv").show();
		  $("span[name='prospan']").html("-");
      }else{
    	  expandstatus=0;
    	  $(".citydiv").hide();
    	  $("span[name='prospan']").html("+");
      }
  
});

//省份前面的加号点击事件
$("span[name='prospan']").on('click', function (e) {
    if($(this).html()=="+"){
    	$(this).html("-");
    	$("#taskCity"+$(this).attr("id")).show();
    }else{
    	$(this).html("+");
    	$("#taskCity"+$(this).attr("id")).hide();
    }
});
//省份选中时，省内的所有城市都选中
$('input:checkbox[name="chkTaskPro"]').change(function(){
	var citydivid="#taskCity"+$(this).val();
	if($(this).is(':checked')){
		$(citydivid+" input[type='checkbox']").prop("checked","checked");
	}else{
		$(citydivid+" input[type='checkbox']").removeAttr("checked");
	}
	 gettaskregionremark();
});
//某个省内的城市选中时，城市所属的省也选中
$('input:checkbox[name="chkTaskCity"]').change(function(){
	var parentid="#chkTaskPro"+$(this).attr("parentCode");
	if($(this).is(':checked')){
		$(parentid).prop("checked","checked");
	}else{
		var citydivid="#taskCity"+$(this).attr("parentCode");
		if($(citydivid+" input[type='checkbox']:checked").length==0){
			$(parentid).removeAttr("checked");
		}
	}	
	 gettaskregionremark();
});
$('input').attr('readonly','readonly');
$('[name="rTaskType"]').attr('disabled','disabled');
$('[name="rTaskTagId"]').attr('disabled','disabled');
initTaskRegion("<%=taskCityInfo%>");
$('[name="chkTaskPro"]').attr('disabled','disabled');
$('[name="chkTaskCity"]').attr('disabled','disabled');
</script>