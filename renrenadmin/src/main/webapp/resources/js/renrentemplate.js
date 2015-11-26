//控件及分组信息
//步骤
var add='<div class="copy">'
			+'<div class="row">'
			+'<div class="col-lg-3">'
			+'<div class="form-group">'
			+'<label class="col-sm-4 control-label">步骤:1 </label>'
			+'<div class="col-sm-8">'
			+'<input type="text" class="form-control" value=""/>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>';
//补充说明
var add2 = '<div class="copy2">'
			+'<div class="row">'
			+'<div class="col-lg-3">'
			+'<div class="form-group">'
			+'<label class="col-sm-4 control-label">1、 </label>'
			+'<div class="col-sm-8">'
			+'<input type="text" class="form-control" />'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>';
//细则
var add3 = '<tr class="copy3"><td><label>1</label></td>'
			+'<td><input type="text"  style="width:200px;" class="eltitle"></td>'
			+'<td><input type="text"  style="width:200px;" class="elurl"></td>'
			+'<td><a href="javascript:void(0)" onclick="chooseArticle(this)">选择文章</a><a href="javascript:void(0)" >新建页面</a></td>'
			+'</tr>';
//文本组
var txtgroup='<div class="templateGroupText template" style="border: 3px solid #DDDDDD;margin-top: 2px;width: 40%;">'
	+'<label class="boxno">1.</label><span>文本组标题:</span><input type="text" value="文本组标题" class="cltxt">	'
	+'<a href="javascript:void(0);" onclick="addTxtControl(this)">添加文本控件</a>'
	+'<a href="javascript:void(0);" onclick="delTxtControl(this)" >删除文本控件</a> '
	+'<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该文本组</a>'
	+'<div class="textGroup">'
	+'<div class="textitem">说明文本:<input type="text" class="cltitle">默认值:<input type="text" class="cldefval"></div>'
	+'</div>'
	+'</div>';

//图片组
var imggroup='<div class="templateGroupImg template"  style="border: 3px solid #DDDDDD;margin-top: 8px;width: 40%;">'
	+'<label class="boxno">2.</label><span>图片组标题</span><input type="text" value="图片组标题" class="climg">'
	+'<a href="javascript:void(0);" onclick="addImgControl(this)" >添加图片控件</a>'
	+'<a href="javascript:void(0);" onclick="delImgControl(this)" >删除图片控件</a>'
	+'<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该图片组</a>'
	+'<div class="imgGroup">'
	+'<div class="imgitem">图片说明:<input type="text" class="cltitle"></div>'
	+'</div>'
	+'</div>';

//多图组
var moreimggroup='<div class="templateGroupMoreImg template"  style="border: 3px solid #DDDDDD;margin-top: 8px;width: 40%;">'
	+'<label class="boxno">3.</label><span>多图组标题</span><input type="text" value="多图组标题" class="clmoreimg">'
	+'<a href="javascript:void(0);" onclick="delThisGroup(this)" >删除该多图组</a>'
	+'<div class="imgGroup">'
	+'<div class="imgitemnum">图片数量:<input type="text" class="imgitemnumn"></div>'
	+'</div>'
	+'</div>';