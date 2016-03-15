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
	String basePath=PropertyUtils.getProperty("java.renrenwap.url");
	String openid=(String)request.getAttribute("openid");
%>
<!-- 引用block时，可以指定参数 -->
<!DOCTYPE html>
<html>
<head>
    <title>绑定人人推账号送现金，最高100元等你拿~</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <link rel="stylesheet" href="<%=basePath%>/css/home.css" />
    <link rel="shortcut icon" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />
    <script type="text/javascript">
    	var basepath="<%=basePath%>";
		var sendcodeurl=basepath+"/clienter/sendcode";
		var fetchredbagurl=basepath+"/clienter/fetchredbagsubmit";
		var openid="<%=openid%>";
		if(openid==""){
			window.location="<%=basePath%>/wx/redirect";
			//alert("1");
		}
	</script>
</head>

<body>

<div style="height:0px;overflow:hidden;">
    <img src="<%=basePath%>/img/weixinshare1.0c707072.png">
</div>
    <div id="astro-wrap" class="g-main">

        <div class="ctx_block">
            <img src="<%=basePath%>/img/bg1.jpg" alt="领红包">
        </div>
        <div class="ctx_block bg2" data-size="640,415">
            <div class="b_wrap">
                <div class="form-item" data-size="370,66">
                    <input type="number" minlength="1" maxlength="11" class="ipt-txt ipt-phone" placeholder="请输入您的手机号" value="" />
                </div>
                <div class="form-item code" data-size="370,66">
                    <input type="number" minlength="1" maxlength="11" class="ipt-txt ipt-code" placeholder="请输入验证码"> <a href="javascript:;" class="getCode">发送验证码</a>
                </div>
                <a class="sub-btn gethb">领红包</a>
            </div>
        </div>
        <div class="contents">
            <div class="b_wrap c0">
                <div class="form-item" data-size="370,66">
                    <input type="number" minlength="1" maxlength="11" class="ipt-txt ipt-phone" placeholder="请输入您的手机号" value="" />
                </div>
                <div class="form-item code" data-size="370,66">
                    <input type="number" minlength="1" maxlength="11" class="ipt-txt ipt-code" placeholder="请输入验证码"> <a href="javascript:;" class="getCode">发送验证码</a>
                </div>
                <a class="sub-btn gethb">领红包</a>
            </div>
            <!-- <div class="success c1">
                <p>恭喜您获得现金奖励</p>
                <p class="money">
                    ¥ 6 元
                </p>
                您已绑定人人推账号180****0000现金红包将由公众号通过微信红包形式发放
                <a href="#" class="sub-btn">前往查看</a>
            </div>
            <div class="rcode_wrap c2">
                进入<span class="stress">公众号</span>点击<span class="stress">“绑定账号送红包”</span>参与活动
                <img src="img/rcode.png" alt="">
                长按二维码扫描<br>关注[人人推官方平台]
            </div>
            <div class="to_reg c3">
                <div class="title">领取失败</div>
                该手机号尚未注册人人推，请点击下方按钮前往APP注册后 <a href="javascript:;" class="rebind stress">重新绑定</a>
                
                <a href="#" class="sub-btn">马上注册</a>
            </div>
            <div class="to_reg to_bind c4">
                <div class="title">领取失败</div>
                该手机号已被绑定，使用其他号码重新绑定 
                
                <a href="javascript:;" class="sub-btn rebind">返回重新绑定</a>
            </div> -->
        </div>
        <div class="rules">
            <div class="title">
                活动规则
            </div>
            <ul>
                <li><span class="num">1</span>关注人人推微信公众号【人人地推】后进行绑定才能获得现金奖励</li>
                <li><span class="num">2</span>绑定的手机号必须已经注册为人人推用户</li>
                <li><span class="num">3</span>活动期间，单个微信/人人推账号只能绑定一次</li>
                <li><span class="num">4</span>绑定成功后，即可获得1-100元随机现金奖励，奖励发放到您的人人推账户，请登录人人推APP进行查看</li>
                <li><span class="num">5</span>本活动最终解释权归人人推网络科技（北京）有限公司所有</li>
            </ul>
        </div>

    </div>
    <script src="<%=basePath%>/js/zepto.js"></script>
    <script src="<%=basePath%>/js/h5.js"></script>

    <script src="<%=basePath%>/js/home.js"></script>




</body>

</html>