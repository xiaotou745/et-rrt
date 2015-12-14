<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
					<label class="col-sm-4 control-label">文章标题:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="文章标题" class="form-control" id="title" />
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="form-group">
					<label class="col-sm-4 control-label">文章编号:</label>
						<div class="col-sm-7">
							<input type="text" placeholder="文章编号" class="form-control" id="id" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 3px;">查询</button>
					<button type="button" class="btn btn-w-m btn-primary" id="addwen" style="margin-left: 3px;">新建文章</button>
				</div>
			</div>
		</div>
	</div>
</div>
 
<div id="content">
	
</div>
<script>

var jss={
		search:function(currentPage){
			var keyword=$("#txtKeyword").val();
			var url="<%=basePath%>/article/listdo";
			var par={currentPage:currentPage,
					id:$('#id').val(),
					title:$('#title').val(),
					type:1,
					m:Math.random()}
			$.post(url,par,function(d){
				$("#content").html(d);
			});
		}
	}
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});

$('#addwen').click(function(){
	window.location.href="<%=basePath%>/article/new";
});
</script>