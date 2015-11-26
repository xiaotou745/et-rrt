package com.renrentui.renrenapihttp.service.inter;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces; 
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.domain.RegionModel; 
import com.renrentui.renrenentity.req.RegionReq;


/**
 * 区域信息
 * @author wangchao 
 */
@Path("/region")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IRegionService {
	@POST
	@Path("/gethotregionandall")
	 public HttpResultModel<RegionModel> getHotRegionAndAll(RegionReq req);
}
