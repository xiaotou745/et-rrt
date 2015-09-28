package com.renrentui.renrenapihttp.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.renrentui.entity.req.CSendCodeReq;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;



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
	 public HttpResultModel<Object> withdraw(ClienterBalanceReq req);
	
	/**
	 * 获取验证码
	 * @author haichao
	 * @date 2015年9月28日 14:49:55
	 * @return 
	 * */
	@POST
	@Path("/sendcode")
	public HttpResultModel<Object> sendcode(CSendCodeReq req);
	/*
	 * C端注册
	 * wang chao 
	 */
	@POST
	@Path("/signup")
	public HttpResultModel<Object> signup(SignUpReq req);
	/**
	* @Des C端登陆 
	* @Author WangXuDan
	* @Date 2015年9月28日14:44:21
	* @Return
	*/
	@POST
	@Path("/signin")
	public HttpResultModel<Object> signin(SignInReq req);
	/**
	* @Des  C端获取用户收入
	* @Author WangXuDan
	* @Date 2015年9月28日17:11:11
	* @Return
	*/
	public HttpResultModel<Object> myincome(MyIncomeReq req);
	
	
}
