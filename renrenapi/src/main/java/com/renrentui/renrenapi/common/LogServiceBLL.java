package com.renrentui.renrenapi.common;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renrentui.renrenentity.domain.ServiceLog;
import com.renrentui.renrenapi.activemq.ActiveMqService;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.StringUtils;
import com.renrentui.renrencore.util.SystemUtils;
import com.renrentui.renrenentity.domain.ActionLog;

@Component
public class LogServiceBLL {

	@Autowired
	private ActiveMqService activeMqService;
	private static Logger renrenAdminLogger = Logger
			.getLogger("renrenAdminLogger");
	private static Logger renrenApiHttpLogger = Logger
			.getLogger("renrenApiHttpLogger");
	private static Field[] fields = ActionLog.class.getDeclaredFields();
	private static Field[] serviceFields = ServiceLog.class.getDeclaredFields();

	/**
	 * 系统级，记录方法的ActionLog（异步写入db和log文件）
	 * 
	 * @param
	 */
	public void SystemActionLog(ActionLog logEngity) {
		String isSendMail = PropertyUtils.getProperty("IsSendMail");
		System.out.println("issendmail:" + isSendMail);
		try {
			if (logEngity.getStackTrace() != null
					&& !logEngity.getStackTrace().isEmpty()) {

				if (isSendMail.equals("1")) {
					String alertBody = getAlertBody(logEngity);
					if (alertBody != null && !alertBody.isEmpty()) {
						SystemUtils.sendAlertEmail(logEngity.getSourceSys()
								+ "_java项目预警", alertBody);
					}
				}
			}
			// initLog4DB(logEngity);
			String jsonMsg = JsonUtil.obj2string(logEngity);
			writeFile(logEngity.getSourceSys(), jsonMsg);
			activeMqService.asynSendMessage(logEngity.getSourceSys(), jsonMsg);
		} catch (Exception e) {
			if (isSendMail.equals("1")) {
				List<String> ipinfoList = SystemUtils.getLocalIpInfo();
				String appServerIP = JsonUtil.obj2string(ipinfoList);
				SystemUtils.sendAlertEmail(logEngity.getSourceSys()
						+ "_SystemActionLog_java项目预警",
						"appServerIP:" + appServerIP + "\n" + e.getMessage()
								+ StringUtils.getStackTrace(e));
			}
		}

	}

	/**
	 * 记录服务的ActionLog（异步写入db和log文件）
	 * 
	 * @param
	 */
	public void ServiceActionLog(ServiceLog logEngity) {
		String isSendMail = PropertyUtils.getProperty("IsSendMail");
		try {
			if (logEngity.getStackTrace() != null
					&& !logEngity.getStackTrace().isEmpty()) {
				if (isSendMail.equals("1")) {
					String alertBody = getAlertBody(logEngity);
					if (alertBody != null && !alertBody.isEmpty()) {
						SystemUtils.sendAlertEmail(logEngity.getSourceSys()
								+ "_java项目预警", alertBody);
					}
				}
			}
			// initLog4DB(logEngity);
			String jsonMsg = JsonUtil.obj2string(logEngity);
			writeFile(logEngity.getSourceSys(), jsonMsg);
			activeMqService.asynSendServiceLogMessage(jsonMsg);
		} catch (Exception e) {
			if (isSendMail.equals("1")) {
				List<String> ipinfoList = SystemUtils.getLocalIpInfo();
				String appServerIP = JsonUtil.obj2string(ipinfoList);
				SystemUtils.sendAlertEmail(logEngity.getSourceSys()
						+ "_ServiceActionLog_java项目预警",
						"appServerIP:" + appServerIP + "\n" + e.getMessage()
								+ StringUtils.getStackTrace(e));
			}
		}
	}

	public void writeFile(String sourceSys, String jsonMsg) {
		switch (sourceSys) {
		case "renrenadmin":
			renrenAdminLogger.info(jsonMsg);
			break;
		case "renrenapihttp":
			renrenApiHttpLogger.info(jsonMsg);
			break;
		default:
			break;
		}
	}

	private String getAlertBody(Object logEngity) {
		try {
			StringBuilder sb = new StringBuilder();
			String stackTrace = "";
			String propertyValue = "";
			Field[] tempFields = fields;
			if (logEngity instanceof ServiceLog) {
				tempFields = serviceFields;
			}
			for (Field field : tempFields) {
				field.setAccessible(true);
				propertyValue = field.get(logEngity) == null ? "null" : field
						.get(logEngity).toString();
				if (field.getName().equals("stackTrace")) {
					stackTrace = field.getName() + ":" + propertyValue;
				} else {
					sb.append(field.getName() + ":" + propertyValue + "\n");
				}
			}
			sb.append(stackTrace);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private void initLog4DB(Object logEngity) {
		try {
			MDC.clear();
			String propertyValue = "";
			Field[] tempFields = fields;
			if (logEngity instanceof ServiceLog) {
				tempFields = serviceFields;
			}
			for (Field field : tempFields) {
				field.setAccessible(true);
				propertyValue = field.get(logEngity) == null ? "null" : field
						.get(logEngity).toString();
				MDC.put(field.getName(), propertyValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
