
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="java.util.List"%>         
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrencore.enums.AlipayBatchStatus"%>
<%@page import="java.util.ArrayList"%>

<%	
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
%>
<style type="text/css">
#map_contain {
    height: 90%;
    width: 100%;
    max-width: none;
}
label {
    max-width: none;
}

#control {
width: 100%;
}
</style>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<form method="POST" action="#" class="form-horizontal" id="searchForm">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">批次号:</label>
							<div class="col-sm-8">
									<input   class="form-control" type="text" name="batchNo" id="batchNo">
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">打款状态:</label>
							<div class="col-sm-8">
								<%=HtmlHelper.getSelect("status", EnumHelper.GetEnumItems(AlipayBatchStatus.class), "desc", "value",null,"-1","全部") %>
							</div>
						</div>
					</div> 
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">打款日期:</label>
							<div class="col-sm-8">	
							<div class="input-group date">
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span> 
										<input type="text"	class="form-control" name="lastOptTimeStart" id="lastOptTimeStart" placeholder="开始日期" value=""/>
									</div>                                 					
							</div>
						</div>
					</div>					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到</label>
							<div class="col-sm-8">	
							<div class="input-group date">
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span> 
										<input type="text"	class="form-control" name="lastOptTimeEnd" id="lastOptTimeEnd" placeholder="结束日期" value=""/>
									</div>     	                                 					
							</div>
						</div>
					</div>					
				</div>	
				<div class="row">
					<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: 3px;height:30px;">查询</button>
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
<script type="text/javascript">
var jss={
		search:function(currentPage){
			 var data={
					  	currentPage:currentPage,
					  	batchNo:$('#batchNo').val(),
					 	status:$('#status').val(),
					 	lastOptTimeStart:$('#lastOptTimeStart').val(),
					 	lastOptTimeEnd:$('#lastOptTimeEnd').val() 
					 };
			$.post("<%=basePath%>/clienterwithdraw/alibatchlistdo",
					data,
					function(d){
					$("#content").html(d);
			});
		}
	}
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
/*确认打款功能*/
function doSure(bacthNo){
	//询问框
	layer.confirm('您确认要提交修改吗？？', {
	    btn: ['确认','取消'], //按钮
	    shade: false //显示遮罩
	}, function(){
		window.open("<%=basePath%>/clienterwithdraw/alipaybatchtransfer?type=2&data="+bacthNo);
		 var index= layer.alert('请在新打开的页面完成打款！', {
			btn:["已完成打款"],
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: false
		},function(){
			jss.search(1);  //刷新list
			layer.close(index);  //关闭弹层
		}); 
	}, function(){
	    
	});
}
</script>