<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.renrentui.renrenentity.common.PagedResponse"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.List"%>
<%@page import="com.renrentui.renrencore.util.PropertyUtils"%>
<%@page import="com.renrentui.renrencore.util.ParseHelper"%>
<%@page import="com.renrentui.renrenentity.domain.OpenCityModel"%>
<%
	String basePath = PropertyUtils.getProperty("java.renrenadmin.url"); 
%>
<table id='tblCity'
	class="table table-striped table-bordered table-hover dataTables-example"
	style='display: none'>
	<thead>
		<tr>
			<th width="%5">序号</th>
			<th>省编号</th>
			<th>省名称</th>
			<th>市编号</th>
			<th>市编名称</th>
			<th>区县编号</th>
			<th>区县名称</th>
			<th id='ispubliccity'>是否开放
			<input type="checkbox" name="checkPublicCityAllRadio"
				id="selectPublicCityAll" onclick="checkPublicCityAll()" value="" />全选/取消
			</th>
			<th id='ishotcity'>是否热门
			<input type="checkbox" name="checkHotCityAllRadio" id="selecHotCitytAll"
				onclick="checkHotCityAll()" value=""  />全选/取消
			</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<OpenCityModel> data = (List<OpenCityModel>) request
					.getAttribute("listData");
			if (data == null) {
				data = new ArrayList<OpenCityModel>();
			}
			for (int i = 0; i < data.size(); i++) {
		%>
		<tr>
			<td><%=i%></td>
			<td><%=data.get(i).getProvinceCode()%></td>
			<td><%=data.get(i).getProvinceName()%></td>
			<td><%=data.get(i).getCityCode()%></td>
			<td><%=data.get(i).getCityName()%></td>
			<td><%=data.get(i).getDistrictCode()%></td>
			<td><%=data.get(i).getDistrictName()%></td>
			<td class="classPublicCityAll">
				<%
					String checkIsPublicStr = data.get(i).getIsPublic() == 1 ? "checked=\"checked\""
								: "";
				%> <input type="checkbox" name="checkPublicCityAllRadio"
				id="<%=data.get(i).getDistrictCode()%>" <%=checkIsPublicStr%>
				value=<%=data.get(i).getDistrictCode()%> />
			</td>
			<td class="classHotCityAll">
				<%
					String checkIsHotStr = data.get(i).getIsHot() == 1 ? "checked=\"checked\""
								: "";
				%> <input type="checkbox" name="checkHotCityAllRadio"
				id="<%=data.get(i).getDistrictCode()%>" <%=checkIsHotStr%>
				value=<%=data.get(i).getDistrictCode()%> />
			</td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<script>
//开放城市选择
function checkPublicCityAll() {
	var checkedOfAll = $("#selectPublicCityAll").prop("checked");
	$("input[name='checkPublicCityAllRadio']").prop("checked", checkedOfAll);
}
//热门城市选择
function checkHotCityAll() {
	var checkedOfAll = $("#selecHotCitytAll").prop("checked");
	$("input[name='checkHotCityAllRadio']").prop("checked", checkedOfAll);
}
</script>