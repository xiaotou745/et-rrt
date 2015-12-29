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
			</div>
		</div>
	</form>
</div>
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
		alert('分佣比例需要在0-99.99之间');
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
		}else if(d==-2)
		{
			alert('当前设置的分佣总比例大于系统设定的比例');
		}else if(d==-3)
		{
			alert('提交数据中层级不一致');
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
		if($(el).val()=='')
		{
			alert('尚有层级分佣比例未正确填写!');
			flag=false;
			return flag;
		}
	});
	return flag;
}
//保存验证END
</script>
