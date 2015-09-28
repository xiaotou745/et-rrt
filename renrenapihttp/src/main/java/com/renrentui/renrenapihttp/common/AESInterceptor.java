package com.renrentui.renrenapihttp.common;

import java.io.InputStream;

import org.apache.cxf.interceptor.Fault;
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
		
		String interceptSwith =PropertyUtils.getProperty("InterceptSwith");//"1" 开启加密
		if(interceptSwith.equals("1"))
		{
			System.out.println("已开启AES解密拦截器");
			try {
				InputStream mContentString=message.getContent(InputStream.class);
				String string=StreamUtils.copyToStringNoclose(mContentString);
				AesParameterReq req=JsonUtil.str2obj(string, AesParameterReq.class);
				string=AES.aesDecrypt(StringUtils.trimRight(req.getData(), "\n"));//AES解密
				InputStream stream=StreamUtils.StringToInputStream(string);
				message.setContent(InputStream.class, stream);//回填流
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		else
		{
			System.out.println("暂未开启AES解密拦截器");
		}
	}

}
