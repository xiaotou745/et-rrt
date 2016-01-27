
//
function CheckSave(){
	 
	//任务基本信息校验
	if($('#taskTitle').val().length<5||$('#taskTitle').val().length>50)
	{
		alert('任务标题必须在5-50个字符之间');
		return false;
	}
	if($('#taskGeneralInfo').val().length<5||$('#taskGeneralInfo').val().length>20)
	{
		alert('任务描述必须在5-20个字符之间');
		return false;
	}
	if(!isInt($('#auditCycle').val()))
	{
		alert('审核周期必须为整数');
		return false;
	}
	var reg=/^\d{11}$/;
	if($('#hotline').val()!=''&&!reg.test($('#hotline').val()))
	{
		alert('咨询电话必须为11位数字');
		return false;
	}
	if(parseInt($('#auditCycle').val())<1||isNaN(parseInt($('#auditCycle').val())))
	{
		alert('审核周期必须大于1天');
		return false;
	}
	if(parseFloat($('#amount').val())<0.01||isNaN(parseFloat($('#amount').val())))
	{
		alert('地推员佣金必须大于等于0.01元');
		return false;
	}
	if(!isInt($('#estimatedTime').val()))
	{
		alert('预计完成消耗必须为整数');
		return false;
	}
	if(parseInt($('#estimatedTime').val())<1||isNaN(parseInt($('#estimatedTime').val())))
	{
		alert('预计完成消耗必须大于等于1分钟');
		return false;
	}
	if(parseFloat($('#totalAmount').val())<0.01||isNaN(parseFloat($('#totalAmount').val())))
	{
		alert('任务总佣金必须大于等于0.01元');
		return false;
	}
	if(parseFloat($('#totalAmount').val())<parseFloat($('#amount').val()))
	{
		alert('任务总佣金必须大于等于地推员佣金');
		return false;
	};
	if($('input[name="rTaskType"]:checked').val()!=1)
	{
		if($('#downUrl').val()=='')
			{
			alert('下载链接不能为空');
			return false;
			}
		if($('#downUrl').val().length>200){
		alert('下载链接不能大于200个字符');
		return false;
		}
		if($('#scanTip').val().length>20)
		{
		alert('扫码说明不能大于20个字符');
		return false;
		}
		if($('#reminder').val().length>40)
		{
		alert('温馨提示不能大于40个字符');
		return false;
		}
	}
	//开始日期结束日期验证
	  var startDate = $('#beginDate').val();
	  var endDate = $('#endDate').val();
	  if(startDate.length<1||endDate.length<1){
		  alert('开始时间或结束时间不能为空');
			return false;
		  }
	  var intStartDate = startDate.replace(/-/g, "");
	  var intEndDate = endDate.replace(/-/g, "");
	  if (intStartDate > intEndDate) {
	      alert('开始日期不能大于结束日期');
	      $('#beginDate').val("");
	      return false;
	  }
		var myDate = new Date();
		var nowdate=myDate.getFullYear()+""+(myDate.getMonth()<10?("0"+(myDate.getMonth()+1)):(myDate.getMonth()+1)+""+(myDate.getDate()<10?("0"+myDate.getDate()):myDate.getDate()));
		if(parseInt(intStartDate)<parseInt(nowdate)){
			alert("开始日期必须大于等于今天");
			return false;
		}
		if(parseInt(intEndDate)<=parseInt(nowdate)){
			alert("结束日期必须大于今天");
			return false;
		}
		var flag=true;
		//步骤信息不能为空
		$('#setpbox input').each(function(id,el){
			if($(el).val().length<1){
				alert('步骤信息不能为空');
				flag=false;
				return false;
			}
			if($(el).val().length>100){
				alert('步骤信息不能大于100个字符');
				flag=false;
				return false;
			}
	   	});
		if(!flag) return flag;
		//遍历补充说明
		$('#setpbox2 input').each(function(id,el){
			if($(el).val().length<1){
				flag=false;
				alert('补充说明不能为空');
				return false;
			}
			if($(el).val().length>100){
				flag=false;
				alert('补充说明不能大于100个字符');
				return false;
			}
	   	});
		if(!flag) return flag;
		//遍历细则
		$('#setpbox3 tbody tr').each(function(id,el){
	   		if($(el).find('.eltitle').val().length<1){
	   			alert('细则链接标题不能为空');
	   			flag=false;
				return false;
	   			}
	   		if($(el).find('.eltitle').val().length>100){
	   			alert('细则链接标题不能大于100个字符');
	   			flag=false;
				return false;
	   			}
	   		if($(el).find('.elurl').val().length<1){
	   			flag=false;
	   			alert('细则链接地址不能为空');
				return false;
	   			}
	   		if($(el).find('.elurl').val().length>100){
	   			flag=false;
	   			alert('细则链接地址不能大于100个字符');
				return false;
	   			}
	   		//if(!flag) return flag;
	   	});
		
		if(!flag) return flag;
		
		//不是签约任务 后面的就不继续验证了
		if($('input[name="rTaskType"]:checked').val()!=1)
		{
			return true;
		}
		$('#templateBox .template').each(function(id,el){
			if($(el).attr("class").indexOf("templateGroupText")>=0){
				if($(el).find('.cltxt').val().length<1){
				alert('文本组标题不能为空');
				flag=false;
				return false;
				}
				if($(el).find('.cltxt').val().length>50){
					alert('文本组标题不能大于50个字符');
					flag=false;
					return false;
					}
				$(el).find('.textGroup .textitem').each(function(id2,el2){
					if($(el2).find('.cltitle').val().length<1){
						alert('文本控件说明不能为空');
						flag=false;
						return false;
						}//文本标题
					if($(el2).find('.cltitle').val().length>100){
						alert('文本控件说明不能大于100个字符');
						flag=false;
						return false;
						}//文本标题
				});
				if(!flag) return flag;
			}
			else if($(el).attr("class").indexOf("templateGroupImg")>=0){
				//图片组
				if($(el).find('.climg').val().length<1){
					alert('图片组标题不能为空');
					flag=false;
					return false;
				}
				if($(el).find('.climg').val().length>50){
					alert('图片组标题不能大于50个字符');
					flag=false;
					return false;
				}
				if(!flag) return flag;
				//遍历控件
				$(el).find('.imgGroup .imgitem').each(function(id2,el2){

					if($(el2).find('.cltitle').val().length<1){
						alert('图片控件说明不能为空');
						flag=false;
						return false;
					}
					if($(el2).find('.cltitle').val().length>100){
						alert('图片控件说明不能大于100个字符');
						flag=false;
						return false;
					}
					if(!flag) return flag;
				});
				if(!flag) return flag;
			}
			else{
				if($(el).find('.clmoreimg').val().length<1){
					alert('多图组标题不能为空');
					flag=false;
					return false;
				}
				if($(el).find('.clmoreimg').val().length>50){
					alert('多图组标题不能大于50个字符');
					flag=false;
					return false;
				}
				if(!flag) return flag;
				var num=$(el).find('.imgitemnumn').val();
				if(Number(num)<1||parseInt(num)<1||isNaN(parseInt(num)))
				{
					alert('多图组图片控件数量不能小于1');
					flag=false;
					return false;
					}
				if(!flag) return flag;
			}
			if(!flag) return flag;
		});
	    return flag;
}

function isInt(n)
{
     return n == Math.abs( parseInt( n ) );
}