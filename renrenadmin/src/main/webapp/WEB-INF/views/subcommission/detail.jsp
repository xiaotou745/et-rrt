<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrenentity.Strategy"%>
<%@page import="com.renrentui.renrenentity.StrategyChild"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url");
	Strategy strategy=request.getAttribute("strategy")==null?new Strategy():(Strategy) request.getAttribute("strategy");
	List<StrategyChild> child=request.getAttribute("childs")==null?new ArrayList<StrategyChild>():(List<StrategyChild>)request.getAttribute("childs") ;
%>

<div class="wrapper wrapper-content animated fadeInRight">
<form method="POST" action="#" class="form-horizontal" id="searchForm">
	<fieldset>
			<legend>分佣策略信息</legend>
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>策略名称: </label>
								<div class="col-sm-8">
									<input maxlength="50" type="text" class="form-control" name="StrategyName" id="StrategyName" value="<%=strategy.getStrategyName()%>"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>策略描述: </label>
								<div class="col-sm-8">
									<textarea maxlength="200"  rows="3" cols="35" class="form-control" name="Remark" id="Remark"><%=strategy.getRemark()%></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>层级: </label>
								<div class="col-sm-1">
									<label class="col-sm-1 control-label" id="LevalCount"><%=strategy.getLevalCount()%></label>
								</div>
	  							  <label class="col-sm-1 control-label">层</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label class="col-sm-4 control-label"><span style="color:red;">*</span>累计分佣总数: </label>
								<div class="col-sm-2">
									<label class="col-sm-2 control-label" id="Percentage"><%=strategy.getPercentage()%></label>
								</div>
	  							  <label class="col-sm-1 control-label">%</label>
							</div>
						</div>
					</div>
				</div>
			</div>
	</fieldset>
	<fieldset>
	<legend>分佣比例</legend>
	<div class="row">
		
		<div class="col-lg-12" id="collist">
		<% 
		if(child!=null&&child.size()>0)
		{
			for(int i=0;i<child.size();i++)
			{
				%>
				<div class="row">
				<div class="col-lg-3">
					<div class="form-group">
						<label class="col-sm-4 control-label ClassNo"><span style="color:red;">*</span><%=child.get(i).getLevalNo()%>级分佣: </label>
						<div class="col-sm-5">
							<input type="text" class="form-control StrategyChildItem"   value="<%=child.get(i).getPercentage()%>"/>
						</div>
						<div class="col-sm-3" style="line-height: 33px; padding-left: 3px;">
	  						%  
						</div>
					</div>
				</div>
				</div>
				<%
			}
		}
		%>
		
		</div>
	</div>
	</fieldset>
		<div class="row">
			<div class="col-lg-4">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
$('input').attr('disabled','disabled');
//保存验证END
</script>
