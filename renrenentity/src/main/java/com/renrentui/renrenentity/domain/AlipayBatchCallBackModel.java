package com.renrentui.renrenentity.domain;

public class AlipayBatchCallBackModel {
	/// <summary>
    /// 异步通知时间
    /// 通知的发送时间。格式 yyyy-MM-dd HH:mm:ss。
    /// </summary>
    private String notifyTime;
  /// <summary>
    /// 异步通知类型batch_trans_notify
    /// </summary>
    private String notifyType;
    /// <summary>
    /// 异步通知ID
    /// 支付宝通知校验 ID，商户可以用这个流水号询问支付宝该条通知的合法性
    /// </summary>
    private String notifyId;
   /// <summary>
    /// 签名类型 DSA、RSA、MD5 三个值可选，必须大写。
   /// </summary>
    private String signType;
   /// <summary>
   /// 签名内容
   /// </summary>
    private String sign;
    /// <summary>
    /// 付款时的批次号
    /// </summary>
    private String batchNo;
    /// <summary>
    /// 付款账户ID 付款的支付宝账号对应的支付宝唯一用户号。
    /// </summary>
    private String payUserId;
    /// <summary>
    /// 付款账户名称
    /// </summary>
    private String payUserName;
    /// <summary>
    /// 付款账户
    /// </summary>
    private String payAccountNo;
    /// <summary>
    /// 支付成功数据集合
    /// 批量付款中成功付款的信息。格式为：
    /// 流水号^收款方账号^收款账号姓名^付款金额^成功标识(S)^ 成功原因(null)^支付宝内部流水号^完成时间。 
    /// 每条记录以“|”间隔。
    /// 10000008^dou631@163.com^白玉^1.00^S^^20151020528661960^20151020090839|
    /// </summary>
    private String successDetails;
    /// <summary>
    /// 支付失败数据集合
    /// 批量付款中未成功付款的信息。格式为：
    /// 流水号^收款方账号^收款账号姓名^付款金额^失败标识(F)^ 失败原因^支付宝内部流水号^完成时间。 
    /// 每条记录以“|”间隔。
    /// 10000009^dou631@163.com^白玉2^1.00^F^ACCOUN_NAME_NOT_MATCH^20151020528661961^20151020090839|
    /// </summary>
    private String failDetails;
    public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getPayUserId() {
		return payUserId;
	}
	public void setPayUserId(String payUserId) {
		this.payUserId = payUserId;
	}
	public String getPayUserName() {
		return payUserName;
	}
	public void setPayUserName(String payUserName) {
		this.payUserName = payUserName;
	}
	public String getPayAccountNo() {
		return payAccountNo;
	}
	public void setPayAccountNo(String payAccountNo) {
		this.payAccountNo = payAccountNo;
	}
	public String getSuccessDetails() {
		return successDetails;
	}
	public void setSuccessDetails(String successDetails) {
		this.successDetails = successDetails;
	}
	public String getFailDetails() {
		return failDetails;
	}
	public void setFailDetails(String failDetails) {
		this.failDetails = failDetails;
	}
	
}
