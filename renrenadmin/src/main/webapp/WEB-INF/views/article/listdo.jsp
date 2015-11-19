<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.domain.Article"%>
<%@page import="java.util.List"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>文章标题</th>
			<th>发布时间</th>
			<th>链接地址</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
		PagedResponse<Article> data = (PagedResponse<Article>) request.getAttribute("listData");
			List<Article> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Article>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><a target="_balnk" href="<%=basePath%>/article/detail?id=<%=list.get(i).getId()%>"><%=list.get(i).getTitle()%></a></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreateDate())%></td>
			<td><%=list.get(i).getId()%></td>
			<td><a href="javascript:void(0)" onclick="delArticle(<%=list.get(i).getId()%>)">删除</a>
			<a href="<%=basePath%>/article/new?id=<%=list.get(i).getId()%>">编辑</a></td>
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

</script>