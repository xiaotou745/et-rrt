
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrenentity.domain.ClienterWithdrawFormDM"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.enums.ClienterWithdrawFormStatus"%>

<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead> 
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="10%">用户名称</th>
			<th width="10%">电话号码</th>			
			<th width="10%">金额</th>
			<th width="10%">提现单号</th>
			<th width="10%">提现账号</th>
			<th width="10%">真实姓名</th>
			<th width="10%">创建时间</th>
			<th width="10%">状态</th>			
			<th width="10%">操作</th>							
		</tr>
	</thead>

	<tbody>
		<%
			PagedResponse<ClienterWithdrawFormDM> responsePageList = (PagedResponse<ClienterWithdrawFormDM>)request.getAttribute("listData");
			List<ClienterWithdrawFormDM> data=responsePageList.getResultList();
			for (int i = 0; i < data.size(); i++) {			
		%>
		<tr>
			<td><%=data.get(i).getId()%></td>
			<td><%=data.get(i).getClienterName()%></td>
			<td><%=data.get(i).getPhoneNo()%></td>	
			<td><%=ParseHelper.digitsNum(data.get(i).getAmount(),2)%></td>	
			<td><%=data.get(i).getWithdrawNo()%></td>	
			<td><%=data.get(i).getAccountInfo()%></td>
			<td><%=data.get(i).getTrueName()%></td>			
			<td><%=ParseHelper.ToDateString(data.get(i).getCreateTime())%></td>
			<td><%=ClienterWithdrawFormStatus.getEnum(data.get(i).getStatus()).desc()%></td>			
			<td>
			<%if(data.get(i).getStatus()==0) {%>
			<a href="javascript:void(0)"  onclick="WithdrawAuditPass('<%=data.get(i).getId() %>','<%=data.get(i).getClienterId() %>','<%=data.get(i).getAmount() %>')" >审核通过 </a>
			<a href="javascript:void(0)"  onclick="WithdrawAuditRefuse('<%=data.get(i).getId() %>')" >审核拒绝</a>			
			<%} %>			
			
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
<script type="text/javascript">
 
   //审核通过
   function WithdrawAuditPass(id,clienterId,amount) {
       if (!window.confirm("确认要提交审核？")) {
           return;
       }
       var paramaters = {
           "id": id,
           "clienterId": clienterId, 
           "amount": amount 
       };
       var url = "<%=basePath%>/clienterwithdraw/auditpass";
       $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (result) {
        	   if (result>0) {
					alert("操作成功");
					window.location.href = "<%=basePath%>/clienterwithdraw/list";
				} else {
					alert("操作失败");
				}  

              /*  if (result.IsSuccess) {
                   alert(result.Message);
                   window.location.href = "/clienterwithdraw/ClienterWithdraw";
               } else {
                   alert(result.Message);
               } */
           }
       });
   }
    
 //审核拒绝
    function WithdrawAuditRefuse(withwardId)
    {
    	  if (!window.confirm("确认要审核拒绝？")) {
              return;
          }
          var paramaters = {
              "withwardId": withwardId           
          };
          var url = "<%=basePath%>/clienterwithdraw/auditrefuse";
          $.ajax({
              type: 'POST',
              url: url,
              data: paramaters,
              success: function (result) {
           	   
        	   if (result>0) {
					alert("操作成功");
					window.location.href = "<%=basePath%>/clienterwithdraw/list";
				} else {
					alert("操作失败");
				}  
              }
          });
    }
  </script>
	

