package com.renrentui.renrenapi.common;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




//import com.renrentui.renrenapi.activemq.ActiveMqService;
import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.SystemUtils;
import com.renrentui.renrenentity.domain.ActionLog;

@Component
public class LogServiceBLL {

//	@Autowired
//	private ActiveMqService activeMqService;
	private static Logger renrenAdminLogger = Logger.getLogger("renrenAdminLogger");
	private static Logger renrenApiHttpLogger = Logger.getLogger("renrenApiHttpLogger");
	private static Field[] fields = ActionLog.class.getDeclaredFields();
	/**
	 * 系统级，记录方法的ActionLog（异步写入db和log文件）
	 * 
	 * @param
	 */
	public void SystemActionLog(ActionLog logEngity) {
		try {
			if (logEngity.getStackTrace()!=null&&!logEngity.getStackTrace().isEmpty()) {
				String isSendMail = PropertyUtils.getProperty("IsSendMail");
				if (isSendMail.equals("1")) {
					String alertBody=getAlertBody(logEngity);
					if (alertBody!=null&&!alertBody.isEmpty()) {
						SystemUtils.sendAlertEmail(logEngity.getSourceSys()+"_java项目预警", alertBody);
					}
				}
			}
			//initLog4DB(logEngity);
			String jsonMsg = JsonUtil.obj2string(logEngity);
			//activeMqService.asynSendMessage(jsonMsg);
			switch (logEngity.getSourceSys()) {
			case "renrenadmin":
				renrenAdminLogger.info(jsonMsg);
				break;
			case "renrenapihttp":
				renrenApiHttpLogger.info(jsonMsg);
				break;
			default:
				break;
			}
		} catch (Exception e) {
		}

	}

	public void LogInfo(ActionLog logEngity) {
	}

	public void LogInfo(String msg) {
	}

	public void LogError(ActionLog logEngity) {
	}

	public void LogError(String msg) {

	}
	private String getAlertBody(ActionLog logEngity){
		try {
			StringBuilder sb = new StringBuilder();
			String stackTrace = "";
			String propertyValue="";
			for (Field field : fields) {
				field.setAccessible(true);
				propertyValue=field.get(logEngity)==null?"null":field.get(logEngity).toString();
				if (field.getName().equals("stackTrace")) {
					stackTrace = field.getName() + ":"+ propertyValue;
				} else {
					sb.append(field.getName() + ":"+ propertyValue + "\n");
				}
			}
			sb.append(stackTrace);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	private void initLog4DB(ActionLog logEngity) {
		try {
			MDC.clear();
			String propertyValue="";
			for (Field field : fields) {
				field.setAccessible(true);
				propertyValue=field.get(logEngity)==null?"null":field.get(logEngity).toString();
				MDC.put(field.getName(), propertyValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
