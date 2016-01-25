
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrenentity.domain.OrderAudit"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.enums.TaskStatus"%>
<%@page import="com.renrentui.renrencore.util.*"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%10">资料编号</th>
			<th >地推员</th>
			<th >公司名称</th>
			<th >任务名称</th>
			<th >任务状态</th>
			<th>该地推员<br/>完成次数</th>
			<th >总佣金/次</th>
			<th >地推员佣金/次</th>
			<th >上级累计分佣</th>
			<th >盈亏</th>
			<th >提交时间</th>
			<th >终审时间</th>
			<th >审核状态</th>
			<th >操作</th>		
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<OrderAudit> responsePageList = (PagedResponse<OrderAudit>)request.getAttribute("listData");
			List<OrderAudit> data=responsePageList.getResultList();
			String datumString="";
			int endIndex=0;
			String fix="";
			for (int i = 0; i < data.size(); i++) {		
				datumString=data.get(i).getAuditStatus();
				if(data.get(i).getRefuReason()!=null&&!data.get(i).getRefuReason().isEmpty()){
					endIndex=data.get(i).getRefuReason().length();
					fix="";
					if(endIndex>40){
						endIndex=40;
						fix="...";
					}
					datumString=datumString+":"+data.get(i).getRefuReason().substring(0, endIndex)+fix;
				}
				datumString=new String(Base64.encodeBase64(datumString.getBytes("UTF-8")));  
		%>
		<tr>
			<td><%=data.get(i).getId()%>
			</td>
			<td><%=data.get(i).getPhoneNo()%>,<%=data.get(i).getClienterName()%></td>	
			<td><%=data.get(i).getPusher()%></td>
			<td><%=data.get(i).getTaskTitle()%></td>	
			<td><%=data.get(i).getTaskStatus()%></td>	
			<td><%=data.get(i).getCompleteNum()%></td>
			<td><%=ParseHelper.digitsNum(data.get(i).getTotalAmount(), 2)%></td>	
			<td><%=data.get(i).getAmountStr()%></td>
			<td>
			<%if(data.get(i).getAuditStatusCode()==2) 
			{
				%>
				<a href="javascript:void(0)"onMouseOver="javascript:Myshow(this,<%=data.get(i).getId()%>);" onMouseOut="Myhide(this)"><%=ParseHelper.digitsNum(data.get(i).getSubCommisson(), 2)%></a>
				<%
			}else
			{%>
				<%=ParseHelper.digitsNum(data.get(i).getSubCommisson(), 2)%>
				<%
			}%>
			
			</td>
			<td><%
			if(data.get(i).getProfit()>0)
			{%>
			<span style="color:red;">
			<%}
			else
			{%>
				<span style="color:green;">
			<%}
			%><%=ParseHelper.digitsNum(data.get(i).getProfitAmount(), 2)%></span></td>
			<td><%=data.get(i).getFinishTime()%></td>	
			<td><%=data.get(i).getAuditTime()%></td>	
			<td><%=data.get(i).getAuditStatus()%></td>
			<td>
			<%if(data.get(i).getAuditStatusCode()==1) 
			{%>
			<a  href="javascript:void(0)"  onclick="Audit(<%=data.get(i).getId()%>,2,<%=data.get(i).getClienterId()%>,<%=data.get(i).getAmount()%>,<%=data.get(i).getId()%>,'<%=data.get(i).getTaskTitle()%>')">审核通过</a>
		    <a  href="javascript:void(0)"  onclick="AuditRe(<%=data.get(i).getId()%>,3,<%=data.get(i).getClienterId()%>,<%=data.get(i).getAmount()%>,<%=data.get(i).getId()%>,'<%=data.get(i).getTaskTitle()%>')">审核拒绝</a>
			<%	
			}
			%>
			<a href="javascript:void(0)"  onclick="ShowInfo(<%=data.get(i).getClienterId()%>,<%=data.get(i).getTaskId()%>,<%=data.get(i).getId()%>,'<%=data.get(i).getClienterName()%>','<%=datumString%>')">查看</a>
			<a href="javascript:void(0)"  onclick="saveFile(<%=data.get(i).getClienterId()%>,<%=data.get(i).getTaskId()%>,<%=data.get(i).getId()%>,'<%=data.get(i).getClienterName()%>')">下载</a>
			</td>						
		</tr>
		<%
			}
		%>

	</tbody>
</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>
	