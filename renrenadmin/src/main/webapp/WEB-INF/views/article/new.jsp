<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%
String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
String ImgShowUrl= PropertyUtils.getProperty("ImgShowUrl");
%>
<script src="<%=basePath%>/js/bootstrap-treeview.js"></script>
<link rel="stylesheet" href="<%=basePath%>/kindeditor-4.1.10/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath%>/kindeditor-4.1.10/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath%>/kindeditor-4.1.10/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath%>/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath%>/kindeditor-4.1.10/plugins/code/prettify.js"></script>
<script>

KindEditor.ready(function(K) {
	
var editor1 = K.create('textarea[name="content"]', {

cssPath : 'kindeditor/plugins/code/prettify.css',

uploadJson : 'kindeditor/jsp/upload_json.jsp',

fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',

allowFileManager : true,

afterCreate : function() {

var self = this;

K.ctrl(document, 13, function() {

self.sync();

//document.forms['example'].submit();

});

K.ctrl(self.edit.doc, 13, function() {

self.sync();

//document.forms['example'].submit();

});

}

});

prettyPrint();
window.editor=editor1;
});

</script>
<span>文章标题：</span><input class="form-control" type="text" name="title" id="title">
<br />
<span>内容：</span>
<textarea name="content" cols="100" rows="8" id="content"></textarea><br />
<input type="button" class="btn btn-w-m btn-primary" name="button" value="保存文章"  id="save"/>
<input type="button" class="btn btn-w-m btn-primary" name="button" value="预览文章"  id="show"/>
<div tabindex="-1" class="modal inmodal" id="alertbox" role="dialog" aria-hidden="true" style="display: none;">	
	
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title" id="articleTitle"></h4>				
			</div>
			<small class="font-bold">
				<div class="modal-body" id="articleBody">
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

<script>
$('#show').click(function(){
	window.editor.sync();
	$('#articleBody').html($('#content').val());
	$('#articleTitle').html($('#title').val());
	$('#alertbox').modal('show'); 
});

$('#save').click(function(){
	window.editor.sync();
	var url="<%=basePath%>/article/savearticle";
	par={
			"title":$('#title').val(),
			"content":$('#content').val()
	}
	$.post(url,par,function(data){
			if(data>0)
				alert('保存文章成功');
			else
				alert('保存文章失败');
	});
});
</script>
