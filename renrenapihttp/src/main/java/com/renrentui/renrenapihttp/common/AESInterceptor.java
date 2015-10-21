package com.renrentui.renrenapihttp.common;

import java.io.InputStream;
import java.util.Date;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message; 
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;





import com.renrentui.renrencore.security.AES;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.StreamUtils;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrenentity.req.AesParameterReq;
public class AESInterceptor  extends AbstractPhaseInterceptor<Message> {

	public AESInterceptor() {
		//接受参数时候调用
		super(Phase.RECEIVE);
	}
	//解密数据
	@Override
	public void handleMessage(Message message) throws Fault {
		String encryptMsg = "";
		String decryptMsg = "";
		try {
			InputStream inputStream = message.getContent(InputStream.class);
			String inputMsg = StreamUtils.copyToStringNoclose(inputStream);
			String interceptSwith = PropertyUtils.getProperty("InterceptSwith");// "1"// 开启加密										
			if (interceptSwith.equals("1")) {
				System.out.println("已开启AES解密拦截器");
				if (inputMsg.indexOf("data")<0) {
					throw new RuntimeException("传递的入参是没有加密的字符串，但是apihttp项目开启了AES解密");
				}
				AesParameterReq req = JsonUtil.str2obj(inputMsg,AesParameterReq.class);
				encryptMsg = req.getData();
				decryptMsg = AES.aesDecrypt(StringUtils.trimRight(req.getData(), "\n"));// AES解密
			} else {
				encryptMsg = inputMsg;
				decryptMsg = inputMsg;
				System.out.println("暂未开启AES解密拦截器");
				if (decryptMsg.indexOf("{") < 0 && decryptMsg.indexOf("}") < 0) {
					throw new RuntimeException("传递的入参是加密后的字符串，但是apihttp项目暂未开启AES解密");
				}
			}
			InputStream stream = StreamUtils.StringToInputStream(decryptMsg);
			message.setContent(InputStream.class, stream);// 回填流
		} catch (Exception e) {
			throw new RuntimeException("处理入参时出错:"+e.getMessage());
		}

		System.out.println("未解密的入参:" + encryptMsg);
		System.out.println("解密后的入参:" + decryptMsg);
		logCustomerInfo(message, encryptMsg, decryptMsg);
	}
	/**
	 * 记录额外的信息，用于统计log（先删除，后添加）
	 * @author hailongzhao
	 * @date 20151019
	 * @param message
	 * @param encryptMsg
	 * @param decryptMsg
	 */
	private void logCustomerInfo(Message message,String encryptMsg,String decryptMsg){
		Exchange exchange = message.getExchange();
		if (exchange.containsKey("requestTime")) {
			exchange.remove("requestTime");
		}
		if (exchange.containsKey("encryptMsg")) {
			exchange.remove("encryptMsg");
		}
		if (exchange.containsKey("decryptMsg")) {
			exchange.remove("decryptMsg");
		}
		exchange.put("requestTime", new Date());
		exchange.put("encryptMsg", encryptMsg);
		exchange.put("decryptMsg", decryptMsg);
	}
}
