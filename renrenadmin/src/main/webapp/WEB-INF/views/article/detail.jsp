<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%>
<%@page import="com.renrentui.renrencore.util.PageHelper"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.domain.Article"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
Article data = (Article) request.getAttribute("detail");
%>
<div style="text-align:center;margin-left:auto; margin-right:auto;" ><h2><%=data.getTitle() %><h2></div>
<div ><%=data.getContent() %></div>
