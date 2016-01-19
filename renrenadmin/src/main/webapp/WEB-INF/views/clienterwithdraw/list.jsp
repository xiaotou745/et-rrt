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
								<option value="20">打款中</option>
								<option value="3">打款成功</option>
								<option value="30">打款失败</option>
								</select> 
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">提现方式</label>
							<div class="col-sm-8">
								<select id="typewithdraw" class="form-control m-b">
								<option value="-1">全部</option>
								<option value="1">支付宝</option>
								</select> 
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">创建时间:</label>
							<div class="col-sm-8">
							<input id="txtstartdate" class="form-control" type="text" name="startdate" placeholder="创建时间起"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',maxDate:'#F{$dp.$D(\'txtenddate\')||\'2150-10-01\'}'})"/>
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">到:</label>
							<div class="col-sm-8">
							<input id="txtenddate" class="form-control" type="text" name="enddate" placeholder="创建时间止"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'txtstartdate\')}',maxDate:'2150-10-01'})"/>
							</div>
						</div>
					</div>
				</div>
			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
							<button type="button" class="btn btn-w-m btn-primary" id="btnBatchAlipay" onclick="return exportWithdraw()"
							style="margin-left: 3px;height:30px;">导出</button>		
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
<div tabindex="-1" class="modal inmodal" id="refuseRemarkDiv"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">审核拒绝确认提示</h4> 
			</div>
			<small class="font-bold">
				<h3 style="margin-left:25px">是否确认审核拒绝？是否确认拒绝打款<label id='lblRefusePayMoney'></label>元？</h3>
				<div class="modal-body">
					<fieldset> 
			            	<div> 
			            	<input id="txtHideWithdrawId" type="hidden"> 
			            	<input id="txtHideWithdrawDate" type="hidden">
			            	<input id="txtHideclienterId" type="hidden">
			            	拒绝原因：<textarea id="refuseRemrakTxt" style="width:350px;height:120px;max-width:350px;max-height:120px;"></textarea>
			            	</div>
			        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id=btnConfirmRefuse onclick="refuseConfirm()">确认</button>
				</div> 
			</small>
			
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
			 var withType=$('#typewithdraw').val();
			 var startdate=$("#txtstartdate").val();       
             var enddate=$("#txtenddate").val();
			 var paramaters = { 
					 "currentPage":currentPage,					 
					 "withdrawNo":withdrawNo,
					 "clienterName":clienterName,
					 "phoneNo":phoneNo,	
					 "status":typeselect,
					 "withType":withType,
					 "startDate":startdate,
					 "endDate":enddate,					 
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
	var withtype =$("#typewithdraw").val();
	if($(this).val() == 1 && withtype == 1){
		$("#btnBatchAlipay").show();
	}else{
		$("#btnBatchAlipay").hide();
	}
});
$("#typewithdraw").change(function () {
	var typeselect =$("#typeselect").val();
	if($(this).val() == 1 && typeselect == 1){
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
//导出
function exportWithdraw(){
	 var withdrawNo = $("#txtWithdrawNo").val();
	 var clienterName = $("#txtClienterName").val();
	 var phoneNo = $("#txtPhoneNo").val();	 
	 var typeselect=$('#typeselect').val();
	 var withType=$('#typewithdraw').val();
	 var startdate=$("#txtstartdate").val();       
    var enddate=$("#txtenddate").val();
    if (startdate == "" || enddate == "") {
        alert("请输入时间范围!");
        return;
    }
    var url = "<%=basePath%>/clienterwithdraw/exportwithdraw?withdrawNo=" + withdrawNo + "&clienterName=" + clienterName + "&phoneNo=" + phoneNo +  "&typeselect=" + typeselect + "&withType=" + withType+ "&startDate=" + startdate + "&endDate=" + enddate;
    window.location.href = url;
    return;
}
</script>
