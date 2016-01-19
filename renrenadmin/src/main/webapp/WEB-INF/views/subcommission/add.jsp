<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
%>

<div class="wrapper wrapper-content animated fadeInRight">
<form method="POST" action="#" class="form-horizontal" id="searchForm">
	<fieldset>
			<legend>分佣策略信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>策略名称: </label>
								<div class="col-sm-8">
									<input maxlength="50" type="text" class="form-control" name="StrategyName" id="StrategyName" value=""/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>策略描述: </label>
								<div class="col-sm-8">
									<textarea maxlength="200"  rows="3" cols="35" class="form-control" name="Remark" id="Remark" ></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>层级: </label>
								<div class="col-sm-1">
									<label class="col-sm-1 control-label" id="LevalCount">1</label>
								</div>
	  							  <label class="col-sm-1 control-label">层</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>累计分佣总数: </label>
								<div class="col-sm-2">
									<label class="col-sm-2 control-label" id="Percentage">0</label>
								</div>
	  							  <label class="col-sm-1 control-label">%</label>
							</div>
						</div>
					</div>
				</div>
			</div>
	</fieldset>
	<fieldset>
	<legend>分佣比例</legend>
	<div class="row">
		
		<div class="col-lg-12" id="collist">
			<div class="row">
				<a  href="javascript:void(0)" id="addcol">添加分佣层级</a>
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label ClassNo"><span style="color:red;">*</span>1级分佣: </label>
						<div class="col-sm-5">
							<input type="text" class="form-control StrategyChildItem"  onBlur="ItemCheck(this)"/>
						</div>
						<div class="col-sm-3" style="line-height: 33px; padding-left: 3px;">
	  						%  
	  					<a  href="javascript:void(0)" onclick="delCol(this)">删除</a>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	</fieldset>
		<div class="row">
			<div class="col-lg-4">
				<button type="button" class="btn btn-w-m btn-primary" id="save" style="margin-left: 3px; height: 30px;">保存</button>
				<button type="button" class="btn btn-w-m btn-primary" id="jisuanqi" style="margin-left: 3px; height: 30px;">分佣计算器</button>
			</div>
		</div>
	</form>
</div>
<!-- 开始 --------------------------------------------------------------------------------------------------------------------->
	<div tabindex="-1" class="modal inmodal" id="jisuanqiBox" role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<h5 class="modal-title" >分佣计算器</h5>				
			</div>
			<small class="font-bold">
				<div class="modal-body">
					 <div class="ibox-content">
                            <form class="form-horizontal">
                                <div class="form-group" >
                                	<label class="col-lg-3 control-label ">地推员佣金</label>
									<div class="col-lg-6">
										<input type="佣金" placeholder="金额" class="form-control brokerage">
                                    </div>
                                    <label class="col-lg-0 control-label">元</label>
									<button class="btn btn-sm btn-white control-label total" type="button">计算</button>
                                </div>
                            </form>
                            
                            <table class="table">
	                            <thead>
	                            <tr>
	                                <th>级别</th>
	                                <th>分佣比例</th>
	                                <th>可获得分红</th>
	                            </tr>
	                            </thead>
	                            <tbody id="fenyong">
	                            
	                            </tbody>
	                            
	                            <!----------------------------------- 这是要clone复制的对象 级别列表 ----------------------------------------------->
	                            <tbody id="copy" style="display:none;" >
	                            	<tr>
		                                <td></td>
		                                <td></td>
		                                <td></td>
		                           	 </tr>
	                            </tbody>
	                            <!----------------------------------- 这是要clone复制的对象 ----------------------------------------------->
	                        </table>
                        
                        </div>
						
				
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
	<!-- 结束 -------------------------------------------------------------------------------------------------- -->

<script type="text/javascript">
//控件模板
var itemTemp='<div class="row">'
+'<div class="col-lg-3">'
+'<div class="form-group">'
+'<label class="col-sm-4 control-label ClassNo"><span style="color:red;">*</span>2级分佣: </label>'
+'<div class="col-sm-5">'
+'<input type="text" class="form-control StrategyChildItem" onBlur="ItemCheck(this)"/>'
+'</div>'
+'<div class="col-sm-3" style="line-height: 33px; padding-left: 3px;">'
+'%'
+'<a  href="javascript:void(0)" onclick="delCol(this)">删除</a>'
+'</div></div></div></div>'
//控件模板END
//全局变量
//全局变量END

//页面载入后执行事件
$(function(){
	
});
//页面载入后执行事件END


//添加层级
$('#addcol').click(function(){
	$('#collist').append(itemTemp);
	OrderBy();
});
//添加层级end
//删除层级
function delCol(obj){
	  if($('.StrategyChildItem').length==1)
		{
		 alert('最少保留一个层级!');
		 return;
		}
	  $(obj).parent().parent().parent().remove();
	  OrderBy();
  }
//删除层级END
//排序
function OrderBy(){
	 $('.ClassNo').each(function(index,el){
		  var html='<span style="color:red;">*</span>'+(index+1)+'级分佣: ';
		  $(el).html(html);
	  });
	 var  str=$('.ClassNo').length;
	 //排序之后 计算总层数
	 $('#LevalCount').html(str);
	//数字验证通过计算总额
		var totalPercentage=0;
		$('.StrategyChildItem').each(function(index,el){
			//console.log(el);
			var str=parseFloat($(el).val());
			if(str>0)
			{
				totalPercentage+=str;
			}
		});
		$('#Percentage').html(totalPercentage.toFixed(2));
}
//排序end
//计算总百分比
function ItemCheck(obj){
	var Percentage=$(obj).val();
	if(parseFloat(Percentage)<=0||//小于等于0
			isNaN(Percentage)||//非数字
			parseFloat(Percentage)>99.99||//大于99.99
			Percentage.length>5
			)
	{
		alert('分佣比例需要在0%-99.99%之间');
		$(obj).val('');
		$(obj)[0].focus();//获取焦点 
		return;
	}
	var parStr=$(obj).parent().parent().parent().parent().prev().find('.StrategyChildItem').val();
	var parStr=parseFloat(parStr,100);
	if(parseFloat(Percentage)>=parStr){
		alert('下级分佣比例必须小于上级分佣比例!');
		$(obj).val('');
		$(obj)[0].focus();//获取焦点 
		return;
		}
	//数字验证通过计算总额
	var totalPercentage=0;
	$('.StrategyChildItem').each(function(index,el){
		//console.log(el);
		var str=parseFloat($(el).val());
		if(str>0)
		{
			totalPercentage+=str;
		}
	});
	if(totalPercentage>99.99)
	{
		alert('累计分佣总数超过99.99%!');
		$(obj).val('');
		$(obj)[0].focus();//获取焦点 
		return;
	}
	$('#Percentage').html(totalPercentage.toFixed(2));
}
//计算总百分比END
//构建策略参数
function CreateStrategy(){
	var strategy=new Object();
	strategy.strategyName=$('#StrategyName').val();
	strategy.remark=$('#Remark').val();
	strategy.levalCount=parseInt($('#LevalCount').html());
	strategy.percentage=parseFloat($('#Percentage').html());
	return strategy;
}
//构建策略参数END
//构建Child参数 
function CreateChildList(){
	var childList=new Array();
	$('.StrategyChildItem').each(function(index,el){
		var str=parseFloat($(el).val());
		if(str>0)
		{
			var strategyChild=new Object();
			strategyChild.levalNo=(index+1);
			strategyChild.percentage=str;
			childList.push(strategyChild);
		}
	});
	return childList;
}

//构建Child参数 END
//保存及验证
$('#save').click(function(){
	if(!SaveCheck())
		return;
	
	//验证通过
	var strategy=CreateStrategy();
	strategy.childList=CreateChildList();
	var url = "<%=basePath%>/subcommission/save";
	var json_data =JSON.stringify(strategy);
	console.log(json_data);
	var par={"data":json_data};
	$.post(url,par,function(d){
		//alert(d);
		if(d==1)
		{
			alert('添加分佣策略成功');
			window.location.href="<%=basePath%>/subcommission/list";
		}else if(d>1)
		{
			alert('当前设置的分佣总比例大于系统设定的比例,当前系统设置为'+d+'%');
		}else if(d==-3)
		{
			alert('提交数据中层级不一致');
		}
		else if(d==-4)
		{
			alert('分佣总比例与各层级分佣总和不一致!');
		}
		else
		{
			alert('添加分佣策略失败,请重试');
		}
	});
});
function SaveCheck(){
	if($('#StrategyName').val()==''){
		alert('策略名称不能为空!');
		return false;
		}
	if($('#Remark').val()==''){
		alert('策略描述不能为空!');
		return false;
		}
	var flag=true;
	$('.StrategyChildItem').each(function(index,el){
		if($(el).val()==''||parseFloat($(el).val())>99.99||parseFloat($(el).val())<=0)
		{
			alert('尚有层级分佣比例未正确填写!');
			flag=false;
			return flag;
		}
	});
	return flag;
}

$('#jisuanqi').click(function(){
	$(".total").unbind("click");
	$('#fenyong').html('');
	 var arr = new Array();
	 arr=[];
	 var  arr2= CreateChildList();
	 console.log(arr2);
	 for(var i=0;i<arr2.length;i++)
	 {
		 arr.push({"levalNo":arr2[i].levalNo+"级","percentage":+arr2[i].percentage+"%"});
	 }
	
	 for(var i=0;i<arr.length;i++){
		 var levalNo = arr[i].levalNo;
		 var percentage = arr[i].percentage;
		 var obj = $("#copy").clone(true).show();
		 obj.find("tr td").eq(0).html(levalNo);
		 obj.find("tr td").eq(1).html(percentage);
		 $("#fenyong").append(obj.html());
	 }
	  $(".total").click(function() {
		  	var brokerage = $(".brokerage").val();//获取佣金
		  	if(brokerage==""){ 
		  		alert("请填写佣金！");
		  		return false;
		  	}
	  		if(isNaN(brokerage)){
				alert("佣金必须是数字！");
				return false;
			}
	  		$("#fenyong td:nth-child(2)").each(function(i){
				var  scale= parseFloat($(this).text())*0.01; //获取比例
				var  CenCommission = (brokerage * scale).toFixed(2)+"元";  //分佣
				$(this).next().html(CenCommission);
			});
			
		});
	  $('#jisuanqiBox').modal('show');
});
//保存验证END
</script>
