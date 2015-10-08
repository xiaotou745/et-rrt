package com.renrentui.renrenapi.dao.inter;


import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.ClienterDetail;
import com.renrentui.renrenentity.req.ClienterReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.ModifyClienterStatusReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;
import com.renrentui.renrenentity.resp.ClienterResp;
import com.renrentui.renrenentity.resp.MyIncomeResp;

public interface IClienterDao {
    int deleteByPrimaryKey(Long id);
 
    int insertSelective(Clienter record);

    Clienter selectByPrimaryKey(Long id);

    /**
     * 修改骑士信息
     * @author CaoHeYang
     * @param record
     * @date  20151008
     * @return
     */
    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);
    
	/**
	 * 验证手机号是否存在
	 * 茹化肖
	 * 2015年9月28日11:39:52
	 * 
	 */
    boolean isExistPhone(String phoneNo);
    /**
	 * 验证手机号是否存在
	 * 茹化肖
	 * 2015年9月28日11:39:52
	 * 
	 */
    boolean forgotPassword(ForgotPwdReq req);
    
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
	* @Date 2015年9月28日17:18:18
	* @Return
	*/
	boolean isExistUserC(long userId);
	/**
	* @Des 获取用户信息
	* @Author WangXuDan
	* @Date 2015年9月28日17:31:59
	* @Return
	*/
	ClienterDetail getUserC(long userId);
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
	boolean editClienterStatus(ModifyClienterStatusReq req);
}