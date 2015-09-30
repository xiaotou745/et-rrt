package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.req.ClienterBalanceReq;


@Repository
public class ClienterBalanceDao extends DaoBase implements IClienterBalanceDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @Des 获取用户金额 
	 *      注：此处用写串
	 * @Author 胡灵波
	 * @Date 2015年9月30日 15:02:48
	 * @param req
	 * @return
	 */
	@Override
	public ClienterBalance selectByPrimaryKey(Long id) {		
		return getMasterSqlSessionUtil().selectOne(
				"com.renrentui.renrenapi.dao.inter.IClienterBalanceDao.selectByPrimaryKey", id);	
		        
		         
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterBalance record) {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	/**
	* @Des 更新用户余额，可提现余额
	* @Author 胡灵波
	* @Date 2015年9月28日 18:38:05
	* @Return
	*/
	@Override
	public int updateMoneyByKey(ClienterBalanceReq record)
	{
		return getMasterSqlSessionUtil()
				.update("com.renrentui.renrenapi.dao.inter.IClienterBalanceDao.updateMoneyByKey",
						record);	
	}

}
