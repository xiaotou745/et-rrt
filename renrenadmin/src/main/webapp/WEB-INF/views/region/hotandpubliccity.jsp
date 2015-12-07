<%@page import="com.renrentui.renrencore.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrenentity.domain.OpenCityModel"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<div
	class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row"> 
			<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">城市名称：</label>
						<div class="col-sm-8">
							 <input type="text" placeholder="请输入城市名称" class="form-control"
							id="txtpubliccityname" name="key" />
						</div>
					</div>
				</div> 
				<div class="row">
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary"
						id="btnPublicCitySearch">查询</button>
					<button type="button" class="btn btn-w-m btn-primary"
						id="btnSavePublicCitySearch">保存修改</button>
				</div>
			</div></div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>

<script>  
//城市查询
  $("#btnPublicCitySearch").click(function(){ 
	  var key=$("#txtpubliccityname").val().trim(); 
	  if(key==""){
		  alert("城市名称不能为空!");
		  return;
	  } 
	  var jss={ 
				search:function(currentPage){	 
					 var cityName = $("#txtpubliccityname").val().trim();
					 var paramaters = {  
							 "cityName":cityName
							 };
				        var url = "<%=basePath%>/region/listdo";
				        $.ajax({
		 		            type: 'POST',
		 		            url: url,
		 		            data: paramaters,
		 		            success: function (result) { 
			 		            	$("#content").html(result);    
			 		            	$("#tblCity").show();
		 		            }
		 		        });
				}
			}	
		jss.search(1);
  }); 
  //保存城市修改
	  $("#btnSavePublicCitySearch").click(function(){ 
		  	var openPublicCityCodeList = "";
			var closePublicCityCodeList = "";
			var openHotCityCodeList="";
			var closeHotCityCodeList="";
			$("input[name='checkPublicCityAllRadio']").each(
					function() {
						if ($(this).is(':checked')) {
							if($(this).val()!=""){
							openPublicCityCodeList = openPublicCityCodeList
									+ $(this).val() + ",";
							}
						} else {
							if($(this).val()!=""){
							closePublicCityCodeList = closePublicCityCodeList
									+ $(this).val() + ",";
							}
						}
					});
			if (openPublicCityCodeList.length > 0)
				openPublicCityCodeList = openPublicCityCodeList.substring(0,
						openPublicCityCodeList.length - 1);
			if (closePublicCityCodeList.length > 0) {
				closePublicCityCodeList = closePublicCityCodeList.substring(0,
						closePublicCityCodeList.length - 1);
			}
			$("input[name='checkHotCityAllRadio']").each(
					function() {
						if ($(this).is(':checked')) {
							if($(this).val()!=""){
							openHotCityCodeList = openHotCityCodeList
									+ $(this).val() + ",";
							}
						} else {
							if($(this).val()!=""){
							closeHotCityCodeList = closeHotCityCodeList
									+ $(this).val() + ",";
							}
						}
					});
			if (openHotCityCodeList.length > 0)
				openHotCityCodeList = openHotCityCodeList.substring(0,
						openHotCityCodeList.length - 1);
			if (closeHotCityCodeList.length > 0) {
				closeHotCityCodeList = closeHotCityCodeList.substring(0,
						closeHotCityCodeList.length - 1);
			} 
		   var paramaters = {
					"openPublicCityCodeList" : openPublicCityCodeList,
					"closePublicCityCodeList" : closePublicCityCodeList,
					"openHotCityCodeList" : openHotCityCodeList,
					"closeHotCityCodeList" : closeHotCityCodeList
				};
				var url = "<%=basePath%>/region/modifycity";
				$.ajax({
					type : 'POST',
					url : url,
					data : paramaters,
					success : function(result) {		
						if(result>0){
							alert('操作成功！');
						}else
						{
							alert('操作失败，请联系管理员');
					    }						
					}
				});  
	  });  
</script>