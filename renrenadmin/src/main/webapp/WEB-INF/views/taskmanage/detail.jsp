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
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%@page import="com.renrentui.renrenadmin.common.UserContext"%>
<%@page import="com.renrentui.renrenentity.domain.TaskSetp"%>
<%@page import="com.renrentui.renrenentity.domain.TemplateGroup"%>
<%@page import="com.renrentui.renrenentity.PublicProvinceCity"%>

<%
String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
RenRenTask taskInfo = (RenRenTask) request.getAttribute("taskInfo");
List<TaskSetp> taskSetps=(List<TaskSetp>) request.getAttribute("taskSetps");
List<TemplateGroup> groups=(List<TemplateGroup>) request.getAttribute("groups");

PublicProvinceCity city=request.getAttribute("pro_city")==null?null:(PublicProvinceCity) request.getAttribute("pro_city");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
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
	  							   小时
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
					<% 
					if(city==null)
					{
						%>
						<div class="col-lg-3">
								<div class="form-group">
									<label class="col-sm-4 control-label">区域: </label>
									<div class="col-sm-8">
										<input type="text" value="全部区域"/>
									</div>
								</div>
							</div>
						<%
					}
					else if(city.getJiBie()==2)
					{
						%>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">省份: </label>
								<div class="col-sm-8">
								<input type="text" value="<%=city.getName() %>"/>
								</div>
							</div>
						</div>
						<%
					}
					else
					{
					%>
					<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">城市: </label>
								<div class="col-sm-8">
									<input type="text" value="<%=city.getName() %>"/>
								</div>
							</div>
						</div>
					<%
						
					}%>
					</div>
				</div>
			</div>

		</fieldset>
	</form>
</div>

<script>
//$('input').attr('disabled','disabled');

</script>