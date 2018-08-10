/**
  * 模块: [该类所处的模块功能说明]<br>
  * 版权: Copyright (c) 2012-2017 Travelsky,Corp.<br>
  * 编号：日期：2017年9月5日 作者：王财文  描述<br>
  * -------------------------------------------------------------------------------------<br>
  * 001：2017年9月5日：Administrator：SVNlog:新建<br>
  * <br>
  */
package com.wangcw.framework.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @ClassName:  Log4jFileGenerateFactory
 * @Description:用于自动添加日志文件的模块
 * @author: 王财文
 * @date:   2017年9月5日
 */

public class Log4jFileGenerateFactory {

	 private final static Logger LOG = LoggerFactory.getLogger("utils");
	 public  static String profilepath = "src/main/resources/log4j.properties";
	 
	 public static void main(String[] args) throws Exception {
		 updateLog4jFile("suquan","suquan日志");
		 LOG.info("完成");
	  }   
	 
	private static void updateLog4jFile(String logName, String annotations) throws Exception{
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(profilepath, true);
		} catch (FileNotFoundException e1) {
			LOG.info("logName：{}文件输出流获取出现异常", logName);
			LOG.error("读取文件流异常：", e1);
			throw new Exception(e1.getMessage());
		}
		OutputStream out = new BufferedOutputStream(fileOutputStream);
		String line0  = "####################################\n";
		String line1  = "####"+annotations+"的操作日志\n";
		String line2  = "log4j.additivity."+logName+"=false\n";
		String line3  = "log4j.logger."+logName+"=INFO,"+logName+",console\n";
		String line4  = "log4j.appender."+logName+"=org.apache.log4j.RollingFileAppender\n";
		String line5  = "log4j.appender."+logName+".File=${log_dir}/"+logName+"/"+logName+".log\n";
		String line6  = "log4j.appender."+logName+".Threshold=INFO\n";
		String line7  = "log4j.appender."+logName+".MaxFileSize=20MB\n";
		String line8  = "log4j.appender."+logName+".MaxBackupIndex=30\n";
		String line9  = "log4j.appender."+logName+".layout.ConversionPattern=[%t][%c][%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} method: %l%n%m%n\n";
		String line10 = "log4j.appender."+logName+".layout=org.apache.log4j.PatternLayout\n";
		String line11 = "log4j.appender."+logName+".encoding=UTF-8\n";
		try {
			out.write(line0 .getBytes());
			out.write(line1 .getBytes());
			out.write(line2 .getBytes());
			out.write(line3 .getBytes());
			out.write(line4 .getBytes());
			out.write(line5 .getBytes());
			out.write(line6 .getBytes());
			out.write(line7 .getBytes());
			out.write(line8 .getBytes());
			out.write(line9 .getBytes());
			out.write(line10.getBytes());
			out.write(line11.getBytes());
			out.flush();
			LOG.info("写入成功");
		} catch(IOException e){
			LOG.info("logName：{}写入log4j文件时出现IO异常", logName);
			LOG.error("写入log4j文件时出现IO异常:", e);
			throw e;
		} finally {
			try {
				out.close();
				fileOutputStream.close();
			} catch (IOException e) {
				LOG.info("logName：{}关闭流出现异常", logName);
				LOG.error("关闭流出现异常:", e);
				throw e;
			}
		} 
	}
}
