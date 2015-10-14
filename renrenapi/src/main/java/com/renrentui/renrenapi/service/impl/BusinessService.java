package com.renrentui.renrenapi.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.renrentui.renrenapi.dao.inter.IBusinessBalanceDao;
import com.renrentui.renrenapi.dao.inter.IBusinessBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IBusinessDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IBusinessService;
import com.renrentui.renrenapi.service.inter.IClienterService;
import com.renrentui.renrencore.enums.BBalanceRecordType;
import com.renrentui.renrencore.security.MD5Util;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.BusinessModel;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.Business;
import com.renrentui.renrenentity.BusinessBalance;
import com.renrentui.renrenentity.BusinessBalanceRecord;
import com.renrentui.renrenentity.Clienter;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.req.BusinessBalanceReq;
import com.renrentui.renrenentity.req.ForgotPwdReq;
import com.renrentui.renrenentity.req.MyIncomeReq;
import com.renrentui.renrenentity.req.PagedBusinessReq;
import com.renrentui.renrenentity.req.SignUpReq;
import com.renrentui.renrenentity.req.ModifyPwdReq;
import com.renrentui.renrenentity.req.SignInReq;

@Service
public class BusinessService implements IBusinessService {
	@Autowired
	private IBusinessDao businessDao;

	@Autowired
	private IBusinessBalanceDao businessBalanceDao;

	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;

	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		// TODO Auto-generated method stub
		return businessDao.getBusinessList(req);
	}

	@Override
	public String UploadFile(byte[] fileArr, String fileType) {

		String url = "http://192.168.1.38/Upload/UploadImg?uploadFrom=1";
		// ArrayList<FileInformation> dataFromService = new
		// ArrayList<FileInformation>();
		try (CloseableHttpClient httpClient = HttpClientBuilder.create()
				.build()) {
			HttpPost request = new HttpPost(url);
			// HttpGet request = new HttpGet(url + function);
			request.addHeader("content-type",
					"application/x-www-form-urlencoded");
			HttpResponse result = httpClient.execute(request);
			HttpEntity entity = result.getEntity();
			String json = EntityUtils.toString(result.getEntity(), "UTF-8");

		} catch (IOException ex) 
		{
		}
		return null;
	}

	@Override
	public List<Business> getAllList() {
		return businessDao.getAllList();
	}

	/**
	 * 添加商户 胡灵波 2015年9月29日 16:58:06
	 * 
	 * @param req
	 * @return 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int add(Business record) {

		String password = MD5Util.MD5("111111");// 默认值
		record.setPassWord(password);
		record.setCityCode(0);// 临时
		int bId = businessDao.insert(record);

		BusinessBalance businessBalanceModel = new BusinessBalance();
		businessBalanceModel.setBusinessId(record.getId());
		businessBalanceModel.setBalance(0.0);
		int bbId = businessBalanceDao.insert(businessBalanceModel);

		if (bId > 0 && bbId > 0)
			return 1;
		else {
			Error error = new Error("添加商户错误");
			throw new RuntimeErrorException(error);
		}
	}

	
	/**
	 * 修改商户
	 * 胡灵波
	 * 2015年10月13日 22:05:05
	 * @param req
	 * @return 
	 */
	@Override	
	public int modify(Business record) {		
	
		int bId= businessDao.updateByPrimaryKeySelective(record);		

		if(bId>0 )
			return 1;
		else
		{
			Error error=new Error("修改商户错误");
			throw new RuntimeErrorException(error);
		}			
	}	
	
	/**
	 * @Des 商户充值
	 * @Author 胡灵波
	 * @Date 2015年9月29日 16:58:06
	 * @param req
	 * @return 临时为1
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int addBalance(BusinessBalanceReq req,String userName)
	{
		int bbId= businessBalanceDao.updateBalanceByBusinessId(req);
		
		BusinessBalanceRecord businessBalanceRecordModel=new BusinessBalanceRecord();
		businessBalanceRecordModel.setBusinessId(req.getBusinessId());
		businessBalanceRecordModel.setAmount(req.getBalance());
		businessBalanceRecordModel
				.setRecordType((short) BBalanceRecordType.Delta.value());
		businessBalanceRecordModel.setOptName(userName);// 登 陆名称
		businessBalanceRecordModel.setOrderId((long) 0);
		businessBalanceRecordModel.setRelationNo("");
		businessBalanceRecordModel.setRemark(req.getRemark());		
		int bbrId= businessBalanceRecordDao.insert(businessBalanceRecordModel);
		
		if(bbId>0 && bbId>0 )
		return 1;
		else
		{
			Error error=new Error("商户充值失败");
			throw new RuntimeErrorException(error);
		}		
		
	}	
}
