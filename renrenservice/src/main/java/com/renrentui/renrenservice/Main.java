package com.renrentui.renrenservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import org.apache.log4j.Logger;
import java.io.OutputStreamWriter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 系统服务
 * @author CaoHeYang
 * @date 20151009
 */
public class Main {   
    public static void main(String[] args) throws Exception {
        /*加载定时任务*/
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
