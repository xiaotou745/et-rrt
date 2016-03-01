<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.Map"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrenentity.domain.RenRenTaskModel"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%@page import="com.renrentui.renrencore.enums.TaskType"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th >编号</th>
			<th >任务标题</th>
			<th >参与人数</th>
			<th >总完成次数</th>
			<th >投放城市</th>
			<th >创建人</th>
			<th >创建时间</th>
			<th >起止日期</th>
			<th >商家名称</th>
			<th >单次佣金</th>
			<th >任务状态</th>
			<th >任务类型</th>
			<th >操作</th>
		</tr>
	</thead>
	<tbody>
		<%
			PagedResponse<RenRenTaskModel> responsePageList = (PagedResponse<RenRenTaskModel>) request.getAttribute("listData");
			Map<Long,String> taskRegionMap = (Map<Long,String>) request.getAttribute("taskRegionMap");
			List<RenRenTaskModel> data = responsePageList.getResultList();
			if (data == null) {
				data = new ArrayList<RenRenTaskModel>();
			}
			String baseOrderPath="";
			String regionInfo="";
			String tasknameinfo="";
			String fullregioninfo="";
			String [] tempRegion=null;
			for (int i = 0; i < data.size(); i++) {
				baseOrderPath=basePath+"/taskmanage/partnerlist?taskTitle="+
						java.net.URLEncoder.encode(data.get(i).getTaskTitle(), "utf-8")+
						"&taskType="+java.net.URLEncoder.encode(TaskType.getEnum(data.get(i).getTaskType()).desc(), "utf-8")+
						"&taskId="+data.get(i).getId()+
						"&taskStatus="+java.net.URLEncoder.encode(TaskStatus.getEnum(data.get(i).getStatus()).desc(), "utf-8");
				tempRegion=taskRegionMap.get(data.get(i).getId()).split("<br/>");
				tasknameinfo=new String(Base64.encodeBase64(data.get(i).getTaskTitle().getBytes("utf-8")));  
				fullregioninfo=new String(Base64.encodeBase64(taskRegionMap.get(data.get(i).getId()).getBytes("utf-8")));
				for(int k=0;k<tempRegion.length;k++){
					if(k==0){
						regionInfo=tempRegion[k];	
					}else if(k==1){
						regionInfo+=("<br/>"+tempRegion[k]);
					}else{
						regionInfo+="<br/>...";
					  break;	
					}
				}
		%>
		<tr>
			<td><%=data.get(i).getId()%></td>
			<td><a target="_blank" class="blue2" href="<%=basePath%>/taskmanage/detail?taskId=<%=data.get(i).getId()%>"><%=ParseHelper.ShowString(data.get(i).getTaskTitle())%></a></td>
			<td>
			<%
				if(data.get(i).getPartnerNum()>0){%>
					<a href="<%=baseOrderPath%>" target="_blank"><%=data.get(i).getPartnerNum()%></a>
				<%}else{%>
					<%=data.get(i).getPartnerNum()%>
				<%}
			%>
			
			</td>
			<td><%=data.get(i).getCompleteNum()%> </td>
			<td><%=regionInfo%>
			<input type="hidden" id="hid<%=data.get(i).getId()%>" value="<%=fullregioninfo%>"></input>
			<%if(regionInfo.endsWith("...")){%>
				<a href="javascript:showregiondetail('<%=tasknameinfo %>','hid<%=data.get(i).getId()%>')">查看全部</a></td>
			<%} %>
			
			<td><%=ParseHelper.ShowString(data.get(i).getCreateName())%> </td>
			<td><%=ParseHelper.ToDateString(data.get(i).getCreateTime())%></td>
			<td><%=ParseHelper.ToDateString(data.get(i).getBeginTime(),"yyyy-MM-dd")+"/"+ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-MM-dd")%></td>
			<td><%=ParseHelper.ShowString(data.get(i).getPusher())%></td>
			<td><%=ParseHelper.digitsNum(data.get(i).getAmount(),2)%></td>
			<td><%=TaskStatus.getEnum(data.get(i).getStatus()).desc()%></td>
			<td><%=TaskType.getEnum(data.get(i).getTaskType()).desc()%></td>
			<td>
			<% if(data.get(i).getStatus()==TaskStatus.WaitAudit.value()){%>
				<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',1,0,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">审核通过</a>
				<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',2,0,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">驳回</a>
			<%} 
			if(data.get(i).getStatus()==TaskStatus.Audited.value()){%>
			<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',4,1,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">终止</a>
			<%}
		    if(data.get(i).getStatus()==TaskStatus.WaitAudit.value()||data.get(i).getStatus()==TaskStatus.Reject.value()){%>
			<a href="javascript:setTaskStatus('<%=data.get(i).getId()%>',5,<%=data.get(i).getStatus() %>,'<%=ParseHelper.ToDateString(data.get(i).getEndTime(),"yyyy-M-d") %>')">取消</a>
			<a href="<%=basePath%>/taskmanage/newtask?taskId=<%=data.get(i).getId()%>" target="_blank">修改任务</a>
			<%}	
			
			if((data.get(i).getStatus()==TaskStatus.Audited.value()
					||data.get(i).getStatus()==TaskStatus.Expired.value()
					||data.get(i).getStatus()==TaskStatus.Stop.value()
					)&&data.get(i).getTaskType()==1)
			{%>
				<a href="<%=basePath%>/taskmanage/taskexport?taskId=<%=data.get(i).getId()%>" target="_blank">导出资料数据</a>
			<%}
			%>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
					responsePageList.getCurrentPage(),
					responsePageList.getTotalRecord(),
					responsePageList.getTotalPage())%>