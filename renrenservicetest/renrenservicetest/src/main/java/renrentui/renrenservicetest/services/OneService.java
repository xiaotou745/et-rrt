package renrentui.renrenservicetest.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class OneService {
	public static Logger log = Logger.getLogger(OneService.class);

	public void Write() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		log.info("oneservice:" + df.format(new Date()));
		// logServiceBLL.SystemActionLog(logEngity);
	}
}
