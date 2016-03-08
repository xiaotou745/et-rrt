<%@page import="org.apache.activemq.thread.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrenentity.domain.TaskDetail"%>
<%@page import="com.renrentui.renrenentity.domain.TaskSetp"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenwap.url");
	TaskDetail task = (TaskDetail)request.getAttribute("task");
	List<TaskSetp> taskstep=task.getTaskSetps();
%>
<html>
<head>
    <title>任务分享—人人推</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <link rel="stylesheet" href="<%=basePath %>/css/sharetask.css" />
    <link rel="shortcut icon" href="<%=basePath %>/img/favicon.ico" type="image/x-icon" />
</head>

<body>
<% if(task==null) 
{%>
	=====无数据，参数错误=====
<%} else{%>
    <div id="astro-wrap" class="g-main">
        <div class="baseinfo">
            <div class="base">
                <img src="<%=basePath %>/img/logo.jpg" alt="">
                <h2><%=task.getTask().getTaskTitle() %></h2>
                <div class="address"><%=task.getTask().getBusinessAddress() %></div>
                <div class="price">
                    <div class="sign"><%=task.getTask().getTagName() %></div>
                    <p class="num"><%=task.getTask().getAmount() %></p>
                    <p>元 / 次</p>
                </div>
            </div>
            <div class="timeinfo">
                <ul>
                    <li>
                        <i class="icon i-view"></i>审核：<%=ParseHelper.ToInt(task.getTask().getTaskCycle()) %>天
                    </li>
                    <li>
                        <i class="icon i-time"></i>截止日期：<%=ParseHelper.ToDateString(task.getTask().getEndTime(),"yyyy-MM-dd") %>
                    </li>
                    <li>
                        <i class="icon i-time"></i>预计用时：<%=task.getTask().getEstimatedTime()%>分钟
                    </li>
                </ul>
            </div>
        </div>
        <div class="task">
            <div class="title">任务流程</div>
            <ul class="flow">
                <li style="position:absolute"></li>
                <% for (int i = 0; i < taskstep.size(); i++) 
                	{ if(taskstep.get(i).getSetpType().equals(1)){%>
                <li><span class="num"><%=taskstep.get(i).getSortNo() %></span><%=taskstep.get(i).getContent() %></li>
                <%}} %>
            </ul>
            <ul class="tips">
            <% for (int i = 0; i < taskstep.size(); i++) { 
            	if(taskstep.get(i).getSetpType().equals(2)){%>
                <li><%=taskstep.get(i).getContent() %></li>
            <%}}%>
            </ul>
        </div>
        <div class="labinfo">
            <i class="icon i-phone"></i>咨询电话:<%=task.getTask().getHotLine() %>
        </div>
        <div class="labinfo">
            任务参与人数<%=ParseHelper.ToInt(task.getTask().getPartnerNum(),0) %>
            <img src="<%=basePath %>/img/arrow.jpg" class="arrow">
        </div>
        <a class="to_app" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.renrentui.app">前往APP做任务</a>

    </div> 
<% }%>
</body>

</html>