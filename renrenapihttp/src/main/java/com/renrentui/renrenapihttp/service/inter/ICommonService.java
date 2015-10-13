package com.renrentui.renrenapihttp.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.AppVersion;
import com.renrentui.renrenentity.req.VersionCheckReq;


/**
 * 通用模块 
 * @author CaoHeYang
 * @date 20151013
 */
@Path("/common")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface ICommonService {
	/**
	 *  版本升级检测 
	 * @author CaoHeYang
	 * @date 20151013
	 * @return
	 */
	@POST
	@Path("/versioncheck")
	 public HttpResultModel<AppVersion> versionCheck(VersionCheckReq para);

	/**
	 * C端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	@POST
	@Path("/getrecordtypec")
	public HttpResultModel<List<String>> getRecordtypeC();	

}

