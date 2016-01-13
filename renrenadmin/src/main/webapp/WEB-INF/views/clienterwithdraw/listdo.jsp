
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
			<th width="%5">全选<input type="checkbox" id="checkPayAll" onclick="checkAll(this.checked)" /></th>
			<th width="%5">编号</th>
			<th width="10%">用户名称</th>
			<th width="10%">电话号码</th>			
			<th width="7%">申请提现金额</th>
			<th width="7%">地推员需支付手续费</th>
			<th width="7%">实际支付手续费</th>
			<th width="7%">实际提现金额</th>
			<th width="10%">提现单号</th>
			<th width="7%">提现账号</th>
			<th width="7%">真实姓名</th>
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
		<td>
		<%if(data.get(i).getStatus()==1) {%>
			<input type="checkbox" name="checkPay" value="<%=data.get(i).getId() %>" />
		<%} else {%>
			<input type="checkbox" name="checkPay" value="<%=data.get(i).getId() %>" disabled="disabled"/>
		<%} %>
		</td>
			<td><%=data.get(i).getId()%></td>
			<td><%=data.get(i).getClienterName()%></td>
			<td><%=data.get(i).getPhoneNo()%></td>	
			<td><%=data.get(i).getAmountString()%></td>
			<td><%=data.get(i).getHandCharge()%></td>
			<td><%=data.get(i).getActualHandCharge()%></td>
			<td><%=data.get(i).getActualAmount() %></td>
			<td><%=data.get(i).getWithdrawNo()%></td>	
			<td><%=data.get(i).getAccountInfo()%></td>
			<td><%=data.get(i).getTrueName()%></td>			
			<td><%=ParseHelper.ToDateString(data.get(i).getCreateTime())%></td>
			<td><%=ClienterWithdrawFormStatus.getEnum(data.get(i).getStatus()).desc()%></td>			
			<td>
			<%if(data.get(i).getStatus()==0) {%>
			<a href="javascript:void(0)"  onclick="WithdrawAuditPass('<%=data.get(i).getId() %>','<%=data.get(i).getClienterId() %>','<%=data.get(i).getAmount() %>')" >审核通过 </a>
			<a href="javascript:void(0)"  onclick="WithdrawAuditRefuse('<%=data.get(i).getId() %>',<%=data.get(i).getAmount() %>)" >审核拒绝</a>			
			<%} %>			
			<%if(data.get(i).getStatus()==1) {%>
			<a href="javascript:void(0)"  onclick="funPayOK('<%=data.get(i).getId() %>',<%=data.get(i).getAmount() %>)" >确认打款</a>
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
           }
       });
   }
    
 //审核拒绝
    function WithdrawAuditRefuse(withwardId,amount) {
	 	$("#lblRefusePayMoney").html(amount);
	 	$("#txtHideWithdrawId").val(withwardId);
	 	$("#refuseRemarkDiv").modal('show'); 
    }
 function refuseConfirm(){
	 if (!window.confirm("确认要审核拒绝？")) {
         return;
     } 
	 var refuseRemrak = $("#refuseRemrakTxt").val().trim();
	 if(refuseRemrak.length<0){
		 alert("拒绝原因不能为空");
		 return;
	 }
	 
	 if(refuseRemrak.length>100){
		 alert("拒绝原因请控制在100字符以内");
		 return;
	 }	 
     var paramaters = {
         "withwardId": $("#txtHideWithdrawId").val(), 
         "auditFailedReason":refuseRemrak
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
 //确认打款
 function funPayOK(id,amount){
	 var url = "<%=basePath%>/clienterwithdraw/alipaybatchtransfer?type=1&data=" + id;
     window.open(url);
     alert('请新打开的页面完成支付宝付款!');
     $('#btnSearch').click();
     return;
 }
  
 //全选
 function checkAll(val){
	$("input[name='checkPay']").each(function (index, obj) { 
		 if (obj.value != "" && !obj.disabled) {
		 	this.checked = val; 
		 }else { 
             $(obj).removeAttr("checked");
         }
	});
 }
  </script>
	

