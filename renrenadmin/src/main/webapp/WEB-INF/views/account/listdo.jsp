<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrenentity.AccountInfo"%>
<%@page import="java.util.List"%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>账号名称</th>
			<th>登录名称</th>
			<th>启用状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<AccountInfo> data = (PagedResponse<AccountInfo>) request
					.getAttribute("listData");
			List<AccountInfo> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<AccountInfo>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getUserName()%></td>
			<td><%=list.get(i).getLoginName()%></td>
			<td><%=list.get(i).getStatus() == 1 ? "√" : "×"%></td>
			<td><a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId()%>)">编辑</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script type="text/javascript">				
    function modify(id) {
        $('#myModal').modal('show');
    }
    </script>