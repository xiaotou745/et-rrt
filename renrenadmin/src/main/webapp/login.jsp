<%@page import="com.renrentui.renrencore.consts.GlobalSettings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenadmin.common.LoginUtil" %>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenadmin.common.LoginUtil"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<%
	boolean isLogin = LoginUtil.checkIsLogin(request,response);
	if(isLogin){
		//如果登录,跳转到首页
		response.sendRedirect(basePath+"/ordermanage/auditorder");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="renderer" content="webkit" />
<title>人人地推管理后台</title>
<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=basePath%>/css/font-awesome.css" rel="stylesheet" />
<link href="<%=basePath%>/css/animate.css" rel="stylesheet" />
<link href="<%=basePath%>/css/style.css" rel="stylesheet" />
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		//当用户注销后，到登录页面，点击了浏览器的返回按钮时，
		//由于浏览器的缓存，会显示一个复合页面：登录后的页面嵌套了一个登录页面
		//因此，当这种情况时，页面定位到登录页面
		if($("#loginofbtn").length>0){
			window.location.href ='<%=basePath%>';
		}
	});
</script>
</head>
<body class="gray-bg">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div style="margin-top: 100px;">
			<!-- 			<div>
				<h1 class="logo-name">H+</h1>
			</div> -->
			<h2>人人地推管理后台</h2>
			<form class="m-t" role="form" action="<%=basePath%>/account/login"
				method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="用户名"
						required="" name="username">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="密码"
						required="" name="password">
				</div>
				<div class="form-group">
					<input type="text" name="code" class="form-control"
						style="width: 100px; float: left;" placeholder="验证码"
						required=""> <img src="<%=basePath %>/account/code?x=Math.random();" id="imgCode"
						height="30px" style="float: left;" onclick="changeCodeImg()"/>
				</div>
				<div class="form-group">
					<label> <input type="checkbox" name="rememberMe" id="rememberMe" value="1" class="i-checks">自动登录
					</label>
				</div>
				<button type="submit" class="btn btn-primary block full-width m-b">登
					录</button>
				<div class="form-group">
					<h3 style="color:red;"><%=request.getAttribute("error")==null?"": request.getAttribute("error")%></h3>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
	function changeCodeImg(){
		var ran = Math.random();
		document.getElementById("imgCode").src = "<%=basePath %>/account/code?x="+ran;
	}
	</script>
	<!-- Mainly scripts -->
</body>
</html>
