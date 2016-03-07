<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@page import="java.lang.Double"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenwap.url");
%>
<html>
<head>
    <title>任务分享—人人推</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <link rel="stylesheet" href="<%=basePath %>/css/home.css" />
    <link rel="shortcut icon" href="<%=basePath %>/img/favicon.ico" type="image/x-icon" />
</head>

<body>
    <div id="astro-wrap" class="g-main">

        <div class="baseinfo">
            <div class="base">
                <img src="<%=basePath %>/img/logo.jpg" alt="">
                <h2>聚网客加盟任务</h2>
                <div class="address">建外B座底商建外B座底商F24建外</div>
                <div class="price">
                    <div class="sign">签约</div>
                    <p class="num">1800.00</p>
                    <p>元 / 次</p>
                </div>
            </div>
            <div class="timeinfo">
                <ul>
                    <li>
                        <i class="icon i-view"></i>审核：5天
                    </li>
                    <li>
                        <i class="icon i-time"></i>截止日期：2015-12-31
                    </li>
                    <li>
                        <i class="icon i-time"></i>预计用时：0小时
                    </li>
                </ul>
            </div>
        </div>
        <div class="task">
            <div class="title">
                任务流程
            </div>
            <ul class="flow">
                <li style="position:absolute"></li>
                <li>
                    <span class="num">1</span> 寻找尚未加入聚网客的门店</li>
                <li>
                    <span class="num">2</span> 到店邀请商家加入聚网客，收集商家信息</li>
                <li>
                    <span class="num">3</span> 录入商家信息，提交审核</li>
                <li class="last">
                    <span class="num">4</span> 店铺信息将在6个工作日内审核完毕，通过审核后，推广人即可获得20元现金奖励</li>
            </ul>
            <ul class="tips">
                <li>当前任务补充说明1</li>
                <li>当前任务补充说明2</li>
                <li>当前任务补充说明3</li>
                <li>当前任务补充说明4</li>
            </ul>
        </div>
        <div class="labinfo">
            <i class="icon i-phone"></i>咨询电话: <a href="javascript:;">暂无</a>
        </div>
        <div class="labinfo">
            任务参与人数
            <img src="<%=basePath %>/img/arrow.jpg" class="arrow">
        </div>
        <a class="to_app" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.renrentui.app">前往APP做任务</a>

    </div>



</body>

</html>