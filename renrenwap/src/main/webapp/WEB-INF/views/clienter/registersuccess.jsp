<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenwap.url");
	String phoneNo=(String)request.getAttribute("phoneNo");
%>
<!-- 引用block时，可以指定参数 -->
<!DOCTYPE html>
<html>

<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" /> 
    <link rel="stylesheet" href="<%=basePath %>/css/reg.css" />
    <link rel="shortcut icon" href="<%=basePath %>/img/favicon.ico" type="image/x-icon" />
    <script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
    <script type="text/javascript">
	    $(function() {
	    	$("#lblrrt").text(<%=phoneNo%>);
	    });
    </script>
</head>

<body>
    <div id="astro-wrap" class="g-main">
        <div class="success">
            <div class="title">注册成功！</div>
            <div class="info">您的人人推账号：<label id="lblrrt"></label></div>
            <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.renrentui.app" class="download">下载人人推APP</a>
        </div>
    </div>
</body>

</html>