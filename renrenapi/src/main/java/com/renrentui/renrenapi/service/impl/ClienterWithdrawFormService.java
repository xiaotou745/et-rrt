package com.renrentui.renrenapi.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.tree.BackedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.renrentui.renrenapi.common.LogServiceBLL;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterFinanceAcountService;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrenapi.service.inter.IGlobalConfigService;
import com.renrentui.renrenapi.service.inter.ITaskMsgService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.CBalanceRecordStatus;
import com.renrentui.renrencore.enums.CBalanceRecordType;
import com.renrentui.renrencore.enums.ClienterWithdrawFormStatus;
import com.renrentui.renrencore.enums.ClienterWithdrawFormWithType;
import com.renrentui.renrencore.enums.MsgOpType;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.security.DES;
import com.renrentui.renrencore.util.Config;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.TaskMsg;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchCallBackModel;
import com.renrentui.renrenentity.domain.AlipayBatchClienterWithdrawForm;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.domain.AlipayCallBackData;
import com.renrentui.renrenentity.domain.AlipayClienterWithdrawModel;
import com.renrentui.renrenentity.domain.ClienterFinanceAcountModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.domain.ClienterWithdrawLog;
import com.renrentui.renrenentity.domain.ClienterWithdrawLogModel;
import com.renrentui.renrenentity.req.AlipayBatchReq;
import com.renrentui.renrenentity.req.ClienterBalanceReq;
import com.renrentui.renrenentity.req.PagedClienterWithdrawFormReq;
import com.renrentui.renrenentity.req.UpdateAlipayBatchReq;

import java.util.*;

import com.alipay.util.*;
import com.alipay.config.*;

@Service
public class ClienterWithdrawFormService implements
		IClienterWithdrawFormService {

	@Autowired
	private IClienterWithdrawFormDao clienterWithdrawFormDao;
	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;
	@Autowired
	private IClienterBalanceDao clienterBalanceDao;

	@Autowired
	private RedisService redisService;
	@Autowired
	private IGlobalConfigService globalConfigService;
	@Autowired
	private IClienterFinanceAcountService clienterFinanceAcountService;
	@Autowired
	private ITaskMsgService taskMsgService;
	@Autowired 
	private LogServiceBLL logService;
	@Override
	public int Add(ClienterWithdrawForm record) {
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
	public WithdrawState WithdrawC(ClienterBalanceReq req) {
		ClienterBalance clienterBalanceModel = clienterBalanceDao
				.selectByClienterId(req.getUserId());

		double amount = clienterBalanceModel.getWithdraw();
		if (req.getAmount() > amount) {
			return WithdrawState.MoneyError;
		}

		// 创建提现表
		ClienterWithdrawForm clienterWithdrawFormModel = new ClienterWithdrawForm();
		clienterWithdrawFormModel.setClienterId(req.getUserId());
		clienterWithdrawFormModel.setAmount(req.getAmount());
		String no = OrderNoHelper.generateOrderCode(req.getUserId());
		clienterWithdrawFormModel.setWithdrawNo(no);
		clienterWithdrawFormModel
				.setWithType((short) ClienterWithdrawFormWithType.Alipay
						.value());// 支付宝
		clienterWithdrawFormModel.setAccountInfo(req.getAccountInfo());
		clienterWithdrawFormModel.setTrueName(req.getTrueName());
		clienterWithdrawFormModel
				.setStatus((short) ClienterWithdrawFormStatus.UnAudit.value());// 待审核

		double actualHandCharge = PayToZhiFuBao(req.getAmount());
		String handchargeString = globalConfigService
				.getValueByName("HandCharge");
		clienterWithdrawFormModel.setHandCharge(ParseHelper.ToDouble(
				handchargeString, 3)); // 骑士付给我们的手续费金额，从缓存中读取
		clienterWithdrawFormModel.setActualHandCharge(actualHandCharge); // 我们付给支付宝的手续费
		clienterWithdrawFormModel.setActualAmount(req.getAmount()-ParseHelper.ToDouble(handchargeString, 3));
		int cwfId = clienterWithdrawFormDao.insert(clienterWithdrawFormModel);
		// 申请提现，扣减金额
		ClienterBalanceReq cBReq = new ClienterBalanceReq();
		cBReq.setUserId(req.getUserId());
		cBReq.setAmount(-req.getAmount());
		int cbId = clienterBalanceDao.updateMoneyByKey(cBReq);
		//增加地推员提现流水记录，实际到账金额，比如申请提现20，其中到账17，手续费3元，插入流水两条记录
		ClienterBalanceRecord clienterBalanceRecordModel = new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(req.getUserId());
		clienterBalanceRecordModel.setAmount(-Math.abs(req.getAmount()));   
		clienterBalanceRecordModel.setRecordType((short) CBalanceRecordType.ApplicationFor.value());// 提现申请
		clienterBalanceRecordModel.setOptName(req.getTrueName());
		clienterBalanceRecordModel.setOrderId((long) clienterWithdrawFormModel.getId());
		clienterBalanceRecordModel.setRelationNo(no);
		clienterBalanceRecordModel.setRemark("提现申请实际到账金额");
		clienterBalanceRecordModel
				.setStatus((short) CBalanceRecordStatus.Trading.value());// 交易中
		int cbrId = clienterBalanceRecordDao.insert(clienterBalanceRecordModel);
//		//增加地推员提现手续费金额  ，这里增加记录后 会影响后面的 流程，审核通过、拒绝、确认打款，都需要去改
//		ClienterBalanceRecord cbrHandCharge = new ClienterBalanceRecord();
//		cbrHandCharge.setClienterId(req.getUserId());
//		cbrHandCharge.setAmount(-Math.abs(ParseHelper.ToDouble(handchargeString, 3)));  //金额3元手续费
//		cbrHandCharge.setRecordType((short) CBalanceRecordType.WithDrawHandCharge.value());// 提现申请
//		cbrHandCharge.setOptName(req.getTrueName());
//		cbrHandCharge.setOrderId((long) clienterWithdrawFormModel.getId());
//		cbrHandCharge.setRelationNo(no);
//		cbrHandCharge.setRemark("提现申请手续费");
//		cbrHandCharge.setStatus((short)CBalanceRecordStatus.Trading.value());// 交易中
//		int cbrHandId = clienterBalanceRecordDao.insert(clienterBalanceRecordModel);
		if (cwfId > 0 && cbId > 0 && cbrId > 0 ) {
			return WithdrawState.Success;
		}
		/*
		 * else { Error error=new Error("提现出错"); throw new
		 * RuntimeErrorException(error); }
		 */

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
	public int AuditPass(ClienterWithdrawForm record) {
		long id = record.getId();// 提现单Id

		ClienterBalanceRecord cbrModel = clienterBalanceRecordDao
				.selectByOrderId(id);
		if (cbrModel.getStatus() != 2)
			return 0;

		// 提现表 更新审核状态
		record.setStatus((short) ClienterWithdrawFormStatus.Audited.value());// 审核通过
		record.setAuditTime(new Date());
		int cwfId = clienterWithdrawFormDao.updateByPrimaryKeySelective(record);

		// 流水表 更新提现状态
		ClienterBalanceRecord cbrModelU = new ClienterBalanceRecord();
		cbrModelU.setOrderId(cbrModel.getOrderId());
		cbrModelU.setStatus((short) CBalanceRecordStatus.Success.value());// 交易成功
		int cbrId = clienterBalanceRecordDao.updateStatusByOrderId(cbrModelU);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", record.getAmount());
		params.put("clienterId", record.getClienterId());
		// 更新累积提现
		int cbId = clienterBalanceDao.updateHadWithdrawByClienterId(params);

		if (cwfId > 0 && cbrId > 0 && cbId > 0)
			return 1;
		else {
			Error error = new Error("审核通过失败");
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
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int AuditRefuse(ClienterWithdrawForm record) {
		long id = record.getId();// 提现单Id
		ClienterBalanceRecord cbrModel = clienterBalanceRecordDao
				.selectByOrderId(id);
		if (cbrModel.getStatus() != 2)
			return 0;

		// 更新提现单审核状态
		record.setStatus((short) ClienterWithdrawFormStatus.AuditRefuse.value());// 审核拒绝
		record.setAuditTime(new Date());
		int cwfId = clienterWithdrawFormDao.updateByPrimaryKeySelective(record);
		// 流水表 更新提现状态
		ClienterBalanceRecord cbrModelU = new ClienterBalanceRecord();
		cbrModelU.setOrderId(cbrModel.getOrderId());
		cbrModelU.setStatus((short) CBalanceRecordStatus.Success.value());// 交易成功
		int cbrId = clienterBalanceRecordDao.updateStatusByOrderId(cbrModelU);

		// 更新用户余额，可提现余额
		ClienterBalanceReq cBReq = new ClienterBalanceReq();
		cBReq.setUserId(cbrModel.getClienterId());
		cBReq.setAmount(-cbrModel.getAmount());
		int cbId = clienterBalanceDao.updateMoneyByKey(cBReq);

		// 写入审核拒绝流水
		ClienterBalanceRecord clienterBalanceRecordModel = new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(cbrModel.getClienterId());
		clienterBalanceRecordModel.setAmount(-cbrModel.getAmount());
		clienterBalanceRecordModel
				.setRecordType((short) CBalanceRecordType.DenialOf.value());//
		clienterBalanceRecordModel.setOptName(record.getAuditName());//
		clienterBalanceRecordModel.setOrderId((long) cbrModel.getOrderId());//
		clienterBalanceRecordModel.setRelationNo(cbrModel.getRelationNo());//
		clienterBalanceRecordModel.setRemark("申请拒绝失败");
		clienterBalanceRecordModel
				.setStatus((short) CBalanceRecordStatus.Success.value());
		int cbrIdInsert = clienterBalanceRecordDao
				.insert(clienterBalanceRecordModel);
		//发送消息
		ClienterFinanceAcountModel cfaModel=new ClienterFinanceAcountModel();
		cfaModel.setClienterId(record.getClienterId());
		cfaModel.setPayFailedReason(record.getAuditFailedReason());
		cfaModel.setCreatetime(record.getCreateTime());
		cfaModel.setWithdrawId(record.getId());
		AddCPlayMoneyFailMessage(cfaModel);
		//发送消息结束
		if (cwfId > 0 && cbrId > 0 && cbId > 0 && cbrIdInsert > 0)
			return 1;
		else {
			Error error = new Error("审核拒绝");
			throw new RuntimeErrorException(error);
		}

	}

	@Override
	public PagedResponse<ClienterWithdrawFormDM> getList(
			PagedClienterWithdrawFormReq req) {
		return clienterWithdrawFormDao.getList(req);
	}

	/*
	 * 根据提现金额，计算支付给支付给的手续费
	 */
	public double PayToZhiFuBao(double amount) {
		double actualhandcharge = 0.0;
		double tempmoney = (double) amount * 0.005;
		if (tempmoney <= 1) {
			actualhandcharge = 1;
		} else if (tempmoney >= 25) {
			actualhandcharge = 25;
		} else {
			actualhandcharge = (double) tempmoney;
		}
		return actualhandcharge;
	}

	/*
	 * 批量付款 wangchao
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public String AlipayBatchTransfer(AlipayBatchReq alipayBatchReq) {
		if (alipayBatchReq.getType() != 1 && alipayBatchReq.getType() != 2) {
			return "<html><body>Type参数有误</body></html>";
		}
		if (alipayBatchReq.getData() == null || alipayBatchReq.getData() == "") {
			return "<html><body>Data参数有误</body></html>";
		}
		int alipayBatchCount = 0;// 总笔数
		double alipayPayAmount = 0;// 该批次总付款金额
		double toatlChargeAmount = 0;// 该批次总手续费
		String alipayBatchNo = "";// 批次号
		String html = "";// 返回的html
		int updateCount = 0;// 事务修改数据量
		StringBuilder wids = new StringBuilder("");//
		StringBuilder wnos = new StringBuilder("");

		alipayBatchNo = CreateAlipayBatchNo(0);// 生成批次号
		if (alipayBatchNo.equals(""))// 生成失败
		{
			return "<html><body>支付宝批量付款批次号生成失败,请重试</body></html>";
		}
		if (alipayBatchReq.getType() == 2)// 已存在的批次号,再次付款
		{
			AlipayBatchModel alipayBatchModel = new AlipayBatchModel();
			alipayBatchModel.setBatchNo(alipayBatchReq.getData());
			alipayBatchModel.setStatus(0);
			// 查询批次号是否在打款中
			int res = clienterWithdrawFormDao
					.CheckAlipayBatch(alipayBatchModel);
			if (res != 1) {
				return "<html><body>批次号:" + alipayBatchReq.getData()
						+ "状态不为打款中,不能再次发起付款</body></html>";
			}
		}
		StringBuffer detailData = new StringBuffer();
		// 获取提现单列表信息
		List<AlipayClienterWithdrawModel> withdrawList = clienterWithdrawFormDao
				.GetWithdrawListForAlipay(alipayBatchReq);
		if (withdrawList == null || withdrawList.size() == 0)
			return "<html><body>无提现单数据,请重试</body></html>";

		alipayBatchCount = withdrawList.size();// 总笔数
		for (AlipayClienterWithdrawModel item : withdrawList) {
			wids.append(item.getId() + ",");
			wnos.append(item.getWithdrawNo() + ",");
			alipayPayAmount += item.getActualAmount();// 实际付款金额
			toatlChargeAmount += item.getActualHandCharge();// 实际付款手续费
			// 提现单号,支付宝账号,支付宝账户名,金额,备注
			// 注意此处用提现单ID作为流水号穿给支付宝,方便支付宝回调后对数据处理
			detailData.append(String.format("%s^%s^%s^%s^地推员申请提现打款|",
					item.getId(), DES.decrypt(item.getAccountInfo()),
					item.getTrueName(),
					new DecimalFormat("0.00").format(item.getActualAmount())));
			if (alipayBatchReq.getType() == 1)// 第一次提交
			{
				ClienterWithdrawLog clienterWithLog = new ClienterWithdrawLog();
				clienterWithLog.setStatus(ClienterWithdrawFormStatus.Paying
						.value());
				clienterWithLog.setWithwardId(item.getId());
				clienterWithLog.setRemark("支付宝批量打款");
				clienterWithLog.setOperator(alipayBatchReq.getOptName());
				// 插入提现单表修改日志
				clienterWithdrawFormDao.InsertLog(clienterWithLog);
			}
			// 如果第一次 插入批次号,再次付款就是更新批次号
			UpdateAlipayBatchReq updateAlipayBatchReq = new UpdateAlipayBatchReq();
			updateAlipayBatchReq.setId(item.getId());
			updateAlipayBatchReq.setAliBatchNo(alipayBatchNo);
			updateCount += clienterWithdrawFormDao
					.UpdateAlipayBatchNo(updateAlipayBatchReq);
		}
		if (alipayBatchReq.getType() == 1)// 插入批次号表
		{
			AlipayBatchModel insertAlipayBatchModel = new AlipayBatchModel();
			insertAlipayBatchModel.setBatchNo(alipayBatchNo);
			insertAlipayBatchModel.setTotalWithdraw(alipayPayAmount);
			insertAlipayBatchModel.setOptTimes(alipayBatchCount);
			insertAlipayBatchModel.setWithdrawIds(wids.toString().substring(0,
					wids.length() - 1));
			insertAlipayBatchModel.setWithdrawNos(wnos.toString().substring(0,
					wnos.length() - 1));
			insertAlipayBatchModel.setRemarks(new Date() + "创建支付宝批次;");
			insertAlipayBatchModel.setCreateBy(alipayBatchReq.getOptName());
			insertAlipayBatchModel.setLastOptUser(alipayBatchReq.getOptName());
			clienterWithdrawFormDao.InsertAlipayBatch(insertAlipayBatchModel);

		} else if (alipayBatchReq.getType() == 2)// 更新批次号信息
		{
			AlipayBatchModel updateAlipayBatchModel = new AlipayBatchModel();
			updateAlipayBatchModel.setLastOptUser(alipayBatchReq.getOptName());
			updateAlipayBatchModel.setBatchNo(alipayBatchReq.getData());			
			updateAlipayBatchModel.setRemarks(ParseHelper.ToDateString(new Date()) + "将批次号"
					+ alipayBatchReq.getData() + "更换为" + alipayBatchNo + ";");
			updateAlipayBatchModel.setNewBatchNo(alipayBatchNo);
			clienterWithdrawFormDao
					.UpdateAlipayBatchForAgain(updateAlipayBatchModel);
		}

		if ((updateCount == alipayBatchCount))// 更新数据量,插入数据量和数据总数一致
		{
			JSONObject json = new JSONObject();
			json.put("notify_url", Config.aliBatchNotifyUrl
					+ "/clienterwithdraw/alibatchnotifytransfercallback");
			json.put("batch_no", alipayBatchNo);
			json.put("batch_fee", alipayPayAmount); // 总金额
			json.put("batch_num", alipayBatchCount);// 总笔数
			json.put("detail_info", detailData.toString());
			json.put("platform", "1");
			String postData = json.toString();
			// if (Config.interceptSwith == "1") {
			postData = com.renrentui.renrencore.security.AES
					.aesEncrypt(postData);
			postData = "{\"data\":\"" + postData + "\"}";
			// }
			String logResultJson = com.renrentui.renrencore.util.HttpUtil
					.sendPost(Config.aliBatchRequstUrl
							+ "/services/aliservice/batchtrans", postData,
							"application/json;charset=utf-8");
			logService.writeFile("renrenadmin", logResultJson);
			return logResultJson;
		} else {
			return "<html><body>提交提现单事务异常,请重试</body></html>";
		}
	}

	/*
	 * 生成支付宝批次号 wangchao
	 */
	public String CreateAlipayBatchNo(int count) {
		String batchno = "";
		try {
			count = count + 1;
			if (count > 3)// 避免无限递归
			{
				return "";
			}
			Random r = new Random();
			int min = 10000;
			int max = 99999;
			int ran = r.nextInt(max - min + 1) + min;// 10000-99999随机取一位

			batchno = ParseHelper.ToDateString(new Date(), "yyyyMMddhhmmssSSS")
					+ ran;
			String key = String.format(RedissCacheKey.Ets_AlipayBatchNo,
					batchno);
			String redisBatchNoString = redisService.get(key, String.class);

			if (redisBatchNoString == null || redisBatchNoString.equals("")) {
				redisService.set(key, batchno);// 将该批次号写入缓存25小时
			} else {
				batchno = CreateAlipayBatchNo(count);// 重新生成批次号
			}
			return batchno;
		} catch (Exception e)// 避免异常引起的错误
		{
			return "";
		}
	}

	/*
	 * 支付宝回调 wangchao HttpServletRequest request
	 */
	@Override
	public String AliBatchNotifyTransferCallback(HttpServletRequest request) {
		logService.writeFile("renrenadmin", "AA回调开始");

		Map<String, String> params = new HashMap<String, String>();

		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 批量付款数据中转账成功的详细信息
		AlipayBatchCallBackModel alipayBatchCallBackModel = new AlipayBatchCallBackModel();
		params.put("platform", "1");
		params.put("verify", "1");
		
		if (AlipayNotify.verify(params)) {// 验证成功
			// 批量付款数据中转账失败的详细信息
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			String fail_details = params.get("fail_details");
			String success_details = params.get("success_details");
			String notify_time = params.get("notify_time");
			String notify_type = params.get("notify_type");
			String notify_id = params.get("notify_id");
			String sign_type = params.get("sign_type");
			String sign = params.get("sign");
			String batch_no = params.get("batch_no");
			String pay_user_id = params.get("pay_user_id");
			String pay_user_name = params.get("pay_user_name");
			String pay_account_no = params.get("pay_account_no");
			alipayBatchCallBackModel.setSuccessDetails(success_details);
			alipayBatchCallBackModel.setFailDetails(fail_details);
			alipayBatchCallBackModel.setNotifyTime(notify_time);
			alipayBatchCallBackModel.setNotifyType(notify_type);
			alipayBatchCallBackModel.setNotifyId(notify_id);
			alipayBatchCallBackModel.setSignType(sign_type);
			alipayBatchCallBackModel.setSign(sign);
			alipayBatchCallBackModel.setBatchNo(batch_no);
			alipayBatchCallBackModel.setPayUserId(pay_user_id);
			alipayBatchCallBackModel.setPayUserName(pay_user_name);
			alipayBatchCallBackModel.setPayAccountNo(pay_account_no);
			//记录参数 失败不做任何处理
			try {
				logService.writeFile("renrenadmin", JsonUtil.obj2string(alipayBatchCallBackModel));
			} catch( Exception e ){
			
			}
			
			if (AliBatchNotifyTransferCallbackBusinessDeal(alipayBatchCallBackModel)) // 处理成功后的业务逻辑
			{
				logService.writeFile("renrenadmin", "AA处理成功");
				return "success";
			} else {
				// 验证失败
				logService.writeFile("renrenadmin", "AA处理失败");
				return "fail";
			}
		} else {
			logService.writeFile("renrenadmin", "AA校验失败");
			return "fail";
		}

	}

	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean AliBatchNotifyTransferCallbackBusinessDeal(
			AlipayBatchCallBackModel alipayBatchCallBackModel) {
		List<AlipayCallBackData> successlist = ConvertAlipayDetails(alipayBatchCallBackModel
				.getSuccessDetails());
		List<AlipayCallBackData> faillist = ConvertAlipayDetails(alipayBatchCallBackModel
				.getFailDetails());
		AlipayBatchModel alipayBatchModel = new AlipayBatchModel();
		alipayBatchModel.setBatchNo(alipayBatchCallBackModel.getBatchNo());
		alipayBatchModel.setStatus(1); // 打款完成
		if (clienterWithdrawFormDao.CheckAlipayBatch(alipayBatchModel) > 0) { // 已经处理了该批次
			return false;
		}
		AlipayBatchModel alipayBatchModel2 = new AlipayBatchModel();
		alipayBatchModel2.setSuccessTimes(successlist.size());
		alipayBatchModel2.setFailTimes(faillist.size());
		alipayBatchModel2.setBatchNo(alipayBatchCallBackModel.getBatchNo());
		int cc = clienterWithdrawFormDao.UpdateAlipayBatchNo(alipayBatchModel2);// 更新批次表信息
		// 处理成功的
		for (int i = 0; i < successlist.size(); i++) {
			ClienterWithdrawLogModel clienterWithdrawLogModel = new ClienterWithdrawLogModel();
			clienterWithdrawLogModel.setOperator("system");
			clienterWithdrawLogModel.setRemark("支付宝提现打款成功，支付宝账号"
					+ successlist.get(i).getAccountNo());
			clienterWithdrawLogModel
					.setStatus(ClienterWithdrawFormStatus.PaySuccess.value());
			clienterWithdrawLogModel
					.setOldStatus(ClienterWithdrawFormStatus.Paying.value());
			clienterWithdrawLogModel.setWithwardId(successlist.get(i)
					.getWithdrawId());
			clienterWithdrawLogModel.setIsCallBack(1);
			clienterWithdrawLogModel.setCallBackRequestId(successlist.get(i)
					.getAlipayInnerNo());
			// 更新骑士 确认打款
			clienterFinanceAcountService
					.ClienterWithdrawPayOk(clienterWithdrawLogModel);
			// //获取骑士相关金融账户信息 发送消息
			ClienterFinanceAcountModel cfaModel = new ClienterFinanceAcountModel();
			cfaModel = clienterFinanceAcountService
					.GetClienterFinanceAccount(successlist.get(i)
							.getWithdrawId());
			if (cfaModel != null) {
				AddCPlayMoneySuccessMessage(cfaModel);
			}
		}
		// 处理失败的提现单
		for (int i = 0; i < faillist.size(); i++) {
			ClienterWithdrawLogModel cwlModel = new ClienterWithdrawLogModel();
			cwlModel.setOperator("system");
			String reString = "支付宝提现打款失败"
					+ faillist.get(i).getReason().trim().toUpperCase() == "ACCOUN_NAME_NOT_MATCH" ? ",支付宝账户和姓名不匹配"
					: "";
			cwlModel.setRemark(reString);
			cwlModel.setPayFailedReason("支付宝失败代码:"
					+ faillist.get(i).getReason().trim().toUpperCase() == "ACCOUN_NAME_NOT_MATCH" ? ",支付宝账户和姓名不匹配"
					: faillist.get(i).getReason());
			cwlModel.setStatus(ClienterWithdrawFormStatus.PayError.value());
			cwlModel.setOldStatus(ClienterWithdrawFormStatus.Paying.value());
			cwlModel.setWithwardId(faillist.get(i).getWithdrawId());
			cwlModel.setIsCallBack(1);
			cwlModel.setCallBackRequestId(faillist.get(i).getAlipayInnerNo());
			// 更新骑士 确认打款
			clienterFinanceAcountService.ClienterWithdrawPayFail(cwlModel);
			// //获取骑士相关金融账户信息 发送消息
			ClienterFinanceAcountModel cfaModel = new ClienterFinanceAcountModel();
			cfaModel = clienterFinanceAcountService
					.GetClienterFinanceAccount(faillist.get(i).getWithdrawId());
			cfaModel.setPayFailedReason(reString);
			if (cfaModel != null) {
				AddCPlayMoneyFailMessage(cfaModel);
			}
		}
		return true;
	}

	public boolean AddCPlayMoneyFailMessage(ClienterFinanceAcountModel cfaModel) {
		TaskMsg req=new TaskMsg();
		req.setTitle("提现失败");
		req.setMsg(String.format(
				"您于%s发起的提现申请交易失败，失败原因："+cfaModel.getPayFailedReason()+"，提现资金已退还到账户余额。如有疑问，请联系地推小管家：010-57173598",
				ParseHelper.ToDateString(cfaModel.getCreatetime(), "yyyy-MM-dd")));
		req.setCreateName("admin");
		req.setMsgType(1);
		req.setClienterId(cfaModel.getClienterId());
		req.setTaskId(cfaModel.getWithdrawId());
		req.setTaskDatumId(0);
		int id = taskMsgService.insertMsg(req);
		return id > 0;
	}

	public boolean AddCPlayMoneySuccessMessage(ClienterFinanceAcountModel cfaModel) {
		TaskMsg req=new TaskMsg();
		req.setTitle("提现成功");
		req.setMsg(String.format(
				"您的%s元提现金额已成功到账，请注意查收，手续费%s元已扣款。如有疑问，请联系地推小管家：010-57173598",
				cfaModel.getAmount(), cfaModel.getHandCharge()));
		req.setCreateName("admin");
		req.setMsgType(1);
		req.setClienterId(cfaModel.getClienterId());
		req.setTaskId(cfaModel.getWithdrawId());
		int id = taskMsgService.insertMsg(req);
		return id > 0;
	}

	public List<AlipayCallBackData> ConvertAlipayDetails(String data) {
		List<AlipayCallBackData> list = new ArrayList<AlipayCallBackData>();
		if (data == null || data.length() == 0) {
			return list;
		}
		String[] dataArr = data.split("\\|");// 单个数据
		for (int i = 0; i < dataArr.length; i++) {
			if (dataArr[i] == "") {
				continue;
			}
			String[] propArr = dataArr[i].split("\\^");
			AlipayCallBackData model = new AlipayCallBackData();
			model.setWithdrawId(ParseHelper.ToLong(propArr[0], 0));
			model.setAccountNo(propArr[1]);
			model.setTrueName(propArr[2]);
			model.setPaidAmount(ParseHelper.ToDouble(propArr[3], 0));
			model.setStatus(propArr[4]);
			model.setReason(propArr[5]);
			model.setAlipayInnerNo(propArr[6]);
			list.add(model);
		}
		return list;
	}
}
