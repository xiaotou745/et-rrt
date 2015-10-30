package renrentui.renrenservicetest.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class TwoService {
	public static Logger log = Logger.getLogger(TwoService.class);

	public void Write() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		log.info("twoservice:" + df.format(new Date()));
	}
}
