package com.renrentui.renrenapihttp.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.req.CWithdrawFormReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;



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
	 public HttpResultModel<Object> modifyPwd(ModifyPwdReq req);
	
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
