package com.renrentui.renrenapi.common;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Component;

import com.renrentui.renrencore.util.JsonUtil;
import com.renrentui.renrencore.util.PropertyUtils;
import com.renrentui.renrencore.util.SystemUtils;
import com.renrentui.renrenentity.domain.ActionLog;

@Component
public class LogServiceBLL {

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
				String alertBody=getAlertBody(logEngity);
				String isSendMail = PropertyUtils.getProperty("IsSendMail");
				if (isSendMail.equals("1")&&alertBody!=null&&!alertBody.isEmpty()) {
					SystemUtils.sendAlertEmail(logEngity.getSourceSys()+"_java项目预警", alertBody);
				}
			}
			//initLog4DB(logEngity);
			String jsonMsg = JsonUtil.obj2string(logEngity);
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
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getName().equals("stackTrace")) {
					stackTrace = field.getName() + ":"+ field.get(logEngity).toString();
				} else {
					sb.append(field.getName() + ":"+ field.get(logEngity).toString() + "\n");
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
			for (Field field : fields) {
				field.setAccessible(true);
				MDC.put(field.getName(), field.get(logEngity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
