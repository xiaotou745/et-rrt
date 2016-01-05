package com.renrentui.renrenapi.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.management.RuntimeErrorException;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.CBalanceRecordStatus;
import com.renrentui.renrencore.enums.CBalanceRecordType;
import com.renrentui.renrencore.enums.ClienterWithdrawFormStatus;
import com.renrentui.renrencore.enums.ClienterWithdrawFormWithType;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;



@Service
public class ClienterWithdrawFormService implements IClienterWithdrawFormService{

	@Autowired
	private IClienterWithdrawFormDao clienterWithdrawFormDao;		
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;	
	@Autowired
	private IClienterBalanceDao clienterBalanceDao;
	

	@Override
	public int Add(ClienterWithdrawForm record) 
	{
		return clienterWithdrawFormDao.insert(record);
	}
	
	/**
	 * @Des 用户提现 申请
	 * @Author 胡灵波
	 * @Date 2015年9月28日 16:58:06
	 * @param req
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public WithdrawState WithdrawC(ClienterBalanceReq req)
	{		
		ClienterBalance clienterBalanceModel= clienterBalanceDao.selectByClienterId(req.getUserId());

		double amount=clienterBalanceModel.getWithdraw();
		if(req.getAmount()>amount)
		{	
			return WithdrawState.MoneyError;
		}		
		
	     //创建提现表
		ClienterWithdrawForm clienterWithdrawFormModel=new ClienterWithdrawForm();
		clienterWithdrawFormModel.setClienterId(req.getUserId());
		clienterWithdrawFormModel.setAmount(req.getAmount());
		String no=OrderNoHelper.generateOrderCode(req.getUserId());
		clienterWithdrawFormModel.setWithdrawNo(no);
		clienterWithdrawFormModel.setWithType((short)ClienterWithdrawFormWithType.Alipay.value());//支付宝
		clienterWithdrawFormModel.setAccountInfo(req.getAccountInfo());
		clienterWithdrawFormModel.setTrueName(req.getTrueName());
		clienterWithdrawFormModel.setStatus((short)ClienterWithdrawFormStatus.UnAudit.value());//待审核	
		
		double actualHandCharge = PayToZhiFuBao(req.getAmount());
		clienterWithdrawFormModel.setHandCharge(10); //骑士付给我们的手续费金额，从缓存中读取
		clienterWithdrawFormModel.setActualHandCharge(actualHandCharge); //我们付给支付宝的手续费
		clienterWithdrawFormModel.setActualAmount(req.getAmount()-actualHandCharge); 
		
		int cwfId= clienterWithdrawFormDao.insert(clienterWithdrawFormModel);
		
		ClienterBalanceReq cBReq=new ClienterBalanceReq();
		cBReq.setUserId(req.getUserId());
		cBReq.setAmount(-req.getAmount());
		int cbId= clienterBalanceDao.updateMoneyByKey(cBReq);
		
	    ClienterBalanceRecord clienterBalanceRecordModel=new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(req.getUserId());
		clienterBalanceRecordModel.setAmount(-Math.abs(req.getAmount()));		
		clienterBalanceRecordModel.setRecordType((short)CBalanceRecordType.ApplicationFor.value());//提现申请		
		clienterBalanceRecordModel.setOptName(req.getTrueName());
		clienterBalanceRecordModel.setOrderId((long)clienterWithdrawFormModel.getId());
		clienterBalanceRecordModel.setRelationNo(no);
		clienterBalanceRecordModel.setRemark("提现申请");
		clienterBalanceRecordModel.setStatus((short)CBalanceRecordStatus.Trading.value());//交易中
		int cbrId=clienterBalanceRecordDao.insert(clienterBalanceRecordModel);				
		
		if(cwfId>0&&cbId>0&&cbrId>0)
		{
			return WithdrawState.Success;
		}
		/*else
		{
			Error error=new Error("提现出错");
			throw new RuntimeErrorException(error);
		}	*/
		
		return WithdrawState.Failure;
	}
	
	/**
	 * @Des 审核通过
	 * @Author 胡灵波
	 * @Date 2015年9月28日 16:58:06
	 * @param 当前只传提现单Id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public	int AuditPass(ClienterWithdrawForm record) 
	{		
		long id=record.getId();//提现单Id
		
		ClienterBalanceRecord cbrModel= clienterBalanceRecordDao.selectByOrderId(id);
		if(cbrModel.getStatus()!=2) return 0;	
		
		//提现表 更新审核状态
		record.setStatus((short)ClienterWithdrawFormStatus.Audited.value());//审核通过	
		record.setAuditTime(new Date());
		int cwfId= clienterWithdrawFormDao.updateByPrimaryKeySelective(record);		
	
		//流水表 更新提现状态
		ClienterBalanceRecord cbrModelU= new ClienterBalanceRecord();
		cbrModelU.setOrderId(cbrModel.getOrderId());
		cbrModelU.setStatus((short)CBalanceRecordStatus.Success.value());//交易成功
		int cbrId= clienterBalanceRecordDao.updateStatusByOrderId(cbrModelU);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", record.getAmount());
		params.put("clienterId", record.getClienterId());		
		//更新累积提现
		int cbId=clienterBalanceDao.updateHadWithdrawByClienterId(params);
		
		if(cwfId>0 && cbrId>0 && cbId>0)
			return 1;
		else
		{
			Error error=new Error("审核通过失败");
			throw new RuntimeErrorException(error);
		}			
		
	}
	
	/**
	 * @Des 审核拒绝
	 * @Author 胡灵波
	 * @Date 2015年9月28日 16:58:06
	 * @param req
	 * @return
	 */
	@Override
	public	int AuditRefuse(ClienterWithdrawForm record) 
	{
		long id=record.getId();//提现单Id
		ClienterBalanceRecord cbrModel= clienterBalanceRecordDao.selectByOrderId(id);
		if(cbrModel.getStatus()!=2) return 0;		
					
		//更新提现单审核状态
		record.setStatus((short)ClienterWithdrawFormStatus.AuditRefuse.value());//审核拒绝
		record.setAuditTime(new Date());
		int cwfId= clienterWithdrawFormDao.updateByPrimaryKeySelective(record);	
		
		//流水表 更新提现状态
		ClienterBalanceRecord cbrModelU= new ClienterBalanceRecord();
		cbrModelU.setOrderId(cbrModel.getOrderId());
		cbrModelU.setStatus((short)CBalanceRecordStatus.Success.value());//交易成功
		int cbrId= clienterBalanceRecordDao.updateStatusByOrderId(cbrModelU);		
		
		//更新用户余额，可提现余额
		ClienterBalanceReq cBReq=new ClienterBalanceReq();
		cBReq.setUserId(cbrModel.getClienterId());
		cBReq.setAmount(-cbrModel.getAmount());
		int cbId= clienterBalanceDao.updateMoneyByKey(cBReq);
		
		//写入审核拒绝流水
	    ClienterBalanceRecord clienterBalanceRecordModel=new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(cbrModel.getClienterId());
		clienterBalanceRecordModel.setAmount(-cbrModel.getAmount());		
		clienterBalanceRecordModel.setRecordType((short)CBalanceRecordType.DenialOf.value());//		
		clienterBalanceRecordModel.setOptName(record.getAuditName());//
		clienterBalanceRecordModel.setOrderId((long)cbrModel.getOrderId());//
		clienterBalanceRecordModel.setRelationNo(cbrModel.getRelationNo());//
		clienterBalanceRecordModel.setRemark("申请拒绝失败");
		clienterBalanceRecordModel.setStatus((short)CBalanceRecordStatus.Success.value());	
		int cbrIdInsert=clienterBalanceRecordDao.insert(clienterBalanceRecordModel);			
	
		if(cwfId>0 && cbrId>0 &&  cbId>0 && cbrIdInsert>0)
			return 1;
		else
		{
			Error error=new Error("审核拒绝");
			throw new RuntimeErrorException(error);
		}			
		
	}
	@Override
	public	PagedResponse<ClienterWithdrawFormDM> getList(PagedClienterWithdrawFormReq req)
	{
		return clienterWithdrawFormDao.getList(req);		
	}
	
	/*
	 * 根据提现金额，计算支付给支付给的手续费
	 */
	public double PayToZhiFuBao(double amount){
		double actualhandcharge =0.0;
		double tempmoney = (double)amount*0.005;
        if (tempmoney <= 1)
        {
        	actualhandcharge = 1;
        }
        else if (tempmoney >= 25)
        {
        	actualhandcharge = 25;
        }
        else {
        	actualhandcharge =(double)tempmoney;
        }
        return actualhandcharge;
	}

	@Override
	public String AlipayBatchTransfer(AlipayBatchModel alipayBatchModel) {
		 
	            if (alipayBatchModel.getType() != 1 && alipayBatchModel.getType() != 2)
	            {
	                return "<html><body>Type参数有误</body></html>";
	            }
	            if (alipayBatchModel.getData()== null || alipayBatchModel.getData()=="")
	            {
	                return "<html><body>Data参数有误</body></html>";
	            }
	            int alipayBatchCount = 0;//总笔数
	            double alipayPayAmount = 0;//该批次总付款金额
	            double toatlChargeAmount = 0;//该批次总手续费
	            String alipayBatchNo = "";//批次号
	            String html = "";//返回的html
	            int updateCount = 0;//事务修改数据量
	            StringBuilder wids = new StringBuilder("");//
	            StringBuilder wnos = new StringBuilder("");
	           
	           
	            //alipayBatchNo = CreateAlipayBatchNo();//生成批次号
	            return "";
	            
	}
	
	public String CreateAlipayBatchNo(int count)
    {
		String batchno="";
        try
        {
            count = count + 1;
            if (count > 3)//避免无限递归
            {
                return "";
            }
            Random r = new Random();
            int min = 10000;
            int max = 99999;
            int ran = r.nextInt(max-min+1)+min;//10000-99999随机取一位
             String.format(RedissCacheKey.Ets_AlipayBatchNo,batchno);
            return batchno;
        }
        catch (Exception e)//避免异常引起的错误
        { 
            return "";
        }

    }
}
