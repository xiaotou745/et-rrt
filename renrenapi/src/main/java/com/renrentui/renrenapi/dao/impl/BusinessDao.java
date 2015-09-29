package com.renrentui.renrenapi.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;





import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IBusinessDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;

@Repository
public class BusinessDao extends DaoBase implements IBusinessDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Business selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Business record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public	PagedResponse<Business> getBusinessList(PagedBusinessReq req)
	{
		PagedResponse<Business> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.renrentui.renrenapi.dao.inter.IBusinessDao.getBusinessList",
						req);
		return model;
	}


}
