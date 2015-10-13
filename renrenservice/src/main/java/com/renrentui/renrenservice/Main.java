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
 * 
 * @author CaoHeYang
 * @date 20151009
 */
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		/* 加载定时任务 */
		writePID();//生成PID
		new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	private static void writePID() throws IOException {
		File f = new File("device.pid");
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(f));
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		String pid = processName.substring(0, processName.indexOf("@"));
		writer.write(String.valueOf(pid));
		writer.flush();
		writer.close();
	}
}
