<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
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
							<label class="col-sm-4 control-label">提现单号:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" name="txtWithdrawNo"  id="txtWithdrawNo" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">用户名称:</label>
							<div class="col-sm-8">								
								<input type="text" class="form-control" name="txtClienterName"  id="txtClienterName" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">电话号码:</label>
							<div class="col-sm-8">
								
								<input type="text" class="form-control" name="txtPhoneNo"  id="txtPhoneNo" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">审核状态</label>
							<div class="col-sm-8">
								<select id="typeselect" class="form-control m-b">
								<option value="-1">全部</option>
								<option value="0">待审核</option>
								<option value="1">审核通过</option>
								<option value="2">审核拒绝</option>
								</select> 
							</div>
						</div>
					</div>
				</div>


			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>	
							<button type="button" class="btn btn-w-m btn-primary" id="btnBatchAlipay" onclick="return BatchAlipay()"
							style="margin-left: 3px;height:30px;display:none">批量支付宝付款</button>	
																		 
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

var jss={
		search:function(currentPage){	
			 var withdrawNo = $("#txtWithdrawNo").val();
			 var clienterName = $("#txtClienterName").val();
			 var phoneNo = $("#txtPhoneNo").val();	 
			 var typeselect=$('#typeselect').val();
			 var paramaters = { 
					 "currentPage":currentPage,					 
					 "withdrawNo":withdrawNo,
					 "clienterName":clienterName,
					 "phoneNo":phoneNo,	
					 "status":typeselect,
					 m:Math.round()
					 };
		        var url = "<%=basePath%>/clienterwithdraw/listdo";
		        $.ajax({
 		            type: 'POST',
 		            url: url,
 		            data: paramaters,
 		            success: function (result) {   	
 		            	$("#content").html(result);               
 		            }
 		        });  
		}
	}	
	
jss.search(1);
$("#btnSearch").click(function(){
	jss.search(1);
});
$("#typeselect").change(function () {
	if($(this).val() == 1){
		$("#btnBatchAlipay").show();
	}else{
		$("#btnBatchAlipay").hide();
	}
});
//批量付款按钮
function BatchAlipay() {
    var ids = "";
    $('input[type="checkbox"][name="checkPay"]:checked').each(function (index, obj) {
        if (!obj.disabled) {
            ids += (obj.value + ",");
        }
    });
    if (ids == "") {
        alert("请选择要批量付款的提现单！");
        return;
    }
    ids = ids.substring(0, ids.length - 1);
    var url = "<%=basePath%>/clienterwithdraw/alipaybatchtransfer?type=1&data=" + ids;
    window.open(url);
    alert('请在新打开的页面完成支付宝付款！');
    $('#btnSearch').click();
}
</script>
