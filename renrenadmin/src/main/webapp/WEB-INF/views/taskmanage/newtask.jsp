<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.AccountInfo"%>
<%@page import="com.renrentui.renrenentity.RoleInfo"%>
<%@page import="com.renrentui.renrenentity.Business"%>
<%@page import="com.renrentui.renrenentity.Template"%>
<%@page import="com.renrentui.renrenentity.PublicProvinceCity"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
String UploadPath= PropertyUtils.getProperty("UploadUrl");
List<Business> businessData = (List<Business>) request.getAttribute("businessData");
String templatelist = (String) request.getAttribute("templatelist");
List<PublicProvinceCity> provincelist = (List<PublicProvinceCity>) request.getAttribute("provincelist");
String pro_city = (String) request.getAttribute("pro_city");
String city_region = (String) request.getAttribute("city_region");
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="<%=basePath%>/js/renrentask.js"></script>
<%-- <script src="<%=basePath%>/js/jquery.json-2.4.js"></script> --%>
<div class="wrapper wrapper-content animated fadeInRight">
	<form method="POST" action="#" class="form-horizontal" id="searchForm">
		<fieldset>
			<legend>基本信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务标题: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskTitle"
										id="taskTitle" />
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">任务描述: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="taskGeneralInfo" id="taskGeneralInfo" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">审核周期: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="auditCycle" id="auditCycle" />
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   天
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">单次佣金: </label>
								<div class="col-sm-6">
									<input type="text" class="form-control" name="amount" id="amount" />
								</div>
								<div class="col-sm-2" style="line-height: 33px; padding-left: 3px;">
	  							   元
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">开始日期:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span> 
										<input type="text" class="form-control" value="" name="beginDate" id="beginDate" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">结束日期:</label>
								<div class="col-sm-8">
									<div class="input-group date">
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span> 
										<input type="text"	class="form-control" value="" name="endDate" id="endDate" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">咨询热线: </label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="hotline" id="hotline" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">任务类型: </label>
								<div class="col-sm-8">
									<input id="rTaskType1" name="rTaskType" type="radio" value="1" > 
									<label>签约任务</label>
									<input id="rTaskType2" name="rTaskType" type="radio" value="2" > 
									<label>分享任务</label>
									<input id="rTaskType3" name="rTaskType" type="radio" value="3" > 
									<label>下载任务</label>
								</div>
							</div>
						</div>
						</div>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>任务流程</legend>
			<span style="color:red;">*</span><span>完成步骤（请详细说明完成任务需要哪几个步骤，方便地推员按要求完成任务）</span>
			<a href="javascript:void(0);" class="fl add" id="setpadd">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel">删除</a> 
			<div class="orderBox dn" id="setpbox">
				<p class="copy">
								<label class="col-sm-4 control-label">步骤1</label>
									<input type="text" class="form-control" name="taskTitle"
										id="taskTitle" />
				</p>
			</div>
			<hr align="center" width="50%" style="color:#333333;">
			<span>补充说明（如果任务有特殊说明，或者注意事项，可在此处进行补充）</span>
			<a href="javascript:void(0);" class="fl add" id="setpadd2">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel2">删除</a> 
			<div class="orderBox dn" id="setpbox2">
				<p class="copy2">
					<label class="control-label">1、 </label> <input type="text"  style="width:200px;">
				</p>
			</div>
			<hr align="center" width="50%" style="color:#333333;">
			<span>细则（添加超链接，打开新页面）</span>
			<a href="javascript:void(0);" class="fl add" id="setpadd3">添加</a>
			<a href="javascript:void(0);" class="fl del" id="setpdel3">删除</a> 
			<div class="orderBox dn" id="setpbox3">
			<table>
				<thead>
					<tr><th>序号</th><th>链接文字</th><th>链接地址</th><th>操作</th></tr>
				</thead>
				<tbody id="setpbox3tbody">
					<tr class="copy3">
					<td><label>1</label></td>
					<td><input type="text"  style="width:200px;" class="eltitle"></td>
					<td><input type="text"  style="width:200px;" class="elurl"></td>
					<td><a href="javascript:void(0)" onclick="chooseArticle(this)">选择文章</a><a href="javascript:void(0)" >新建页面</a></td>
					</tr>
				</tbody>
			</table>
			</div>
		</fieldset>
		<fieldset>
			<legend>提交审核模板</legend>
			<div id="templateBox">
				<div class="templateGroupText template" style="border:1px solid red;">
					<label class="boxno">1.</label><span>文本组标题:</span><input type="text" value="文本组标题" class="cltxt">	
					<a href="javascript:void(0);" onclick="addTxtControl(this)">添加文本控件</a>
					<a href="javascript:void(0);" onclick="delTxtControl(this)" >删除文本控件</a> 
					<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该文本组</a>
					<div class="textGroup">
						<div class="textitem">说明文本:<input type="text" class="cltitle">默认值:<input type="text" class="cldefval"></div>
					</div>
				</div>
				<div class="templateGroupImg template"  style="border:1px solid blue;">
					<label class="boxno">2.</label><span>图片组标题</span><input type="text" value="图片组标题" class="climg">
					<a href="javascript:void(0);" onclick="addImgControl(this)" >添加图片控件</a>
					<a href="javascript:void(0);" onclick="delImgControl(this)" >删除图片控件</a>
					<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该图片组</a>
					<div class="imgGroup">
						<div class="imgitem">图片说明:<input type="text" class="cltitle"></div>
					</div>
				</div>
				<div class="templateGroupMoreImg template"  style="border:1px solid blue;">
					<label class="boxno">3.</label><span>多图组标题</span><input type="text" value="多图组标题" class="clmoreimg">
					<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该多图组</a>
					<div class="imgGroup">
						<div class="imgitemnum">图片数量:<input type="text" class="imgitemnumn"></div>
					</div>
				</div>
			</div>
			<a href="javascript:void(0);"  id="addtxtgroup">添加文本组</a>
			<a href="javascript:void(0);"  id="addimggroup">添加图片组</a>
			<a href="javascript:void(0);"  id="addmoreimggroup">添加多图组</a>
		</fieldset>
		<fieldset>
			<legend>关联设置</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
					<input type="hidden" name="targetPeople" value="1" id="targetPeople" />
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">关联商户: </label>
								<div class="col-sm-8">
									<%=HtmlHelper.getSelect("businessId", businessData, "companyName", "id", null,null, "全部")%>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">当前账户余额: </label>
								<div class="col-sm-8">
									<label class="control-label" id="businessBalance">0元</label>
								</div>
							</div>
						</div>
<!-- 						<div class="col-lg-3"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label class="col-sm-4 control-label">合同模板: </label> -->
<!-- 								<div class="col-sm-8"> -->
<!-- 									<select id="snapshotTemplateId" name="snapshotTemplateId"  class='form-control m-b'></select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<div class="row">
					<div class="col-lg-5">
							<div class="form-group" style="color:red">
								<label class="col-sm-2 control-label">注意: </label>
								<div class="col-sm-10">
									<label class="control-label">被关联商户账号将作为合同审核和支付佣金的账号，请慎重选择</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</fieldset>
				<fieldset>
			<legend>投放范围</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">省份: </label>
								<div class="col-sm-8">
									<%=HtmlHelper.getSelect("provinceCode", provincelist, "name", "code", null,-1, "全部")%>
								</div>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label">城市: </label>
								<div class="col-sm-8">
									<select id="cityCode" name="cityCode"  class="form-control m-b">
										<option value="-1">全部城市</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
		<div class="row">
			<div class="col-lg-4">
				<button type="button" class="btn btn-w-m btn-primary" id="save" onclick="savetask()"
					style="margin-left: 3px; height: 30px;">保存</button>

			</div>
		</div>
	</form> 
	<input type="hidden" id="pro_city" value="<%=pro_city %>" /> 
	<input type="hidden" id="city_region" value="<%=city_region %>" />
</div>
<div tabindex="-1" class="modal inmodal" id="alertbox" role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<input type="text" placeholder="文章标题" class="form-control" id="arttitle" style="width: 100px;"/>
				<input type="text" placeholder="文章编号" class="form-control" id="artid" style="float: left;margin-top: -34px;margin-left: 104px;width: 100px;" />
				<button type="button" class="btn btn-w-m btn-primary" id="btnSearch" style="margin-left: -14px;margin-top: -50px;" onclick="jss.search(1)">查询</button>				
			</div>
			<small class="font-bold">
				<div class="modal-body" id="articleBody">
				
				分页列表
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">确定</button>
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>	
</div>
<script>  
var article="";//选择文章对象
var jss={
		search:function(currentPage){
			var url="<%=basePath%>/article/listdofortask";
			var par={currentPage:currentPage,
					id:$('#artid').val(),
					title:$('#arttitle').val(),
					type:2,
					pageSize:5,
					m:Math.random()}
			$.post(url,par,function(d){
				$("#articleBody").html(d);
			});
		}
	}
  var txtgroup="";
  var imggroup="";
  var moreimggroup="";
  $(document).ready(function() {
		//添加步骤控件行
		var add = $('.copy').clone(true);
		var add2 = $('.copy2').clone(true);
		var add3 = $('.copy3').clone(true);
		txtgroup=$('.templateGroupText').clone(true);
		imggroup=$('.templateGroupImg').clone(true);
		moreimggroup=$('.templateGroupMoreImg').clone(true);
		$("#setpadd").click(function() {
			var clone = add.clone();
			$('#setpbox').append(clone);
			for(index=0;index<$('.copy').length;index++)
            {
            	$('.copy').eq(index).find('label').html('步骤' +  (index+1));
            }
		});
		//删除步骤
		$('#setpdel').click(function() {
			var len=$('.copy').length;
			if(len==1){
				alert("最少保留一个步骤");
				return;
			}
			var ld=$("#setpbox p:last");
			ld.remove(); 
		});
		//添加补充说明
		$("#setpadd2").click(function() {
			var clone = add2.clone();
			$('#setpbox2').append(clone);
			for(index=0;index<$('.copy2').length;index++)
            {
            	$('.copy2').eq(index).find('label').html((index+1)+'、');
            }
		});
		//删除补充说明
		$('#setpdel2').click(function() {
			var len=$('.copy2').length;
			var ld=$("#setpbox2 p:last");
			ld.remove(); 
		});
		//添加细则
		$("#setpadd3").click(function() {
			var clone = add3.clone();
			$('#setpbox3tbody').append(clone);
			for(index=0;index<$('.copy3').length;index++)
            {
            	$('.copy3').eq(index).find('td label').html((index+1));
            }
		});
		//删除细则
		$('#setpdel3').click(function() {
			var len=$('.copy3').length;
			var ld=$("#setpbox3 tr:last");
			ld.remove(); 
		});
		//添加文本组
		$('#addtxtgroup').click(function(){
			var clone=txtgroup.clone()
			$('#templateBox').append(clone);
			orderByGroup();
		});
		//添加图片组
		$('#addimggroup').click(function(){
			var clone=imggroup.clone()
			$('#templateBox').append(clone);
			orderByGroup();
		});
		//添加多图组
		$('#addmoreimggroup').click(function(){
			var clone=moreimggroup.clone()
			$('#templateBox').append(clone);
			orderByGroup();
		});
	    $("#uploadify").uploadify({
	     	'buttonImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/selectFile.gif',
	        'uploader':'<%=basePath%>/js/jquery.uploadify-v2.1.0/uploadify.swf',
	        'script':'<%=UploadPath%>/Upload/UploadFile?uploadFrom=1',//后台处理的请求
	        'cancelImg':'<%=basePath%>/js/jquery.uploadify-v2.1.0/cancel.png',
	        'folder':'uploads',//您想将文件保存到的路径
	        'queueID':'fileQueue',//与下面的id对应
	        'queueSizeLimit':1,
	        'wmode':'transparent',
	        'fileDesc':'文件',    
	    	'fileExt':'*.*', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
	       	'auto':false,
	        'multi':false,
	        'simUploadLimit':1,
	        'maxQueueSize': 1,
	        'successTimeout':600,
	         'buttonText':"BROWSER",
	        'fileSizeLimit' : '2MB',
	        onComplete: function (event, queueId, fileObj, response, data) {
	            var jsonstr = JSON.parse(response);
	             if(jsonstr.Status==1){
	            	 var fileinfo=jsonstr.Result.OriginalName+"#"+jsonstr.Result.RelativePath+"#"+jsonstr.Result.FileUrl;
	            	 appendAttachRow(fileinfo);
	             }else{
	            	 alert("上传失败");
	             }
	        }
	    });
	});
  //添加文本控件
  function addTxtControl(obj){
	  var div=$(obj).parent().find('.textGroup');
	  //console.log(div);
	  var item='<div class="textitem">说明文本:<input type="text" class="cltitle">默认值:<input type="text" class="cldefval"></div>';
	  div.append(item);
	  orderByGroup()
  }
  //删除文本控件
  function delTxtControl(obj){
	  var div=$(obj).parent().find('.textGroup div:last');
	  var len=$(obj).parent().find('.textGroup div').length;
	  if(len==1){
		  alert('控件组最少保留一个控件!');
		  return;
		}
	  div.remove();
  }
  //添加图片控件
  function addImgControl(obj){
	  var div=$(obj).parent().find('.imgGroup');
	  //console.log(div);
	  var item='<div class="imgitem">图片说明:<input type="text" class="cltitle"></div>';
	  div.append(item);
  }
  //删除图片控件
  function delImgControl(obj){
	  var div=$(obj).parent().find('.imgGroup div:last');
	  var len=$(obj).parent().find('.imgGroup div').length;
	  if(len==1){
		  alert('控件组最少保留一个控件!');
		  return;
		}
	  div.remove();
  }
  //删除控件组
  function delThisGroup(obj){
	  $(obj).parent().remove();
	  orderByGroup();
  }
  //重新排序各组序号
  function orderByGroup(){
	  var len=$('.boxno').each(function(index,el){
		  $(el).html(index+1+'.');
	  });
  }
  //选择文章
  function chooseArticle(obj){
	  jss.search(1);
	  article=obj;
	  $('#alertbox').modal('show');
  }
  
  //商家改版重新获余额
function businessChange(){  
	var templateList="<%=templatelist%>";
	initSelectTemplate(templateList,null);
	var paramaters={"businessId":$("#businessId").val()};
	var url = "<%=basePath%>/taskmanage/getbusinessbanlance";
	$.ajax({
		type : 'POST',
		url : url,
		data : paramaters,
		success : function(result) {
			$("#businessBalance").html(result+"元");
		}
	});
};


function initFunction(){
	 
	$("#businessId").on("change",businessChange);
	$("#businessId").change();
}
function realDeleteFiles(){
	if(deleteFiles!=""){
		var tempFiles=deleteFiles.split(";");
		for(var i=0;i<tempFiles.length;i++){
			var s=tempFiles[i].split("#");
			var url = "<%=UploadPath%>/upload/deletefile?fileName="+s[1];
			$.ajax({
					type : 'POST',
					url : url,
					data : "",
					success : function(result) {
			            //alert(result.Status);
					}
			});
		}
	}
}
//构建任务对象参数
function createTaskPar(){
	var task=new Object();
	task.taskTitle=$('#taskTitle').val();
	task.taskGeneralInfo=$('#taskGeneralInfo').val();
	task.businessId=$('#businessId').val();
	task.pusher=$('#businessId').find("option:selected").text();
	task.beginTime=$('#beginDate').val()+" 00:00:00";
	task.endTime=$('#endDate').val()+" 00:00:00";
	task.amount=$('#amount').val();
	task.status=0;
	task.auditCycle=$('#auditCycle').val();
	task.hotLine=$('#hotline').val();
	task.taskType=$('input[name="rTaskType"]:checked').val();
	return task;
}
//创建任务步骤表数据
function createTaskSetpPar(){
	var septsArr=new Array();
	//遍历步骤
   	$('#setpbox input').each(function(id,el){
   		var sept=new Object();
   		sept.linkTitle="";
   		sept.sortNo=id+1;
   		sept.setpType=1;
   		sept.content=$(el).val();
   		septsArr.push(sept);
   	});
	//遍历补充说明
	$('#setpbox2 input').each(function(id,el){
   		var sept=new Object();
   		sept.linkTitle="";
   		sept.sortNo=id+1;
   		sept.setpType=2;
   		sept.content=$(el).val();
   		septsArr.push(sept);
   	});
	//遍历细则
	$('#setpbox3 tbody tr').each(function(id,el){
   		var sept=new Object();
   		sept.linkTitle=$(el).find('.eltitle').val();
   		sept.sortNo=id+1;
   		sept.setpType=3;
   		sept.content=$(el).find('.elurl').val();
   		septsArr.push(sept);
   	});
	 return septsArr;
}
//创建分组参数
function createGroupPar(){
	var groupArr=new Array();
	//遍历控件组
	$('#templateBox .template').each(function(id,el){
		var TemplateGroup=new Object;
		if($(el).attr("class").indexOf("templateGroupText")>=0){
			//文本组
			TemplateGroup.groupType=1;//组类型
			TemplateGroup.title=$(el).find('.cltxt').val();//图片组说明
			var detailArr=new Array();
			//遍历组里面的控件
			$(el).find('.textGroup .textitem').each(function(id2,el2){
				var detail=new Object();
				detail.title=$(el2).find('.cltitle').val();//文本标题
				detail.name="key"+id+id2;//key
				detail.orderNum=id2+1;//排序
				detail.controlId=1;//控件类型
				detail.defaultValue=$(el2).find('.cldefval').val();//默认是
				detail.controlData='';
				detailArr.push(detail);//添加到数组中
			});
			//console.log(detailArr);
			TemplateGroup.templateList=detailArr;//数组为group的一个对象
		}
		else if($(el).attr("class").indexOf("templateGroupImg")>=0){
			//图片组
			TemplateGroup.groupType=2;
			TemplateGroup.title=$(el).find('.climg').val();
			var detailArr=new Array();
			//遍历控件
			$(el).find('.imgGroup .imgitem').each(function(id2,el2){
				var detail=new Object();
				detail.title=$(el2).find('.cltitle').val();
				detail.name="key"+id+id2;
				detail.orderNum=id2+1;
				detail.controlId=3;
				detail.defaultValue="";
				detail.controlData='';
				detailArr.push(detail);
			});
			//console.log(detailArr);
			TemplateGroup.templateList=detailArr;
		}
		else{
			//多图组
			TemplateGroup.groupType=3;
			TemplateGroup.title=$(el).find('.clmoreimg').val();
			var num=$(el).find('.imgitemnumn').val();
			console.log(num);
			var detailArr=new Array();
			for(var nx=0;nx<num;nx++){
				var detail=new Object();
				detail.title="";
				detail.name="key"+id+nx;
				detail.orderNum=nx+1;
				detail.controlId=3;
				detail.defaultValue="";
				detail.controlData='';
				detailArr.push(detail);
			}
			TemplateGroup.templateList=detailArr;
		}
		groupArr.push(TemplateGroup);
	});
	return groupArr;
}
//保存任务
function savetask(){
	
    var saveTaskReq=new Object();
    saveTaskReq.renRenTask=createTaskPar();
    saveTaskReq.taskSetps=createTaskSetpPar();
    saveTaskReq.templateGroup=createGroupPar();
    saveTaskReq.provinceCode=$('#provinceCode').val();
    saveTaskReq.cityCode=$('#cityCode').val();
    var json_data =JSON.stringify(saveTaskReq);
	console.log(saveTaskReq);
  return;
// 	if(!validPage(true)){
// 		return;
// 	}
	
	//组建参数对象
		var url = "<%=basePath%>/taskmanage/savetask";
		$.ajax({
					type : 'POST',
					url : url,
					data : {"data":json_data},
					success : function(result) {
						if (result > 0) {
							realDeleteFiles();
							alert("操作成功");
							window.location.href = window.location.href;
						} else {
							alert("操作失败:当前商户账户余额不足，不能新建任务");
						}
					}
		});

//var url = "<%=basePath%>/taskmanage/savetask";
// alert(json_data);
// $.post(url,json_data,function(d){
// 		alert(d);
// });
}

</script>