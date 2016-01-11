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
<%@page import="com.renrentui.renrenentity.domain.TaskSetp"%>
<%@page import="com.renrentui.renrenentity.domain.TemplateGroup"%>
<%@page import="com.renrentui.renrenentity.RenRenTask"%>
<%@page import="com.renrentui.renrenentity.StrategyChild"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%
String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
List<PublicProvinceCity> provincelist = (List<PublicProvinceCity>) request.getAttribute("provincelist");//省份
String pro_city = (String) request.getAttribute("pro_city");//城市字符串
Long taskID=request.getAttribute("taskID")==null?0:(Long)request.getAttribute("taskID");
RenRenTask taskInfo =request.getAttribute("taskInfo")==null?null:(RenRenTask)request.getAttribute("taskInfo");
List<TaskSetp> taskSetps=request.getAttribute("taskSetps")==null?null:(List<TaskSetp>)request.getAttribute("taskSetps");
List<TemplateGroup> groups=request.getAttribute("groups")==null?null:(List<TemplateGroup>)request.getAttribute("groups");
List<StrategyChild> chiList=request.getAttribute("childs")==null?null:(List<StrategyChild>)request.getAttribute("childs");
%>
<script>
var imgPath="<%=basePath%>/img/11235.png";
</script>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
 <script src="<%=basePath%>/js/renrentask.js"></script> 
<script src="<%=basePath%>/js/renrentemplate.js"></script>

<div class="wrapper wrapper-content animated fadeInRight">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<input type="hidden" id="hdtaskid" value="<%=taskID%>"/>
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
										id="taskTitle" value="<%=taskInfo==null?"":taskInfo.getTaskTitle() %>"/>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务描述: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskGeneralInfo" id="taskGeneralInfo" value="<%=taskInfo==null?"":taskInfo.getTaskGeneralInfo() %>"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">审核周期: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="auditCycle" id="auditCycle" value="<%=taskInfo==null?"":taskInfo.getAuditCycle()%>"/>
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
									<input type="text" class="form-control" name="amount" id="amount" value="<%=taskInfo==null?"":taskInfo.getAmount()%>"/>
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
									<input type="text" class="form-control" name="totalAmount" id="totalAmount" value="<%=taskInfo==null?"":taskInfo.getTotalAmount() %>"/>
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
									<input type="text" class="form-control" name="estimatedTime" id="estimatedTime" value="<%=taskInfo==null?"":taskInfo.getEstimatedTime() %>"/>
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
										<input type="text" class="form-control"  name="beginDate" id="beginDate" value="<%=taskInfo==null?"":ParseHelper.ToDateString(taskInfo.getBeginTime(), "yyyy-MM-dd") %>"/>
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
										<input type="text"	class="form-control" name="endDate" id="endDate" value="<%=taskInfo==null?"":ParseHelper.ToDateString(taskInfo.getEndTime(), "yyyy-MM-dd") %>"/>
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
									<input type="text" class="form-control" name="hotline" id="hotline"  maxlength="12" value="<%=taskInfo==null?"":taskInfo.getHotLine()%>"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">任务类型: </label>
								<div class="col-sm-8">
									<input id="rTaskType1" name="rTaskType" type="radio" value="1"  <%=taskInfo==null?"checked":(taskInfo.getTaskType()==1?"checked" : "")%>> 
									<label>签约任务</label>
									<input id="rTaskType2" name="rTaskType" type="radio" value="2" <%=taskInfo==null?"":(taskInfo.getTaskType()==2?"checked" : "")%>> 
									<label>分享任务</label>
									<input id="rTaskType3" name="rTaskType" type="radio" value="3" <%=taskInfo==null?"":(taskInfo.getTaskType()==3?"checked" : "")%>> 
									<label>下载任务</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row" id="tasktype2">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">下载地址: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="downUrl" id="downUrl" value="<%=taskInfo==null?"":taskInfo.getDownUrl()%>"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">扫码说明: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="scanTip" id="scanTip" value="<%=taskInfo==null?"":taskInfo.getScanTip()%>"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row" id="tasktype3">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">温馨提示: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="reminder" id="reminder" value="<%=taskInfo==null?"":taskInfo.getReminder()%>"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		
		<%if(taskID==0)
		{
			 %>
			 <fieldset>
			<legend>任务流程</legend>
			<span style="color:red;">*</span><span>完成步骤（请详细说明完成任务需要哪几个步骤，方便地推员按要求完成任务）</span>
			<a href="javascript:void(0);" class="fl add" id="setpadd">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel">删除</a> 
			<div class="orderBox dn" id="setpbox">
				<div class="copy">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">步骤1: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" maxlength="999" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr align="center" width="50%" style="color:#333333;">
			
			
			
			
			
			<span>补充说明（如果任务有特殊说明，或者注意事项，可在此处进行补充）</span>
			<a href="javascript:void(0);" class="fl add" id="setpadd2">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel2">删除</a> 
			<div class="orderBox dn" id="setpbox2">
				<div class="copy2">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">1、 </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" maxlength="999"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr align="center" width="50%" style="color:#333333;">
			
			
			
			<span>细则（添加超链接，打开新页面）</span>
			<a href="javascript:void(0);" class="fl add" id="setpadd3">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel3">删除</a> 
			<a target="_blank" href="<%=basePath%>/article/new" >新建页面</a>
			<div class="orderBox dn" id="setpbox3">
			<table class="table table-striped table-bordered table-hover dataTables-example" style="width:40%;">
				<thead>
					<tr><th>序号</th><th>链接文字</th><th>链接地址</th><th>操作</th></tr>
				</thead>
				<tbody id="setpbox3tbody">
					<tr class="copy3">
					<td><label>1</label></td>
					<td><input type="text"  style="width:200px;" class="eltitle"></td>
					<td><input type="text"  style="width:200px;" class="elurl" onBlur="HttpC(this)"></td>
					<td><a href="javascript:void(0)" onclick="chooseArticle(this)">选择文章</a></td>
					</tr>
				</tbody>
			</table>
			</div>
		</fieldset>		
			 <%
		}
		else{
			 %>
			 
			<fieldset>
			<legend>任务流程</legend>
			<span style="color:red;">*</span><span>完成步骤（请详细说明完成任务需要哪几个步骤，方便地推员按要求完成任务）</span>
			<a href="javascript:void(0);" class="fl add" id="setpadd">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel">删除</a> 
			
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
											<input type="text" maxlength="999" class="form-control" value="<%=taskSetps.get(i).getContent()%>"/>
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
			<a href="javascript:void(0);" class="fl add" id="setpadd2">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel2">删除</a> 
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
											<input type="text"  maxlength="999" class="form-control" value="<%=taskSetps.get(i).getContent()%>" />
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
			<a href="javascript:void(0);" class="fl add" id="setpadd3">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel3">删除</a>
			<a target="_blank" href="<%=basePath%>/article/new" >新建页面</a> 
			<div class="orderBox dn" id="setpbox3" >
			<table class="table table-striped table-bordered table-hover dataTables-example" style="width:40%;">
				<thead>
					<tr><th>序号</th><th>链接文字</th><th>链接地址</th><th>操作</th></tr>
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
						<td><input type="text"  style="width:200px;" class="elurl" value="<%=taskSetps.get(i).getContent()%>" onBlur="HttpC(this)"></td>
						<td><a href="javascript:void(0)" onclick="chooseArticle(this)">选择文章</a></td>
						</tr>
						<%
					}
				}%>
				</tbody>
			</table>
			</div>
		</fieldset>
			 <% 
		}%>
		
		<%if(taskID==0)
		{
			%>
			<fieldset id="mubanbox">
			<legend>提交审核模板</legend>
			<div id="templateBox">
				<div class="templateGroupText template" style="border: 3px solid #DDDDDD;margin-top: 2px;width: 40%;">
					<label class="boxno">1.</label><span>文本组标题:</span><input type="text" value="" class="cltxt" maxlength="30" >	
					<a href="javascript:void(0);" onclick="addTxtControl(this)">添加文本控件</a>
					<a href="javascript:void(0);" onclick="delTxtControl(this)" >删除文本控件</a> 
					<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该文本组</a>
					<div class="textGroup">
						<div class="textitem">说明文本:<input type="text" class="cltitle" maxlength="30" >
<!-- 						默认值:<input type="text" class="cldefval" maxlength="30" > -->
						</div>
					</div>
				</div>
				<div class="templateGroupImg template"  style="border: 3px solid #DDDDDD;margin-top: 8px;width: 40%;">
					<label class="boxno">2.</label><span>图片组标题</span><input type="text" value="" class="climg" maxlength="30" >
					<a href="javascript:void(0);" onclick="addImgControl(this)" >添加图片控件</a>
					<a href="javascript:void(0);" onclick="delImgControl(this)" >删除图片控件</a>
					<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该图片组</a>
					<div class="imgGroup">
						<div class="imgitem">图片说明:<input type="text" class="cltitle" maxlength="30" ></div>
					</div>
				</div>
				<div class="templateGroupMoreImg template"  style="border: 3px solid #DDDDDD;margin-top: 8px;width: 40%;">
					<label class="boxno">3.</label><span>多图组标题</span><input type="text" value="" class="clmoreimg" maxlength="30" >
					<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该多图组</a>
					<div class="imgGroup">
						<div class="imgitemnum">图片数量:<input type="text" class="imgitemnumn"></div>
					</div>
				</div>
			</div>
			<a href="javascript:void(0);"  id="addtxtgroup">添加文本组</a>
			<a href="javascript:void(0);"  id="addimggroup">添加图片组</a>
			<a href="javascript:void(0);"  id="addmoreimggroup">添加多图组</a>
			<a href="javascript:void(0);"  id="yulan">预览模板</a>
		</fieldset>
			<%
		}
		else{
			if(taskInfo.getTaskType()==1){
			%>
			
			<fieldset id="mubanbox">
			<legend>提交审核模板</legend>
			<div id="templateBox">
				<% int num2=0; 
				for(int i=0;i<groups.size();i++)
				{	num2++;
					if(groups.get(i).getGroupType()==1)
					{
						%>
						<div class="templateGroupText template" style="border: 3px solid #DDDDDD;margin-top: 2px;width: 40%;">
							<label class="boxno"><%=num2%>.</label><span>文本组标题:</span><input type="text" value="<%=groups.get(i).getTitle()%>" class="cltxt" maxlength="30" >
							<a href="javascript:void(0);" onclick="addTxtControl(this)">添加文本控件</a>
							<a href="javascript:void(0);" onclick="delTxtControl(this)" >删除文本控件</a> 
							<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该文本组</a>	
							<div class="textGroup">
								<% 
									for(int j=0;j<groups.get(i).getTemplateList().size();j++)
									{
										%>
											<div class="textitem">
											说明文本:<input type="text" class="cltitle" value="<%=groups.get(i).getTemplateList().get(j).getTitle()%>" maxlength="30" >
<%-- 											默认值:<input type="text" class="cldefval" value="<%=groups.get(i).getTemplateList().get(j).getDefaultValue()%>" maxlength="30" > --%>
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
							<label class="boxno"><%=num2%>.</label><span>图片组标题</span><input type="text" value="<%=groups.get(i).getTitle()%>"  class="climg" maxlength="30" >
							<a href="javascript:void(0);" onclick="addImgControl(this)" >添加图片控件</a>
							<a href="javascript:void(0);" onclick="delImgControl(this)" >删除图片控件</a>
							<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该图片组</a>
							<div class="imgGroup">
							<% 
									for(int j=0;j<groups.get(i).getTemplateList().size();j++)
									{
										%>
											<div class="imgitem">图片说明:<input type="text"  maxlength="30" class="cltitle" value="<%=groups.get(i).getTemplateList().get(j).getTitle()%>"></div>
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
							<label class="boxno"><%=num2%>.</label><span>多图组标题</span><input type="text" value="多图组标题"  maxlength="30" class="clmoreimg" value="<%=groups.get(i).getTitle()%>">
							<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该多图组</a>
							<div class="imgGroup">
								<div class="imgitemnum">图片数量:<input type="text" class="imgitemnumn" value="<%=groups.get(i).getTemplateList().size()%>"></div>
							</div>
						</div>
						<%
					}
				}%>

			</div>
			<a href="javascript:void(0);"  id="addtxtgroup">添加文本组</a>
			<a href="javascript:void(0);"  id="addimggroup">添加图片组</a>
			<a href="javascript:void(0);"  id="addmoreimggroup">添加多图组</a>
			<a href="javascript:void(0);"  id="yulan">预览模板</a>
		</fieldset>
			<%
			}}%>
		
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
									<%=HtmlHelper.getSelect("businessId", businessData, "companyName", "id", null,null, "全部")%>
								</div>
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
					</div>
				</div>
			</div>
		</fieldset>
		<div class="row">
			<div class="col-lg-4">
				<button type="button" class="btn btn-w-m btn-primary" id="save" onclick="savetask()"
					style="margin-left: 3px; height: 30px;">保存</button>
				<button type="button" class="btn btn-w-m btn-primary" id="jisuanqi"
					style="margin-left: 3px; height: 30px;">分佣计算</button>
			</div>
		</div>
	</form> 
	<input type="hidden" id="pro_city" value="<%=pro_city %>" /> 
</div>
<div tabindex="-1" class="modal inmodal" id="alertbox" role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<input type="text" placeholder="文章标题" class="form-control" id="arttitle" style="width: 100px;"/>
				<input type="text" placeholder="文章编号" class="form-control" id="artid" style="float: left;margin-top: -34px;margin-left: 104px;width: 100px;" />
				<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: -14px;margin-top: -50px;" onclick="jss.search(1)">查询</button>				
			</div>
			<small class="font-bold">
				<div class="modal-body" id="articleBody">
				
				分页列表
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">确定</button>
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>	
</div>
<div tabindex="-1" class="modal inmodal" id="yulanbox" role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h1>预览模板</h1>		
			</div>
			<small class="font-bold">
				<div class="modal-body" id="aaabody">
				
					
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>	
</div>
<!-- 开始 --------------------------------------------------------------------------------------------------------------------->
	<div tabindex="-1" class="modal inmodal" id="jisuanqiBox" role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<h5 class="modal-title" >分佣计算器</h5>				
			</div>
			<small class="font-bold">
				<div class="modal-body">
					 <div class="ibox-content">
                            <form class="form-horizontal">
                                <div class="form-group" >
                                	<label class="col-lg-3 control-label ">地推员佣金</label>
									<div class="col-lg-6">
										<input type="佣金" placeholder="金额" class="form-control brokerage">
                                    </div>
                                    <label class="col-lg-0 control-label">元</label>
									<button class="btn btn-sm btn-white control-label total" type="button">计算</button>
                                </div>
                            </form>
                            
                            <table class="table">
	                            <thead>
	                            <tr>
	                                <th>级别</th>
	                                <th>分佣比例</th>
	                                <th>可获得分红</th>
	                            </tr>
	                            </thead>
	                            <tbody id="fenyong">
	                            
	                            </tbody>
	                            
	                            <!----------------------------------- 这是要clone复制的对象 级别列表 ----------------------------------------------->
	                            <tbody id="copy" style="display:none;" >
	                            	<tr>
		                                <td></td>
		                                <td></td>
		                                <td></td>
		                           	 </tr>
	                            </tbody>
	                            <!----------------------------------- 这是要clone复制的对象 ----------------------------------------------->
	                        </table>
                        
                        </div>
						
				
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>	
</div>
	<!-- 结束 -------------------------------------------------------------------------------------------------- -->
<script>

//任务类型切换事件
$('input:radio[name="rTaskType"]').change(function(){
		var id=$('input[name="rTaskType"]:checked').val();
		if(id==1){
			$('#tasktype2').hide();
			$('#tasktype3').hide();
			$('#mubanbox').show();
		}
		else{
			$('#tasktype2').show();
			$('#tasktype3').show();
			$('#mubanbox').hide();
		}	
});
<%if(taskInfo==null){
	%>
	$('input:radio[name="rTaskType"]').change();
	<%}
else{
	%>
	$('input:radio[name="rTaskType"]').attr('disabled','disabled');
	$("#businessId").val(<%=taskInfo.getBusinessId()%>);
	var id=$('input[name="rTaskType"]:checked').val();
	if(id==1){
		$('#tasktype2').hide();
		$('#tasktype3').hide();
		$('#mubanbox').show();
	}else{
		$('#tasktype2').show();
		$('#tasktype3').show();
		$('#mubanbox').hide();
	}	
	<%
}
%>
//失去焦点
function HttpC(obj){
	var str=$(obj).val();
	if(str.indexOf('http://')<0)
	{
		str="http://"+str;
	}
	$(obj).val(str);
}
//省市联动
$('#provinceCode').on('change',function(){
	 try{  
	        var pro=$(this).val();  
	        var pro_city=$("#pro_city").val().split("#");
	        
	        var i,j,tmpprocity=new Array();  
	        var tmpkeyvalue=new Array();  
	        for(i=0;i<pro_city.length;i++){
	        	tmpcity=pro_city[i].split("=");
	            if(pro==tmpcity[0]){  
	                tmpcity=tmpcity[1].split(";");  
	                $("#cityCode").html("<option value='-1'>全部城市</option>");  
	                for(j=0;j<tmpcity.length;j++){  
	                	tmpkeyvalue=tmpcity[j].split("|");
	                    $("#cityCode").append("<option value='"+tmpkeyvalue[0]+"'>"+tmpkeyvalue[1]+"</option>");     
	                }
	                break;
	            }  
	        }
	        $("#divregion").html(""); 
	        $("#selectAll").prop("checked",false);
	    }catch(e){  
	        alert(e);     
	    }  
});

   
var article="";//选择文章对象
var jss={
		search:function(currentPage){
			var url="<%=basePath%>/article/listdofortask";
			var par={currentPage:currentPage,
					id:$('#artid').val(),
					title:$('#arttitle').val(),
					type:2,
					pageSize:5,
					m:Math.random()}
			$.post(url,par,function(d){
				$("#articleBody").html(d);
			});
		}
	}
  $(document).ready(function() {
	  //初始化时间控件
	  $(' .input-group.date').datepicker({
	        todayBtn: "linked",
	        keyboardNavigation: false,
	        forceParse: false,
	        calendarWeeks: true,
	        autoclose: true
	    });
		//添加步骤控件行
		$("#setpadd").click(function() {
			//var clone = add.clone();
			$('#setpbox').append(add);
			for(index=0;index<$('.copy').length;index++)
            {
            	$('.copy').eq(index).find('label').html('步骤' +  (index+1));
            }
		});
		//删除步骤
		$('#setpdel').click(function() {
			var len=$('.copy').length;
			if(len==1){
				alert("最少保留一个步骤");
				return;
			}
			var ld=$("#setpbox .copy:last");
			ld.remove(); 
		});
		//添加补充说明
		$("#setpadd2").click(function() {
			//var clone = add2.clone();
			$('#setpbox2').append(add2);
			for(index=0;index<$('.copy2').length;index++)
            {
            	$('.copy2').eq(index).find('label').html((index+1)+'、');
            }
		});
		//删除补充说明
		$('#setpdel2').click(function() {
			var len=$('.copy2').length;
			var ld=$("#setpbox2 .copy2:last");
			ld.remove(); 
		});
		//添加细则
		$("#setpadd3").click(function() {
			$('#setpbox3 tbody').append(add3);
			for(index=0;index<$('.copy3').length;index++)
            {
				
            	$('.copy3').eq(index).find('td label').html((index+1));
            }
		});
		//删除细则
		$('#setpdel3').click(function() {
			var len=$('.copy3').length;
			if(len==0)
				{
				alert('没有可以删除的细则了!');
				return;
				}
			var ld=$("#setpbox3 tr:last");
			ld.remove(); 
		});
		//添加文本组
		$('#addtxtgroup').click(function(){
			//var clone=txtgroup.clone()
			$('#templateBox').append(txtgroup);
			orderByGroup();
		});
		//添加图片组
		$('#addimggroup').click(function(){
			//var clone=imggroup.clone()
			$('#templateBox').append(imggroup);
			orderByGroup();
		});
		//添加多图组
		$('#addmoreimggroup').click(function(){
			//var clone=moreimggroup.clone()
			$('#templateBox').append(moreimggroup);
			orderByGroup();
		});
	    
	});
  //添加文本控件
  function addTxtControl(obj){
	  var div=$(obj).parent().find('.textGroup');
	  //console.log(div);
	  var item='<div class="textitem">说明文本:<input type="text" class="cltitle"  maxlength="30" >';
	  div.append(item);
	  orderByGroup()
  }
  //删除文本控件
  function delTxtControl(obj){
	  var div=$(obj).parent().find('.textGroup div:last');
	  var len=$(obj).parent().find('.textGroup div').length;
	  if(len==1){
		  alert('控件组最少保留一个控件!');
		  return;
		}
	  div.remove();
  }
  //添加图片控件
  function addImgControl(obj){
	  var div=$(obj).parent().find('.imgGroup');
	  //console.log(div);
	  var item='<div class="imgitem">图片说明:<input type="text" class="cltitle"  maxlength="30" ></div>';
	  div.append(item);
  }
  //删除图片控件
  function delImgControl(obj){
	  var div=$(obj).parent().find('.imgGroup div:last');
	  var len=$(obj).parent().find('.imgGroup div').length;
	  if(len==1){
		  alert('控件组最少保留一个控件!');
		  return;
		}
	  div.remove();
  }
  //删除控件组
  function delThisGroup(obj){
	  if($('.boxno').length==1)
		{
		 alert('最少保留一个控件组!');
		 return;
		}
	  $(obj).parent().remove();
	  orderByGroup();
  }
  //重新排序各组序号
  function orderByGroup(){
	  $('.boxno').each(function(index,el){
		  $(el).html(index+1+'.');
	  });
  }
  //选择文章
  function chooseArticle(obj){
	  jss.search(1);
	  article=obj;
	  $('#alertbox').modal('show');
  }
  
function initFunction(){
	 
	$("#businessId").on("change",businessChange);
	$("#businessId").change();
}

//构建任务对象参数
function createTaskPar(){
	var task=new Object();
	task.id=$('#hdtaskid').val();
	task.taskTitle=$('#taskTitle').val();
	task.taskGeneralInfo=$('#taskGeneralInfo').val();
	task.businessId=$('#businessId').val();
	task.pusher=$('#businessId').find("option:selected").text();
	task.beginTime=$('#beginDate').val()+" 00:00:00";
	task.endTime=$('#endDate').val()+" 23:59:59";
	task.amount=$('#amount').val();
	task.status=0;
	task.auditCycle=$('#auditCycle').val();
	task.hotLine=$('#hotline').val();
	task.taskType=$('input[name="rTaskType"]:checked').val();
	task.downUrl=$('#downUrl').val();
	task.scanTip=$('#scanTip').val();
	task.reminder=$('#reminder').val();
	task.totalAmount=$('#totalAmount').val();
	task.estimatedTime=$('#estimatedTime').val();
	return task;
}
//创建任务步骤表数据
function createTaskSetpPar(){
	var septsArr=new Array();
	//遍历步骤
   	$('#setpbox input').each(function(id,el){
   		var sept=new Object();
   		sept.linkTitle="";
   		sept.sortNo=id+1;
   		sept.setpType=1;
   		sept.content=$(el).val();
   		septsArr.push(sept);
   	});
	//遍历补充说明
	$('#setpbox2 input').each(function(id,el){
   		var sept=new Object();
   		sept.linkTitle="";
   		sept.sortNo=id+1;
   		sept.setpType=2;
   		sept.content=$(el).val();
   		septsArr.push(sept);
   	});
	//遍历细则
	$('#setpbox3 tbody tr').each(function(id,el){
   		var sept=new Object();
   		sept.linkTitle=$(el).find('.eltitle').val();
   		sept.sortNo=id+1;
   		sept.setpType=3;
   		sept.content=$(el).find('.elurl').val();
   		septsArr.push(sept);
   	});
	 return septsArr;
}
//创建分组参数
function createGroupPar(){
	var groupArr=new Array();
	//遍历控件组
	$('#templateBox .template').each(function(id,el){
		var TemplateGroup=new Object;
		if($(el).attr("class").indexOf("templateGroupText")>=0){
			//文本组
			TemplateGroup.groupType=1;//组类型
			TemplateGroup.title=$(el).find('.cltxt').val();//图片组说明
			var detailArr=new Array();
			//遍历组里面的控件
			$(el).find('.textGroup .textitem').each(function(id2,el2){
				var detail=new Object();
				detail.title=$(el2).find('.cltitle').val();//文本标题
				detail.name="key"+id+id2;//key
				detail.orderNum=id2+1;//排序
				detail.controlId=1;//控件类型
				detail.defaultValue='';//$(el2).find('.cldefval').val();//默认是
				detail.controlData='';
				detailArr.push(detail);//添加到数组中
			});
			//console.log(detailArr);
			TemplateGroup.templateList=detailArr;//数组为group的一个对象
		}
		else if($(el).attr("class").indexOf("templateGroupImg")>=0){
			//图片组
			TemplateGroup.groupType=2;
			TemplateGroup.title=$(el).find('.climg').val();
			var detailArr=new Array();
			//遍历控件
			$(el).find('.imgGroup .imgitem').each(function(id2,el2){
				var detail=new Object();
				detail.title=$(el2).find('.cltitle').val();
				detail.name="key"+id+id2;
				detail.orderNum=id2+1;
				detail.controlId=3;
				detail.defaultValue="";
				detail.controlData='';
				detailArr.push(detail);
			});
			//console.log(detailArr);
			TemplateGroup.templateList=detailArr;
		}
		else{
			//多图组
			TemplateGroup.groupType=3;
			TemplateGroup.title=$(el).find('.clmoreimg').val();
			var num=$(el).find('.imgitemnumn').val();
			if(Number(num)>0){
				num=Number(num);
				}else{
					num=0;
				}
			console.log(num);
			var detailArr=new Array();
			for(var nx=0;nx<num;nx++){
				var detail=new Object();
				detail.title="";
				detail.name="key"+id+nx;
				detail.orderNum=nx+1;
				detail.controlId=3;
				detail.defaultValue="";
				detail.controlData='';
				detailArr.push(detail);
			}
			TemplateGroup.templateList=detailArr;
		}
		groupArr.push(TemplateGroup);
	});
	return groupArr;
}
//保存任务
function savetask(){
	//控件验证
	var bl=CheckSave();
	if(!bl){
		return;
	}
    var saveTaskReq=new Object();
    saveTaskReq.renRenTask=createTaskPar();
    saveTaskReq.taskSetps=createTaskSetpPar();
    saveTaskReq.templateGroup=createGroupPar();
    saveTaskReq.provinceCode=$('#provinceCode').val();
    saveTaskReq.cityCode=$('#cityCode').val();
    var json_data =JSON.stringify(saveTaskReq);
	//console.log(json_data);
		var url = "<%=basePath%>/taskmanage/savetask";
		$.ajax({
					type : 'POST',
					url : url,
					data : {"data":json_data},
					success : function(result) {
						if (result > 0) {
							alert("操作成功");
							window.location.href = window.location.href;
						} else if(result==0){
							alert("操作失败");
						}else{
							alert("操作失败:下载链接无法访问，请修改后重试");
						}
					}
		});
}
//预览模板
$('#yulan').click(function(){
	var templateGroup=createGroupPar();
	var html= getMuban(templateGroup);
	$('#aaabody').html(html);
	console.log(templateGroup);
	$('#yulanbox').modal('show');
});
//点击计算器
$('#jisuanqi').click(function(){
	$(".total").unbind("click");
	$('#fenyong').html('');
	 var arr = new Array();
	 arr=[];
	 <%
	 if(chiList!=null)
	 {
		 for(int i=0;i<chiList.size();i++)
		 {%>
		 arr.push({"levalNo":"<%=chiList.get(i).getLevalNo()%>级","percentage":"<%=chiList.get(i).getPercentage()%>%"});	 
		<%}
	 }
	 %>
	 for(var i=0;i<arr.length;i++){
		 var levalNo = arr[i].levalNo;
		 var percentage = arr[i].percentage;
		 var obj = $("#copy").clone(true).show();
		 obj.find("tr td").eq(0).html(levalNo);
		 obj.find("tr td").eq(1).html(percentage);
		 $("#fenyong").append(obj.html());
	 }
	  $(".total").click(function() {
		  	var brokerage = $(".brokerage").val();//获取佣金
		  	if(brokerage==""){ 
		  		alert("请填写佣金！");
		  		return false;
		  	}
	  		if(isNaN(brokerage)){
				alert("佣金必须是数字！");
				return false;
			}
	  		$("#fenyong td:nth-child(2)").each(function(i){
				var  scale= parseFloat($(this).text())*0.01; //获取比例
				var  CenCommission = (brokerage * scale).toFixed(2)+"元";  //分佣
				$(this).next().html(CenCommission);
			});
			
		});
	  $('#jisuanqiBox').modal('show');
});
</script>