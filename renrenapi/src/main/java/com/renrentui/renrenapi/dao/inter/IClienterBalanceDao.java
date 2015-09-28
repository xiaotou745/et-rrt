package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.req.ClienterBalanceReq;

public interface IClienterBalanceDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterBalance record);

    int insertSelective(ClienterBalance record);

    ClienterBalance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterBalance record);

    int updateByPrimaryKey(ClienterBalance record);
    
	/**
	* @Des 更新用户余额，可提现余额
	* @Author 胡灵波
	* @Date 2015年9月28日 18:38:05
	* @Return
	*/
    int updateMoneyByKey(ClienterBalanceReq record);
}