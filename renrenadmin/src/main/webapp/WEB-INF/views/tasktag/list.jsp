<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrenentity.TaskTag"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.HtmlHelper"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
List<TaskTag> list = (List<TaskTag>) request.getAttribute("listData");
%>
<div class="wrapper wrapper-content animated fadeInRight form-horizontal">
	<div class="row">
		<div class="col-lg-12">
			<div class="row">
				<div class="col-lg-3">
					<button type="button" class="btn btn-w-m btn-primary"
						 onclick="AddShow()" id="addtag">添加新标签</button>
				</div>
			</div>
		</div>
	</div>
</div>
 
<div id="content">
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>标签名称</th>
			<th>颜色背景</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>修改人</th>
			<th>修改时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getTagName()%></td>
			<td><span style='background-color: <%=list.get(i).getTagColorCode()%>;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			<td><%=list.get(i).getCreateName()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreateTime())%></td>
			<td><%=list.get(i).getUpdateName()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getUpdateTime())%></td>
			<td>
			<a href="javascript:void(0)" onclick="modify(<%=list.get(i).getId()%>,'<%=list.get(i).getTagName()%>','<%=list.get(i).getTagColorCode()%>')">编辑</a>
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>	
</div>



<div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
<div class="modal-dialog modal-sm">
    <div class="modal-content">
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	    <h4 class="modal-title">添加新标签</h4>
	</div>
<div class="modal-body">
标签名称：<input id="tagName" class="form-control"/><br/><br/>
颜色背景：<input id="tagColorCode" class="form-control colorwell colorwell-selected"/><br/>
</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
	    <button type="button" id="savetag" class="btn btn-primary">保存</button>
	</div>
    </div>
</div>
</div>
<input type="hidden" name="tagid" id="tagid" value="0"/> 
<input type="hidden" name="optype" id="optype" value="0"/> 
<script>

function AddShow(){
	$("#tagName").val("");
	$("#tagColorCode").val("");
	$("#tagid").val("0");
	$("#optype").val("1");
	oldtagName="";
	oldtagColorCode="";
    $('#myModal').modal('show');

}
var oldtagName="";
var oldtagColorCode="";
function modify(id,tagName,tagColorCode) {
	$("#tagName").val(tagName);
	$("#tagColorCode").val(tagColorCode);
	$("#tagid").val(id);
	$("#optype").val("0");
	oldtagName=tagName;
	oldtagColorCode=tagColorCode;
    $('#myModal').modal('show');
}
$("#savetag").click(function(){
	if($("#tagName").val()==""){
		alert("标签名称不能为空");
		return;
	}
	if($("#tagColorCode").val()==""){
		alert("颜色背景不能为空");
		return;
	}
	if($("#tagName").val()==oldtagName&&$("#tagColorCode").val()==oldtagColorCode){
		alert("没有修改，不需要保存！");
		return;
	}
	var paramaters = {
			"id":$("#tagid").val(),
			"tagName" :  $("#tagName").val().trim(),
			"tagColorCode" : $("#tagColorCode").val().trim()
		};
		var url = "<%=basePath%>/tasktag/insert";
		if($("#optype").val()=="0"){
			url = "<%=basePath%>/tasktag/update";
		}
		$.ajax({
			type : 'POST',
			url : url,
			data : paramaters,
			success : function(result) {
				if (result>0) {
					alert("操作成功");
					window.location.href = window.location.href;
				} else {
					alert("操作失败:"+$("#tagName").val()+"已经存在！");
				}
			}
		});
});

</script>