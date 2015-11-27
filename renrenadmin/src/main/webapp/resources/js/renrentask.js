
//
function CheckSave(){
	 
	//任务基本信息校验
	if($('#taskTitle').val().length<=5||$('#taskTitle').val().length>50)
	{
		alert('任务标题必须在5-50个字符之间');
		return false;
	}
	if($('#taskGeneralInfo').val().length<=5||$('#taskGeneralInfo').val().length>200)
	{
		alert('任务描述必须在5-200个字符之间');
		return false;
	}
	if(parseInt($('#auditCycle').val())<1||isNaN(parseInt($('#auditCycle').val())))
	{
		alert('审核周期必须大于1天');
		return false;
	}
	if(parseFloat($('#amount').val())<=0||isNaN(parseFloat($('#amount').val())))
	{
		alert('单次佣金必须大于0元');
		return false;
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
		var pagestart=new Date(startDate);
		var pageStartDate=pagestart.getFullYear()+""+(pagestart.getMonth()+1)+""+pagestart.getDate();
		var myDate = new Date();
		var nowdate=myDate.getFullYear()+""+(myDate.getMonth()+1)+""+myDate.getDate();
		if(parseInt(pageStartDate)<parseInt(nowdate)){
			alert("开始日期必须大于等于今天");
			return false;
		}
		var pageEnd=new Date(endDate);
		var pageEndDate=pageEnd.getFullYear()+""+(pageEnd.getMonth()+1)+""+pageEnd.getDate();
		if(parseInt(pageEndDate)<=parseInt(nowdate)){
			alert("结束日期必须大于今天");
			return false;
		}
		var flag=true;
		//步骤信息不能为空
		$('#setpbox input').each(function(id,el){
			if($(el).val().length<1)
			{
				alert('步骤信息不能为空');
				return false;
			}
	   	});
		if(!flag) return flag;
		//遍历补充说明
		$('#setpbox2 input').each(function(id,el){
			if($(el).val().length<1)
			{
				alert('补充说明不能为空');
				return false;
			}
	   	});
		if(!flag) return flag;
		//遍历细则
		$('#setpbox3 tbody tr').each(function(id,el){
	   		if($(el).find('.eltitle').val().length<1){
	   			alert('细则链接标题不能为空');
				return false;
	   			}
	   		if($(el).find('.elurl').val().length<1)
	   			{
	   			alert('细则链接地址不能为空');
				return false;
	   			}
	   	});
		if(!flag) return flag;
		$('#templateBox .template').each(function(id,el){
			if($(el).attr("class").indexOf("templateGroupText")>=0){
				if($(el).find('.cltxt').val().length<1){
				alert('文本组标题不能为空')
				return false;
				}
				$(el).find('.textGroup .textitem').each(function(id2,el2){
					if($(el2).find('.cltitle').val().length<1){
						alert('文本控件说明不能为空')
						return false;
						}//文本标题
				});
			}
			else if($(el).attr("class").indexOf("templateGroupImg")>=0){
				//图片组
				if($(el).find('.climg').val().length<1){
					alert('图片组标题不能为空')
					return false;
				}
				//遍历控件
				$(el).find('.imgGroup .imgitem').each(function(id2,el2){

					if($(el2).find('.cltitle').val().length<1){
						alert('图片控件说明不能为空')
						return false;
					}
				});
			}
			else{
				if($(el).find('.clmoreimg').val().length<1){
					alert('多图组标题不能为空')
					return false;
				}
				var num=$(el).find('.imgitemnumn').val();
				if(Number(num)<1){
					alert('多图组图片控件数量不能小于0')
					return false;
					}
			}

		});
}