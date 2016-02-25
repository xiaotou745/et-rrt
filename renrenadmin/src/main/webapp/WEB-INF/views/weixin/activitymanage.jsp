<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.Activity"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>活动名称</th>
			<th>奖励金额（元）</th>
			<th>发放概率</th>
			<th>已发放数量</th>
			<th>已发放总金额（元）</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<%
		List<Activity> list = (List<Activity>)request.getAttribute("listData");
			if (list == null) {
				list = new ArrayList<Activity>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getActivityName()%></td>
			<td><%=list.get(i).getRewardMoney()%></td>
			<td><%=list.get(i).getGrantProbability()%></td>
			<% 
					if (list.get(i).getHadRewardCount()>0)
					{
			%>
			<td><a href="<%=basePath%>/weixin/activitydetail?id=<%=list.get(i).getId()%>">list.get(i).getHadRewardCount()</a></td>
			<%
				} else {
			%>
			<td><a href="javascript:void(0)"><%=list.get(i).getHadRewardCount()%></a></td>
			<%
				}
			%>
			<td><%=list.get(i).getHadRewardMoney()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getStartTime())%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getEndTime())%></td>
			<td><%=list.get(i).getStatus()==1?"进行中":"关闭中"%></td>
			 <% 
					if (list.get(i).getStatus()==1)
					{
			%>
			<td><a href="javascript:void(0)" onclick="shutupactivity(<%=list.get(i).getId()%>)">关闭</a></td>
			<%
				} else {
			%>
			<td><a href="javascript:void(0)" onclick="startupactivity(<%=list.get(i).getId()%>)">开启</a></td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</tbody>
</table>

<script>
function shutupactivity(id){
	 var url = "<%=basePath%>/weixin/shutupactivity";
     $.ajax({
         type: 'POST',
         url: url,
         data: {"id":id},
         success: function (result) {   			            
         	if(result){
         		alert("关闭成功");
         		window.location.reload(true);
         	}else{
         		alert("关闭失败");
         	}
         }
     });
     window.location.reload(true);
}
function startupactivity(id){
	var url = "<%=basePath%>/weixin/startupactivity";
    $.ajax({
        type: 'POST',
        url: url,
        data: {"id":id},
        success: function (result) {   			            
        	if(result){
        		alert("开启成功");
        		window.location.reload(true);
        	}else{
        		alert("开启失败");
        	}           
        }
    });
}
</script>