<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.common.PagedResponse"%>
<%@page import="com.renrentui.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.entity.AccountInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.core.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>登录名称</th>
			<th>启用状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<AccountInfo> data = (PagedResponse<AccountInfo>) request.getAttribute("listData");
			List<AccountInfo> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<AccountInfo>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=(i+1)%></td>
			<td><%=list.get(i).getLoginname()%></td>
			<td><%=list.get(i).getStatus() > 0 ? "可用" : "锁定"%></td>
			<td><a href="javascript:void(0)"
				onclick="setauth(<%=list.get(i).getId()%>)">分配权限</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>