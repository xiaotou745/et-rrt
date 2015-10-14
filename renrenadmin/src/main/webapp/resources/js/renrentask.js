$(function(){
	  $(' .input-group.date').datepicker({
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        autoclose: true
    });
	  $("input[type='text']").on('keypress',function(e){
			var  key=e.keyCode|| e.which;
			var oldValue=this.value;
			if((oldValue==""||oldValue=="0") && key==48){
				this.value="";
				return false;
			}
		  if(e.target.id=="auditCycle"||e.target.id=="taskTotalCount"||e.target.id=="taskCycle"){
				if (key<=57 && key>=48) { //数字
				   	return true;
				}
				return false;  
		  }else if(e.target.id=="amount"){
			  if (key == 46 && (oldValue.indexOf(".") >= 0 || oldValue == "")) {
					this.value="";
					return false;
			  }
				if ((key<=57 && key>=48)||key == 46) { //数字
					if(oldValue.indexOf(".")>0){
						var fix=oldValue.substring(oldValue.indexOf(".")+1,oldValue.length);
						if(fix.length>=2){
							return false;
						}
					}
				   	return true;
				}
				return false;  
		  }
		});
	  $("#provinceCode").on("change",provinceChange);
	  $("#cityCode").on("change",cityChange);
	  $('#selectAll').on('click',selectAllChange);
	  initFunction();
});

function appendAttachRow(fileinfo){
	var rowNum=$("#uploadfiletable tr").length-1;
	var newRowNum=rowNum+1;
	var row = $("<tr></tr>");
	row.append("<td>"+newRowNum+"</td>");
	row.append("<td>"+fileinfo.split("#")[0]+"</td>");
	row.append("<td><a href='javascript:void(0)' onclick='deleterow(this)'>删除</a></td>");
	$("#uploadfiletable").append(row);
	var oldAttach=$("#attachmentfiles").val();
	if(oldAttach==""){
		$("#attachmentfiles").val(fileinfo);
	}else{
		$("#attachmentfiles").val(oldAttach+";"+fileinfo);
	}
};
function deleterow(delobj){
	var trs=$("#uploadfiletable tr");
	var oldRowNum=trs.length-1;
	var deltr=$(delobj).parent().parent();
	var rownum=parseInt(deltr.children('td').eq(0).html());
	deltr.remove();
	var oldfiles=$("#attachmentfiles").val();
	var files=oldfiles.split(";");
	var newFiles="";
	for(var i=0;i<files.length;i++){
		if((i+1)!=parseInt(rownum)){
			if(newFiles==""){
				newFiles=files[i];	
			}else{
				newFiles+=(";"+files[i]);	
			}
		}
	}
	$("#attachmentfiles").val(newFiles);
	//将要删除的行的下面的所有行的行号重置
	if(rownum<oldRowNum){
		 for(var i=rownum+1;i<oldRowNum+1;i++){ 
		     var tr=$(trs[i]);
		     var td=tr.children('td').eq(0);
		     $(td).html(i-1);
		 }
	}
};
function provinceChange(){  
    try{  
        var pro=$(this).val();  
        var pro_city=$("#pro_city").val().split("#");
        
        var i,j,tmpprocity=new Array();  
        var tmpkeyvalue=new Array();  
        for(i=0;i<pro_city.length;i++){
        	tmpcity=pro_city[i].split("=");
            if(pro==tmpcity[0]){  
                tmpcity=tmpcity[1].split(";");  
                $("#cityCode").html("<option value='-1'>全部城市</option>");  
                for(j=0;j<tmpcity.length;j++){  
                	tmpkeyvalue=tmpcity[j].split("|");
                    $("#cityCode").append("<option value='"+tmpkeyvalue[0]+"'>"+tmpkeyvalue[1]+"</option>");     
                }
                break;
            }  
        }
        $("#divregion").html(""); 
        $("#selectAll").prop("checked",false);
    }catch(e){  
        alert(e);     
    }  
};
function cityChange(){  
	return;
//     try{  
//         $("#divregion").html("");  
//         $("#selectAll").prop("checked",false);
//     	var pro=$(this).val();  
//         var pro_city=$("#city_region").val().split("#");
        
//         var i,j,tmpprocity=new Array();  
//         var tmpkeyvalue=new Array();  
//         for(i=0;i<pro_city.length;i++){
//         	tmpcity=pro_city[i].split("=");
//             if(pro==tmpcity[0]){  
//                 tmpcity=tmpcity[1].split(";");  
//                 $("#divregion").html("");  
//                 for(j=0;j<tmpcity.length;j++){  
//                 	tmpkeyvalue=tmpcity[j].split("|");
//                     $("#divregion").append("<input type='checkbox' name='regionCode"+tmpkeyvalue[0]+"' onclick='chanageSelectAll()' value='"+tmpkeyvalue[0]+"' /> <label>"+tmpkeyvalue[1]+"</label>");     
//                 }  
//             }  
//	     	 break;
//         } 
//     }catch(e){  
//         alert(e);     
//     }  
};
//全选全消
function selectAllChange() {
	var checkedOfAll = $("#selectAll").prop("checked");
	$("#divregion input[type='checkbox']").prop("checked", checkedOfAll);
};
function chanageSelectAll(){
	var allNum=$("#divregion input[type='checkbox']").length;
	var checkedNum=$("#divregion input[type='checkbox']:checked").length;
	if(allNum==checkedNum){
		$("#selectAll").prop("checked",true);
	}else{
		$("#selectAll").prop("checked",false);
	}
};

function validPage(){
	var hasempty=false;
	$("select").each(function(index,e){
		if($(e).val()==""||$(e).val()==null){
			alert($(this).parent().prev().html().replace(": ","")+"不能为空");
			hasempty=true;
			return false;
		}
	});
	if(hasempty){
		return false;
	}
	$("input[type='text']").each(function(index,e){
		if($(e).val()==""||$(e).val()==null){
			if(e.id=="beginDate"||e.id=="endDate"){
				alert("开始日期或结束日期不能为空");
			}else{
				alert($(this).parent().prev().html().replace(": ","")+"不能为空");
			}
			hasempty=true;
			return false;
		}else if($(e).val().length>200){
			  if(!(e.id=="auditCycle"||e.id=="taskTotalCount"||e.id=="amount"||e.id=="taskCycle")){
					alert($(this).parent().prev().html().replace(": ","")+"不能超过200个字符");
					hasempty=true;
					return false;  
			  }
		}
	});
	if(hasempty){
		return false;
	}
	$("textarea").each(function(index,e){
		if($(e).val()==""||$(e).val()==null){
			alert($(this).parent().prev().html().replace(": ","")+"不能为空");
			hasempty=true;
			return false;
		}else if($(e).val().length>200){
			alert($(this).parent().prev().html().replace(": ","")+"不能超过200个字符");
			hasempty=true;
			return false;
		}
	});
	if(hasempty){
		return false;
	}
	
	  var startDate = $('#beginDate').val();
	  var endDate = $('#endDate').val();
	  var intStartDate = startDate.replace(/-/g, "");
	  var intEndDate = endDate.replace(/-/g, "");
	  if (intStartDate > intEndDate) {
	      alert('开始日期不能大于结束日期');
	      $('#beginDate').val("");
	      return false;
	  }

	var pagestart=new Date(startDate);
	var pageComapreDate=pagestart.getFullYear()+""+(pagestart.getMonth()+1)+""+pagestart.getDate();
	var myDate = new Date();
	var nowdate=myDate.getFullYear()+""+(myDate.getMonth()+1)+""+myDate.getDate();
	if(pageComapreDate<nowdate){
		alert("开始日期必须大于等于今天");
		return;
	}

  $("input[type='text']").each(function(index,e){
		  if(e.id=="auditCycle"||e.id=="taskTotalCount"||e.id=="amount"||e.id=="taskCycle"){
				if(isNaN($(e).val())){
				 	alert($(this).parent().prev().html().replace(": ","")+"必须为数字");
					hasempty=true;
					return false;  
				}else if(parseFloat($(e).val())>100000){
				  	alert($(this).parent().prev().html().replace(": ","")+"不能超过100000");
					hasempty=true;
					return false;  
				}else if(parseFloat($(e).val())<1){
					alert($(this).parent().prev().html().replace(": ","")+"必须大于1");
					hasempty=true;
					return false;  
				}
				if(e.id=="amount"&&$(e).val().indexOf(".")>0){
					var fix=$(e).val().substring($(e).val().indexOf(".")+1,$(e).val().length);
					if(fix.length>2){
						alert($(this).parent().prev().html().replace(": ","")+"最多只能有两位小数");
						hasempty=true;
						return false;  
					}else if(fix.length==0){
						alert($(this).parent().prev().html().replace(": ","")+"小数点后不能为空");
						hasempty=true;
						return false; 
					}
			  }
		  }  
	});
	if(hasempty){
		return false;
	}
//	var checkedNum=$("#divregion input[type='checkbox']:checked").length;
//	if($("#cityCode").val()!="-1"&&checkedNum==0){
//		alert("城市不是全部城市时,请至少选择一个区域");
//		return;
//	}
	return true;
};
function initSelectTemplate(templateList,selectedTemplateId){
	var data=templateList.split("#");
	var i,j,tmpTemplate=new Array();  
    var tmpkeyvalue=new Array();
    var tmpOption=new Array();
    $("#snapshotTemplateId").html("");
    for(i=0;i<data.length;i++){
    	tmpTemplate=data[i].split("=");
        if($("#businessId").val()==tmpTemplate[0]){  
        	tmpkeyvalue=tmpTemplate[1].split(";");  
            for(j=0;j<tmpkeyvalue.length;j++){  
            	tmpOption=tmpkeyvalue[j].split("|");
            	if(tmpOption[0]==selectedTemplateId&&selectedTemplateId!=null&&selectedTemplateId!=""){
            		 $("#snapshotTemplateId").append("<option selected='selected' value='"+tmpOption[0]+"'>"+tmpOption[1]+"</option>");     
            	}else{
            		 $("#snapshotTemplateId").append("<option value='"+tmpOption[0]+"'>"+tmpOption[1]+"</option>");     	
            	}
            }
            return;
        }  
    }
};