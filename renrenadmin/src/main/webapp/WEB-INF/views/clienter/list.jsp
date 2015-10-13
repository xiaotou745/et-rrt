<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%@page import="com.renrentui.renrencore.util.EnumHelper"%>
<%@page import="com.renrentui.renrencore.enums.ClienterStatus"%>
<%String basePath =PropertyUtils.getProperty("java.renrenadmin.url");%>
<div class="row"  style="margin-top: 15px;margin-left: 5px;">
	<div class="col-lg-2">
		<div class="form-group">
				<input id="txtClienterName" type="tel" name="txtClienterName" placeholder="地推员姓名" class="form-control"/>
		</div>
	</div>
	<div class="col-lg-3">
		<div class="form-group">
			<div class="col-sm-4" style="line-height:30px;font-size:14px;">
		     	审核状态:
		    </div>
			<div class="col-sm-8">
				<%=HtmlHelper.getSelect("clienterStatus", EnumHelper.GetEnumItems(ClienterStatus.class),"desc", "value", null, "-1", "全部")%>
			</div>
		</div>
	</div>
	<div class="col-lg-2">
		<div class="form-group">
				<input id="txtPhoneNo" type="tel" name="txtPhoneNo" placeholder="手机号" class="form-control"/>
		</div>
	</div>
	<div class="col-lg-2">
		<input type="submit" value="查询" class="btn btn-w-m btn-primary" id="btnSearch" />
	</div>		
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="ibox-content" id="content"></div>
	</div>
</div>

<script>
	var jss={
			search:function(currentPage){	
                 var clienterName = $("#txtClienterName").val();
                 var status = $("#clienterStatus").val();          
                 var phoneNo=$("#txtPhoneNo").val();  
				 var paramaters = { 
						 "currentPage":currentPage,
						 "clienterName": clienterName,
						 "status": status,
						 "phoneNo": phoneNo,	
						 };        
			        var url = "<%=basePath%>/clienter/listdo";
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
	
</script>