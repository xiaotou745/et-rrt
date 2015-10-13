$(function(){
	  $("#addrow").on("click",addrow);
	  $("#addImgRow").on("click",addImgRow);
	  $("#resetpage").on("click",resetpage);
});
function resetpage(){
	if (!confirm("确定要放弃已经录入的数据吗？")){
		return;
	}
	window.location.href = window.location.href;
}
function addrow(){
	var rowNum=$("#templatetable tr").length-1;
	var newRowNum=rowNum+1;
	var row = $("<tr></tr>");
	row.append("<td>"+newRowNum+"</td>");
	row.append("<td>文本框</td>");
	row.append("<td><input type='text' class='form-control' placeholder='性别' value='' name='title"+newRowNum+"' id='title"+newRowNum+"' /></td>");
	row.append("<td><input type='text' class='form-control' placeholder='sex' value='' name='name"+newRowNum+"' id='name"+newRowNum+"' /></td>");
	row.append("<td><input type='text' class='form-control' placeholder='女' value='' name='defaultvalue"+newRowNum+"' id='defaultvalue"+newRowNum+"' /></td>");
	row.append("<td><a href='javascript:void(0)' onclick='deleterow(this)'>删除</a></td>");
	$("#templatetable").append(row);
};
function addImgRow(){
	var rowNum=$("#templatetable tr").length-1;
	var newRowNum=rowNum+1;
	var row = $("<tr></tr>");
	row.append("<td>"+newRowNum+"</td>");
	row.append("<td>图片上传</td>");
	row.append("<td><input type='text' placeholder='身份证正面照片' class='form-control' value='' name='title"+newRowNum+"' id='title"+newRowNum+"' /></td>");
	row.append("<td><input type='text' placeholder='face_pic' class='form-control' value='' name='name"+newRowNum+"' id='name"+newRowNum+"' /></td>");
	row.append("<td></td>");
	row.append("<td><a href='javascript:void(0)' onclick='deleterow(this)'>删除</a></td>");
	$("#templatetable").append(row);
};
function deleterow(delobj){
	var trs=$("#templatetable tr");
	var oldRowNum=trs.length-1;
	var deltr=$(delobj).parent().parent();
	var rownum=parseInt(deltr.children('td').eq(0).html());
	deltr.remove();
	//将要删除的行的下面的所有行的行号重置
	if(rownum<oldRowNum){
		 for(var i=rownum+1;i<oldRowNum+1;i++){ 
		     var tr=$(trs[i]);
		     var td=tr.children('td').eq(0);
		     $(td).html(i-1);
		 }
	}
};
function validPage(){
	if($("#businessId").val()==""||$("#businessId").val()==null){
		alert("所属商家不能为空");
		return "";
	}

	if($("#tempName").val()==""){
		alert("模板名称不能为空");
		return "";
	}
	if($("#tempRemark").val()==""){
		alert("模板说明不能为空");
		return "";
	}
	if($("#tempName").val().length>200){
		alert("模板名称不能超过200个字符");
		return "";
	}
	if($("#tempRemark").val().length>200){
		alert("模板说明不能超过200个字符");
		return "";
	}
	var trs=$("#templatetable tr");
	var oldRowNum=trs.length-1;
	var haserror=false;
	var childparamaters="";
	for(var i=1;i<=oldRowNum;i++){ 
	     var rownum=$(trs[i]).children('td').eq(0).html();
	     childparamaters+=("#ordernum="+rownum+";");
	     var controlType=$(trs[i]).children('td').eq(1).html();
			if(controlType=="文本框"){
				 childparamaters+=("controlid=1;");
			}else if(controlType=="图片上传"){
				 childparamaters+=("controlid=3;");
			}
	    
	     $(trs[i]).find("input").each(function(index,e){
	    	 if(e.id.indexOf("title")>=0){
		    	 childparamaters+=("title="+$(e).val()+";");
	    	 }else if(e.id.indexOf("defaultvalue")>=0){
	    		 childparamaters+=("defaultvalue="+$(e).val()+";");
	    	 }else{
	    		 childparamaters+=("name="+$(e).val()+";");
	    	 }
	    	 if($(e).val()==""){
	    		 if(e.id.indexOf("defaultvalue")<0){
			    	 if(e.id.indexOf("title")>=0){
			    		 alert("第"+rownum+"行说明文本不能为空");
			    	 }else{
			    		 alert("第"+rownum+"行名称不能为空");
			    	 }
			    	 haserror=true;
			    	 return false;
		    	 }
	    	 }else if($(e).val().length>200){
		    	 if(e.id.indexOf("title")>=0){
		    		 alert("第"+rownum+"行说明文本不能超过200个字符");
		    	 }else{
		    		 alert("第"+rownum+"行名称不能超过200个字符");
		    	 }
		    	 haserror=true;
		    	 return false;
	    	 }
	     });
	     if(haserror){
	    	 return "";
	     }
	 }
	var inputNames=$("input[id^='title']");
	inputNames.each(function(index,e){
		inputNames.each(function(index2,e2){
			if($(e).val()==$(e2).val()&&index!=index2){
				 alert("第"+(index+1)+"行的说明文本不能和第"+(index2+1)+"的说明文本一致");
				 haserror=true;
		    	 return false;
			}
		});
	     if(haserror){
	    	 return false;
	     }
	});
    if(haserror){
    	return "";
    }
	var inputNames=$("input[id^='name']");
	inputNames.each(function(index,e){
		inputNames.each(function(index2,e2){
			if($(e).val()==$(e2).val()&&index!=index2){
				 alert("第"+(index+1)+"行的名称不能和第"+(index2+1)+"的名称一致");
				 haserror=true;
		    	 return false;
			}
		});
	     if(haserror){
	    	 return false;
	     }
	});
    if(haserror){
    	return "";
    }	
    return childparamaters;
};
