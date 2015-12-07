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
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">开放城市名称:</label>
						<div class="col-sm-8">
							 <input type="text" placeholder="请输入开放城市名称" class="form-control" id="txtpubliccityname" name="key" />
							 <button type="button" class="btn btn-w-m btn-primary" id="btnPublicCitySearch" >查询</button>
							 <button type="button" class="btn btn-w-m btn-primary" id="btnSavePublicCitySearch" >保存修改</button> 
						</div>
					</div>
				</div> 
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">热门城市名称:</label>
						<div class="col-sm-8">
							 <input type="text" placeholder="请输入热门城市名称" class="form-control" id="txthotcityname" name="key" />
							 <button type="button" class="btn btn-w-m btn-primary" id="btnHotCitySearch" >查询</button>
							 <button type="button" class="btn btn-w-m btn-primary" id="btnSaveHotCitySearch" >保存修改</button>
						</div>
					</div>
				</div>  
			</div>  
		</div> 
	</div>
<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>
  
<script> 

//开放城市
  $("#btnPublicCitySearch").click(function(){ 
	  var key=$("#txtpubliccityname").val().trim(); 
	  if(key==""){
		  alert("开放城市名称不能为空!");
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
		 		          	  	$("#ishotcity").hide();
		 		          	  	$("#ispubliccity").show();
		 		          	 	$(".classHotCityAll").hide();
		 		          	    $(".classPublicCityAll").show();
		 		            }
		 		        });  
				}
			}	
		jss.search(1);
  });
//热门城市
  $("#btnHotCitySearch").click(function(){
	var key=$("#txthotcityname").val().trim(); 
	  if(key==""){
		  alert("热门城市名称不能为空!");
		  return;
	  } 
	  var jss={ 
				search:function(currentPage){	 
					 var cityName = $("#txthotcityname").val().trim();
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
		 		         	  	$("#ishotcity").show(); 
		 		          	  	$("#ispubliccity").hide(); 
		 		          	    $(".classPublicCityAll").hide();
		 		          	    $(".classHotCityAll").show();
		 		            }
		 		        });  
				}
			}	
		jss.search(1);
  });
  //开放城市选择
  function checkPublicCityAll(){
	  var checkedOfAll = $("#selectPublicCityAll").prop("checked");
      $("input[name='checkPublicCityAll']").prop("checked", checkedOfAll);
  }
  //热门城市选择
  function checkHotCityAll(){
	  var checkedOfAll = $("#selectHotCityAll").prop("checked");
      $("input[name='checkHotCityAll']").prop("checked", checkedOfAll);
  }
</script>