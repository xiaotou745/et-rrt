package com.renrentui.renrenapi.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
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
import com.renrentui.renrenapi.dao.inter.IClienterBalanceDao;
import com.renrentui.renrenapi.dao.inter.IClienterBalanceRecordDao;
import com.renrentui.renrenapi.dao.inter.IClienterWithdrawFormDao;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrenapi.service.inter.IClienterWithdrawFormService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.enums.CBalanceRecordStatus;
import com.renrentui.renrencore.enums.CBalanceRecordType;
import com.renrentui.renrencore.enums.ClienterWithdrawFormStatus;
import com.renrentui.renrencore.enums.ClienterWithdrawFormWithType;
import com.renrentui.renrencore.enums.WithdrawState;
import com.renrentui.renrencore.security.DES;
import com.renrentui.renrencore.util.Config;
import com.renrentui.renrencore.util.OrderNoHelper;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrenentity.ClienterBalance;
import com.renrentui.renrenentity.ClienterBalanceRecord;
import com.renrentui.renrenentity.ClienterWithdrawForm;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.AlipayBatchClienterWithdrawForm;
import com.renrentui.renrenentity.domain.AlipayBatchModel;
import com.renrentui.renrenentity.domain.AlipayClienterWithdrawModel;
import com.renrentui.renrenentity.domain.ClienterWithdrawFormDM;
import com.renrentui.renrenentity.domain.ClienterWithdrawLog;
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
		clienterWithdrawFormModel.setHandCharge(10); // 骑士付给我们的手续费金额，从缓存中读取
		clienterWithdrawFormModel.setActualHandCharge(actualHandCharge); // 我们付给支付宝的手续费
		clienterWithdrawFormModel.setActualAmount(req.getAmount()
				- actualHandCharge);

		int cwfId = clienterWithdrawFormDao.insert(clienterWithdrawFormModel);

		ClienterBalanceReq cBReq = new ClienterBalanceReq();
		cBReq.setUserId(req.getUserId());
		cBReq.setAmount(-req.getAmount());
		int cbId = clienterBalanceDao.updateMoneyByKey(cBReq);

		ClienterBalanceRecord clienterBalanceRecordModel = new ClienterBalanceRecord();
		clienterBalanceRecordModel.setClienterId(req.getUserId());
		clienterBalanceRecordModel.setAmount(-Math.abs(req.getAmount()));
		clienterBalanceRecordModel
				.setRecordType((short) CBalanceRecordType.ApplicationFor
						.value());// 提现申请
		clienterBalanceRecordModel.setOptName(req.getTrueName());
		clienterBalanceRecordModel.setOrderId((long) clienterWithdrawFormModel
				.getId());
		clienterBalanceRecordModel.setRelationNo(no);
		clienterBalanceRecordModel.setRemark("提现申请");
		clienterBalanceRecordModel
				.setStatus((short) CBalanceRecordStatus.Trading.value());// 交易中
		int cbrId = clienterBalanceRecordDao.insert(clienterBalanceRecordModel);

		if (cwfId > 0 && cbId > 0 && cbrId > 0) {
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
		if (withdrawList == null)
			return "<html><body>无提现单数据,请重试</body></html>";

		alipayBatchCount = withdrawList.size();// 总笔数
		for (AlipayClienterWithdrawModel item : withdrawList) {
			wids.append(item.getId() + ",");
			wnos.append(item.getWithdrawNo() + ",");
			alipayPayAmount += item.getActualAmount();// 实际付款金额
			toatlChargeAmount += item.getActualHandCharge();// 实际付款手续费
			// 提现单号,支付宝账号,支付宝账户名,金额,备注
			// 注意此处用提现单ID作为流水号穿给支付宝,方便支付宝回调后对数据处理
			detailData.append(String.format("%s^%s^%s^%s^骑士申请提现打款|", item
						.getId(), DES.decrypt(item.getAccountInfo()), item
						.getTrueName(), new DecimalFormat("0.00").format(item
						.getActualAmount())));
				 
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
			updateAlipayBatchModel.setRemarks(new Date() + "将批次号"
					+ alipayBatchReq.getData() + "更换为" + alipayBatchNo + ";");
			updateAlipayBatchModel.setNewBatchNo(alipayBatchNo);
			clienterWithdrawFormDao.UpdateAlipayBatchForAgain(updateAlipayBatchModel);
		}

		if ((updateCount == alipayBatchCount))// 更新数据量,插入数据量和数据总数一致
		{
			JSONObject json = new JSONObject();
			json.put("notify_url", Config.aliBatchNotifyUrl+"/renrenadmin/clienterwithdraw/alibatchnotifytransfercallback");
			json.put("batch_no", alipayBatchNo);
			json.put("batch_fee",alipayPayAmount); //总金额
			json.put("batch_num",alipayBatchCount);//总笔数
			json.put("detail_info",detailData.toString());

			String postData = json.toString();
			if (Config.interceptSwith == "1") {
				postData = com.renrentui.renrencore.security.AES
						.aesEncrypt(postData);
				postData="{\"data\":\"" + postData + "\"}";
			} 
			String logResultJson = com.renrentui.renrencore.util.HttpUtil.sendPost(Config.aliBatchRequstUrl +"/services/aliservice/batchtrans",postData,"application/json; charset=utf-8"); 
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

			
			batchno = ParseHelper.ToDateString(new Date(), "yyyyMMddhhmmssSSS") + ran;
			String key = String.format(RedissCacheKey.Ets_AlipayBatchNo,
					batchno);
			String redisBatchNoString = redisService.get(key, String.class);

			if (redisBatchNoString == null ||redisBatchNoString.equals("")) {
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
	 * 支付宝回调
	 * wangchao
	 */
	@Override
	public String AliBatchNotifyTransferCallback(HttpServletRequest request) throws UnsupportedEncodingException {

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
		// request.getParameter("success_details")
		String success_details = new String(request.getParameter("").getBytes(
				"ISO-8859-1"), "UTF-8");

		// 批量付款数据中转账失败的详细信息
		String fail_details = new String(request.getParameter("fail_details")
				.getBytes("ISO-8859-1"), "UTF-8"); 
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			// 判断是否在商户网站中已经做过了这次通知返回的处理
			// 如果没有做过处理，那么执行商户的业务程序
			// 如果有做过处理，那么不执行商户的业务程序
//			System.out.println("success");
			return "success";
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
//			System.out.println("fail");
			return "fail";
		}

	}
}
