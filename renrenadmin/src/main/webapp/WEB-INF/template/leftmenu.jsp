
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java"
	import="com.renrentui.renrenapi.service.inter.IMenuInfoService"%>
<%@ page language="java"
	import="com.renrentui.renrencore.util.SpringBeanHelper"%>
<%@ page language="java" import="com.renrentui.renrenentity.MenuInfo"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="java.util.ArrayList"%>
<%@page import="com.renrentui.renrenadmin.common.UserContext"%>
<%
	String basePath =PropertyUtils.getProperty("java.renrenadmin.url");
	IMenuInfoService menuService = SpringBeanHelper.getCustomBeanByType(IMenuInfoService.class);
	
	UserContext context = UserContext.getCurrentContext(request);
	if(context == null){
		response.sendRedirect(basePath);
		return;
	}
	List<MenuInfo> menuList = menuService.getMenuListByUserID(context.getId());
    String viewPath =request.getAttribute("viewPath").toString();
%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span> <img alt="image" class="img-circle"
						src="<%=basePath%>/img/profile_small.jpg" />
					</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
						class="clear"> <span class="block m-t-xs"> <strong
								class="font-bold"><%=context.getLoginName() %></strong>
						</span> <span class="text-muted text-xs block"><%=context.getUserName() %> <b
								class="caret"></b></span>
					</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="<%=basePath %>/account/changepwd">修改密码</a></li>
<!-- 						<li><a href="contacts.html">联系方式</a></li> -->
<!-- 						<li><a href="mailbox.html">消息</a></li> -->
						<li class="divider"></li>
						<li><a href="<%=basePath %>/account/logoff">注销</a></li>
					</ul>
				</div>
				<div class="logo-element">IN+</div>
			</li>

			<%
			        String parentClass="";
					for (MenuInfo menu : menuList) {
						if (menu.getParId() == 0) {
							parentClass="";
							List<MenuInfo> data=new ArrayList<>();
							for (MenuInfo itemMenu : menuList) {
								if (itemMenu.getParId() == menu.getId()
										&& itemMenu.getIsButton() == false) {
									if(viewPath.equals(itemMenu.getUrl().substring(1))){
										parentClass=" class='active' ";
									}
									data.add(itemMenu);
								}
							}
							if(data.size()>0){
								%>
								<li <%=parentClass%>><a href="#"><i class="fa fa-th-large"></i>
										<span class="nav-label"><%=menu.getMenuName()%></span> <span
										class="fa arrow"></span></a>
									<ul class="nav nav-second-level">
										<%
								for (MenuInfo submenu : data) {
									%>
									<li <%=viewPath.equals(submenu.getUrl().substring(1))?"class='active'":""%>><a href="<%=basePath+submenu.getUrl()%>"><%=submenu.getMenuName()%></a></li>
									<%}%>
								</ul></li>
							<%
							}	
					}
				}	
			%>
		</ul>
	</div>
</nav>
