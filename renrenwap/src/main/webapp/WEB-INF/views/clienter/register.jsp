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
<!-- 引用block时，可以指定参数 -->
<!DOCTYPE html>
<html>

<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

    <link rel="stylesheet" href="<%=basePath%>/css/reg.css" />
    <link rel="shortcut icon" href="<%=basePath%>/img/favicon.ico" type="image/x-icon" />
        <script type="text/javascript">
    	var basepath="<%=basePath%>"; 
    	var sendcodeurl=basepath+"/clienter/sendcode";
    	var registerurl=basepath+"/clienter/registersubmit";
	</script>
</head>

<body>
    <div id="astro-wrap" class="g-main">

        <div class="reg-wrap">
            <div class="form-element ele-phone">
                <i class="m-icon i-phone"></i>
                <input placeholder="请输入手机号" value="" type="number" />
                <a href="javascript:;" class="getCode">获取验证码</a>
            </div>
            <div class="form-element">
                <i class="m-icon i-code"></i>
                <input placeholder="请输入验证码" type="number" class="ipt-code" />
            </div>
            <div class="form-element">
                <i class="m-icon i-pwd"></i>
                <input placeholder="请输入密码" value="" type="password" class="ipt-pwd1" />
            </div>
            <div class="form-element">
                <i class="m-icon i-pwd"></i>
                <input placeholder="请再次输入密码" value="" type="password" class="ipt-pwd2" />
            </div>
            <div class="form-element last">
                <i class="m-icon i-phone"></i>
                <input placeholder="请输入推荐人手机号（非必填）" value="" type="number" class="ipt-rem" />
            </div>
        </div>
        <div class="agree">注册即视为同意<a href="javascript:;" class="agreement">《人人推用户协议》</a></div>

        <a href="javascript:;" class="submit">注册</a>
        <div class="tip" style="display: none">该账号已存在</div>

        <div class="agreement-book">
            <div class="close">
                <img src="<%=basePath%>/img/close.jpg" alt="">
            </div>
            <div class="title">用户协议</div>
            <p>
                <人人推>订立了有关网站服务（以下简称服务）的下述协议（“使用条款”）。无论谁使用我们的服务，都被视为已同意遵守我们的使用条款。</p>
            <p>1. 同意使用条款
                <br> 本协议（使用条款）包括现在和未来所经营的网站及客户端。
                <br> 通过使用此服务，无论您是否注册成为
                <人人推>的会员（以下简称为会员），您将被视为已经同意这些使用条款。如果您不同意此服务条款，请您停止使用我们的服务。</p>

            <p>2. 合格要求
                <br> 年龄未满18岁的未成年人不得使用我们的服务。通过使用我们的服务，您已声明并保证您有权利和能力遵守使用条款的所有规定。
            </p>

            <p>3. 使用条款的变更
                <br>
                <人人推>有权在无需事先通知的情况下，变更本使用条款。您可以定期查看服务条款的变更情况。 当我们对使用条款进行变更后，如果您使用我们的服务，无论您浏览与否，即视为您同意这些变更。</p>
			<p>4. 隐私
				<br>
                <人人推>尊重网站访问者的隐私权。服务的使用应遵循我们的隐私权政策。<人人推>的合作伙伴同意遵循隐私权政策的规定。</p>

			<p>5. 服务变更
				<br>
				<人人推>可以自行决定增加，删除或修改服务功能或各项服务，而无需事先通知。</p>

			<p>6. 订制
				<br>
				<br>a) 支付方式
				<br>您的支付条款将基于您的支付方式上，支付条款由您和金融机构，信用卡发行机构或其他您选择的支付服务提供商（支付服务提供商）之间的协定来决定。如果<人人推>不接受您的“支付服务提供商”所提供的付款服务，您同意根据需要支付所有金额。
				<br>
				<br>您同意为使用我们的服务，支付所有的应付账款。如果您的账款已付，您可以请求我们取消您的已付账款的状态，一旦您的账户被取消，你的已付费状态将在您最后支付的日期时终止。已付费状态不能转让。<人人推>的已付费状态是最终销售，不能退款。有争议的费用应在您购买服务后的60天内上报给<人人推>。如对于您的已付费状态有何疑问，请联系我们。
				<br>
				<br>b) 服务续约
				<br>您在<人人推>网站订制的服务将不会自动续约。您可以联系我们的客服团队，随时变更或重新订制。如果您选择重新订制，您的订制服务在到期后将不能自动续约。在订制期满前取消订制的，不能得到退款。
				<br><br>
				c) 免费试用和其他促销<br>
				任何免费试用或其他促销方式，可使订制者在特定的试用期限使用我们的服务。如果促销条款规定使用者需要订制服务，您应该在试用期之前取消订制，以避免交付订制费。</p>

				<p>7. 通过合作伙伴加入<人人推>
				<br>
				如果您通过<人人推>的合作伙伴注册使用我们的服务，并且<人人推>与其合作伙伴进行合作，提供该服务。你将有使用该服务的会员权利，并且<人人推>有权转移您的会员资格或将您的已付款账户转移到<人人推>的类似服务项目内。</p>

				<p>8. 社区内容
				<br>
				所有的服务内容都应该遵循社区内容指南的要求。
				<br>
				您同意不发布，传送，分发或链接任何涉及违法，骚扰，毁谤，威胁，有害，猥亵，悖逆，诽谤，诋毁他人名誉，以及侵犯别人隐私的内容，也不发送其他宁人不悦或侵害其他人或团体权益的内容。未经知识产权人的书面同意，不得使用他人的知识产权，包括受版权和商标权保护的资料，变更或未变更的知识产权，其他人的非版权文本或图片等。
				<br><br>
				<人人推>将不为会员所发的任何邮件或群讨论帖的内容负责，也不对第三方或会员提供的任何信息，商品或服务负责。使用我们的服务，即表明您同意：在以下情况下，<人人推>将不对您或其他人负责：例如：任何方的威胁，诽谤，猥亵，下流，攻击或违法行为，或侵权行为，包括违法使用知识产权。<人人推> 不拥有您提供给所有信息（包括回馈和建议），或您在<人人推>所发布，下载，输入或提交的所有信息（“提交的信息”）。然而，如果您在我们的网站或客户端上发布，上传，输入，提供或提交信息，即表明您给予<人人推>永久不可撤销并且可转让的权利，<人人推>有权复制，使用，储存，修改，编辑，翻译或发布任何或全部您所提交的信息，并且无需支付信息的使用费。<人人推>没有义务发布或使用您所提交的信息，并且<人人推>可以随时自行决定删除您所提交的信息。你在本网站发布，下载，输入或提交信息，即表明您拥有您所发布信息的控制权。对于您的信息被删除，无效或储存失败，<人人推>和其合作方将不为此负责。</p>

				<p>9. 会员账户
				<br>
				一旦您注册了我们的服务，您将会收到一个账号和密码，您应对您的账户和密码保密，并对您账户相关的所有活动负责。一旦注册成为会员，您将为您档案的所有信息负责。禁止在档案或网站服务项目中发布色情或其他淫秽的内容，因为这样会威胁您的会员身份。对于<人人推>网站的会员进行的线下约会，<人人推>将不负任何责任。
				<br>
				您同意<人人推>向您发送邮件（涉及服务信息（包括变更升级），新功能或事件，使用服务的意见和建议）。</p>

				<p>10. 受版权保护和商标权保护的材料
				<br>
				<人人推>网站的内容均受版权保护Copyright© 2011-2016。</p>

				<p>11. 免责声明
				<br>
				在<人人推>网站或通过<人人推>（或<人人推>的代理商或合作方）发布的资料，均按“现有”和“现存”提供。它并无各种明示或暗示的保证和条件。<人人推>不控制第三方所提供的任何信息，产品或服务。您明确同意，使用服务将独自承担风险。
				<br><br>
				根据适用法例的最大容许程度：<人人推>就所有明确或隐含保证做出免责声明，而此等保证范围包括但不限于隐含的可销售性及特殊用途合适性保证；<人人推>并不保证所提供的服务能满足您的需求，也不保证服务将不中断，适时，安全或正确无误。<人人推>不为服务使用和使用的结果做担保。<人人推>也不保证本网站或服务项目不含任何病毒，或其他有害组件。<人人推>不保证或声明：保持网站传送信息的机密性。<人人推>不保证网站翻译的准确和完整。 <人人推>不为以下做担保：通过本网站购买或获得的任何商品或服务，或在本网站做广告或赞助的商品或服务，或任何通过本网站所进行的交易。<人人推>不对您从<人人推>或其服务中所获得的信息做任何明示的保证（无论口头还是书面）。</p>
        </div> 
    </div> 
    <script src="<%=basePath%>/js/zepto.js"></script>
    <script src="<%=basePath%>/js/h5.js"></script>
    <script src="<%=basePath%>/js/reg.js"></script> 
</body> 
</html>