package com.renrentui.renrenapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IBusinessDao;
import com.renrentui.renrenapi.service.inter.IBusinessServce;
import com.renrentui.renrenentity.Business;
@Service
public class BusinessServce implements IBusinessServce{
	@Autowired
private IBusinessDao businessDao;
	@Override
	public List<Business> getAllList() {
return businessDao.getAllList();
	}

}
