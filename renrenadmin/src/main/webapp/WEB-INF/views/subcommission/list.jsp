<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal"id="searchForm" onSubmit="return false;">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">策略标题:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="StrategyName" id="StrategyName" />
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 3px; height: 30px;">查询</button>
						<button type="button" class="btn btn-w-m btn-primary" id="addStrategy" style="margin-left: 3px; height: 30px;">添加分佣策略</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox-content" id="content"></div>
		</div>
	</div>
</div>
<script>
$(function(){
	//添加分佣策略
	$('#addStrategy').click(function(){
		window.location.href="<%=basePath%>/subcommission/add";
	});
	//点击查询
	$('#btnSearch').click(function(){
// 		if($('StrategyName').val()==''){
// 			alert('请输入策略名称进行查询!');
// 			}
		var url="<%=basePath%>/subcommission/listdo";
		var par={"strategyName":$('#StrategyName').val()}
		$.post(url,par,function(d){
			$('#content').html(d);
		});
	});
	$('#btnSearch').click();
});
//更新状态
function updateStatus(id,status,percentage)
{
	//1 禁用 2 启用 3 删除
	var url="<%=basePath%>/subcommission/updateStatus";
	var  par={"id":id,"status":status,"percentage":percentage};
	$.post(url,par,function(d){
		if(d>=1){
			alert('操作成功!');
			window.location.reload();
			}
		else if (d==-1){
			alert('该策略总分佣大于系统当前设置分佣,无法启用')
			}
		else{
			alert('操作失败!');
			}
	});
}
</script>