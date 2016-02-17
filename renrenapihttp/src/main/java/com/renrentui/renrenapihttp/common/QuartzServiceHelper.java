package com.renrentui.renrenapihttp.common;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;

import com.renrentui.renrenapi.common.IJobDo;
import com.renrentui.renrenapi.common.LogServiceBLL;
import com.renrentui.renrenapi.redis.RedisService;
import com.renrentui.renrencore.consts.RedissCacheKey;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.ParseHelper;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.SpringBeanHelper;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrencore.util.SystemUtils;
import com.renrentui.renrenentity.domain.ActionLog;

public class QuartzServiceHelper {
	private static RedisService redisService=SpringBeanHelper.getCustomBeanByType(RedisService.class);
	private static LogServiceBLL logServiceBLL=SpringBeanHelper.getCustomBeanByType(LogServiceBLL.class);
	
	public static HttpResultModel<String> doSend(String beanName){
		String isAsynQuartz=PropertyUtils.getProperty("IsAsynQuartz");
		if (isAsynQuartz.equals("1")) {
			return asynDo(beanName);
		}else {
			return synDo(beanName);
		}
	}
	/**
	 * 同步调用业务的服务
	 * @date 20160217
	 * @author hailongzhao
	 * @param beanName
	 * @return
	 */
	private static HttpResultModel<String> synDo(String beanName) {
		HttpResultModel<String> result = new HttpResultModel<String>();
		String key=RedissCacheKey.HttpQuartz_Key+beanName;
		String quartzObj=redisService.get(key, String.class);
		//redis中存在这个key，则表示当前服务正在执行，防止套圈执行
		if (quartzObj!=null&&quartzObj.equals("1")) {
			result.setCode(HttpReturnRnums.ParaError.value());
			result.setMsg("服务正在执行，不能重复调用");
			result.setData("");
			return result;
		}
		redisService.set(key, "1",4,TimeUnit.HOURS);
		try {
			Object obj = SpringBeanHelper.getCustomBean(beanName);
			if (obj != null && obj instanceof IJobDo) {
				((IJobDo) obj).run();
			} else {
				result.setCode(HttpReturnRnums.ParaError.value());
				result.setMsg("给定的bean:" + beanName
						+ "不存在或没有实现com.renrentui.renrenapi.common.IJobDo接口");
				result.setData("");
			}
		} catch (Exception e) {
			String stackTrace = StringUtils.getStackTrace(e);
			result.setCode(HttpReturnRnums.ParaError.value());
			result.setMsg("执行给定的bean:" + beanName+ "的run方法时异常:"+e.getMessage());
			result.setData(stackTrace);
		}
		redisService.remove(key);
		return result;
	}
	/**
	 * 异步调用业务的服务
	 * @date 20160217
	 * @author hailongzhao
	 * @param beanName
	 * @return
	 */
	private static HttpResultModel<String> asynDo(String beanName){
		//由于记录日志是异步执行的，因此需要将request中的属性先拿出来
		//否则当方法返回时，异步方法中可能访问不到request中的属性
		ActionLog logEngity=getActionLog();
		Thread dThread = new Thread(new Runnable() {
			@Override
			public void run() {
				String exceptionMsg = "";
				String stackTrace = "";
				String resultJson="";
				Date requestTime=new Date();
				HttpResultModel<String> result =synDo(beanName);
				if (result.getCode()!=HttpReturnRnums.Success.value()) {
					exceptionMsg=result.getMsg();
					stackTrace=result.getData();
				}
				resultJson=JsonUtil.obj2string(result);
				writelog(logEngity,requestTime,exceptionMsg,stackTrace,resultJson);
			}
		});
		dThread.setDaemon(false);
		dThread.start();
		return new HttpResultModel<String>();
	}
	/**
	 * 从cxf框架的Message对象中获取请求相关的属性
	 * @date 20160217
	 * @author hailongzhao
	 * @param beanName
	 * @return
	 */
	private static ActionLog getActionLog(){
		Message message = PhaseInterceptorChain.getCurrentMessage();
		Message inMessage = message.getExchange().getInMessage();
		Method methodName = (Method) inMessage.get("org.apache.cxf.resource.method");
		TreeMap header = (TreeMap) inMessage.get(Message.PROTOCOL_HEADERS);
		String contentType = (String) inMessage.get(Message.CONTENT_TYPE);
		String httpRequestMethod = (String) inMessage.get(Message.HTTP_REQUEST_METHOD);
		String url = (String) inMessage.get(Message.REQUEST_URL);
		HttpServletRequest request = (HttpServletRequest)inMessage.get(AbstractHTTPDestination.HTTP_REQUEST);
		String clientIp = SystemUtils.getClientIp(request);
		ActionLog logEngity = new ActionLog();
		logEngity.setClientIp(clientIp);
		logEngity.setRequestUrl(url);
		logEngity.setContentType(contentType);
		logEngity.setHeader(header.toString());
		logEngity.setRequestMethod(httpRequestMethod);
		logEngity.setMethodName(methodName.toString());
		return logEngity;
	}
	/**
	 * 将异步执行的日志写入mq
	 * @date 20160217
	 * @author hailongzhao
	 * @param beanName
	 * @return
	 */
 	private static void writelog(ActionLog logEngity,Date requestTime,String exceptionMsg,String stackTrace,String resultJson){
		try {
			List<String> ipinfoList = SystemUtils.getLocalIpInfo();
			String appServerIP = JsonUtil.obj2string(ipinfoList);
			logEngity.setUserID(-1);
			logEngity.setUserName("");
			logEngity.setRequestType(0);
			logEngity.setSourceSys("renrenapihttp");
			logEngity.setParam("");
			logEngity.setDecryptMsg("");
			logEngity.setResultJson(resultJson);
			logEngity.setAppServer(appServerIP);
			logEngity.setException(exceptionMsg);
			logEngity.setStackTrace(stackTrace);
			Date endDate = new Date();
			logEngity.setExecuteTime(endDate.getTime() - requestTime.getTime());
			logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
			logEngity.setRequestEndTime(ParseHelper.ToDateString(endDate, ""));
			logServiceBLL.SystemActionLog(logEngity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
