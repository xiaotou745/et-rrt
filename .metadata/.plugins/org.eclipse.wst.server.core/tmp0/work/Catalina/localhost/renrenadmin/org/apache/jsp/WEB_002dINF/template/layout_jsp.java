/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.24
 * Generated at: 2015-09-29 05:08:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.template;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.renrentui.renrencore.util.PropertyUtils;

public final class layout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("jar:file:/D:/renrenditui/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/renrenadmin/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("jar:file:/D:/renrenditui/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/renrenadmin/WEB-INF/lib/tiles-jsp-3.0.5.jar!/META-INF/tld/tiles-jsp.tld", Long.valueOf(1411313530000L));
    _jspx_dependants.put("/WEB-INF/lib/tiles-jsp-3.0.5.jar", Long.valueOf(1435848268796L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1435834339131L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.renrentui.renrencore.util.PropertyUtils");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<meta charset=\"utf-8\" />\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<title>");
      if (_jspx_meth_tiles_005fgetAsString_005f0(_jspx_page_context))
        return;
      out.write("</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/css/bootstrap.min.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/font-awesome/css/font-awesome.min.css\" />\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/css/animate.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/css/style.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(basePath);
      out.write("/css/plugins/dataTables/dataTables.bootstrap.css\" />\r\n");
      out.write("<link href=\"");
      out.print(basePath);
      out.write("/css/admin.css\" rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("<!-- Mainly scripts -->\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/js/jquery-2.1.1.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/js/plugins/metisMenu/jquery.metisMenu.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/js/plugins/slimscroll/jquery.slimscroll.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/My97DatePicker/WdatePicker.js\"></script>\r\n");
      out.write("<!-- Flot -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Peity -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Custom and plugin javascript -->\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/js/inspinia.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery UI -->\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/js/plugins/jquery-ui/jquery-ui.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- GITTER -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Sparkline -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Sparkline demo data  -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- ChartJS-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Toastr -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 第三方弹窗js -->\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("/js/layer.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<!-- 分页相关js -->\r\n");
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    $(document).ajaxError( function(event, jqXHR, options, errorMsg){\r\n");
      out.write("   \t var content=\"内部服务器错误\";\r\n");
      out.write("    \tif(jqXHR.responseText==undefined){\r\n");
      out.write("    \t\tcontent=jqXHR.statusText;\r\n");
      out.write("    \t}else{\r\n");
      out.write("    \t var start=jqXHR.responseText.indexOf(\"<body>\");\r\n");
      out.write("\r\n");
      out.write("    \t if(start>0){\r\n");
      out.write("        \t var end=jqXHR.responseText.indexOf(\"</body>\");\r\n");
      out.write("        \t content=jqXHR.responseText.substring(start+6,end);\r\n");
      out.write("        \t content=content.replace(\"h1\",\"h4\"); \r\n");
      out.write("    \t }else{\r\n");
      out.write("    \t\t var start2=jqXHR.responseText.indexOf(\"<pre>\");\r\n");
      out.write("    \t\t var end2=jqXHR.responseText.indexOf(\"</pre>\");\r\n");
      out.write("        \t content=jqXHR.responseText.substring(start2,end2+6);\r\n");
      out.write("    \t }\r\n");
      out.write("    \t }\r\n");
      out.write("\r\n");
      out.write("    \t $(\"#gloablErrorParam\").html(options.url+\"调用出错了！\");\r\n");
      out.write("    \t $(\"#gloablErrorContent\").html(content);\r\n");
      out.write("    \t $(\"#gloablShowError\").html(\"显示详细信息\");\r\n");
      out.write("    \t $(\"#gloablErrorContent\").hide();\r\n");
      out.write("    \t $('#gloablErrorDiv').modal('show');\r\n");
      out.write("    });\r\n");
      out.write("    \r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\t$(\"#gloablShowError\").click(function() {\r\n");
      out.write("\t\t\tif ($(\"#gloablShowError\").html() == \"显示详细信息\") {\r\n");
      out.write("\t\t\t\t$(\"#gloablShowError\").html(\"隐藏详细信息\");\r\n");
      out.write("\t\t\t\tvar timeSet=2000;\r\n");
      out.write("\t\t\t\tif($(\"#gloablErrorContent\").html().length<500){\r\n");
      out.write("\t\t\t\t\ttimeSet=500;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$(\"#gloablErrorContent\").slideDown(timeSet);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\t$(\"#gloablShowError\").html(\"显示详细信息\");\r\n");
      out.write("\t\t\t\t$(\"#gloablErrorContent\").slideUp(500);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t//分页跳转按钮事件处理方法\r\n");
      out.write("\t\t$(document).on(\"click\", \"#pagesearch\", function(){\r\n");
      out.write("\t\t\tvar page=$(\"#pagesearchvalue\").val();\r\n");
      out.write("\t\t\tvar maxpage=$(\"#pagesearchmax\").val();\r\n");
      out.write("\t\t\tvar s = new RegExp(\"^\\\\s*(\\\\d+)\\\\s*$\");\r\n");
      out.write("\t\t\tif(!s.test(page)||parseInt(page) < 1 || parseInt(page) > maxpage){\r\n");
      out.write("\t\t\t  alert(\"页索引超出范围\");\r\n");
      out.write("\t\t\t  $(\"#pagesearchvalue\").val(\"1\");\r\n");
      out.write("\t\t\t  return;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tjss.search(page);\r\n");
      out.write("\t\t}); \r\n");
      out.write("\t\t$(document).on(\"keydown\", \"#pagesearchvalue\", function(e){\r\n");
      out.write("\t\t    var key = null;\r\n");
      out.write("\t\t    if (e.which) {\r\n");
      out.write("\t\t        key = e.which;\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t    else if (e.keyCode) {\r\n");
      out.write("\t\t        key = e.keyCode;\r\n");
      out.write("\t\t    }\r\n");
      out.write("\r\n");
      out.write("\t\t\tif ((48<=key&&key<=57)||(96<=key&&key<=105)) {\r\n");
      out.write("\t\t\t    return true;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t    return false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      if (_jspx_meth_tiles_005finsertAttribute_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_tiles_005finsertAttribute_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t<div id=\"page-wrapper\" class=\"gray-bg dashbard-1\">\r\n");
      out.write("\t\t\t<div class=\"row border-bottom\">\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-12\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f6(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div tabindex=\"-1\" class=\"modal inmodal\" id=\"gloablErrorDiv\" role=\"dialog\"\r\n");
      out.write("\t\t\taria-hidden=\"true\" style=\"display: none;\">\r\n");
      out.write("\t\t\t<div class=\"modal-dialog\">\r\n");
      out.write("\t\t\t\t<div class=\"modal-content animated bounceInRight\">\r\n");
      out.write("\t\t\t\t\t<div class=\"modal-header\">\r\n");
      out.write("\t\t\t\t\t\t<button class=\"close\" type=\"button\" data-dismiss=\"modal\">\r\n");
      out.write("\t\t\t\t\t\t\t<span aria-hidden=\"true\">×</span><span class=\"sr-only\">关闭</span>\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t<h4 class=\"modal-title\">服务器异常</h4>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<small class=\"font-bold\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"modal-body\">\r\n");
      out.write("\t\t\t\t\t\t<div id=\"gloablErrorParam\"></div>\r\n");
      out.write("\t\t\t\t\t\t<div><a id=\"gloablShowError\"  href=\"javascript:void(0)\">显示详细信息</a></div>\r\n");
      out.write("\t\t\t\t\t\t<pre id=\"gloablErrorContent\" style=\"width: 560px;display: none; \"></pre>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"modal-footer\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-white\" type=\"button\" data-dismiss=\"modal\">关闭</button>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</small>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<small class=\"font-bold\"> </small>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<small class=\"font-bold\"> </small>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_tiles_005fgetAsString_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:getAsString
    org.apache.tiles.jsp.taglib.GetAsStringTag _jspx_th_tiles_005fgetAsString_005f0 = (new org.apache.tiles.jsp.taglib.GetAsStringTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005fgetAsString_005f0);
    _jspx_th_tiles_005fgetAsString_005f0.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(14,7) name = name type = java.lang.String reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005fgetAsString_005f0.setName("title");
    _jspx_th_tiles_005fgetAsString_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005fgetAsString_005f0);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f0 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f0);
    _jspx_th_tiles_005finsertAttribute_005f0.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(145,0) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f0.setName("header_js");
    // /WEB-INF/template/layout.jsp(145,0) name = ignore type = boolean reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f0.setIgnore(true);
    _jspx_th_tiles_005finsertAttribute_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f0);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f1 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f1);
    _jspx_th_tiles_005finsertAttribute_005f1.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(146,0) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f1.setName("header_css");
    // /WEB-INF/template/layout.jsp(146,0) name = ignore type = boolean reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f1.setIgnore(true);
    _jspx_th_tiles_005finsertAttribute_005f1.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f1);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f2 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f2);
    _jspx_th_tiles_005finsertAttribute_005f2.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(151,2) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f2.setName("leftmenu");
    _jspx_th_tiles_005finsertAttribute_005f2.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f2);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f3 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f3);
    _jspx_th_tiles_005finsertAttribute_005f3.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(154,4) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f3.setName("header");
    _jspx_th_tiles_005finsertAttribute_005f3.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f3);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f4 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f4);
    _jspx_th_tiles_005finsertAttribute_005f4.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(156,3) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f4.setName("breadcrumbs");
    _jspx_th_tiles_005finsertAttribute_005f4.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f4);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f5(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f5 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f5);
    _jspx_th_tiles_005finsertAttribute_005f5.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(159,5) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f5.setName("body");
    _jspx_th_tiles_005finsertAttribute_005f5.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f5);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f6(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f6 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f6);
    _jspx_th_tiles_005finsertAttribute_005f6.setJspContext(_jspx_page_context);
    // /WEB-INF/template/layout.jsp(165,5) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f6.setName("footer");
    // /WEB-INF/template/layout.jsp(165,5) name = ignore type = boolean reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f6.setIgnore(true);
    _jspx_th_tiles_005finsertAttribute_005f6.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f6);
    return false;
  }
}
