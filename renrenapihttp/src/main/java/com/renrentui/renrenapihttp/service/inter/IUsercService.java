package com.renrentui.renrenapihttp.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

<<<<<<< HEAD:renrenapihttp/src/main/java/com/renrentui/apihttp/service/inter/IUsercService.java
import com.renrentui.apihttp.common.HttpResultModel;
import com.renrentui.entity.req.CWithdrawFormReq;
import com.renrentui.entity.req.ForgotPwdReq;
=======
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.req.ForgotPwdReq;
>>>>>>> cef36ecf4e7c81a3546cad7a1597a59d9198445e:renrenapihttp/src/main/java/com/renrentui/renrenapihttp/service/inter/IUsercService.java

/**
 * C端用户模块
 * @author 茹化肖
 * @date 2015年9月28日09:55:18
 */
@Path("/userc")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IUsercService {
	/**
	 * C端忘记密码
	 * @author 茹化肖
	 * @date 2015年9月28日10:18:57
	 * @return
	 */
	@POST
	@Path("/forgotpwd")
	 public HttpResultModel<Object> forgotPwd(ForgotPwdReq req);
	/**
	 * C端忘记密码
	 * @author 茹化肖
	 * @date 2015年9月28日10:18:57
	 * @return
	 */
	@POST
	@Path("/modifypwd")
	 public HttpResultModel<Object> modifyPwd();
	
	/**
	 * C申请提现
	 * @author 胡灵波
	 * @date 2015年9月28日 11:30:15
	 * @return
	 */
	@POST
	@Path("/withdraw")
	 public HttpResultModel<Object> withdraw(CWithdrawFormReq req);
}
