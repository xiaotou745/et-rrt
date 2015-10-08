package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;
import com.renrentui.renrenentity.resp.ClienterResp;
import com.renrentui.renrenentity.resp.MyIncomeResp;


/**
 * 
 * C端用户相关
 * @author ofmyi_000
 *
 */
public interface IClienterService {
	/**
	 * 忘记密码
	 * @param req
	 * @return
	 */
	boolean forgotPwdUserc(ForgotPwdReq req);
	
	/**
	 * 手机号是否存在
	 * @param phoneNo
	 * @return
	 */
	boolean isExistPhoneC(String phoneNo);
	
	/**
	 * 验证用户ID 和密码是否正确
	 * @return
	 */
	boolean isRightPwd(int uid,String md5Pwd);
	/**
	 * 根据UID 修改密码
	 * 茹化肖
	 * 2015年9月28日15:02:52
	 * @param req
	 * @return
	 */
	boolean modifyPwdUserc(ModifyPwdReq req);

	long signup(SignUpReq req);
	/**
	* @Des 查询C端用户信息  
	* @Author WangXuDan
	* @Date 2015年9月28日16:14:35
	* @Return
	*/
	Clienter queryClienter(SignInReq req);
	/**
	* @Des 根据用户Id判断是否存在 
	* @Author WangXuDan
	* @Date 2015年9月28日17:16:32
	* @Return
	*/
	boolean isExistUserC(long userId);
	/**
	* @Des 获取用户收入 
	* @Author WangXuDan
	* @Date 2015年9月28日17:31:59
	* @Return
	*/
	ClienterDetail getUserC(long userId);
	/**
	 * 用户提现
	 * 胡灵波
	 * 2015年9月28日 16:58:06
	 * @param req
	 * @return
	 */
	WithdrawState WithdrawC(ClienterBalanceReq req);
	/**
	* @Des 获取地推员信息列表  
	* @Author WangXuDan
	* @Date 2015年9月29日16:15:39
	* @Return
	*/
	PagedResponse<ClienterResp> queryClienterList(ClienterReq req);
	/**
	* @Des 修改用户状态 
	* @Author WangXuDan
	* @Date 2015年10月8日11:43:44
	* @Return
	*/
	ResponseBase editClienterStatus(ModifyClienterStatusReq req);
	
}
