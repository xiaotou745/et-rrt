
//
function CheckSave(){
		// 任务基本信息校验
	if ($('#taskTitle').val().length < 5 || $('#taskTitle').val().length > 50) {
		alert('任务标题必须在5-50个字符之间');
		return false;
	}
	if ($('#taskGeneralInfo').val().length < 5
			|| $('#taskGeneralInfo').val().length > 20) {
		alert('任务描述必须在5-20个字符之间');
		return false;
	}
	if (!isInt($('#auditCycle').val())
			|| isNaN(parseInt($('#auditCycle').val()))||
			parseInt($('#auditCycle').val())>2147483648) {
		alert('审核周期必须为整数');
		return false;
	}
	if (parseInt($('#auditCycle').val()) < 1) {
		alert('审核周期必须大于1天');
		return false;
	}
	if (parseFloat($('#amount').val()) < 0.01
			|| isNaN(parseFloat($('#amount').val()))||
			parseInt($('#amount').val())>2147483648) {
		alert('地推员佣金必须大于等于0.01元');
		return false;
	}
	if (parseFloat($('#totalAmount').val()) < 0.01
			|| isNaN(parseFloat($('#totalAmount').val()))||
			parseInt($('#totalAmount').val())>2147483648) {
		alert('任务总佣金必须大于等于0.01元');
		return false;
	}
	if (parseFloat($('#totalAmount').val()) < parseFloat($('#amount').val())) {
		alert('任务总佣金必须大于等于地推员佣金');
		return false;
	}
	;
	if (!isInt($('#estimatedTime').val())
			|| isNaN(parseInt($('#estimatedTime').val()))||
			parseInt($('#estimatedTime').val())>2147483648) {
		alert('预计完成消耗必须为整数');
		return false;
	}
	if (parseInt($('#estimatedTime').val()) < 1) {
		alert('预计完成消耗必须大于等于1分钟');
		return false;
	}
	// 开始日期结束日期验证
	var startDate = $('#beginDate').val();
	var endDate = $('#endDate').val();
	if (startDate.length < 1 || endDate.length < 1) {
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
	var nowdate = myDate.getFullYear()
			+ (myDate.getMonth() < 10 ? ("0" + (myDate.getMonth() + 1)): (myDate.getMonth() + 1)
			+ (myDate.getDate() < 10 ? ("0" + myDate.getDate()): myDate.getDate()));
	if (parseInt(intStartDate) < parseInt(nowdate)) {
		alert("开始日期必须大于等于今天");
		return false;
	}
	if (parseInt(intEndDate) <= parseInt(nowdate)) {
		alert("结束日期必须大于今天");
		return false;
	}
	var reg = /^\d{11}$/;
	if ($('#hotline').val() != '' && !reg.test($('#hotline').val())) {
		alert('咨询电话必须为11位数字');
		return false;
	}

	if ($('input[name="rTaskType"]:checked').val() != 1) {
		if ($('#downUrl').val() == '') {
			alert('下载链接不能为空');
			return false;
		}
		if ($('#downUrl').val().length > 200) {
			alert('下载链接不能大于200个字符');
			return false;
		}
		if ($('#scanTip').val().length > 20) {
			alert('扫码说明不能大于20个字符');
			return false;
		}
		if ($('#reminder').val().length > 40) {
			alert('温馨提示不能大于40个字符');
			return false;
		}
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
				if(Number(num)<1||parseInt(num)<1||isNaN(parseInt(num))||parseInt(num)>10)
				{
					alert('多图组图片控件数量不能小于1或大于10');
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


//获取任务投放区域字符串，页面显示用
function gettaskregionremark(){
	var result="";
	var allLength=$("#taskregion input[type='checkbox']").length;
	var checkedAllLength=$("#taskregion input[type='checkbox']:checked").length;
	//如果全部都选中了，则表示全国
	if(allLength==checkedAllLength){
		result= "全国";
	}else{
		$('input:checkbox[name=chkTaskPro]:checked').each(function(indexPro,objPro) {
			var citydivid="#taskCity"+$(objPro).val();
	   		var allCitysLength=$(citydivid+" input[type='checkbox']").length;
	   		var checkedCitys=$(citydivid+" input[type='checkbox']:checked");
	   		var proName=$(objPro).next().html();
	   		//如果选中的是全省，则只保存省份的数据
	   		if(checkedCitys.length==allCitysLength){
	   			result+=(proName+":全省<br/>");
	   		}else{
	   			result+=(proName+":");
	   			//遍历省内选中的城市
	   			$(checkedCitys).each(function(indexCity,objCity) {
	   				if(indexCity==0){
	   					result+=$(objCity).next().html();
	   				}else{
	   					result+=(","+$(objCity).next().html());
	   				}
	   			});
	   			result+="<br/>";
	   		}
		});
	}
	$("#selectedregions").html(result);	
}
//任务投放区域数据
function gettaskregion(){
	var septsArr=new Array();
	var taskId=$('#hdtaskid').val();
	
	var allLength=$("#taskregion input[type='checkbox']").length;
	var checkedAllLength=$("#taskregion input[type='checkbox']:checked").length;
	//如果全部都选中了，则表示全国
	if(allLength==checkedAllLength){
		var all=new Object();
		all.taskId=taskId;
		all.parentCode=0;
		all.parentName="";
		all.cityCode=-1;
		all.cityName="全国";
   		septsArr.push(all);
   		return septsArr;
	}
	$('input:checkbox[name=chkTaskPro]:checked').each(function(indexPro,objPro) {
		var citydivid="#taskCity"+$(objPro).val();
   		var allCitysLength=$(citydivid+" input[type='checkbox']").length;
   		var checkedCitys=$(citydivid+" input[type='checkbox']:checked");
   		var proCode=$(objPro).val();
   		var proName=$(objPro).next().html();
   		//如果选中的是全省，则只保存省份的数据
   		if(checkedCitys.length==allCitysLength){
   	   		var pro=new Object();
   	   		pro.taskId=taskId;
   	   		pro.parentCode=0;
   	   		pro.parentName="";
   	   		pro.cityCode=proCode;
   	   		pro.cityName=proName;
   	   		septsArr.push(pro);
   		}else{
   			//遍历省内选中的城市
   			$(checkedCitys).each(function(indexCity,objCity) {
   		   		var city=new Object();
				city.taskId=taskId;
				city.parentCode=proCode;
				city.parentName=proName;
				city.cityCode=$(objCity).val();
				city.cityName=$(objCity).next().html();
				septsArr.push(city);
   			});
   		}

	});
	 return septsArr;
}
//修改任务时，初始化任务投放区域
function initTaskRegion(taskCityInfo){
	if(taskCityInfo==""){
		return;
	}
	//如果是全国，则选中所有的checkbox
	if(taskCityInfo=="-1"){
  	  $("input[name='chkTaskPro']").prop("checked","checked");//所有的省都选中
	  $("input[name='chkTaskCity']").prop("checked","checked");//所有的市都选中
		gettaskregionremark();
		return;
	}
	var procity=taskCityInfo.split("#");
	var temp="";
	var tempcity="";
	for(var i=0;i<procity.length;i++){
		temp=procity[i].split(":");
		tempcity=temp[1].split(",");
		if(temp[0]==""){//上级为空，则表示全省
			for(var k=0;k<tempcity.length;k++){
				$("#chkTaskPro"+tempcity[k]).prop("checked","checked");
				//省内的所有城市都选中
				$("#chkTaskPro"+tempcity[k]).change();
			}
		}else{
			//省份选中
			$("#chkTaskPro"+temp[0]).prop("checked","checked");
			//省内的城市也选中
			for(var j=0;j<tempcity.length;j++){
				$("#chkTaskCity"+tempcity[j]).prop("checked","checked");
			}
		}
	}
	gettaskregionremark();
}