<%@page import="com.renrentui.renrencore.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<script src="<%=Config.adminurl%>/js/bootstrap-treeview.js"></script>
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
						</div>
					</div>
				</div> 
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label">热门城市名称:</label>
						<div class="col-sm-8">
							 <input type="text" placeholder="请输入热门城市名称" class="form-control" id="txthotcityname" name="key" />
							 <button type="button" class="btn btn-w-m btn-primary" id="btnHotCitySearch" >查询</button>
						</div>
					</div>
				</div>  
			</div>  
		</div> 
	</div>
</div>
<table id='tblCity' class="table table-striped table-bordered table-hover dataTables-example" style='display:none'>
	<thead>
		<tr>
			<th width="%5">序号</th>
            <th>省编号</th>
            <th>省名称</th>
            <th>市编号</th>
            <th>市编名称</th>
            <th>区县编号</th>
            <th>区县名称</th>
            <th id='ispubliccity'>是否开放</br><input type="checkbox" name="checkAll" id="selectAll" onclick="checkPublicCityAll()" />全选/取消</th>
            <th id='ishotcity'>是否热门</br><input type="checkbox" name="checkAll" id="selectAll" onclick="checkHotCityAll()" />全选/取消</th>
		</tr>
	</thead>
	<tbody id="Content">
	</tbody>
</table> 
<script>
//开放城市
  $("#btnPublicCitySearch").click(function(){
	  var key=$("#txtpubliccityname").val().trim(); 
	  if(key==""){
		  alert("开放城市名称不能为空!");
		  return;
	  }
	  $("#tblCity").show();
	  $("#ishotcity").hide();
	  $("#ispubliccity").show();
	  
  });
//热门城市
  $("#btnHotCitySearch").click(function(){
	var key=$("#txthotcityname").val().trim(); 
	  if(key==""){
		  alert("热门城市名称不能为空!");
		  return;
	  }
	  $("#tblCity").show();
	  $("#ishotcity").show();
	  $("#ispubliccity").hide();
  });
  //开放城市选择
  function checkPublicCityAll(){
	  
  }
  //热门城市选择
  function checkHotCityAll(){
	  
  }
</script>