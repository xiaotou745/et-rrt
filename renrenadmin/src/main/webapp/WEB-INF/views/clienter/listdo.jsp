
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.resp.ClienterResp"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%String basePath = PropertyUtils.getProperty("java.admin.url");%>
<%PagedResponse<ClienterResp> data = (PagedResponse<ClienterResp>) request.getAttribute("listData");%>
<% if(data.getResultList()==null||data.getResultList().size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">编号</th>
						<th width="%5">姓名</th>
						<th width="%5">电话</th>
						<th width="%5">余额</th>
						<th width="%5">可提现</th>
						<th width="%5">已提现</th>
						<th width="%5">审核状态</th>
						<th width="%5">操作时间</th>
						<th width="%5">操作人</th>
						<th width="%5">操作</th>	
				</tr>
			</thead>
			<tbody>                           
		<%List<ClienterResp> list = data.getResultList();
           for (int i = 0; i < list.size(); i++) {%>  
			 <tr> 
			    <td><%=i+1 %></td>
                <td><%=list.get(i).getClienterName() %></td>
                <td><%=list.get(i).getPhoneNo() %></td>
                <td><%=list.get(i).getBalance() %></td>
                <td><%=list.get(i).getWithdraw() %></td>
                <td><%=list.get(i).getHadWithdraw() %></td>
                <td><%=list.get(i).getStatus() %></td>
                <td><%=ParseHelper.ToDateString(list.get(i).getLastOptTime())%></td>
                <td><%=list.get(i).getLastOptName() %></td>
                <td>
                	<%if(list.get(i).getStatus()==1){%>
					<a href="javascript:modifyMarkStatus(<%=list.get(i).getId()%>,0)">审核拒绝</a>
					<%}else{%> 
					<a href="javascript:modifyMarkStatus(<%=list.get(i).getId()%>,1)">审核通过</a>
					<%}%>
				</td>				
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script>
//标签编辑弹窗
	
	//修改标签状态
	   function modifyMarkStatus(id,isEnable){
		   var confirmMsg="";
		   if(isEnable==0)
			   {
			   	confirmMsg="确认禁止此标签?";
			   }
		   else
			   {
			   	confirmMsg="确认启动此标签?";
			   }
		   if (!confirm(confirmMsg)) {
	            return;
	        }
		   var paramaters = {
		    	   "id":id,
		    	   "isenable":isEnable,
	           };
	      var url = "<%=basePath%>/mark/modifyMarkStatus";
		   $.ajax({
	           type: 'POST',
	           url: url,
	           data: paramaters,
	           success: function (result) {   			            
	        	   alert(result.message);
	               if (result.responseCode > 0) {
	                   window.location.href = "<%=basePath%>/mark/list";
	               }               
	           }
	       });
	   }


</script>

