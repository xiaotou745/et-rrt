
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.resp.ClienterResp"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.enums.ClienterStatus"%>
<%String basePath = PropertyUtils.getProperty("java.renrenadmin.url");%>
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
						<th width="%5">操作时间</th>
						<th width="%5">操作人</th>
				</tr>
			</thead>
			<tbody>                           
		<%List<ClienterResp> list = data.getResultList();
           for (int i = 0; i < list.size(); i++) {%>  
			 <tr> 
			    <td><%=i+1 %></td>
                <td><%=list.get(i).getClienterName() %></td>
                <td><%=list.get(i).getPhoneNo() %></td>
                <td><%=ParseHelper.digitsNum( list.get(i).getBalance(), 2)%></td>
                <td><%=ParseHelper.digitsNum(list.get(i).getWithdraw(), 2)%></td>
                <td><%=ParseHelper.digitsNum(list.get(i).getHadWithdraw(), 2)%></td>
                <td><%=ParseHelper.ToDateString(list.get(i).getLastOptTime())%></td>
                <td><%=list.get(i).getLastOptName() %></td>
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script>
//修改标签状态
function editClienterStatus(id,status,oldStatus){
	   var confirmMsg="";
	   if(status==1)
		   {
		   	confirmMsg="确认审核通过此地推员?";
		   }
	   else
		   {
		   	confirmMsg="确认审核拒绝此地推员?";
		   }
	   if (!confirm(confirmMsg)) {
         return;
     }
	   var paramaters = {
	    	   "userId":id,
	    	   "status":status,
	    	   "oldStatus":oldStatus
        };
   var url = "<%=basePath%>/clienter/editclienterstatus";
	   $.ajax({
        type: 'POST',
        url: url,
        data: paramaters,
        success: function (result) {   			            
     	   alert(result.message);
            if (result.responseCode > 0) {
                window.location.href = "<%=basePath%>/clienter/list";
            }               
        }
    });
}
</script>

