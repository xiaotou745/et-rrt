package com.renrentui.renrenapi.common;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Component;

import com.renrentui.renrencore.util.JsonUtil;
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
			initLog4DB(logEngity);
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
