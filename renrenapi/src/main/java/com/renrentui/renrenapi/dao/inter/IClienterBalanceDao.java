package com.renrentui.renrenapi.dao.inter;

import java.util.Map;

import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.req.ClienterBalanceReq;

public interface IClienterBalanceDao {
	
	/**
	 * @Des 获取用户金额 
	 *      注：此处用写串
	 * @Author 胡灵波
	 * @Date 2015年9月30日 15:02:48
	 * @param Id
	 * @return
	 */
	ClienterBalance selectByPrimaryKey(Long id);
	
	/**
	 * @Des 获取用户金额 
	 *      注：此处用写串
	 * @Author 胡灵波
	 * @Date 2015年9月30日 15:02:48
	 * @param 推广员Id
	 * @return
	 */
	ClienterBalance selectByClienterId(Long clienterId);
	
		/**
	* @Des 更新用户余额，可提现余额
	* @Author 胡灵波
	* @Date 2015年9月28日 18:38:05
	* @Return
	*/
    int updateMoneyByKey(ClienterBalanceReq record);
    
	/**
	* @Des 递推员累积提现金额
	* @Author 胡灵波
	* @Date 2015年10月14日 19:33:09
	* @Return
	*/
    int  updateHadWithdrawByClienterId(Map<String, Object> params);
	
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalance record);

    int insertSelective(ClienterBalance record);    

    int updateByPrimaryKeySelective(ClienterBalance record);

    int updateByPrimaryKey(ClienterBalance record);
    

}