<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:getAsString name="title" /></title>



<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/font-awesome/css/font-awesome.min.css" />
<%-- <link rel="stylesheet" href="<%=basePath%>/css/plugins/toastr/toastr.min.css" /> --%>
<%-- <link rel="stylesheet" href="<%=basePath%>/js/plugins/gritter/jquery.gritter.css" /> --%>
<link rel="stylesheet" href="<%=basePath%>/css/animate.css" />
<link rel="stylesheet" href="<%=basePath%>/css/style.css" />
<link rel="stylesheet" href="<%=basePath%>/css/plugins/dataTables/dataTables.bootstrap.css" />
<link href="<%=basePath%>/css/admin.css" rel="stylesheet" />

<!-- Mainly scripts -->
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=basePath%>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=basePath%>/My97DatePicker/WdatePicker.js"></script>
<script src="<%=basePath%>/js/ajaxfileupload.js"></script>

<link href="<%=basePath%>/js/jquery.uploadify-v2.1.0/uploadify.css" rel="stylesheet" />
<link href="<%=basePath%>/js/jquery.uploadify-v2.1.0/example/css/default.css" rel="stylesheet" />
<script src="<%=basePath%>/js/jquery.uploadify-v2.1.0/swfobject.js"></script> 
<script src="<%=basePath%>/js/jquery.uploadify-v2.1.0/jquery.uploadify.v2.1.0.min.js"></script> 

<!-- Flot -->
<%-- <script src="<%=basePath%>/js/plugins/flot/jquery.flot.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="<%=basePath%>/js/plugins/flot/jquery.flot.pie.js"></script> --%>

<!-- Peity -->
<%-- <script src="<%=basePath%>/js/plugins/peity/jquery.peity.min.js"></script>
<script src="<%=basePath%>/js/demo/peity-demo.js"></script> --%>

<!-- Custom and plugin javascript -->
<script src="<%=basePath%>/js/inspinia.js"></script>
<%-- <script src="<%=basePath%>/js/plugins/pace/pace.min.js"></script> --%>

<!-- jQuery UI -->
<script src="<%=basePath%>/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- GITTER -->
<%-- <script src="<%=basePath%>/js/plugins/gritter/jquery.gritter.min.js"></script> --%>

<!-- Sparkline -->
<%-- <script src="<%=basePath%>/js/plugins/sparkline/jquery.sparkline.min.js"></script> --%>

<!-- Sparkline demo data  -->
<%-- <script src="<%=basePath%>/js/demo/sparkline-demo.js"></script> --%>

<!-- ChartJS-->
<%-- <script src="<%=basePath%>/js/plugins/chartJs/Chart.min.js"></script> --%>

<!-- Toastr -->
<%-- <script src="<%=basePath%>/js/plugins/toastr/toastr.min.js"></script> --%>


<!-- 第三方弹窗js -->
<script src="<%=basePath%>/js/layer.js"></script>

<!-- 分页相关js -->
<%--     <script type="text/javascript" src="<%=basePath%>/js/admin.js"></script> --%>
<%-- <script src="<%=basePath%>/js/plugins/jeditable/jquery.jeditable.js"></script>  --%>
<%-- <script src="<%=basePath%>/js/plugins/dataTables/jquery.dataTables.js"></script> --%>
<%-- <script src="<%=basePath%>/js/plugins/dataTables/dataTables.bootstrap.js"></script> --%>
<%-- <script src="<%=basePath%>/js/hplus.js"></script> --%>
<script>
    $(document).ajaxError( function(event, jqXHR, options, errorMsg){
   	 var content="内部服务器错误";
    	if(jqXHR.responseText==undefined){
    		content=jqXHR.statusText;
    	}else{
    	 var start=jqXHR.responseText.indexOf("<body>");

    	 if(start>0){
        	 var end=jqXHR.responseText.indexOf("</body>");
        	 content=jqXHR.responseText.substring(start+6,end);
        	 content=content.replace("h1","h4"); 
    	 }else{
    		 var start2=jqXHR.responseText.indexOf("<pre>");
    		 var end2=jqXHR.responseText.indexOf("</pre>");
        	 content=jqXHR.responseText.substring(start2,end2+6);
    	 }
    	 }
		if(content.indexOf("AjaxNotLoginRunTimeException")>0){
			alert("由于你长时间没操作，请重新登录");  
			window.location.href = "<%=basePath %>";
			return;
		}
    	 $("#gloablErrorParam").html(options.url+"调用出错了！");
    	 $("#gloablErrorContent").html(content);
    	 $("#gloablShowError").html("显示详细信息");
    	 $("#gloablErrorContent").hide();
    	 $('#gloablErrorDiv').modal('show');
    });
    
	$(document).ready(function() {
		//当用户注销后，到登录页面，点击了浏览器的返回按钮时，
		//由于浏览器的缓存，会显示一个复合页面：登录后的页面嵌套了一个登录页面
		//因此，当这种情况时，页面定位到登录页面
		if($("#rememberMe").length>0){
			window.location.href ='<%=basePath%>';
		}
		$("#gloablShowError").click(function() {
			if ($("#gloablShowError").html() == "显示详细信息") {
				$("#gloablShowError").html("隐藏详细信息");
				var timeSet=2000;
				if($("#gloablErrorContent").html().length<500){
					timeSet=500;
				}
				$("#gloablErrorContent").slideDown(timeSet);
			} else {
				$("#gloablShowError").html("显示详细信息");
				$("#gloablErrorContent").slideUp(500);
			}
		});
		//分页跳转按钮事件处理方法
		$(document).on("click", "#pagesearch", function(){
			var page=$("#pagesearchvalue").val();
			var maxpage=$("#pagesearchmax").val();
			var currentpage=$("#pagesearchcurrentpage").val();
			var s = new RegExp("^\\s*(\\d+)\\s*$");
			if(!s.test(page)||parseInt(page) < 1 || parseInt(page) > maxpage){
			  alert("页索引超出范围");
			  $("#pagesearchvalue").val(currentpage);
			  return;
			}
			jss.search(page);
		}); 
		$(document).on("keydown", "#pagesearchvalue", function(e){
		    var key = null;
		    if (e.which) {
		        key = e.which;
		    }
		    else if (e.keyCode) {
		        key = e.keyCode;
		    }

			if ((48<=key&&key<=57)||(96<=key&&key<=105)) {
			    return true;
			}else{
			    return false;
			}
		});
		//列表页下拉框改变时，自动查询
		$("select").on("change",function(e){
			try{
				jss.search(1);
			}catch(e){
			}
		});
		//列表页点击回车时，自动查询
		$(document).on("keydown",function(event){
			try {
				var e = event || 
						window.event|| 
						arguments.callee.caller.arguments[0];
				if (e && e.keyCode == 13) { // enter 键
					jss.search(1);
				}
			} catch (e) {
			}
		});
	});
</script>
<tiles:insertAttribute name="header_js" ignore="true"></tiles:insertAttribute>
<tiles:insertAttribute name="header_css" ignore="true"></tiles:insertAttribute>
</head>
<body>

	<div id="wrapper">
		<tiles:insertAttribute name="leftmenu"></tiles:insertAttribute>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<tiles:insertAttribute name="header"></tiles:insertAttribute>
			</div>
			<tiles:insertAttribute name="breadcrumbs"></tiles:insertAttribute>
			<div class="row">
				<div class="col-lg-12">
					<tiles:insertAttribute name="body"></tiles:insertAttribute>

				</div>
			</div>
			<div class="row">
				<div class="">
					<tiles:insertAttribute name="footer" ignore="true"></tiles:insertAttribute>
				</div>
			</div>
		</div>
		<div tabindex="-1" class="modal inmodal" id="gloablErrorDiv" role="dialog"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">服务器异常</h4>
					</div>
					<small class="font-bold">
						<div class="modal-body">
						<div id="gloablErrorParam"></div>
						<div><a id="gloablShowError"  href="javascript:void(0)">显示详细信息</a></div>
						<pre id="gloablErrorContent" style="width: 560px;display: none; "></pre>
						</div>
						<div class="modal-footer">
							<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
						</div>
					</small>
				</div>
				<small class="font-bold"> </small>
			</div>
			<small class="font-bold"> </small>
		</div>
	</div>
</body>
</html>
