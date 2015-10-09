package com.renrentui.renrenservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import org.apache.log4j.Logger;
import java.io.OutputStreamWriter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 定时生成数据发送邮件
 *
 * @author duanzq
 * @date Apr 10, 2015
 * @time 4:46:05 PM
 */
public class Main {   
    public static void main(String[] args) throws Exception {
        /*加载定时任务*/
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
