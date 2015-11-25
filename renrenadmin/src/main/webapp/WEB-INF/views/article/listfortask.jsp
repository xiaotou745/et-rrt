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
 			<th>选中</th> 
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
 			<td><%=list.get(i).getTitle()%></a></td> 
 			<td><%=ParseHelper.ToDateString(list.get(i).getCreateDate())%></td> 
 			<td><a href="javascript:void(0)" onclick="clickArticle(<%=list.get(i).getId()%>,'<%=list.get(i).getTitle()%>')">选中</a></td> 
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
//选中文章事件
 function clickArticle(id,title){
		console.log(id+title);
		//console.log($(article).parent().parent());
		//console.log($(article).parent());
	  $(article).parent().parent().find('.eltitle').val(title);
	  $(article).parent().parent().find('.elurl').val('<%=basePath%>'+'/article/detail?id='+id);
	  $('#alertbox').modal('hide');
 }
 </script> 